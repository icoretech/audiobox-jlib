/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 *
 * This file is part of the AudioBox-Jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.core.models;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.util.Key;
import fm.audiobox.core.Client;
import fm.audiobox.core.exceptions.*;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.core.utils.ModelUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Playlists are the main containers for Media Files and are represented in the system by an unique token.
 * <br/>
 * Internally a Cloud Drive is treated as a Playlist.
 * <br/>
 * There are different types of playlists and each one can represent a particular media storage service, here's a
 * rundown:
 * <dl>
 * <dt>LocalPlaylist</dt>
 * <dd>
 * Contains AudioBox Desktop media files. AudioBox Desktop is an application that is able to stream media files
 * directly from the User's pc, without uploading an entire collection first.
 * <br/>
 * During normal desktop usage and syncing this playlist will be filled with metadata of files found on the user's
 * filesystem in the folder he specified. This playlist is not treated differently, except that media files actions are
 * limited, such as destroy, since it is automatically managed.
 * </dd>
 * <dt>CloudPlaylist</dt>
 * <dd>
 * Contains AudioBox Cloud media files. AudioBox Cloud is the official AudioBox Cloud Media Storage offering and it's
 * available space is unlimited.
 * <br/>
 * This playlist can be accessed only when a valid subscription is in place, so in order to perform actions against it
 * make sure that the permissions: cloud is true in the /api/v1/user.json call.
 * </dd>
 * <dt>
 * DropboxPlaylist, SkydrivePlaylist, BoxPlaylist, GdrivePlaylist, YouTubePlaylist, SoundcloudPlaylist, UbuntuPlaylist
 * </dt>
 * <dd>
 * Contains media files synced from the relative remote storage. Accessible when a valid subscription exists,
 * whereas applicable, and a proper service authentication is stored in database. The link between an AudioBox account
 * and the remote service is called an AudioMash.
 * </dd>
 * <dt>CustomPlaylist</dt>
 * <dd>User-created playlist that contains media files assigned by the user.</dd>
 * <dt>SmartPlaylist</dt>
 * <dd>
 * User-created playlist that contains media files that corresponds to rules defined by the user. Updates
 * automatically on demand.
 * </dd>
 * <dt>OfflinePlaylist</dt>
 * <dd>System-created playlist that contains media files the user would like to store on client.</dd>
 * </dl>
 * Each playlist supports a set of attributes:
 * <p/>
 * <dl>
 * <dt>token:</dt>
 * <dd>identifier used to perform actions on a playlist, usually part of the URL.</dd>
 * <dt>embeddable:</dt>
 * <dd>boolean identifying if this playlist can be embedded on an external website. For future use.</dd>
 * <dt>visible:</dt>
 * <dd>boolean identifying if this playlist should be hidden from the user interface.</dd>
 * <dt>syncable:</dt>
 * <dd>
 * boolean identifying if this playlist supports syncing with remote content, usually valid for external storage
 * services.
 * </dd>
 * <dt>system_name:</dt>
 * <dd>a code friendly name identifying playlist's type.</dd>
 * <dt>position:</dt>
 * <dd>
 * integer identifying the order of which playlists should be shown, this is a user's preference. The most
 * important playlists has this attribute unchangeable.
 * </dd>
 * </dl>
 */
public class Playlist {

  private Logger logger = LoggerFactory.getLogger( Playlist.class );

  private static final String PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + ".json";

  private static final String SYNC_PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/sync.json";

  @Key
  private String token;

  @Key
  private String name;

  @Key
  private String system_name;

  @Key
  private String type;

  @Key
  private long media_files_count;

  @Key
  private long position;

  @Key
  private boolean offline;

  @Key
  private boolean embeddable;

  @Key
  private boolean visible;

  @Key
  private boolean last_accessed;

  @Key
  private String updated_at;

  @Key
  private boolean syncable;


  /**
   * Instantiates a new Playlist.
   * <p/>
   * Default empty constructor.
   */
  @SuppressWarnings( "unused" )
  public Playlist() {
  }


  /**
   * Instantiates a new Playlist.
   *
   * @param name the name of the playlist
   */
  public Playlist(String name) {
    this.name = name;
  }


