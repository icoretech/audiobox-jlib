/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 * This file is part of the audiobox-jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.core.models;

import com.google.api.client.util.Key;

/**
 * Created by keytwo on 20/01/14.
 */
public class PlaylistWrapper {

  @Key
  private Playlist playlist;


  /**
   * Gets playlist.
   *
   * @return the playlist
   */
  public Playlist getPlaylist() {
    return playlist;
  }
}
