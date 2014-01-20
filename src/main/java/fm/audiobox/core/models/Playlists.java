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
 * Created by keytwo on 20/01/14.
 */
public class Playlists {

  private static final String PATH = "/api/v1/playlists.json";

  @Key("playlists")
  private List<Playlist> playlists;


  /**
   * Gets path.
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


