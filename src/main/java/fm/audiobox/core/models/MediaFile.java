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


import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.util.Key;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.net.Download;
import fm.audiobox.core.net.NetworkProgressListener;
import fm.audiobox.core.utils.ModelUtil;

import java.io.IOException;
import java.io.OutputStream;


/**
 * <p>
 * Media Files are the most important entity in the platform, has many properties, are editable, deletable and streamable.
 * <br/>
 * Not every media container is supported by AudioBox, you can find this information in the <code>user.json</code> API
 * call by looking <code>user.accepted_extensions</code> and <code>user.accepted_formats</code>.
 * </p>
 * <h3>Uploads</h3>
 * <p>
 * Media Files uploaded into AudioBox Cloud will use storage space that will count toward the monthly bill of the User.
 * Please always check the permissions.cloud value prior to attempt an upload since a subscription might no be in good standing.
 * </p>
 * <h3>Streaming</h3>
 * <p>
 * Developers can request to stream a file in any moment.
 * </p>
 * <h3>Attributes</h3>
 * <dl>
 * <dt><strong>type:</strong></dt>
 * <dd>string, readonly. Can be either 'AudioFile' or 'VideoFile'</dd>
 * <dt><strong>token:</strong></dt>
 * <dd>string, readonly. Identify uniquely this Media File in the system</dd>
 * <dt><strong>artist:</strong></dt>
 * <dd>string, readwrite. Artist name, automatically set depending on the Cloud Drive in use, if not set.</dd>
 * <dt><strong>album:</strong></dt>
 * <dd>string, readwrite. Album name, automatically set depending on the Cloud Drive in use if not set.</dd>
 * <dt><strong>genre:</strong></dt>
 * <dd>string, readwrite. Genre name, automatically set depending on the Cloud Drive in use if not set.</dd>
 * <dt><strong>releaseYear:</strong></dt>
 * <dd>integer, readwrite. Four integers, represents the year when the Media File has been released.</dd>
 * <dt><strong>title:</strong></dt>
 * <dd>string, readwrite. The title of this Media File.</dd>
 * <dt><strong>albumArtist:</strong></dt>
 * <dd>string, readwrite. Album Artist name. Useful to differentiate between compilations.</dd>
 * <dt><strong>composer:</strong></dt>
 * <dd>string, readwrite. Composer name.</dd>
 * <dt><strong>comment:</strong></dt>
 * <dd>string, readwrite. Custom comment.</dd>
 * <dt><strong>lenlenStrtr:</strong></dt>
 * <dd>string, readonly. Formatted as "3:04" to ease display in views. Represents the duration of the media file.</dd>
 * <dt><strong>lenInt:</strong></dt>
 * <dd>integer, readonly. Represents the duration in seconds of the media file.</dd>
 * <dt><strong>position:</strong></dt>
 * <dd>integer, readwrite. Represents the position this Media File occupy in a collection/album (track's number).</dd>
 * <dt><strong>filename:</strong></dt>
 * <dd>string, readonly. File name indicating where the file has been stored on our systems.</dd>
 * <dt><strong>loved:</strong></dt>
 * <dd>boolean, readwrite. Indicates if this Media File is 'loved' by its owner or not. Default is false.</dd>
 * <dt><strong>discNumber:</strong></dt>
 * <dd>integer, readwrite. Represents the number of the disc in a multi collection set.</dd>
 * <dt><strong>mime:</strong></dt>
 * <dd>string, readonly. Formatted such as "audio/mpeg", represents the recognized mime type for this file. This value can help some client to better recognize the type of file to be streamed.</dd>
 * <dt><strong>remotePath:</strong></dt>
 * <dd>string, readonly. Used along with some Cloud Drives to help the platform identifying where this file is stored for retrieval.</dd>
 * <dt><strong>source:</strong></dt>
 * <dd>string, readonly. Represents where this Media File comes from. Possible values are: 'cloud', 'local', 'dropbox', 'skydrive', 'box', 'gdrive', 'youtube', 'soundcloud', 'ubuntu'.</dd>
 * <dt><strong>shareToken:</strong></dt>
 * <dd>string, readonly. Shared token across multiple Media Files with the same artist/album combo that builds up a Share URL, such as: http://audiobox.fm/share/cfa4b55e1736415b491147f041b95392.</dd>
 * <dt><strong>artwork:</strong></dt>
 * <dd>
 * string, readwrite. Represents the path for the Artwork of a Media File on the HTTP(S) CDN
 * <strong>m.audiobox.fm.</strong>
 * 256x256. 1.5MB Max. PNG/JPEG/GIF.
 * </dd>
 * <dt><strong>size:</strong></dt>
 * <dd>integer, readonly. Physical size of the Media File, in bytes.</dd>
 * <dt><strong>hash:</strong></dt>
 * <dd>string, readonly. MD5 Hash of the Media File.</dd>
 * <dt><strong>videoBitrate:</strong></dt>
 * <dd>string, readonly. Indicates the bitrate in case of VideoFile.</dd>
 * <dt><strong>videoCodec:</strong></dt>
 * <dd>string, readonly. Indicates the codec in case of VideoFile.</dd>
 * <dt><strong>videoResolution:</strong></dt>
 * <dd>string, readonly. Indicates the resolution in case of VideoFile.</dd>
 * <dt><strong>videoFps:</strong></dt>
 * <dd>string, readonly. Indicates the frame per second in case of VideoFile.</dd>
 * <dt><strong>videoAspect:</strong></dt>
 * <dd>string, readonly. Indicates the aspect ratio in case of VideoFile.</dd>
 * <dt><strong>videoContainer:</strong></dt>
 * <dd>string, readonly. Indicates the container in case of VideoFile.</dd>
 * <dt><strong>audioBitrate:</strong></dt>
 * <dd>string, readonly. Indicates the bitrate in case of AudioFile or the first audio track in a VideoFile.</dd>
 * <dt><strong>audioCodec:</strong></dt>
 * <dd>string, readonly. Indicates the codec in case of AudioFile or the first audio track in a VideoFile.</dd>
 * <dt><strong>audioSampleRate:</strong></dt>
 * <dd>string, readonly. Indicates the sample rate in case of AudioFile or the first audio track in a VideoFile.</dd>
 * </dl>
 */
