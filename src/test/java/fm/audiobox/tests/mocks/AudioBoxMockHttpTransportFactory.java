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

import java.io.IOException;


/**
 * Created by keytwo on 22/01/14.
 */
public class AudioBoxMockHttpTransportFactory {


  /**
   * Gets transport.
   *
   * @return the transport
   */
  public static HttpTransport getTransport() {
    return getTransport( HttpStatus.SC_NO_CONTENT );
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
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();

            result.setContentType( Json.MEDIA_TYPE );
            if ( responseFilePath != null ) {
              try {
                result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( responseFilePath ), "UTF-8" ) );
              } catch ( IOException | NullPointerException e ) {
                e.printStackTrace();
                result.setStatusCode( HttpStatus.SC_NOT_FOUND );
              }
            }

            result.setStatusCode( status );

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
  public static HttpTransport getFourOFourTransport() {
    return getTransport( HttpStatus.SC_NOT_FOUND );
  }


  /**
   * Gets two o four http transport.
   *
   * @return the two o four http transport
   */
  public static HttpTransport getTwoOFourHttpTransport() {
    return getTransport();
  }


  /**
   * Gets wrong account http transport.
   *
   * @return the wrong account http transport
   */
  public static HttpTransport getWrongAccountHttpTransport() {
    return getTransport( HttpStatus.SC_BAD_REQUEST, "/responses/oauth2/invalid_account.json" );
  }


  /**
   * Gets invalid refresh token http transport.
   *
   * @return the invalid refresh token http transport
   */
  public static HttpTransport getInvalidRefreshTokenHttpTransport() {
    return getTransport( HttpStatus.SC_BAD_REQUEST, "/responses/oauth2/invalid_refresh_token.json" );
  }


  /**
   * Gets right account http transport.
   *
   * @return the right account http transport
   */
  public static HttpTransport getRightAccountHttpTransport() {
    return getTransport( "/responses/oauth2/token.json" );
  }


  /**
   * Gets right user http transport.
   *
   * @return the right user http transport
   */
  public static HttpTransport getRightUserHttpTransport() {
    return getTransport( "/responses/user.json" );
  }


  /**
   * Gets notifications http transport.
   *
   * @return the notifications http transport
   */
  public static HttpTransport getNotificationsHttpTransport() {
    return getTransport( "/responses/notifications.json" );
  }


  /**
   * Gets malformed http transport.
   *
   * @return the malformed http transport
   */
  public static HttpTransport getMalformedHttpTransport() {
    return getTransport( HttpStatus.SC_UNAUTHORIZED, "/responses/oauth2/auth_exception.json" );
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
      return getTransport( HttpStatus.SC_CONFLICT, "/responses/upload_409.json" );
    } else {
      return getTransport( HttpStatus.SC_CREATED, "/responses/upload_202.json" );
    }
  }


  /**
   * Gets playlists transport.
   *
   * @return the playlists transport
   */
  public static HttpTransport getPlaylistsTransport() {
    return getTransport( "/responses/playlists.json" );
  }
}
