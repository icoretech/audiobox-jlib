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

import com.google.api.client.http.HttpResponse;

import java.io.IOException;

/**
 * <p>
 * Generic AudioBox exceptions.
 * </p>
 * <p>
 * This kind of exceptions are typically thrown when a problem with the remote service occurs.
 * </p>
 *
 * Managed remote handled errors are:
 * <dl>
 * <dt>HTTP Status 402:</dt>
 * <dd>Action requires a valid subscription ({@link ForbiddenException}).</dd>
 * <dt>HTTP Status 403:</dt>
 * <dd>Action requires a valid account link to the remote service ({@link ForbiddenException}).</dd>
 * <dt>HTTP Status 404:</dt>
 * <dd>Resource not found or immutable ({@link ResourceNotFoundException}).</dd>
 * <dt>HTTP Status 409:</dt>
 * <dd>Conflict occurred.</dd>
 * <dt>HTTP Status 422:</dt>
 * <dd>Validation errors ({@link ValidationException}).</dd>
 * <dt>HTTP Status 503:</dt>
 * <dd>Remote service unavailable (overload or system failure)</dd>
 * </dl>
 */
public class AudioBoxException extends IOException {

  private HttpResponse response;

  protected int statusCode;


  /**
   * Instantiates a new AudioBoxException.
   *
   * @param message the message
   */
  public AudioBoxException(String message) {
    super( message );
  }


  /**
   * Instantiates a new AudioBoxException exception starting from the response.
   *
   * @param response the response
   */
  public AudioBoxException(HttpResponse response) {
    this( response.getStatusMessage() );
    this.response = response;
    this.statusCode = response.getStatusCode();
  }


  /**
   * Gets response.
   *
   * @return the response
   */
  public HttpResponse getResponse() {
    return response;
  }


  /**
   * Gets status code.
   *
   * @return the status code
   */
  public int getErrorCode() {
    return statusCode;
  }
}
