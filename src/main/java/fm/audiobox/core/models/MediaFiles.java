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
 * Used to get a list of all media files in a specific user's playlist.
 */
public class MediaFiles {


  public static final String PARAM_SET = "set";

  public static final String PARAM_SINCE = "since";


  @Key("media_files")
  private List<MediaFile> media_files;



  /**
   * Gets playlists.
   *
   * @return the playlists collection.
   */
  public List<MediaFile> getMediaFiles() {
    return media_files;
  }
}