public class MediaFile extends Model {

  /**
   * GET, PUT, DELETE
   */
  private static final String PATH = "/api/v1/media_files/" + ModelUtil.TOKEN_PLACEHOLDER + ".json";

  /**
   * GET
   */
  private static final String STREAM_PATH = "/api/v1/stream/" + ModelUtil.TOKEN_PLACEHOLDER;

  /**
   * GET
   */
  private static final String DOWNLOAD_PATH = "/api/v1/download/" + ModelUtil.TOKEN_PLACEHOLDER;

  /**
   * POST
   */
  private static final String SCROBBLE_PATH = "/api/v1/media_files/" + ModelUtil.TOKEN_PLACEHOLDER + "/scrobble.json";

  /**
   * GET
   */
  private static final String LYRICS_PATH = "/api/v1/media_files/" + ModelUtil.TOKEN_PLACEHOLDER + "/lyrics.json";

  /**
   * POST
   */
  private static final String LOVE_PATH = "/api/v1/media_files/" + ModelUtil.TOKEN_PLACEHOLDER + "/love.json";

  /**
   * POST
   */
  private static final String UNLOVE_PATH = "/api/v1/media_files/" + ModelUtil.TOKEN_PLACEHOLDER + "/unlove.json";

  /**
   * POST
   */
  private static final String TOGGLE_LOVE_PATH = "/api/v1/media_files/" + ModelUtil.TOKEN_PLACEHOLDER + "/toggle_love.json";

  /**
   * The Type.
   */
  @Key
  protected String type;

  /**
   * The Token.
   */
  @Key
  protected String token;

  /**
   * The Artist.
   */
  @Key
  protected String artist;

  /**
   * The Album.
   */
  @Key
  protected String album;

  /**
   * The Genre.
   */
  @Key
  protected String genre;

  /**
   * The Release year.
   */
  @Key("release_year")
  protected int releaseYear;

  /**
   * The Title.
   */
  @Key
  protected String title;

  /**
   * The duration string formatted.
   */
  @Key("len_str")
  protected String lenStr;

  /**
   * The duration in seconds.
   */
  @Key("len_int")
  protected int lenInt;

  /**
   * The Position.
   */
  @Key
  protected int position;

  /**
   * The Filename.
   */
  @Key
  protected String filename;

