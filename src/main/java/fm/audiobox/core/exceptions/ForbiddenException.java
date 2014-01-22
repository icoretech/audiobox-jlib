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

/**
 * This exception is thrown when the user tries to access
 * a resource he's not allowed to access, because of ownership
 * or account state.
 */
public class ForbiddenException extends AudioBoxException {

  private int statusCode;

  private HttpResponse response;


  /**
   * Instantiates a new Forbidden exception.
   *
   * @param response the response
   */
  public ForbiddenException(HttpResponse response) {
    super( response.getStatusMessage() );
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
  public int getStatusCode() {
    return statusCode;
  }
}
