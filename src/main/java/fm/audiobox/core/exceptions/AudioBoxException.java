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
 * <dd>Action requires a valid subscription ({@link fm.audiobox.core.exceptions.ForbiddenException}).</dd>
 * <dt>HTTP Status 403:</dt>
 * <dd>Action requires a valid account link to the remote service ({@link fm.audiobox.core.exceptions.ForbiddenException}).</dd>
 * <dt>HTTP Status 404:</dt>
 * <dd>Resource not found or immutable ({@link fm.audiobox.core.exceptions.ResourceNotFoundException}).</dd>
 * <dt>HTTP Status 409:</dt>
 * <dd>Conflict occurred ({@link fm.audiobox.core.exceptions.ForbiddenException}).</dd>
 * <dt>HTTP Status 422:</dt>
 * <dd>Validation errors ({@link fm.audiobox.core.exceptions.ValidationException}).</dd>
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