  /**
   * Creates a new CustomPlaylist or SmartPlaylist depending on the input parameters.
   * <p/>
   * The user can create a CustomPlaylist by setting the playlist name.
   * <p/>
   * The user can create a SmartPlaylist by setting the playlist name AND playlist search_params.
   * <p/>
   * NOTE: Currently the search_params hash construction is complex enough and therefore it's
   * restricted to the Cloud Web Player, we'll open up the possibility for developers to create them as well.
   *
   * @param client the client
   *
   * @return a new instance of the saved Playlist if success or null if any error occurs
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public Playlist create(Client client) throws IOException {
    validate(false);
    try {
      HttpResponse rsp = client.doPOST( Playlists.getPath(), new JsonHttpContent( client.getConf().getJsonFactory(), this ) );
      if ( rsp.isSuccessStatusCode() ) {
        try {
          return rsp.parseAs( PlaylistWrapper.class ).getPlaylist();
        } catch ( IOException e ) {
          logger.error( "Unable to perform request due to IO Exception: " + e.getMessage() );
        }
      }
    } catch ( ResourceNotFoundException e ) {
      // According to the documentation a 404 is returned if playlist is immutable (not smart or custom).
      // Flatten this error as validation exception.
      throw new ValidationException( e.getResponse() );
    }
    return null;
  }


  /**
   * Handle the update of a custom or smart playlist.
   * <p/>
   * As a rule of thumb you can have one uniquely named playlist for each type.
   * <p/>
   * SmartPlaylist's +search_params+ can be set only on creation and thus cannot be changed, in this action.
   * <p/>
   * SmartPlaylist cannot be tweaked in their search_params due to the complexity of their construct.
   * Since SmartPlaylist are compiled on demand, just destroy the old and create a new one.
   *
   * @param client the client
   *
   * @return the playlist
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public Playlist update(Client client) throws IOException {
    validate(true);
    HttpResponse rsp = client.doPUT( ModelUtil.interpolate( getPath(), getToken() ), new JsonHttpContent( client.getConf().getJsonFactory(), this ) );
    if ( rsp.isSuccessStatusCode() ) {
      try {
        return rsp.parseAs( PlaylistWrapper.class ).getPlaylist();
      } catch ( IOException e ) {
        logger.error( "Unable to perform request due to IO Exception: " + e.getMessage() );
      }
    }
    return null;
  }


  /**
   * Permanently destroy a playlist.
   * <p/>
   * Only Custom and Smart playlists can be destroyed.
   *
   * @param client the client to use for the request
   *
   * @return true if operation succeeds
   *
   * @throws AudioBoxException in case of 401, 402, 403, 404 or 422 response codes.
   */
  public boolean delete(Client client) throws IOException {
    ensurePlaylistForRequest();
    HttpResponse rsp = client.doDELETE( ModelUtil.interpolate( getPath(), getToken() ) );
    // OK -> 204
    // Not Found OR Cannot delete -> 404
    return rsp.getStatusCode() == HttpStatus.SC_NO_CONTENT;
  }


  /**
   * Begin content sync with the remote platform. Supported only by syncable playlists.
   * <p/>
   * Calling this method will initiate a job that will synchronize data with the remote storage,
   * such as Dropbox, SkyDrive and others.
   * <p/>
   * Playlists supporting official storage such as AudioBox Cloud or AudioBox Desktop does not require syncing.
   *
   * @param client the client to use for the request
   *
   * @return the boolean
   *
   * @throws SyncException if any problem occurs.
   */
  public boolean sync(Client client) throws IOException {
    ensurePlaylistForRequest();
    if ( !this.isSyncable() ) // Well...
      throw new SyncException( HttpStatus.SC_UNPROCESSABLE_ENTITY );
    try {
      client.doPUT( getSyncPath(), new JsonHttpContent( client.getConf().getJsonFactory(), this ) );
    } catch ( AudioBoxException e ) {
      throw new SyncException( e.getResponse() );
    }

    return true;
  }


  /**
   * Gets the generic remote resource path (token interpolation is needed).
   *
   * @return the path String
   */
  public static String getPath() {
    return PATH;
  }


  /**
   * Gets playlist unique token.
   *
   * @return the token String
   */
  public String getToken() {
    return this.token;
  }


  /**
   * Gets name.
   *
   * @return the playlist name
   */
  public String getName() {
    return this.name;
  }


