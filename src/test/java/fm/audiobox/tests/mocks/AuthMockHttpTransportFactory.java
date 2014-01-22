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
public class AuthMockHttpTransportFactory {

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
   * Gets right account http transport.
   *
   * @return the right account http transport
   */
  public static HttpTransport getInvalidRefreshTokenHttpTransport() {
    return new MockHttpTransport() {

      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {

          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/oauth2/invalid_refresh_token.json" ), "UTF-8" ) );
            result.setStatusCode( HttpStatus.SC_BAD_REQUEST );
            return result;
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
}
