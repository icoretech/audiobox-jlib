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
 * This class is used as simple {@link fm.audiobox.core.models.MediaFile} wrapper
 * for those JSON parser that do not support root elements.
 */
public class MediaFileWrapper {

  @Key
  private MediaFile media_file;


  /**
   * Gets the {@link fm.audiobox.core.models.MediaFile}.
   *
   * @return the wrapped media file
   */
  public MediaFile getMediaFile() {
    return media_file;
  }
}
