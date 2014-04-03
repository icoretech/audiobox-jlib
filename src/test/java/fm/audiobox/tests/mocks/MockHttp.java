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

package fm.audiobox.tests.mocks;


import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.Json;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import fm.audiobox.core.utils.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by keytwo on 22/01/14.
 */
public class MockHttp {

  protected static Logger l = LoggerFactory.getLogger( MockHttp.class );


  /**
   * Gets transport.
   *
   * @return the transport
   */
  public static HttpTransport getTransport() {
    return getTransport( HttpStatus.SC_OK );
  }


  /**
   * Gets transport.
   *
   * @param responseFilePath the response file path
   *
   * @return the transport
   */
  public static HttpTransport getTransport(final String responseFilePath) {
    return getTransport( HttpStatus.SC_OK, responseFilePath );
  }


  /**
   * Gets transport.
   *
   * @param status the status
   *
   * @return the transport
   */
  public static HttpTransport getTransport(final int status) {
    return getTransport( status, null );
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
    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(String method, final String url) throws IOException {
        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {

            String fileName = responseFilePath;

            if ( fileName == null ) {
              Pattern p = Pattern.compile( "^.*(/api/v1|\\:443)/" );
              Matcher m = p.matcher( url );
              fileName = m.replaceAll( "" );
            }

            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setStatusCode( status );
            result.setContentType( Json.MEDIA_TYPE );

            if ( !fileName.endsWith( ".json" ) ) {
              fileName += ".json";
            }

            try {
              result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/" + fileName ), "UTF-8" ) );
            } catch ( NullPointerException e ) {
              l.warn( "Requested resource not found: " + fileName );
              l.warn( "Try to perform the request on:  " + url);
            }

            if ( HttpStatus.SC_NOT_FOUND == status ) {
              result.setReasonPhrase( "Not Found" );
            }


            return result;
          }
        };
      }
    };
  }


  /**
   * Gets playlists transport.
   *
   * @return the playlists transport
   */
  public static HttpTransport get404() {
    return getTransport( HttpStatus.SC_NOT_FOUND );
  }


  /**
   * Gets two o four http transport.
   *
   * @return the two o four http transport
   */
  public static HttpTransport get204() {
    return getTransport( HttpStatus.SC_NO_CONTENT );
  }


  /**
   * Gets wrong account http transport.
   *
   * @return the wrong account http transport
   */
  public static HttpTransport getWrongAccountHttpTransport() {
    return getTransport( HttpStatus.SC_BAD_REQUEST );
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
    return getTransport( HttpStatus.SC_UNAUTHORIZED );
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

}
