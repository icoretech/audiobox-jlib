/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 * This file is part of the AudioBox-Jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.core.models;

import com.google.api.client.util.Key;

import java.util.List;

/**
 * Represent a regrouped media files by album.
 * <p/>
 * Also includes the same media file entities in other collection views, sorted by the media files' position attribute.
 */
public class Album {

  @Key
  private String token;

  @Key
  private String album;

  @Key
  private int release_year;

  @Key
  private String artwork;

  @Key
  private String artist;

  @Key
  private MediaFiles media_files;


  /**
   * Gets token.
   *
   * @return the token
   */
  public String getToken() {
    return token;
  }


  /**
   * Gets album.
   *
   * @return the album
   */
  public String getAlbum() {
    return album;
  }


  /**
   * Gets release year.
   *
   * @return the release year
   */
  public int getReleaseYear() {
    return release_year;
  }


  /**
   * Gets artwork.
   *
   * @return the artwork
   */
  public String getArtwork() {
    return artwork;
  }


  /**
   * Gets artist.
   *
   * @return the artist
   */
  public String getArtist() {
    return artist;
  }


  /**
   * Gets media files.
   *
   * @return the media files
   */
  public List<? extends MediaFile> getMediaFiles() {
    return media_files != null ? media_files.getMediaFiles() : null;
  }
}
