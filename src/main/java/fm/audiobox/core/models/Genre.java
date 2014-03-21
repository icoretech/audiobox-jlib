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
 * Represent a regrouped media files by genre.
 * <p/>
 * Also includes the same media file entities in other collection views, sorted by the media files' artist, album and position attributes.
 */
public class Genre {

  @Key
  private String token;


  @Key
  private String genre;

  @Key
  private List<? extends MediaFile> media_files;


  /**
   * Gets genre.
   *
   * @return the genre
   */
  public String getGenre() {
    return genre;
  }


  /**
   * Gets token.
   *
   * @return the token
   */
  public String getToken() {
    return token;
  }


  /**
   * Gets media files.
   *
   * @return the media files
   */
  public List<? extends MediaFile> getMediaFiles() {
    return media_files;
  }

}
