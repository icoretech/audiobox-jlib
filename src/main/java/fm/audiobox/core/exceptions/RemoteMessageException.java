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

package fm.audiobox.core.exceptions;


import com.google.api.client.http.HttpStatusCodes;

import java.util.Map;


/**
 * Created by keytwo on 22/01/14.
 */
public class RemoteMessageException extends RuntimeException {

  private int statusCode = HttpStatusCodes.STATUS_CODE_UNAUTHORIZED;

  private Errors errors;


  /**
   * Instantiates a new Validation exception.
   *
   * @param errors the error
   */
  public RemoteMessageException(Errors errors) {
    this( RemoteMessageException.firstErrorToString( errors ), errors );
  }


  /**
   * Instantiates a new Validation exception.
   *
   * @param message the message
   * @param errors the errors
   */
  public RemoteMessageException(String message, Errors errors) {
    super( message );
    this.errors = errors;
  }


  /**
   * Instantiates a new Validation exception.
   *
   * @param errors the errors
   * @param statusCode the status code
   */
  public RemoteMessageException(Errors errors, int statusCode) {
    this( errors );
    this.statusCode = statusCode;
  }


  @Override
  public String getMessage() {
    return super.getMessage();
  }


  /**
   * Gets error code.
   *
   * @return the error code
   */
  public int getErrorCode() {
    return statusCode;
  }


  /**
   * Gets errors.
   *
   * @return the errors
   */
  public Errors getErrors() {
    return errors;
  }


  /**
   * Errors to string.
   *
   * @param errors the errors
   * @return the string
   */
  public static String errorsToString(Errors errors) {
    String msg = "";
    for ( Map.Entry<String, Object> error : errors.getUnknownKeys().entrySet() ) {
      msg += error.getKey() + ": " + error.getValue() + "\n";
    }
    return msg;
  }




  /* =============== */
  /* Private methods */
  /* =============== */


  /**
   * First error to string.
   *
   * @param errors the errors
   * @return the string
   */
  private static String firstErrorToString(Errors errors) {
    String msg = "";
    for ( Map.Entry<String, Object> error : errors.getUnknownKeys().entrySet() ) {
      msg = error.getKey() + ": " + error.getValue();
      break;
    }
    return msg;
  }
}