  /**
   * The Media file name.
   */
  @Key("media_file_name")
  protected String mediaFileName; // Used when upload succeeds.

  /**
   * The Loved.
   */
  @Key
  protected boolean loved;

  /**
   * The Disc number.
   */
  @Key("disc_number")
  protected int discNumber;

  /**
   * The Mime.
   */
  @Key
  protected String mime;

  /**
   * The Remote path.
   */
  @Key("remote_path")
  protected String remotePath;

  /**
   * The Source.
   */
  @Key
  protected String source;

  /**
   * The Share token.
   */
  @Key("share_token")
  protected String shareToken;

  /**
   * The Artwork.
   */
  @Key
  protected String artwork;

  /**
   * The Size.
   */
  @Key
  protected long size;

  /**
   * The Album artist.
   */
  @Key("album_artist")
  protected String albumArtist;

  /**
   * The Hash.
   */
  @Key
  protected String hash;

  /**
   * The Composer.
   */
  @Key
  protected String composer;

  /**
   * The Comment.
   */
  @Key
  protected String comment;

  /**
   * The Video bitrate.
   */
  @Key("video_bitrate")
  protected String videoBitrate;

  /**
   * The Video codec.
   */
  @Key("video_codec")
  protected String videoCodec;

  /**
   * The Video resolution.
   */
  @Key("video_resolution")
  protected String videoResolution;

  /**
   * The Video FPS.
   */
  @Key("video_fps")
  protected String videoFps;

  /**
   * The Video aspect ratio.
   */
  @Key("video_aspect")
  protected String videoAspect;

  /**
   * The Video container.
   */
  @Key("video_container")
  protected String videoContainer;

  /**
   * The Audio bitrate.
   */
  @Key("audio_bitrate")
  protected String audioBitrate;

  /**
   * The Audio codec.
   */
  @Key("audio_codec")
  protected String audioCodec;

  /**
   * The Audio sample rate.
   */
  @Key("audio_sample_rate")
  protected String audioSampleRate;

  /**
   * The Lyrics.
   */
  @Key
  protected String lyrics;


