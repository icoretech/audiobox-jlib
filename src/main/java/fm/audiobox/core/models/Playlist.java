/*
 * Copyright 2009-2014 iCoreTech, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.audiobox.core.models;


import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import fm.audiobox.core.AudioBoxClient;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.SyncException;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.core.utils.ModelUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;


/**
 * Playlists are the main containers for Media Files and are represented in the system by an unique token.
 * <br>
 * Internally a Cloud Drive is treated as a Playlist.
 * <br>
 * There are different types of playlists and each one can represent a particular media storage service, here's a
 * rundown:
 * <dl>
 * <dt><strong>LocalPlaylist</strong></dt>
 * <dd>
 * Contains AudioBox Desktop media files. AudioBox Desktop is an application that is able to stream media files
 * directly from the User's pc, without uploading an entire collection first.
 * <br>
 * During normal desktop usage and syncing this playlist will be filled with metadata of files found on the user's
 * filesystem in the folder he specified. This playlist is not treated differently, except that media files actions are
 * limited, such as destroy, since it is automatically managed.
 * </dd>
 * <dt><strong>CloudPlaylist</strong></dt>
 * <dd>
 * Contains AudioBox Cloud media files. AudioBox Cloud is the official AudioBox Cloud Media Storage offering and it's
 * available space is unlimited.
 * <br>
 * This playlist can be accessed only when a valid subscription is in place, so in order to perform actions against it
 * make sure that the permissions: cloud is true in the /api/v1/user.json call.
 * </dd>
 * <dt><strong>
 * DropboxPlaylist, SkydrivePlaylist, BoxPlaylist, GdrivePlaylist, YouTubePlaylist, SoundcloudPlaylist, MegaPlaylist
 * </strong></dt>
 * <dd>
 * Contains media files synced from the relative remote storage. Accessible when a valid subscription exists,
 * whereas applicable, and a proper service authentication is stored in database. The link between an AudioBox account
 * and the remote service is called an AudioMash.
 * </dd>
 * <dt><strong>CustomPlaylist</strong></dt>
 * <dd>User-created playlist that contains media files assigned by the user.</dd>
 * <dt><strong>SmartPlaylist</strong></dt>
 * <dd>
 * User-created playlist that contains media files that corresponds to rules defined by the user. Updates
 * automatically on demand.
 * </dd>
 * <dt><strong>OfflinePlaylist</strong></dt>
 * <dd>System-created playlist that contains media files the user would like to store on client.</dd>
 * </dl>
 * <p>
 * Each playlist supports a set of attributes:
 * <dl>
 * <dt><strong>token:</strong></dt>
 * <dd>identifier used to perform actions on a playlist, usually part of the URL.</dd>
 * <dt><strong>embeddable:</strong></dt>
 * <dd>boolean identifying if this playlist can be embedded on an external website. For future use.</dd>
 * <dt><strong>visible:</strong></dt>
 * <dd>boolean identifying if this playlist should be hidden from the user interface.</dd>
 * <dt><strong>syncable:</strong></dt>
 * <dd>boolean identifying if this playlist supports syncing with remote content, usually valid for external storage services.</dd>
 * <dt><strong>system_name:</strong></dt>
 * <dd>a code friendly name identifying playlist's type.</dd>
 * <dt><strong>position</strong></dt>
 * <dd>integer identifying the order of which playlists should be shown, this is a user's preference. The most important playlists has this attribute unchangeable.</dd>
 * </dl>
 */
public class Playlist extends Model {

  private static final String PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + ".json";

  private static final String SYNC_PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/sync.json";

  /**
   * PUT
   */
  private static final String VISIBILITY_PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/visible.json";

  /**
   * GET
   */
  private static final String MEDIA_FILES_PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files.json";

  /**
   * POST
   */
  private static final String ADD_MEDIA_FILES_PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files/add.json";

  /**
   * DELETE
   */
  private static final String REMOVE_MEDIA_FILES_PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files/remove.json";

  /**
   * GET
   */
  private static final String FINGERPRINTS_PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files/fingerprints.json";

  @Key
  private String token;

  @Key
  private String name;

  @Key( "system_name" )
  private String systemName;

  @Key
  private String type;

  @Key( "media_files_count" )
  private long mediaFilesCount;

  @Key
  private long position;

  @Key
  private boolean embeddable;

  @Key
  private boolean visible;

