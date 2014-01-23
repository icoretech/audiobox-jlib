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

import java.util.List;

/**
 * Used to get a list of all the playlist in the user's account.
 */
public class Playlists {

  private static final String PATH = "/api/v1/playlists.json";

  @Key("playlists")
  private List<Playlist> playlists;


  /**
   * Gets the generic remote resource collection path.
   *
   * @return the path
   */
  public static String getPath() {
    return PATH;
  }


  /**
   * Gets playlists.
   *
   * @return the playlists collection.
   */
  public List<Playlist> getPlaylists() {
    return playlists;
  }

}


