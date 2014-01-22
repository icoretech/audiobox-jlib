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
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.AuthorizationException;
import fm.audiobox.core.exceptions.RemoteMessageException;
import fm.audiobox.core.exceptions.SyncException;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.core.utils.ModelUtil;

/**
 * Created by keytwo on 20/01/14.
 */
public class Playlist {

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
  public Playlist() { }


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
   * @return the boolean
   *
   * @throws AuthorizationException if the oauth token has been invalidated or is expired
   */
  public boolean save(Client client) throws AuthorizationException {
    HttpResponse rsp = client.doPUT( ModelUtil.interpolate( getPath(), getToken() ), new JsonHttpContent( client.getConf().getJsonFactory(), this ) );
    return rsp.getStatusCode() == HttpStatus.SC_NO_CONTENT;
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
   * @throws AuthorizationException if the oauth token has been invalidated or is expired
   */
  public boolean delete(Client client) throws AuthorizationException {
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
  public boolean sync(Client client) throws SyncException {

    if ( !this.isSyncable() ) // Well...
      throw new SyncException( HttpStatus.SC_UNPROCESSABLE_ENTITY );
    try {
      client.doPUT( getSyncPath(), new JsonHttpContent( client.getConf().getJsonFactory(), this ) );
    } catch ( RemoteMessageException e ) {
      throw new SyncException( e.getErrorCode() );
    }

    return true;
  }


  /**
   * Gets path.
   *
   * @return the path
   */
  public static String getPath() {
    return PATH;
  }


  /**
   * Gets token.
   *
   * @return the token
   */
  public String getToken() {
    return this.token;
  }


  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }


  /**
   * Sets name.
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
   * Gets media files count.
   *
   * @return the media files count
   */
  public long getMediaFilesCount() {
    return this.media_files_count;
  }


  /**
   * Gets position.
   *
   * @return the position
   */
  public long getPosition() {
    return this.position;
  }


  /**
   * Sets position.
   *
   * @param position the position
   */
  public void setPosition(long position) {
    this.position = position;
  }


  /**
   * Gets offline.
   *
   * @return the offline
   */
  @Deprecated
  public boolean isOffline() {
    return this.offline;
  }


  /**
   * Gets embeddable.
   *
   * @return the embeddable
   */
  public boolean isEmbeddable() {
    return this.embeddable;
  }


  /**
   * Sets embeddable.
   *
   * @param embeddable the embeddable
   */
  public void setEmbeddable(boolean embeddable) {
    this.embeddable = embeddable;
  }


  /**
   * Gets visible.
   *
   * @return the visible
   */
  public boolean isVisible() {
    return this.visible;
  }


  /**
   * Sets visible.
   *
   * @param visible the visible
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
  }


  /**
   * Gets last accessed.
   *
   * @return the last accessed
   */
  public boolean getLastAccessed() {
    return this.last_accessed;
  }


  /**
   * Gets updated at.
   *
   * @return the updated at
   */
  public String getUpdatedAt() {
    return this.updated_at;
  }


  /**
   * Gets syncable.
   *
   * @return the syncable
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


}
