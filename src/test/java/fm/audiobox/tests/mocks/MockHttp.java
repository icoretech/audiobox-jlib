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

package fm.audiobox.tests.mocks;


import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.testing.json.MockJsonFactory;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MockHttp {

  protected static Logger l = LoggerFactory.getLogger( MockHttp.class );


  /**
   * Gets transport.
   *
   * @return the transport
   */
  public static HttpTransport getTransport() {
    return getTransport( HttpStatus.SC_OK, null );
  }

  /**
   * Gets transport.
   *
   * @param status           the status
   * @param responseFilePath the response file path
   *
   * @return the transport
   */
  public static HttpTransport getTransport(final int status, final String responseFilePath) {

    if (Configuration.Env.development != AudioBoxTests.env) {
      return new NetHttpTransport();
    }

    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) throws IOException {


        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {

            String fileName = responseFilePath;

            if ( fileName == null ) {
              Pattern p = Pattern.compile( "^(.*\\:(80|443|3000|5000))?(.*/api/v1)?/" );
              Matcher m = p.matcher( url );
              fileName = m.replaceAll( "" );
            }


            Pattern queryString = Pattern.compile( "\\?.*$" );
            Matcher qStringMatcher = queryString.matcher( fileName );
            boolean hasQueryString = false;
            if ( qStringMatcher.find() ) {
              hasQueryString = true;
              fileName = qStringMatcher.replaceAll( "" );
            }

            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setStatusCode( status );

            if ( HttpMethods.DELETE.equals( method ) ) {
              result.setStatusCode( HttpStatus.SC_NO_CONTENT );
            }

            result.setContentType( Json.MEDIA_TYPE );

            if ( !hasQueryString && !fileName.endsWith( ".json" ) && !fileName.contains( "daemon" )) {
              fileName += ".json";
            }

            if ( url.endsWith( "sync.json" ) ) {
              Pattern p = Pattern.compile( "000_(dropbox|ubuntu|soundcloud)" );
              Matcher m = p.matcher( url );
              if ( m.find() ) {
                result.setStatusCode( HttpStatus.SC_FORBIDDEN );
              } else {
                result.setStatusCode( HttpStatus.SC_NO_CONTENT );
              }
              return result;
            }

            if ( HttpMethods.PUT.equals( method ) ) {
              if ( url.endsWith( "000_cloud.json" ) ) {
                result.setStatusCode( HttpStatus.SC_NOT_FOUND );
              }
            }

            // "since" param on get playlist media files
            if (url.contains( "since=" )) {
              fileName = "playlists/000_cloud/media_files_since.json";
            }


            try {
              result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/" + fileName ), "UTF-8" ) );
            } catch ( NullPointerException e ) {
              l.warn( "Requested resource not found: /responses/" + fileName );
              l.warn( "Try to perform the request on:  " + url );
              result.setStatusCode( HttpStatus.SC_NOT_FOUND );
            }

            if ( HttpStatus.SC_NOT_FOUND == result.getStatusCode() ) {
              result.setReasonPhrase( "Not Found" );
            }

            if (HttpStatus.SC_INTERNAL_SERVER_ERROR == result.getStatusCode()) {
              result.setReasonPhrase( "Internal Server Error" );
            }

            if (HttpStatus.SC_SERVICE_UNAVAILABLE == result.getStatusCode()) {
              result.setReasonPhrase( "Service Unavailable" );
              result.addHeader( "Retry-After", "10" );
            }

            return result;
          }
        };
      }
    };
  }


  /**
   * Gets wrong account http transport.
   *
   * @return the wrong account http transport
   */
  public static HttpTransport getWrongAccountHttpTransport() {
    return getTransport( HttpStatus.SC_BAD_REQUEST, "oauth2/invalid_account.json" );
  }


  /**
   * Gets invalid refresh token http transport.
   *
   * @return the invalid refresh token http transport
   */
  public static HttpTransport getInvalidRefreshTokenHttpTransport() {
    return getTransport( HttpStatus.SC_BAD_REQUEST, "oauth2/invalid_refresh_token" );
  }


  /**
   * Gets malformed http transport.
   *
   * @return the malformed http transport
   */
  public static HttpTransport getMalformedHttpTransport() {
    return getTransport( HttpStatus.SC_UNAUTHORIZED, "oauth2/auth_exception.json" );
  }


  /**
   * Gets upload transport.
   *
   * @param error the error
   *
   * @return the upload transport
   */
  public static HttpTransport getUploadTransport(final boolean error) {
    if ( error ) {
      return getTransport( HttpStatus.SC_CONFLICT, "upload_409" );
    } else {
      return getTransport( HttpStatus.SC_CREATED, "upload_202" );
    }
  }


  /**
   * Gets daemon not running transport.
   *
   * @return the daemon not running transport
   */
  public static HttpTransport getDaemonNotRunningTransport() {
    return getTransport( HttpStatus.SC_BAD_GATEWAY, "daemon/keepalive_not_ready");
  }

}
