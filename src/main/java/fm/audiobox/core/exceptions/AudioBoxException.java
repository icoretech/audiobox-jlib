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

package fm.audiobox.core.exceptions;

import java.io.IOException;

/**
 * Generic AudioBox exceptions.
 */
public class AudioBoxException extends IOException {

  /**
   * Instantiates a new Audio box exception.
   *
   * @param message the message
   */
  public AudioBoxException(String message) {
    super(message);
  }
}
