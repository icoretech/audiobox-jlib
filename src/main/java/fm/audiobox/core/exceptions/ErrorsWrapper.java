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
 * Created by keytwo on 21/01/14.
 */
public class ErrorsWrapper {

  @Key
  private Errors errors;


  /**
   * Gets errors.
   *
   * @return the errors
   */
  public Errors getErrors() {
    return errors;
  }
}