  /**
   * Instantiates a new Playlist.
   * <p/>
   * Default empty constructor.
   */
  @SuppressWarnings("unused")
  public MediaFile() {
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
   * Gets stream path for this media file.
   *
   * @return the stream path
   */
  public String getStreamPath() {
    return ModelUtil.interpolate( STREAM_PATH, getFilename() );
  }


  /**
   * Gets stream url for this media file.
   * <p>
   * Some device does not support SSL streaming (such as old Android), in this case
   * you are forced to serve via canonical HTTP protocol (<code>secure = false</code>).
   * </p>
   *
   * @param client the Client instance to grab env configuration from
   * @param secure whether to serve the SSL protected stream url or not
   *
   * @return the stream path
   */
  public String getStreamUrl(Client client, boolean secure) {
    return client.getConf().getBaseUrl( Configuration.Channels.api, secure ) + getStreamPath();
  }


  /**
   * Gets download path for this media file.
   *
   * @return the download path
   */
  public String getDownloadPath() {
    return ModelUtil.interpolate( DOWNLOAD_PATH, getFilename() );
  }


  /**
   * Gets download url for this media file.
   *
   * @param client the Client instance to grab env configuration from
   *
   * @return the stream path
   */
  public String getDownloadUrl(Client client) {
    return client.getConf().getBaseUrl( Configuration.Channels.api ) + getDownloadPath();
  }


  /**
   * Load a single media file.
   *
   * @param client the
   *               to use for the request
   * @param token  the token that uniquely identify the media file
   *
   * @return the requested media file
   *
   * @throws fm.audiobox.core.exceptions.ResourceNotFoundException if the requested media was not found on AudioBox.
   * @see
   */
  public static MediaFile load(Client client, String token) throws IOException {
    HttpResponse rsp = client.doGET( ModelUtil.interpolate( PATH, token ) );
    return rsp.isSuccessStatusCode() ? rsp.parseAs( client.getConf().getMediaFileWrapperClass() ).getMediaFile() : null;
  }


  /**
   * (Re)load a new instance of the same media file.
   *
   * @param client the
   *               to use for the request
   *
   * @return the requested media file
   *
   * @throws fm.audiobox.core.exceptions.ResourceNotFoundException if the requested media was not found on AudioBox.
   * @see
   */
  public MediaFile reload(Client client) throws IOException {
    return MediaFile.load( client, getToken() );
  }


  /**
   * Downloads the media to specified {@link java.io.OutputStream}
   *
   * @param client the client
   * @param out    the out
   *
   * @return the same given
   * instance with downloaded data
   *
   * @throws IOException if any network communication or IO occurs
   */
  public OutputStream download(Client client, OutputStream out) throws IOException {
    return this.download( client, out, null );
  }


  /**
   * Downloads the media to specified {@link java.io.OutputStream}
   *
   * @param client   the to use for the request
   * @param out      the desired
   *                 where to store the downloaded data
   * @param listener a
   *                 for monitoring download progress
   *
   * @return the same given
   * instance with downloaded data
   *
   * @throws IOException if any network communication or IO occurs
   */
  public OutputStream download(Client client, OutputStream out, NetworkProgressListener listener) throws IOException {
    HttpResponse rsp = client.doRequestToChannel( HttpMethods.GET, ModelUtil.interpolate( getDownloadPath(), token ), null, null, Configuration.Channels.upload );
    Download d = new Download( rsp.getContent(), out, listener, rsp.getHeaders().getContentLength() );
    return d.start();
  }


  /**
   * Handle a single media file update.
   *
   * @param client the
   *               to use for the request
   *
   * @return the media file in order to chain other calls.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @see
   */
  public MediaFile update(Client client) throws IOException {
    client.doPUT( ModelUtil.interpolate( getPath(), getToken() ), new JsonHttpContent( client.getConf().getJsonFactory(), this ) );
    return this;
  }


  /**
   * Handle a single media file destruction synchronously.
   * <p/>
   * If you need to permanently destroy more than one single media file in one sweep use
   * {@link MediaFiles#destroyAll(fm.audiobox.core.Client, java.util.List)}
   * <p/>
   * Do not attempt to call this endpoint for single tracks the user selected to destroy.
   * <p/>
   * This will remove the media file from our database. If the media file is stored on AudioBox Cloud it will be
   * physically removed as well. If the media file is stored on a remote storage solution like AudioBox Desktop,
   * Dropbox, SkyDrive, etc. it will not be harmed unless management mode is enabled.
   *
   * @param client the
   *               to use for the request
   *
   * @return true if the operation succeeds.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @see
   */
  public boolean destroy(Client client) throws IOException {
    HttpResponse rsp = client.doDELETE( ModelUtil.interpolate( getPath(), getToken() ) );
    return rsp.isSuccessStatusCode();
  }


  /**
   * Notify the system that this media file finished playing.
   * <p/>
   * This endpoint is highly recommended to be used and should be called when the Media File is approaching the end of the stream.
   * <p/>
   * Triggers different actions in the system, such as Scrobbling to Last.fm and much more.
   *
   * @param client the
   *               to use for the request
   *
   * @return the media file in order to chain other calls.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @see
   */
  public MediaFile scrobble(Client client) throws IOException {
    client.doPOST( ModelUtil.interpolate( SCROBBLE_PATH, getToken() ) );
    return this;
  }


  /**
   * Loads the media file lyrics from AudioBox.
   *
   * @param client the
   *               to use for the request
   *
   * @return the lyrics or null
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @see
   */
  public String getLyrics(Client client) throws IOException {

    if ( getLyricsField() != null ) {
      return getLyricsField();
    }

    HttpResponse rsp = client.doGET( ModelUtil.interpolate( LYRICS_PATH, getToken() ) );
    MediaFile m = rsp.isSuccessStatusCode() ? rsp.parseAs( MediaFileWrapper.class ).getMediaFile() : null;
    this.lyrics = m != null ? m.getLyricsField() : null;

    return this.lyrics;
  }


  /**
   * Manually 'love' a Media File
   * <p/>
   * This will also trigger love/like on all supported and linked remote services, such as Last.fm/Facebook.
   * <p/>
   * You can also 'love' the media file by issuing a PUT update request and setting the attribute 'loved' to true,
   * but this will not notifying any remote services.
   * <p/>
   * Last.fm will see a track as loved, Facebook as liked, Google Drive as starred, and so on.
   *
   * @param client the
   *               to use for the request
   *
   * @return the media file in order to chain other calls.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @see
   */
  public MediaFile love(Client client) throws IOException {
    setPreferred( client, LOVE_PATH );
    this.loved = true;
    return this;
  }


  /**
   * Manually 'unlove' a Media File
   * <p/>
   * This will also trigger 'unlove' on all supported and linked remote services, such as Last.fm/Facebook/Google Drive.
   * <p/>
   * You can also 'unlove' the media file by issuing a PUT update request and setting the attribute 'loved' to false,
   * but this will not notifying any remote services.
   * <p/>
   * Last.fm will see a track as unloved, Facebook as unliked, Google Drive as not starred, and so on.
   *
   * @param client the
   *               to use for the request
   *
   * @return the media file in order to chain other calls.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @see
   */
  public MediaFile unlove(Client client) throws IOException {
    setPreferred( client, UNLOVE_PATH );
    this.loved = false;
    return this;
  }


  /**
   * Switch the loved attribute to true/false and fire off remote services notifications.
   * <p/>
   * This is useful for application that care about the loved attribute after it's being set.
   * <p/>
   * Preserve all the features of the love and unlove endpoints.
   *
   * @param client the
   *               to use for the request
   *
   * @return the media file in order to chain other calls.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @see
   */
  public MediaFile toggleLove(Client client) throws IOException {
    setPreferred( client, TOGGLE_LOVE_PATH );
    this.loved = !this.loved;
    return this;
  }


  /**
   * Gets the media file type, can be either 'AudioFile' or 'VideoFile'.
   *
   * @return the type
   */
  public String getType() {
    return this.type;
  }


  /**
   * Gets token, identify uniquely this Media File in the system.
   *
   * @return the token
   */
  public String getToken() {
    return this.token;
  }


  /**
   * Artist name, automatically set depending on the Cloud Drive in use, if not set.
   *
   * @return the artist
   */
  public String getArtist() {
    return this.artist;
  }


  /**
   * Sets artist.
   *
   * @param artist the artist
   */
  public void setArtist(String artist) {
    this.artist = artist;
  }


  /**
   * Album name, automatically set depending on the Cloud Drive in use if not set.
   *
   * @return the album
   */
  public String getAlbum() {
    return this.album;
  }


  /**
   * Sets album.
   *
   * @param album the album
   */
  public void setAlbum(String album) {
    this.album = album;
  }


  /**
   * Genre name, automatically set depending on the Cloud Drive in use if not set.
   *
   * @return the genre
   */
  public String getGenre() {
    return this.genre;
  }


  /**
   * Sets genre.
   *
   * @param genre the genre
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }


  /**
   * Four integers, represents the year when the Media File has been released.
   *
   * @return the release year
   */
  public int getReleaseYear() {
    return this.releaseYear;
  }


  /**
   * Sets release year.
   *
   * @param releaseYear the release year
   */
  public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
  }


