/*
 * Copyright 2009-2014 iCoreTech, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.audiobox.core.exceptions;


import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * This exception is the parent exception for:
 * <ul>
 * <li>{@link fm.audiobox.core.exceptions.AuthorizationException}</li>
 * <li>{@link fm.audiobox.core.exceptions.ValidationException}</li>
 * <li>{@link fm.audiobox.core.exceptions.FileAlreadyUploaded}</li>
 * <li>{@link fm.audiobox.core.exceptions.SystemOverloadedException}</li>
 * </ul>
 * It is used for those exception risen by responses given by the service
 * that bring some information on what gone wrong.
 */
public class RemoteMessageException extends AudioBoxException {

  private int statusCode = HttpStatusCodes.STATUS_CODE_UNAUTHORIZED;

  private static final int NO_RESPONSE = -1;

  private Errors errors;


  /**
   * Instantiates a new Remote message exception.
   *
   * @param response the response
   */
  public RemoteMessageException(HttpResponse response) {
    this( RemoteMessageException.parseErrors( response ), response != null ? response.getStatusCode() : NO_RESPONSE );
  }


  /**
   * Instantiates a new Validation exception.
   *
   * @param errors     the errors
   * @param statusCode the status code
   */
  public RemoteMessageException(Errors errors, int statusCode) {
    super( RemoteMessageException.firstErrorToString( errors ) );
    this.errors = errors;
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
    if ( errors == null ) {
      errors = buildEmptyErrors( getErrorCode() );
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
   * @return the string
   */
  public static String errorsToString(Errors errors) {
    String msg = errors.toString();
    if ( StringUtils.EMPTY.equals( msg ) ) {
      for ( Map.Entry<String, Object> error : errors.getUnknownKeys().entrySet() ) {
        msg += errorToString( error ) + "\n";
      }
    }
    return msg;
  }


  /**
   * Transform error set into a string.
   *
   * @param error an error in form of a {@link java.util.Map.Entry}
   * @return a single string containing the error.
   */
  private static String errorToString(Map.Entry<String, Object> error) {
    return String.format( "%1s: %2s", error.getKey(), error.getValue() );
  }




  /* =============== */
  /* Private methods */
  /* =============== */


  /**
   * Parse errors.
   *
   * @param response the response
   * @return the errors
   */
  private static Errors parseErrors(HttpResponse response) {
    try {
      return response.parseAs( Errors.class );
    } catch ( Exception e ) {
      logger.warn( "Here's the 'Maytag(tm) repair man', we got something not expected: " + e.getMessage() );
      // Catchall, preserve status message
      return buildEmptyErrors( response == null ? NO_RESPONSE : response.getStatusCode() );
    }
  }


  /**
   * Build empty errors.
   *
   * @return the errors
   */
  private static Errors buildEmptyErrors(int statusMessage) {
    Map<String, Object> err = new HashMap<>();
    err.put( RemoteMessageException.class.getCanonicalName(), String.format( "Client got a remote error (%1d) but no message was given.", statusMessage ) );
    Errors errors = new Errors();
    errors.setUnknownKeys( err );
    return errors;
  }


  /**
   * Gets the first error and transforms it into a human readable string.
   *
   * @param errors the errors object
   * @return the message string or empty string if none found.
   */
  private static String firstErrorToString(Errors errors) {

    if ( errors == null ) {
      errors = RemoteMessageException.buildEmptyErrors( 0 );
    }

    for ( Map.Entry<String, Object> error : errors.getUnknownKeys().entrySet() ) {
      return errorToString( error );
    }

    return StringUtils.EMPTY;
  }
}
