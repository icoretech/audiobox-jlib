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

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.Json;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import fm.audiobox.core.models.UserWrapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by keytwo on 21/01/14.
 */
public class AudioBoxMockHttpTransport extends MockHttpTransport {

  @Override
  public LowLevelHttpRequest buildRequest(String method, final String url) throws IOException {
    return new MockLowLevelHttpRequest() {
      @Override
      public LowLevelHttpResponse execute() throws IOException {
        MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
        result.setContentType( Json.MEDIA_TYPE );

        Pattern oauthPattern = Pattern.compile( "/oauth2/token$" );
        Pattern userPattern = Pattern.compile( UserWrapper.getPath() + "$" );

        if ( oauthPattern.matcher( url ).find() ) {
          result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/oauth2/token.json" ), "UTF-8" ) );
        }

        if ( userPattern.matcher( url ).find() ) {
          result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/user.json" ), "UTF-8" ) );
        }
        return result;
      }
    };
  }


}