  @Key( "last_accessed" )
  private boolean lastAccessed;

  @Key( "updated_at" )
  private String updatedAt;

  @Key
  private boolean syncable;

  @Key
  private String description;


  /**
   * Instantiates a new Playlist.
   * <p>
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
    this.setVisible( true );
  }


  /**
   * Creates a new CustomPlaylist or SmartPlaylist depending on the input parameters.
   * <p>
   * The user can create a CustomPlaylist by setting the playlist name.
   * <p>
   * The user can create a SmartPlaylist by setting the playlist name AND playlist search_params.
   * <p>
   * NOTE: Currently the search_params hash construction is complex enough and therefore it's
   * restricted to the Cloud Web Player, we'll open up the possibility for developers to create them as well.
   *
   * @param audioBoxClient the client
   *
   * @return a new instance of the saved Playlist if success or null if any error occurs
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Playlist create(AudioBoxClient audioBoxClient) throws IOException {
    validateForRequest( false );
    HttpResponse rsp = audioBoxClient.doPOST( Playlists.getPath(), new JsonHttpContent( audioBoxClient.getConf().getJsonFactory(), this ) );
    return rsp != null && rsp.isSuccessStatusCode() ? rsp.parseAs( PlaylistWrapper.class ).getPlaylist() : null;
  }


  /**
   * Handle the update of a custom or smart playlist.
   * <p>
   * As a rule of thumb you can have one uniquely named playlist for each type.
   * <p>
   * SmartPlaylist's <code>search_params</code> can be set only on creation and thus cannot be changed, in this action.
   * <p>
   * SmartPlaylist cannot be tweaked in their <code>search_params</code> due to the complexity of their construct.
   * Since SmartPlaylist are compiled on demand, just destroy the old and create a new one.
   *
   * @param audioBoxClient the client
   *
   * @return the playlist
   *
   * @throws java.lang.IllegalStateException                       if the playlist is not persisted yet.
   * @throws fm.audiobox.core.exceptions.ForbiddenException        if no valid subscription found
   * @throws fm.audiobox.core.exceptions.ResourceNotFoundException if playlist is not found or immutable
   * @throws fm.audiobox.core.exceptions.ValidationException       if playlist data is not valid (ex: name already taken)
   * @throws fm.audiobox.core.exceptions.AudioBoxException         if any of the remote error exception is detected.
   * @throws java.io.IOException                                   if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Playlist update(AudioBoxClient audioBoxClient) throws IOException {
    ensurePlaylistForRequest();
    audioBoxClient.doPUT( ModelUtil.interpolate( getPath(), getToken() ), new JsonHttpContent( audioBoxClient.getConf().getJsonFactory(), this ) );
    return this;
  }


  /**
   * Permanently destroy a playlist.
   * <p>
   * Only Custom and Smart playlists can be destroyed.
   *
   * @param audioBoxClient the client to use for the request
   *
   * @return true if operation succeeds
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public boolean destroy(AudioBoxClient audioBoxClient) throws IOException {
    ensurePlaylistForRequest();
    HttpResponse rsp = audioBoxClient.doDELETE( ModelUtil.interpolate( getPath(), getToken() ) );
    return rsp != null && rsp.isSuccessStatusCode();
  }


  /**
   * Begin content sync with the remote platform. Supported only by syncable playlists.
   * <p>
   * Calling this method will initiate a job that will synchronize data with the remote storage,
   * such as Dropbox, SkyDrive and others.
   * <p>
   * Playlists supporting official storage such as AudioBox Cloud or AudioBox Desktop does not require syncing.
   *
   * @param audioBoxClient the client to use for the request
   *
   * @return true if operation succeeds.
   *
   * @throws SyncException                                 if any problem occurs.
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public boolean sync(AudioBoxClient audioBoxClient) throws IOException {
    ensurePlaylistForRequest();
    if ( !this.isSyncable() ) // Well...
      throw new SyncException( HttpStatus.SC_UNPROCESSABLE_ENTITY );
    HttpResponse rsp;
    try {
      rsp = audioBoxClient.doPUT( getSyncPath(), new JsonHttpContent( audioBoxClient.getConf().getJsonFactory(), this ) );
    } catch ( AudioBoxException e ) {
      throw new SyncException( e.getResponse() );
    }

    return rsp != null && rsp.isSuccessStatusCode();
  }


  /**
   * <p>
   * Toggle visibility mode for this playlist.
   * </p>
   * <p>
   * By toggling the visibility attribute clients should avoid to render the playlist marked with visible: false
   * or grey them out.
   * </p>
   * <p>
   * Non visible playlists retains their functionality, they just should be hidden in views.
   * </p>
   *
   * @param audioBoxClient the client to use for the request
   *
   * @return true if operation succeeds.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public boolean toggleVisibility(AudioBoxClient audioBoxClient) throws IOException {
    ensurePlaylistForRequest();
    audioBoxClient.doPUT( getVisibilityPath(), new JsonHttpContent( audioBoxClient.getConf().getJsonFactory(), this ) );
    return true;
  }


  /**
   * Same as {@link fm.audiobox.core.models.Playlist#getMediaFiles(fm.audiobox.core.AudioBoxClient, long, String)} but
   * all media file fields are returned and *no* time filter is applied.
   *
   * @param audioBoxClient the client to use for the request
   *
   * @return A list of {@link MediaFile} elements
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public List<MediaFile> getMediaFiles(AudioBoxClient audioBoxClient) throws IOException {
    return getMediaFiles( audioBoxClient, 0 );
  }


  /**
   * <p>
   * Returns media files linked to the specified playlist token along with primary information.
   * </p>
   * <p>
   * This does not return all the attributes for a MediaFile, instead it returns an optimized JSON for fast view-level
   * rendering and parsing purposes. Full details about a particular MediaFile can be obtained by calling the dedicated
   * show endpoint.
   * </p>
   * <p>
   * Supports a comma separated 'set' parameter which indicates which attributes to render, like 'type,token' so a
   * developer can just ask the needed attributes.
   * </p>
   * <p>
   * Supports a datetime 'since' parameter that filters the collection and returns records modified since the specified
   * date.
   * </p>
   * <p>
   * Remote and third party Cloud Storage services' content can be accessed through this endpoint, however an error will
   * be returned if the user has no valid authentication information stored towards the service in question or has an
   * invalid subscription. For example if the user tries to access the Dropbox playlist but he has not the related account
   * linked a ForbiddenException will be thrown, along with the subscription status. Valid subscription statuses are
   * active and trialing.
   * </p>
   *
   * @param audioBoxClient the client to use for the request
   * @param parser         the parser to use to parse the response
   *
   * @return A list of {@link MediaFile} elements
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public List<MediaFile> getMediaFiles(AudioBoxClient audioBoxClient, JsonObjectParser parser) throws IOException {
    return getMediaFiles( audioBoxClient, 0, null, parser );
  }


  /**
   * Same as {@link fm.audiobox.core.models.Playlist#getMediaFiles(fm.audiobox.core.AudioBoxClient, long, String)} but
   * all media file fields are returned.
   * <br>
   * Time filter is applied.
   *
   * @param audioBoxClient the client to use for the request
   * @param since          unix timestamp that filters the collection and returns records modified since the specified date.
   *
   * @return A list of {@link MediaFile} elements
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public List<MediaFile> getMediaFiles(AudioBoxClient audioBoxClient, long since) throws IOException {
    return getMediaFiles( audioBoxClient, since, null );
  }


  /**
   * <p>
   * Returns media files linked to the specified playlist token along with primary information.
   * </p>
   * <p>
   * This does not return all the attributes for a MediaFile, instead it returns an optimized JSON for fast view-level
   * rendering and parsing purposes. Full details about a particular MediaFile can be obtained by calling the dedicated
   * show endpoint.
   * </p>
   * <p>
   * Supports a comma separated 'set' parameter which indicates which attributes to render, like 'type,token' so a
   * developer can just ask the needed attributes.
   * </p>
   * <p>
   * Supports a datetime 'since' parameter that filters the collection and returns records modified since the specified
   * date.
   * </p>
   * <p>
   * Remote and third party Cloud Storage services' content can be accessed through this endpoint, however an error will
   * be returned if the user has no valid authentication information stored towards the service in question or has an
   * invalid subscription. For example if the user tries to access the Dropbox playlist but he has not the related account
   * linked a ForbiddenException will be thrown, along with the subscription status. Valid subscription statuses are
   * active and trialing.
   * </p>
   *
   * @param audioBoxClient the client to use for the request
   * @param since          unix timestamp that filters the collection and returns records modified since the specified date.
   * @param set            comma separated 'set' parameter which indicates which attributes to render, like 'type,token', null will return all available fields.
   *
   * @return A list of {@link MediaFile} elements
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public List<MediaFile> getMediaFiles(AudioBoxClient audioBoxClient, long since, String set) throws IOException {
    return getMediaFiles( audioBoxClient, since, null, null );
  }


  /**
   * <p>
   * Returns media files linked to the specified playlist token along with primary information.
   * </p>
   * <p>
   * This does not return all the attributes for a MediaFile, instead it returns an optimized JSON for fast view-level
   * rendering and parsing purposes. Full details about a particular MediaFile can be obtained by calling the dedicated
   * show endpoint.
   * </p>
   * <p>
   * Supports a comma separated 'set' parameter which indicates which attributes to render, like 'type,token' so a
   * developer can just ask the needed attributes.
   * </p>
   * <p>
   * Supports a datetime 'since' parameter that filters the collection and returns records modified since the specified
   * date.
   * </p>
   * <p>
   * Remote and third party Cloud Storage services' content can be accessed through this endpoint, however an error will
   * be returned if the user has no valid authentication information stored towards the service in question or has an
   * invalid subscription. For example if the user tries to access the Dropbox playlist but he has not the related account
   * linked a ForbiddenException will be thrown, along with the subscription status. Valid subscription statuses are
   * active and trialing.
   * </p>
   *
   * @param audioBoxClient the client to use for the request
   * @param since          unix timestamp that filters the collection and returns records modified since the specified date.
   * @param set            comma separated 'set' parameter which indicates which attributes to render, like 'type,token', null will return all available fields.
   * @param parser         the parser to use to parse the response
   *
   * @return A list of {@link MediaFile} elements
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public List<MediaFile> getMediaFiles(AudioBoxClient audioBoxClient, long since, String set, JsonObjectParser parser) throws IOException {
    ensurePlaylistForRequest();

    String url = getMediaFilesPath();
    if ( since != 0 ) {
      url += "?" + MediaFiles.PARAM_SINCE + "=" + since;
    }

    if ( set != null ) {
      String setParam = MediaFiles.PARAM_SET + "=" + set;
      setParam = url.contains( "?" ) ? "&" : "?" + setParam;
      url += setParam;
    }

    HttpResponse rsp = audioBoxClient.doGET( url, parser, null );
    return rsp != null && rsp.isSuccessStatusCode() ? rsp.parseAs( audioBoxClient.getConf().getMediaFilesWrapperClass() ).getMediaFiles() : null;
  }


  /**
   * Gets media files grouped by albums.
   * <p>
   * Also includes the same media file entities in other collection views, sorted by the media files' position attribute.
   * <p>
   * Throws {@link fm.audiobox.core.exceptions.ForbiddenException} if the user is not enabled to view this playlist due
   * to bad subscription state or missing AudioMash link.
   * <p>
   * Throws {@link fm.audiobox.core.exceptions.ResourceNotFoundException} if the playlist does not exists.
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use for the request
   *
   * @return grouped {@link fm.audiobox.core.models.Albums} data.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Albums getAlbums(AudioBoxClient audioBoxClient) throws IOException {
    return getGroupedCollection( audioBoxClient, audioBoxClient.getConf().getAlbumsWrapperClass(), Albums.getPath( this.token ) );
  }


  /**
   * Gets media files grouped by albums.
   * <p>
   * Also includes the same media file entities in other collection views, sorted by the media files' position attribute.
   * <p>
   * Throws {@link fm.audiobox.core.exceptions.ForbiddenException} if the user is not enabled to view this playlist due
   * to bad subscription state or missing AudioMash link.
   * <p>
   * Throws {@link fm.audiobox.core.exceptions.ResourceNotFoundException} if the playlist does not exists.
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use for the request
   *
   * @return grouped {@link fm.audiobox.core.models.Albums} data.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Genres getGenres(AudioBoxClient audioBoxClient) throws IOException {
    return getGroupedCollection( audioBoxClient, audioBoxClient.getConf().getGenresWrapperClass(), Genres.getPath( this.token ) );
  }


  /**
   * Gets media files grouped by albums.
   * <p>
   * Also includes the same media file entities in other collection views, sorted by the media files' position attribute.
   * <p>
   * Throws {@link fm.audiobox.core.exceptions.ForbiddenException} if the user is not enabled to view this playlist due
   * to bad subscription state or missing AudioMash link.
   * <p>
   * Throws {@link fm.audiobox.core.exceptions.ResourceNotFoundException} if the playlist does not exists.
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use for the request
   *
   * @return grouped {@link fm.audiobox.core.models.Albums} data.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Artists getArtists(AudioBoxClient audioBoxClient) throws IOException {
    return getGroupedCollection( audioBoxClient, audioBoxClient.getConf().getArtistsWrapperClass(), Artists.getPath( this.token ) );
  }


  /**
   * Add Media Files to a CustomPlaylist.
   * <p>
   * Shallow action that requires a list of media files tokens to be added to this custom playlist.
   * Media files can be added manually only to custom playlists.
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use for the request
   * @param tokens         the list of the tokens string to add to this playlist
   *
   * @return the playlist instance in order to chain other operations on it if needed.
   *
   * @throws java.lang.IllegalStateException                       if the playlist is not persisted yet.
   * @throws fm.audiobox.core.exceptions.ResourceNotFoundException if the playlist not found or not of type CustomPlaylist.
   * @throws fm.audiobox.core.exceptions.AudioBoxException         if any of the remote error exception is detected.
   * @throws java.io.IOException                                   if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Playlist addMediaFiles(AudioBoxClient audioBoxClient, List<String> tokens) throws IOException {

    ensurePlaylistForRequest();

    GenericData d = new GenericData();
    for ( String token : tokens ) {
      d.put( MediaFiles.PARAM_TOKENS, token );
    }
    JsonHttpContent data = new JsonHttpContent( audioBoxClient.getConf().getJsonFactory(), d );
    audioBoxClient.doPOST( ModelUtil.interpolate( ADD_MEDIA_FILES_PATH, getToken() ), data, null, null );
    return this;
  }


  /**
   * Remove Media Files from a CustomPlaylist.
   * <p>
   * Shallow action that requires a list of media files tokens to be removed from this playlist.
   * Only custom playlist tokens are allowed, being the only ones for which content can be modified.
   * <br>
   * AudioBox will not remove media files not present in the destination playlist.
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use for the request
   * @param tokens         the list of the tokens string to add to this playlist
   *
   * @return the playlist instance in order to chain other operations on it if needed.
   *
   * @throws java.lang.IllegalStateException                       if the playlist is not persisted yet.
   * @throws fm.audiobox.core.exceptions.ResourceNotFoundException if the playlist not found or not of type CustomPlaylist.
   * @throws fm.audiobox.core.exceptions.AudioBoxException         if any of the remote error exception is detected.
   * @throws java.io.IOException                                   if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Playlist removeMediaFiles(AudioBoxClient audioBoxClient, List<String> tokens) throws IOException {

    ensurePlaylistForRequest();

    // NOTE: We are building the request with query parameters because google-http-java-client at the moment of
    // writing this library does not support DELETE methods with content-length != 0.
    // Trying to change this to a valid HttpContent type for request will result in an exception.
    String url = ModelUtil.interpolate( REMOVE_MEDIA_FILES_PATH, getToken() ) + "?utf8=true";
    for ( String tk : tokens ) url += "&" + MediaFiles.PARAM_TOKENS + "=" + tk;

    audioBoxClient.doDELETE( url );
    return this;
  }


  /**
   * Returns known file fingerprints for this playlist. Available only for local and cloud playlist tokens.
   * <p>
   * Will return all MD5 fingerprints of the media files on this playlist. Useful to know what has already been uploaded.
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use for the request
   *
   * @return A list of {@link MediaFile} elements
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public List<? extends MediaFile> getFingerprints(AudioBoxClient audioBoxClient) throws IOException {
    return getFingerprints( audioBoxClient, null );
  }


  /**
   * Returns known file fingerprints for this playlist. Available only for local and cloud playlist tokens.
   * <p>
   * Will return all MD5 fingerprints of the media files on this playlist. Useful to know what has already been uploaded.
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use for the request
   * @param parser         the parser to use to parse the response
   *
   * @return A list of {@link MediaFile} elements
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public List<? extends MediaFile> getFingerprints(AudioBoxClient audioBoxClient, JsonObjectParser parser) throws IOException {
    HttpResponse rsp = audioBoxClient.doGET( ModelUtil.interpolate( FINGERPRINTS_PATH, getToken() ), parser, null );
    return rsp != null && rsp.isSuccessStatusCode() ? rsp.parseAs( audioBoxClient.getConf().getMediaFilesWrapperClass() ).getMediaFiles() : null;
  }


  /**
   * Gets the generic remote resource path (token interpolation needed,
   * see {@link fm.audiobox.core.utils.ModelUtil#interpolate(String, String)} ).
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
   * Gets the playlist name.
   *
   * @return the playlist name
   */
  public String getName() {
    return this.name;
  }


