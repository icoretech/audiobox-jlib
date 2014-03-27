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

import com.google.api.client.util.Key;

/**
 * This class is the {@link Errors} wrapper.
 */
public class ErrorsWrapper {

  @Key
  private Errors errors;

  @Key
  private Errors error;

  /**
   * Gets errors (generic type).
   *
   * @return the errors
   */
  public Errors getErrors() {
    return errors == null ? error : errors;
  }

}
