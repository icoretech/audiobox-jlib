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
 * Generic AudioBox exceptions.
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
