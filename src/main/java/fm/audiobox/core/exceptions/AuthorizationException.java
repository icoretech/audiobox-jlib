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


/**
 * Created by keytwo on 22/01/14.
 */
public class AuthorizationException extends RemoteMessageException {

  /**
   * Instantiates a new Authorization exception.
   *
   * @param ex the ex
   */
  public AuthorizationException(TokenResponseException ex) {
    super( AuthorizationException.buildErrors( ex.getDetails() ), ex.getStatusCode() );
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
   * @return the errors
   */
  private static Errors buildErrors(TokenErrorResponse rsp) {
    Errors e = new Errors();
    e.set( rsp.getError(), rsp.getErrorDescription() );
    return e;
  }
}