  /**
   * The title of this Media File.
   *
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }


  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }


  /**
   * Formatted as "3:04" to ease display in views. Represents the duration of the media file.
   *
   * @return the len str
   */
  public String getLenStr() {
    return this.lenStr;
  }


  /**
   * Represents the duration in seconds of the media file.
   *
   * @return the len int
   */
  public int getLenInt() {
    return this.lenInt;
  }


  /**
   * Represents the position this Media File occupy in a collection/album (track's number).
   *
   * @return the position
   */
  public int getPosition() {
    return this.position;
  }


  /**
   * Sets position.
   *
   * @param position the position
   */
  public void setPosition(int position) {
    this.position = position;
  }


  /**
   * File name indicating where the file has been stored on our systems.
   *
   * @return the filename
   */
  public String getFilename() {
    return this.filename == null ? this.mediaFileName : this.filename;
  }


  /**
   * Indicates if this Media File is 'loved' by its owner or not. Default is false.
   *
   * @return the boolean
   */
  public boolean isLoved() {
    return this.loved;
  }


  /**
   * Sets loved.
   *
   * @param loved the loved
   */
  public void setLoved(boolean loved) {
    this.loved = loved;
  }


  /**
   * Represents the number of the disc in a multi collection set.
   *
   * @return the disc number
   */
  public int getDiscNumber() {
    return this.discNumber;
  }


