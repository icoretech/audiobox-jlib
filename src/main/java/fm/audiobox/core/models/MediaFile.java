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


import com.google.api.client.util.Key;
import fm.audiobox.core.utils.ModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <p>
 * Media Files are the most important entity in the platform, has many properties, are editable, deletable and streamable.
 * <br/>
 * Not every media container is supported by AudioBox, you can find this informations in the user.json API call by looking
 * user.accepted_extensions and user.accepted_formats.
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
 * <dt>type:</dt>
 * <dd>string, readonly. Can be either 'AudioFile' or 'VideoFile'</dd>
 * <dt>token:</dt>
 * <dd>string, readonly. Identify uniquely this Media File in the system</dd>
 * <dt>artist:</dt>
 * <dd>string, readwrite. Artist name, automatically set depending on the Cloud Drive in use, if not set.</dd>
 * <dt>album:</dt>
 * <dd>string, readwrite. Album name, automatically set depending on the Cloud Drive in use if not set.</dd>
 * <dt>genre:</dt>
 * <dd>string, readwrite. Genre name, automatically set depending on the Cloud Drive in use if not set.</dd>
 * <dt>release_year:</dt>
 * <dd>integer, readwrite. Four integers, represents the year when the Media File has been released.</dd>
 * <dt>title:</dt>
 * <dd>string, readwrite. The title of this Media File.</dd>
 * <dt>album_artist:</dt>
 * <dd>string, readwrite. Album Artist name. Useful to differentiate between compilations.</dd>
 * <dt>composer:</dt>
 * <dd>string, readwrite. Composer name.</dd>
 * <dt>comment:</dt>
 * <dd>string, readwrite. Custom comment.</dd>
 * <dt>len_str:</dt>
 * <dd>string, readonly. Formatted as "3:04" to ease display in views. Represents the duration of the media file.</dd>
 * <dt>len_int:</dt>
 * <dd>integer, readonly. Represents the duration in seconds of the media file.</dd>
 * <dt>position:</dt>
 * <dd>integer, readwrite. Represents the position this Media File occupy in a collection/album (track's number).</dd>
 * <dt>filename:</dt>
 * <dd>string, readonly. File name indicating where the file has been stored on our systems.</dd>
 * <dt>loved:</dt>
 * <dd>boolean, readwrite. Indicates if this Media File is 'loved' by its owner or not. Default is false.</dd>
 * <dt>disc_number:</dt>
 * <dd>integer, readwrite. Represents the number of the disc in a multi collection set.</dd>
 * <dt>mime:</dt>
 * <dd>string, readonly. Formatted such as "audio/mpeg", represents the recognized mime type for this file. This value can help some client to better recognize the type of file to be streamed.</dd>
 * <dt>remote_path:</dt>
 * <dd>string, readonly. Used along with some Cloud Drives to help the platform identifying where this file is stored for retrieval.</dd>
 * <dt>source:</dt>
 * <dd>string, readonly. Represents where this Media File comes from. Possible values are: 'cloud', 'local', 'dropbox', 'skydrive', 'box', 'gdrive', 'youtube', 'soundcloud', 'ubuntu'.</dd>
 * <dt>share_token:</dt>
 * <dd>string, readonly. Shared token across multiple Media Files with the same artist/album combo that builds up a Share URL, such as: http://audiobox.fm/share/cfa4b55e1736415b491147f041b95392.</dd>
 * <dt>artwork:</dt>
 * <dd>
 * string, readwrite. Represents the path for the Artwork of a Media File on the HTTP(S) CDN
 * <strong>m.audiobox.fm.</strong>
 * 256x256. 1.5MB Max. PNG/JPEG/GIF.
 * </dd>
 * <dt>size:</dt>
 * <dd>integer, readonly. Physical size of the Media File, in bytes.</dd>
 * <dt>hash:</dt>
 * <dd>string, readonly. MD5 Hash of the Media File.</dd>
 * <dt>video_bitrate:</dt>
 * <dd>string, readonly. Indicates the bitrate in case of VideoFile.</dd>
 * <dt>video_codec:</dt>
 * <dd>string, readonly. Indicates the codec in case of VideoFile.</dd>
 * <dt>video_resolution:</dt>
 * <dd>string, readonly. Indicates the resolution in case of VideoFile.</dd>
 * <dt>video_fps:</dt>
 * <dd>string, readonly. Indicates the frame per second in case of VideoFile.</dd>
 * <dt>video_aspect:</dt>
 * <dd>string, readonly. Indicates the aspect ratio in case of VideoFile.</dd>
 * <dt>video_container:</dt>
 * <dd>string, readonly. Indicates the container in case of VideoFile.</dd>
 * <dt>audio_bitrate:</dt>
 * <dd>string, readonly. Indicates the bitrate in case of AudioFile or the first audio track in a VideoFile.</dd>
 * <dt>audio_codec:</dt>
 * <dd>string, readonly. Indicates the codec in case of AudioFile or the first audio track in a VideoFile.</dd>
 * <dt>audio_sample_rate:</dt>
 * <dd>string, readonly. Indicates the sample rate in case of AudioFile or the first audio track in a VideoFile.</dd>
 * </dl>
 */
public class MediaFile {

  private Logger logger = LoggerFactory.getLogger( MediaFile.class );

  private static final String PATH = "/api/v1/media_files/" + ModelUtil.TOKEN_PLACEHOLDER + ".json";

  private static final String SYNC_PATH = "/api/v1/media_files/" + ModelUtil.TOKEN_PLACEHOLDER + "/sync.json";

  @Key
  private String type;

  @Key
  private String token;

  @Key
  private String artist;

  @Key
  private String album;

  @Key
  private String genre;

  @Key
  private int release_year;

  @Key
  private String title;

  @Key
  private String len_str;

  @Key
  private int len_int;

  @Key
  private int position;

  @Key
  private String filename;

  @Key
  private boolean loved;

  @Key
  private int disc_number;

  @Key
  private String mime;

  @Key
  private String remote_path;

  @Key
  private String source;

  @Key
  private String share_token;

  @Key
  private String artwork;

  @Key
  private long size;

  @Key
  private String album_artist;

  @Key
  private String hash;

  @Key
  private String composer;

  @Key
  private String comment;

  @Key
  private String video_bitrate;

  @Key
  private String video_codec;

  @Key
  private String video_resolution;

  @Key
  private String video_fps;

  @Key
  private String video_aspect;

  @Key
  private String video_container;

  @Key
  private String audio_bitrate;

  @Key
  private String audio_codec;

  @Key
  private String audio_sample_rate;


  public static Set<String> getAllAvailableFields() {
    Set<String> fields = new HashSet<>();
    for( Field f : MediaFile.class.getDeclaredFields()) {
      if (f.isAnnotationPresent( Key.class )) {
        fields.add( f.getName() );
      }
    }
    return fields;
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
   * Gets token.
   *
   * @return the token
   */
  public String getToken() {
    return this.token;
  }


  /**
   * Gets artist.
   *
   * @return the artist
   */
  public String getArtist() {
    return this.artist;
  }


  /**
   * Gets album.
   *
   * @return the album
   */
  public String getAlbum() {
    return this.album;
  }


  /**
   * Gets genre.
   *
   * @return the genre
   */
  public String getGenre() {
    return this.genre;
  }


  /**
   * Gets release year.
   *
   * @return the release year
   */
  public int getReleaseYear() {
    return this.release_year;
  }


  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }


  /**
   * Gets len str.
   *
   * @return the len str
   */
  public String getLenStr() {
    return this.len_str;
  }


  /**
   * Gets len int.
   *
   * @return the len int
   */
  public int getLenInt() {
    return this.len_int;
  }


  /**
   * Gets position.
   *
   * @return the position
   */
  public int getPosition() {
    return this.position;
  }


  /**
   * Gets filename.
   *
   * @return the filename
   */
  public String getFilename() {
    return this.filename;
  }


  /**
   * Is loved.
   *
   * @return the boolean
   */
  public boolean isLoved() {
    return this.loved;
  }


  /**
   * Gets disc number.
   *
   * @return the disc number
   */
  public int getDiscNumber() {
    return this.disc_number;
  }


  /**
   * Gets mime.
   *
   * @return the mime
   */
  public String getMime() {
    return this.mime;
  }


  /**
   * Gets remote path.
   *
   * @return the remote path
   */
  public String getRemotePath() {
    return this.remote_path;
  }


  /**
   * Gets source.
   *
   * @return the source
   */
  public String getSource() {
    return this.source;
  }


  /**
   * Gets share token.
   *
   * @return the share token
   */
  public String getShareToken() {
    return this.share_token;
  }


  /**
   * Gets artwork.
   *
   * @return the artwork
   */
  public String getArtwork() {
    return this.artwork;
  }


  /**
   * Gets size.
   *
   * @return the size
   */
  public long getSize() {
    return this.size;
  }


  /**
   * Gets album artist.
   *
   * @return the album artist
   */
  public String getAlbumArtist() {
    return this.album_artist;
  }


  /**
   * Gets hash.
   *
   * @return the hash
   */
  public String getHash() {
    return this.hash;
  }


  /**
   * Gets composer.
   *
   * @return the composer
   */
  public String getComposer() {
    return this.composer;
  }


  /**
   * Gets comment.
   *
   * @return the comment
   */
  public String getComment() {
    return this.comment;
  }


  /**
   * Gets video bitrate.
   *
   * @return the video bitrate
   */
  public String getVideoBitrate() {
    return this.video_bitrate;
  }


  /**
   * Gets video codec.
   *
   * @return the video codec
   */
  public String getVideoCodec() {
    return this.video_codec;
  }


  /**
   * Gets video resolution.
   *
   * @return the video resolution
   */
  public String getVideoResolution() {
    return this.video_resolution;
  }


  /**
   * Gets video fps.
   *
   * @return the video fps
   */
  public String getVideoFps() {
    return this.video_fps;
  }


  /**
   * Gets video aspect.
   *
   * @return the video aspect
   */
  public String getVideoAspect() {
    return this.video_aspect;
  }


  /**
   * Gets video container.
   *
   * @return the video container
   */
  public String getVideoContainer() {
    return this.video_container;
  }


  /**
   * Gets audio bitrate.
   *
   * @return the audio bitrate
   */
  public String getAudioBitrate() {
    return this.audio_bitrate;
  }


  /**
   * Gets audio codec.
   *
   * @return the audio codec
   */
  public String getAudioCodec() {
    return this.audio_codec;
  }


  /**
   * Gets audio sample rate.
   *
   * @return the audio sample rate
   */
  public String getAudioSampleRate() {
    return this.audio_sample_rate;
  }

}