  /**
   * Changes playlist name, if you want to persist the change
   * remember to call {@link Playlist#update(fm.audiobox.core.AudioBoxClient)}
   * method.
   *
   * @param name the name
   *
   * @return the playlist instance in order to chain other methods.
   */
  public Playlist setName(String name) {
    this.name = name;
    return this;
  }


  /**
   * Gets system name.
   *
   * @return the system name
   */
  public String getSystemName() {
    return this.systemName;
  }


  /**
   * Gets the playlist type.
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
    return this.mediaFilesCount;
  }


  /**
   * Gets the position of the playlist (client application should respect this field while sorting playlists).
   *
   * @return the position of the playlist
   */
  public long getPosition() {
    return this.position;
  }


  /**
   * Changes position value, if you want to persist the change
   * remember to call {@link Playlist#update(fm.audiobox.core.AudioBoxClient)}
   * method.
   *
   * @param position the position to set
   *
   * @return the playlist instance in order to chain other methods.
   */
  public Playlist setPosition(long position) {
    this.position = position;
    return this;
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
   * remember to call {@link Playlist#update(fm.audiobox.core.AudioBoxClient)}
   * method.
   *
   * @param embeddable true to mark it as embeddable, false to disable the feature.
   *
   * @return the playlist instance in order to chain other methods.
   */
  public Playlist setEmbeddable(boolean embeddable) {
    this.embeddable = embeddable;
    return this;
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
   * remember to call {@link Playlist#update(fm.audiobox.core.AudioBoxClient)}
   * method.
   *
   * @param visible true to mark it as visible, false to disable the feature.
   *
   * @return the playlist instance in order to chain other methods.
   */
  public Playlist setVisible(boolean visible) {
    this.visible = visible;
    return this;
  }


  /**
   * Use this method to check if the playlist is the last one accessed.
   * (i.e. {@link fm.audiobox.core.AudioBoxClient#getPlaylist(String)} was called at last}
   *
   * @return true if this was the last accessed playlist
   */
  public boolean isLastAccessed() {
    return this.lastAccessed;
  }


  /**
   * Gets the last time (in form of an UTC String) this playlist have been changed.
   * <br>
   * NOTE: Since client timezone may differ significantly you are strongly invited
   * to always trust and use this datetime in case you are building a sync tool.
   *
   * @return the UTC String of the last update on this playlist
   */
  public String getUpdatedAt() {
    return this.updatedAt;
  }


  /**
   * Checks if this playlist supports syncing with remote content, usually valid for external storage services.
   *
   * @return true if this is a syncable playlist, false otherwise
   */
  public boolean isSyncable() {
    return this.syncable;
  }


  /**
   * Sets token.
   *
   * Note: this is a readonly field (when saving this value will be discarded) and the meaning of this method is
   * to populate this object field (i.e. load from persisted playlist).
   *
   * @param token the token
   */
  public void setToken(String token) {
    this.token = token;
  }


  /**
   * Sets system name.
   *
   * Note: this is a readonly field (when saving this value will be discarded) and the meaning of this method is
   * to populate this object field (i.e. load from persisted playlist).
   *
   * @param systemName the system name
   */
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }


  /**
   * Sets type.
   *
   * Note: this is a readonly field (when saving this value will be discarded) and the meaning of this method is
   * to populate this object field (i.e. load from persisted playlist).
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }


  /**
   * Sets media files count.
   *
   * Note: this is a readonly field (when saving this value will be discarded) and the meaning of this method is
   * to populate this object field (i.e. load from persisted playlist).
   *
   * @param mediaFilesCount the media files count
   */
  public void setMediaFilesCount(long mediaFilesCount) {
    this.mediaFilesCount = mediaFilesCount;
  }


  /**
   * Sets as last accessed.
   *
   * Note: this is a readonly field (when saving this value will be discarded) and the meaning of this method is
   * to populate this object field (i.e. load from persisted playlist).
   *
   * @param lastAccessed true if the playlist was the last accessed.
   */
  public void setLastAccessed(boolean lastAccessed) {
    this.lastAccessed = lastAccessed;
  }


  /**
   * Sets updated at.
   *
   * Note: this is a readonly field (when saving this value will be discarded) and the meaning of this method is
   * to populate this object field (i.e. load from persisted playlist).
   *
   * @param updatedAt last time updated
   */
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }


  /**
   * Sets syncable property.
   *
   * Note: this is a readonly field (when saving this value will be discarded) and the meaning of this method is
   * to populate this object field (i.e. load from persisted playlist).
   *
   * @param syncable the syncable
   */
  public void setSyncable(boolean syncable) {
    this.syncable = syncable;
  }


  /**
   * Sets a brief description of the playlist.
   *
   * @param description a brief String to describe this playlist
   *
   * @return the playlist instance in order to chain other methods.
   */
  public Playlist setDescription(String description) {
    this.description = description;
    return this;
  }


  /**
   * Gets this playlist brief description.
   */
  public String getDescription() {
    return this.description;
  }


  /**
   * Checks if the playlist is a custom one.
   *
   * @return true if the playlist is a custom playlist.
   */
  public boolean isCustom() {
    return Playlists.PLAYLIST_CUSTOM.equals( getSystemName() );
  }


  /**
   * Checks if the playlist is a smart one.
   *
   * @return true if the playlist is a smart playlist.
   */
  public boolean isSmart() {
    return Playlists.PLAYLIST_SMART.equals( getSystemName() );
  }


  /**
   * Checks if the playlist is the offline items playlist.
   *
   * @return true if the playlist is the offline playlist.
   */
  public boolean isOffline() {
    return Playlists.PLAYLIST_OFFLINE.equals( getSystemName() );
  }


  /**
   * Checks if the playlist represents a cloud drive.
   *
   * @return true if the playlist is a cloud drive.
   */
  public boolean isDrive() {
    return !( isCustom() || isSmart() || isOffline() );
  }


  @Override
  public final boolean equals(Object other) {
    if ( other == null || !( other instanceof Playlist ) ) {
      return false;
    }

    Playlist o = ( Playlist ) other;

    boolean eq;
    if ( this.token != null ) {
      eq = this.token.equals( o.getToken() );
    } else {
      eq = o.getToken() == null;
    }

    if ( this.updatedAt != null ) {
      eq = eq && this.updatedAt.equals( o.getUpdatedAt() );
    } else {
      eq = o.getUpdatedAt() == null;
    }

    return eq;

  }


  @Override
  public final int hashCode() {
    int hashCode = 0;

    if ( this.token != null )
      hashCode = hashCode * 37 + this.token.hashCode();
    if ( this.updatedAt != null )
      hashCode = hashCode * 37 + this.updatedAt.hashCode();

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
   * Gets visibility path for this playlist.
   *
   * @return the visibility path
   */
  private String getVisibilityPath() {
    return ModelUtil.interpolate( VISIBILITY_PATH, getToken() );
  }


  /**
   * Gets media_files path.
   *
   * @return the sync path
   */
  private String getMediaFilesPath() {
    return ModelUtil.interpolate( MEDIA_FILES_PATH, getToken() );
  }


  /**
   * Checks whether the playlist is ready for requesting remote server.
   * <p>
   * If something is missing or not valid an IllegalStateException is thrown
   */
  private void ensurePlaylistForRequest() {
    validateForRequest( true );
  }


  /**
   * Validates the playlist before performing requests.
   *
   * @param newRecordNotAllowed whether a new record should rise an error or not
   */
  private void validateForRequest(boolean newRecordNotAllowed) {

    if ( newRecordNotAllowed && isNewRecord() ) {
      throw new IllegalStateException( "Playlist must be remotely created before performing the requested action." );
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


  /**
   * Given a class and a string path this method will return a collection of the specified type.
   *
   * @param audioBoxClient the client to use for the request
   * @param klass          the class to use for response parsing
   * @param path           the path to call
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  private <T> T getGroupedCollection(AudioBoxClient audioBoxClient, Class<T> klass, String path) throws IOException {
    ensurePlaylistForRequest();
    HttpResponse rsp = audioBoxClient.doGET( path );
    return rsp != null && rsp.isSuccessStatusCode() ? rsp.parseAs( klass ) : null;
  }


}