  /**
   * Sets disc number.
   *
   * @param discNumber the disc number
   */
  public void setDiscNumber(int discNumber) {
    this.discNumber = discNumber;
  }


  /**
   * Formatted such as "audio/mpeg", represents the recognized mime type for this file. This value can help some client
   * to better recognize the type of file to be streamed.
   *
   * @return the mime
   */
  public String getMime() {
    return this.mime;
  }


  /**
   * Used along with some Cloud Drives to help the platform identifying where this file is stored for retrieval.
   *
   * @return the remote path
   */
  public String getRemotePath() {
    return this.remotePath;
  }


  /**
   * Represents where this Media File comes from. Possible values are: 'cloud', 'local', 'dropbox', 'skydrive', 'box',
   * 'gdrive', 'youtube', 'soundcloud', 'ubuntu'.
   *
   * @return the source
   */
  public String getSource() {
    return this.source;
  }


  /**
   * Shared token across multiple Media Files with the same artist/album combo that builds up a Share URL, such as:
   * <a href='http://audiobox.fm/share/cfa4b55e1736415b491147f041b95392'><code>http://audiobox.fm/share/cfa4b55e1736415b491147f041b95392</code></a>
   *
   * @return the share token
   */
  public String getShareToken() {
    return this.shareToken;
  }


  /**
   * Represents the path for the Artwork of a Media File on the HTTP(S) CDN m.audiobox.fm. 256x256. 1.5MB Max. PNG/JPEG/GIF.
   *
   * @return the artwork
   */
  public String getArtwork() {
    return this.artwork;
  }


  /**
   * Sets artwork.
   *
   * @param artwork the artwork
   */
  public void setArtwork(String artwork) {
    this.artwork = artwork;
  }


  /**
   * Physical size of the Media File, in bytes.
   *
   * @return the size
   */
  public long getSize() {
    return this.size;
  }


  /**
   * Album Artist name. Useful to differentiate between compilations.
   *
   * @return the album artist
   */
  public String getAlbumArtist() {
    return this.albumArtist;
  }


  /**
   * Sets album artist name.
   *
   * @param albumArtist the album artist
   */
  public void setAlbumArtist(String albumArtist) {
    this.albumArtist = albumArtist;
  }


  /**
   * MD5 Hash of the Media File.
   *
   * @return the hash
   */
  public String getHash() {
    return this.hash;
  }


  /**
   * Composer name.
   *
   * @return the composer
   */
  public String getComposer() {
    return this.composer;
  }


  /**
   * Sets composer.
   *
   * @param composer the composer
   */
  public void setComposer(String composer) {
    this.composer = composer;
  }


  /**
   * Gets custom comment.
   *
   * @return the comment
   */
  public String getComment() {
    return this.comment;
  }


  /**
   * Sets comment.
   *
   * @param comment the comment
   */
  public void setComment(String comment) {
    this.comment = comment;
  }


  /**
   * Indicates the codec in case of VideoFile.
   *
   * @return the video bitrate
   */
  public String getVideoBitrate() {
    return this.videoBitrate;
  }


  /**
   * Indicates the codec in case of VideoFile.
   *
   * @return the video codec
   */
  public String getVideoCodec() {
    return this.videoCodec;
  }


  /**
   * Indicates the resolution in case of VideoFile.
   *
   * @return the video resolution
   */
  public String getVideoResolution() {
    return this.videoResolution;
  }


  /**
   * Indicates the frame per second in case of VideoFile.
   *
   * @return the video fps
   */
  public String getVideoFps() {
    return this.videoFps;
  }


  /**
   * Indicates the aspect ratio in case of VideoFile.
   *
   * @return the video aspect
   */
  public String getVideoAspect() {
    return this.videoAspect;
  }


  /**
   * Indicates the container in case of VideoFile.
   *
   * @return the video container
   */
  public String getVideoContainer() {
    return this.videoContainer;
  }


  /**
   * Indicates the bitrate in case of AudioFile or the first audio track in a VideoFile.
   *
   * @return the audio bitrate
   */
  public String getAudioBitrate() {
    return this.audioBitrate;
  }


  /**
   * Indicates the codec in case of AudioFile or the first audio track in a VideoFile.
   *
   * @return the audio codec
   */
  public String getAudioCodec() {
    return this.audioCodec;
  }


