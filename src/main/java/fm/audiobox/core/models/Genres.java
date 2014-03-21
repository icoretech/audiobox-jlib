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
import fm.audiobox.core.utils.ModelUtil;

import java.util.List;

/**
 * Genres wrapper model.
 * <p/>
 * This is a simple wrapper model to handle genre-regrouped media files.
 */
public class Genres {

  private static final String PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files/genres.json";

  @Key
  protected List<? extends Genre> genres;


  /**
   * Use this method to get the unique path of the genre.
   *
   * @param playlistToken the token of the playlist to query for the genre.
   *
   * @return the path of the genres of this playlist
   */
  public static String getPath(String playlistToken) {
    return ModelUtil.interpolate( PATH, playlistToken );
  }


  /**
   * Gets genres.
   *
   * @return the genres
   */
  public List<? extends Genre> getGenres() {
    return this.genres;
  }
}