  /**
   * Changes playlist name, if you want to persist the change
   * remember to call {@link Playlist#update(fm.audiobox.core.Client)}
   * method.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * Gets system name.
   *
   * @return the system name
   */
  public String getSystemName() {
    return this.system_name;
  }


  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return this.type;
  }


  /**
   * Gets media files count on this playlist.
   *
   * @return the media files count
   */
  public long getMediaFilesCount() {
    return this.media_files_count;
  }


  /**
   * Gets position.
   *
   * @return the position of the playlist
   */
  public long getPosition() {
    return this.position;
  }


  /**
   * Changes position value, if you want to persist the change
   * remember to call {@link Playlist#update(fm.audiobox.core.Client)}
   * method.
   *
   * @param position the position to set
   */
  public void setPosition(long position) {
    this.position = position;
  }


  /**
   * Use this method to check if the playlist is embeddable or not.
   *
   * @return true if the playlist is embeddable
   */
  public boolean isEmbeddable() {
    return this.embeddable;
  }


  /**
   * Changes embeddable value, if you want to persist the change
   * remember to call {@link Playlist#update(fm.audiobox.core.Client)}
   * method.
   *
   * @param embeddable true to mark it as embeddable, false to disable the feature.
   */
  public void setEmbeddable(boolean embeddable) {
    this.embeddable = embeddable;
  }


  /**
   * Use this method to check if the playlist is visible or not.
   *
   * @return true if the playlist is embeddable
   */
  public boolean isVisible() {
    return this.visible;
  }


  /**
   * Changes visible value, if you want to persist the change
   * remember to call {@link Playlist#update(fm.audiobox.core.Client)}
   * method.
   *
   * @param visible true to mark it as visible, false to disable the feature.
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
  }


  /**
   * Use this method to check if the playlist is the last one accessed.
   * (i.e. {@link Client#getPlaylist(String)} was called at last}
   *
   * @return true if this was the last accessed playlist
   */
  public boolean getLastAccessed() {
    return this.last_accessed;
  }


  /**
   * Gets the last time (in form of an UTC String) this playlist have been changed.
   * <br/>
   * NOTE: Since client timezone may differ significantly you are strongly invited
   * to always trust and use this datetime in case you are building a sync tool.
   *
   * @return the UTC String of the last update on this playlist
   */
  public String getUpdatedAt() {
    return this.updated_at;
  }


  /**
   * Checks if this playlist supports syncing with remote content, usually valid for external storage services.
   *
   * @return true if this is a syncable playlist, false otherwise
   */
  public boolean isSyncable() {
    return this.syncable;
  }


  @Override
  public boolean equals(Object other) {
    if ( other == null || !( other instanceof Playlist ) ) {
      return false;
    }

    Playlist o = ( Playlist ) other;

    boolean eq = false;
    if ( this.token != null ) {
      eq = this.token.equals( o.getToken() );
    }

    if ( this.updated_at != null ) {
      eq = eq && this.updated_at.equals( o.getUpdatedAt() );
    }

    return eq;

  }


  @Override
  public int hashCode() {
    int hashCode = 0;

    if ( this.token != null )
      hashCode = hashCode * 37 + this.token.hashCode();
    if ( this.updated_at != null )
      hashCode = hashCode * 37 + this.updated_at.hashCode();

    return hashCode;
  }



  /* ================ */
  /*  Private methods */
  /* ================ */


  /**
   * Gets sync path.
   *
   * @return the sync path
   */
  private String getSyncPath() {
    return ModelUtil.interpolate( SYNC_PATH, getToken() );
  }


  /**
   * Checks whether the playlist is ready for requesting remote server.
   * <p/>
   * If something is missing or not valid an IllegalStateException is thrown
   */
  private void ensurePlaylistForRequest() {
    if ( StringUtils.isBlank( this.getToken() ) ) {
      throw new IllegalStateException( "Playlist is not ready for remote request: token not valid" );
    }
    validate(true);
  }


  /**
   * Validates the playlist before performing requests.
   *
   * @param newRecordNotAllowed whether a new record should rise an error or not
   */
  private void validate(boolean newRecordNotAllowed) {

    if (newRecordNotAllowed && isNewRecord()) {
      throw new IllegalStateException( "Playlist must be remotely created first." );
    }

    if ( StringUtils.isBlank( this.getName() ) ) {
      throw new IllegalStateException( "Playlist is not ready for remote request: name not valid" );
    }
  }

  /**
   * Checks whether the playlist is a new record that still needs to be created remotely.
   *
   * @return true if this playlist does not exist on AudioBox, false otherwise.
   */
  private boolean isNewRecord() {
    return StringUtils.isEmpty( getToken() );
  }


}