  /**
   * Indicates the sample rate in case of AudioFile or the first audio track in a VideoFile.
   *
   * @return the audio sample rate
   */
  public String getAudioSampleRate() {
    return this.audioSampleRate;
  }


  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }


  /**
   * Sets token.
   *
   * @param token the token
   */
  public void setToken(String token) {
    this.token = token;
  }


  /**
   * Sets length in string format
   *
   * @param lenStr the media duration in string format
   */
  public void setLenStr(String lenStr) {
    this.lenStr = lenStr;
  }


  /**
   * Sets len int.
   *
   * @param lenInt the len int
   */
  public void setLenInt(int lenInt) {
    this.lenInt = lenInt;
  }


  /**
   * Sets filename.
   *
   * @param filename the filename
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }


  /**
   * Sets media file name.
   *
   * @param mediaFileName the media file name
   */
  public void setMediaFileName(String mediaFileName) {
    this.mediaFileName = mediaFileName;
  }


  /**
   * Sets mime.
   *
   * @param mime the mime
   */
  public void setMime(String mime) {
    this.mime = mime;
  }


  /**
   * Sets remote path.
   *
   * @param remotePath the remote path
   */
  public void setRemotePath(String remotePath) {
    this.remotePath = remotePath;
  }


  /**
   * Sets source.
   *
   * @param source the source
   */
  public void setSource(String source) {
    this.source = source;
  }


  /**
   * Sets share token.
   *
   * @param shareToken the share token
   */
  public void setShareToken(String shareToken) {
    this.shareToken = shareToken;
  }


  /**
   * Sets size.
   *
   * @param size the size
   */
  public void setSize(long size) {
    this.size = size;
  }


  /**
   * Sets hash.
   *
   * @param hash the hash
   */
  public void setHash(String hash) {
    this.hash = hash;
  }


  /**
   * Sets video bitrate.
   *
   * @param videoBitrate the video bitrate
   */
  public void setVideoBitrate(String videoBitrate) {
    this.videoBitrate = videoBitrate;
  }


  /**
   * Sets video codec.
   *
   * @param videoCodec the video codec
   */
  public void setVideoCodec(String videoCodec) {
    this.videoCodec = videoCodec;
  }


  /**
   * Sets video resolution.
   *
   * @param videoResolution the video resolution
   */
  public void setVideoResolution(String videoResolution) {
    this.videoResolution = videoResolution;
  }


  /**
   * Sets video fps.
   *
   * @param videoFps the video fps
   */
  public void setVideoFps(String videoFps) {
    this.videoFps = videoFps;
  }


  /**
   * Sets video aspect.
   *
   * @param videoAspect the video aspect
   */
  public void setVideoAspect(String videoAspect) {
    this.videoAspect = videoAspect;
  }


  /**
   * Sets video container.
   *
   * @param videoContainer the video container
   */
  public void setVideoContainer(String videoContainer) {
    this.videoContainer = videoContainer;
  }


  /**
   * Sets audio bitrate.
   *
   * @param audioBitrate the audio bitrate
   */
  public void setAudioBitrate(String audioBitrate) {
    this.audioBitrate = audioBitrate;
  }


  /**
   * Sets audio codec.
   *
   * @param audioCodec the audio codec
   */
  public void setAudioCodec(String audioCodec) {
    this.audioCodec = audioCodec;
  }


  /**
   * Sets audio sample rate.
   *
   * @param audioSampleRate the audio sample rate
   */
  public void setAudioSampleRate(String audioSampleRate) {
    this.audioSampleRate = audioSampleRate;
  }


  /**
   * Sets lyrics.
   *
   * @param lyrics the lyrics
   */
  public void setLyrics(String lyrics) {
    this.lyrics = lyrics;
  }


/* =============== */
  /* Private methods */
  /* =============== */


  /**
   * The media file lyrics if any.
   *
   * @return the lyrics of this media file.
   */
  private String getLyricsField() {
    return this.lyrics;
  }


  /**
   * Sets and remotely stores the loved preferences on this media file
   *
   * @param client the {@link fm.audiobox.core.Client} to use for the request
   *
   * @return true if the operation succeeds.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  private boolean setPreferred(Client client, String path) throws IOException {
    client.doPOST( ModelUtil.interpolate( path, getToken() ) );
    return true;
  }

}
