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


import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.util.ArrayMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * This exception is the parent exception for:
 * <ul>
 * <li>{@link AuthorizationException}</li>
 * <li>{@link ValidationException}</li>
 * </ul>
 * It is used for those exception risen by responses given by the service
 * that bring some information on what gone wrong.
 */
public class RemoteMessageException extends AudioBoxException {

  private int statusCode = HttpStatusCodes.STATUS_CODE_UNAUTHORIZED;

  private Errors errors;


  /**
   * Instantiates a new Remote message exception.
   *
   * @param response the response
   */
  public RemoteMessageException(HttpResponse response) {
    this( RemoteMessageException.parseErrors( response ), response.getStatusCode() );
  }


  /**
   * Instantiates a new Validation exception.
   *
   * @param errors the errors mapping
   */
  public RemoteMessageException(Errors errors) {
    this( RemoteMessageException.firstErrorToString( errors ), errors );
  }


  /**
   * Instantiates a new Validation exception.
   *
   * @param message the message
   * @param errors  the errors mapping
   */
  public RemoteMessageException(String message, Errors errors) {
    super( message );
    this.errors = errors;
  }


  /**
   * Instantiates a new Validation exception.
   *
   * @param errors     the errors
   * @param statusCode the status code
   */
  public RemoteMessageException(Errors errors, int statusCode) {
    this( errors );
    this.statusCode = statusCode;
  }


  @Override
  public String getMessage() {
    return this.toString();
  }


  /**
   * Gets error code (typically the HTTP STATUS).
   *
   * @return the error code
   */
  public int getErrorCode() {
    return statusCode;
  }


  /**
   * Gets the errors mapping.
   *
   * @return the errors
   */
  public Errors getErrors() {
    if (errors == null) {
      errors = buildEmptyErrors();
    }
    return errors;
  }


  /**
   * Transforms error mapping into strings.
   *
   * @return the string
   */
  public String toString() {
    return errorsToString( getErrors() );
  }


  /**
   * Transforms error mapping into strings.
   *
   * @param errors the errors mapping
   *
   * @return the string
   */
  public static String errorsToString(Errors errors) {
    String msg = "";
    for ( Map.Entry<String, Object> error : errors.getUnknownKeys().entrySet() ) {
      msg += errorToString( error ) + "\n";
    }
    return msg;
  }


  /**
   * Transform error set into a string.
   *
   * @param error an error in form of a {@link java.util.Map.Entry}
   *
   * @return a single string containing the error.
   */
  private static String errorToString(Map.Entry<String, Object> error) {
    return error.getKey() + ": " + error.getValue();
  }




  /* =============== */
  /* Private methods */
  /* =============== */


  /**
   * Parse errors.
   *
   * @param response the response
   *
   * @return the errors
   */
  private static Errors parseErrors(HttpResponse response) {
    try {
      return response.parseAs( ErrorsWrapper.class ).getErrors();
    } catch ( Exception e ) {
      // Catchall
      return buildEmptyErrors();
    }
  }


  /**
   * Build empty errors.
   *
   * @return the errors
   */
  private static Errors buildEmptyErrors() {
    Map<String, Object> err = new HashMap<>();
    err.put( "No message", new Object() );
    Errors errors = new Errors();
    errors.setUnknownKeys( err );
    return errors;
  }


  /**
   * First error to string.
   *
   * @param errors the errors
   *
   * @return the string
   */
  private static String firstErrorToString(Errors errors) {
    for ( Map.Entry<String, Object> error : errors.getUnknownKeys().entrySet() ) {
      return errorToString( error );
    }
    return null;
  }
}
