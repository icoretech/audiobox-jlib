/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 * This file is part of the audiobox-jlib project.
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
 * Created by keytwo on 28/03/14.
 */
public class MediaFilesMockHttpTransportFactory {

  public static HttpTransport getMediaFileTransport(final String mediaFileToken) {
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, final String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );

            String fileName = "/responses/media_files/" + mediaFileToken + ".json";

            try {
              result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( fileName ), "UTF-8" ) );
            } catch ( IOException | NullPointerException e ) {
              result.setStatusCode( HttpStatus.SC_NOT_FOUND );
            }


            return result;
          }
        };
      }
    };
  }
}
