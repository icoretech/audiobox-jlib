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

package com.google.api.client.auth;

import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Objects;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * This credentials are a fix of Google Credential, since
 * the regular expression used for bearer check is actually wrong.
 * <p/>
 * See: https://code.google.com/p/google-oauth-java-client/issues/detail?id=88&colspec=Milestone%20Priority%20Component%20Type%20Summary%20ID%20Status%20Owner
 * <p/>
 * With this class we fix this issue.
 */
public class Credential extends com.google.api.client.auth.oauth2.Credential {

  static final Logger LOGGER = Logger.getLogger( Credential.class.getName() );

  static final String HEADER_PREFIX = "Bearer ";

  static final Pattern INVALID_TOKEN_ERROR = Pattern.compile( "\\s*error\\s*=\\s*\"?invalid_token\"?" );

  /**
   * Access token issued by the authorization server.
   */
  private String accessToken;

  /**
   * Lock on the token response information.
   */
  private final Lock lock = new ReentrantLock();


  public Credential(Builder builder) {
    super( builder );
  }


  /**
   * {@inheritDoc}
   * <p>
   * Default implementation checks if {@code WWW-Authenticate} exists and contains a "Bearer" value
   * (see <a href="http://tools.ietf.org/html/rfc6750#section-3.1">rfc6750 section 3.1</a> for more
   * details). If so, it calls {@link #refreshToken} in case the error code contains
   * {@code invalid_token}. If there is no "Bearer" in {@code WWW-Authenticate} and the status code
   * is {@link com.google.api.client.http.HttpStatusCodes#STATUS_CODE_UNAUTHORIZED} it calls {@link #refreshToken}. If
   * {@link #executeRefreshToken()} throws an I/O exception, this implementation will log the
   * exception and return {@code false}. Subclasses may override.
   * </p>
   */
  public boolean handleResponse(HttpRequest request, HttpResponse response, boolean supportsRetry) {
    boolean refreshToken = false;
    boolean bearer = false;

    List<String> authenticateList = response.getHeaders().getAuthenticateAsList();

    // if authenticate list is not null we will check if one of the entries contains "Bearer"
    if ( authenticateList != null ) {
      for ( String authenticate : authenticateList ) {
        if ( authenticate.startsWith( HEADER_PREFIX ) ) {
          // mark that we found a "Bearer" value, and check if there is a invalid_token error
          bearer = true;
          refreshToken = INVALID_TOKEN_ERROR.matcher( authenticate ).find();
          break;
        }
      }
    }

    // if "Bearer" wasn't found, we will refresh the token, if we got 401
    if ( !bearer ) {
      refreshToken = response.getStatusCode() == HttpStatusCodes.STATUS_CODE_UNAUTHORIZED;
    }

    if ( refreshToken ) {
      try {
        lock.lock();
        try {
          // need to check if another thread has already refreshed the token
          return !Objects.equal( getAccessToken(), getMethod().getAccessTokenFromRequest( request ) ) || refreshToken();
        } finally {
          lock.unlock();
        }
      } catch ( IOException exception ) {
        LOGGER.log( Level.SEVERE, "unable to refresh token", exception );
      }
    }
    return false;
  }


  /**
   * Sets the access token.
   * <p/>
   * <p>
   * Overriding is only supported for the purpose of calling the super implementation and changing
   * the return type, but nothing else.
   * </p>
   *
   * @param accessToken access token or {@code null} for none
   */
  public Credential setAccessToken(String accessToken) {
    lock.lock();
    try {
      this.accessToken = accessToken;
      super.setAccessToken( accessToken );
    } finally {
      lock.unlock();
    }
    return this;
  }


  public static class Builder extends com.google.api.client.auth.oauth2.Credential.Builder {

    /**
     * @param method method of presenting the access token to the resource server (for example
     */
    public Builder(AccessMethod method) {
      super( method );
    }


    /**
     * Returns a new credential instance.
     */
    public Credential build() {
      return new Credential( this );
    }


    public Builder setTransport(HttpTransport transport) {
      super.setTransport( transport );
      return this;
    }


    public Builder setJsonFactory(JsonFactory jsonFactory) {
      super.setJsonFactory( jsonFactory );
      return this;
    }


    public Builder setTokenServerUrl(GenericUrl tokenServerUrl) {
      super.setTokenServerUrl( tokenServerUrl );
      return this;
    }


    public Builder setClientAuthentication(HttpExecuteInterceptor clientAuthentication) {
      super.setClientAuthentication( clientAuthentication );
      return this;
    }


    public Builder addRefreshListener(CredentialRefreshListener refreshListener) {
      super.addRefreshListener( refreshListener );
      return this;
    }
  }

}