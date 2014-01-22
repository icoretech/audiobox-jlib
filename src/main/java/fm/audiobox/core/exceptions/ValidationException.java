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


/**
 * This exception is thrown when a POST or PUT request
 * rise a remote validation error.
 */
public class ValidationException extends RemoteMessageException {


  public ValidationException(Errors errors, int statusCode) {
    super( errors, statusCode );
  }
}
