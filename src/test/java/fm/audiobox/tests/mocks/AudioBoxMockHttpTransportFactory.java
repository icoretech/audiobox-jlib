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
import com.google.api.client.json.JsonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import fm.audiobox.core.exceptions.AuthorizationException;
import fm.audiobox.core.exceptions.Errors;
import fm.audiobox.core.utils.HttpStatus;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;


/**
 * Created by keytwo on 22/01/14.
 */
public class AudioBoxMockHttpTransportFactory {

  //private static final HttpTransport = new NetHttpTransport();


  /**
   * Gets wrong account http transport.
   *
   * @return the wrong account http transport
   */
  public static HttpTransport getWrongAccountHttpTransport() {
    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/oauth2/invalid_account.json" ), "UTF-8" ) );
            result.setStatusCode( HttpStatus.SC_BAD_REQUEST );
            return result;
          }
        };
      }
    };
  }


  /**
   * Gets invalid refresh token http transport.
   *
   * @param factory the factory
   * @return the invalid refresh token http transport
   */
  public static HttpTransport getInvalidRefreshTokenHttpTransport(final JsonFactory factory) {
    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            Errors e = factory.fromInputStream( this.getClass().getResourceAsStream( "/responses/oauth2/invalid_refresh_token.json" ), Charset.defaultCharset(), Errors.class );
            throw new AuthorizationException( e, HttpStatus.SC_BAD_REQUEST );
          }
        };
      }

    };
  }


  /**
   * Gets right account http transport.
   *
   * @return the right account http transport
   */
  public static HttpTransport getRightAccountHttpTransport() {
    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/oauth2/token.json" ), "UTF-8" ) );
            return result;
          }
        };
      }
    };
  }


  /**
   * Gets right user http transport.
   *
   * @return the right user http transport
   */
  public static HttpTransport getRightUserHttpTransport() {
    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/user.json" ), "UTF-8" ) );
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
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setStatusCode( HttpStatus.SC_NOT_FOUND );
            return result;
          }
        };
      }
    };
  }


  /**
   * Gets notifications http transport.
   *
   * @return the notifications http transport
   */
  public static HttpTransport getNotificationsHttpTransport() {
    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/notifications.json" ), "UTF-8" ) );
            return result;
          }
        };
      }
    };
  }


  /**
   * Gets two o four http transport.
   *
   * @return the two o four http transport
   */
  public static HttpTransport getTwoOFourHttpTransport() {
    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setStatusCode( HttpStatus.SC_NO_CONTENT );
            return result;
          }
        };
      }
    };
  }


  /**
   * Gets malformed http transport.
   *
   * @return the malformed http transport
   */
  public static HttpTransport getMalformedHttpTransport() {
    return new MockHttpTransport() {
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setStatusCode( HttpStatus.SC_UNAUTHORIZED );
            result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/oauth2/auth_exception.json" ), "UTF-8" ) );
            return result;
          }
        };
      }
    };
  }
}
