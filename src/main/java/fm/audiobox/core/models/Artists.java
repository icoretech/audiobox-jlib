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
 * Artists wrapper model.
 * <p/>
 * This is a simple wrapper model to handle artist-regrouped media files.
 */
public class Artists {

  private static final String PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files/artists.json";

  @Key
  protected List<? extends Artist> artists;


  /**
   * Use this method to get the unique path of the artist.
   *
   * @param playlistToken the token of the playlist to query for the artist.
   *
   * @return the path of the artists of this playlist
   */
  public static String getPath(String playlistToken) {
    return ModelUtil.interpolate( PATH, playlistToken );
  }


  /**
   * Gets artists.
   *
   * @return the artists
   */
  public List<? extends Artist> getArtists() {
    return this.artists;
  }
}
