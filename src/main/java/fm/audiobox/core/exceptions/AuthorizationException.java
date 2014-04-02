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
