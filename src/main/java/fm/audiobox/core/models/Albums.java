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
 * Albums wrapper model.
 * <p/>
 * This is a simple wrapper model to handle album-regrouped media files.
 */
public class Albums {

  private static final String PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files/albums.json";

  @Key
  protected List<? extends Album> albums;


  /**
   * Use this method to get the unique path of the album.
   *
   * @param playlistToken the token of the playlist to query for the album.
   *
   * @return the path of the albums of this playlist
   */
  public static String getPath(String playlistToken) {
    return ModelUtil.interpolate( PATH, playlistToken );
  }


  /**
   * Gets albums.
   *
   * @return the albums
   */
  public List<? extends Album> getAlbums() {
    return this.albums;
  }

}
