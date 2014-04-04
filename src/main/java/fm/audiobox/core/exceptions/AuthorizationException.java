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


import com.google.api.client.auth.oauth2.TokenErrorResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.http.HttpResponse;


/**
 * This exception is thrown when the OAuth2 token and/or the refresh token
 * have been invalidated or they are expired.
 * <p/>
 * When this exception is thrown a new user authentication is required.
 * <p/>
 * It is also highly recommended to invalidate/delete/clear any stored
 * credential.
 */
public class AuthorizationException extends RemoteMessageException {

  /**
   * Instantiates a new Authorization exception starting from the response.
   *
   * @param response the response
   */
  public AuthorizationException(HttpResponse response) {
    super( response );
  }


  /**
   * Instantiates a new Authorization exception.
   *
   * @param tokenException the TokenResponseException
   */
  public AuthorizationException(TokenResponseException tokenException) {
    this( AuthorizationException.buildErrors( tokenException.getDetails() ), tokenException.getStatusCode() );
  }


  /**
   * Instantiates a new Authorization exception.
   *
   * @param errors     the errors
   * @param statusCode the status code
   */
  public AuthorizationException(Errors errors, int statusCode) {
    super( errors, statusCode );
  }


  /**
   * Build errors.
   *
   * @param rsp the rsp
   *
   * @return the errors
   */
  private static Errors buildErrors(TokenErrorResponse rsp) {
    Errors e = new Errors();
    e.set( rsp.getError(), rsp.getErrorDescription() );
    return e;
  }
}
