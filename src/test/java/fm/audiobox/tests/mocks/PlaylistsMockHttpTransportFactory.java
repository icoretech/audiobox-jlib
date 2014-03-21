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
import java.util.Arrays;


/**
 * Created by keytwo on 22/01/14.
 */
public class PlaylistsMockHttpTransportFactory {


  /**
   * Gets playlists transport.
   *
   * @return the playlists transport
   */
  public static HttpTransport getPlaylistsTransport() {
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/playlists.json" ), "UTF-8" ) );
            return result;
          }
        };
      }
    };
  }


  /**
   * Gets playlist transport.
   *
   * @param playlistToken the playlist token
   *
   * @return the playlist transport
   */
  public static HttpTransport getPlaylistTransport(final String playlistToken) {
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, final String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );

            String fileName = "/responses/playlists/" + playlistToken + ".json";

            try {
              result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( fileName ), "UTF-8" ) );
            } catch ( IOException | NullPointerException e ) {
              result.setStatusCode( HttpStatus.SC_NOT_FOUND );
            }

            if ( url.endsWith( "sync.json" ) ) {
              if ( Arrays.asList( new String[]{ "000_dropbox", "000_ubuntu", "000_soundcloud" } ).contains( playlistToken ) ) {
                result.setStatusCode( HttpStatus.SC_FORBIDDEN );
              } else {
                result.setStatusCode( HttpStatus.SC_NO_CONTENT );
              }
            }

            return result;
          }
        };
      }
    };
  }


  /**
   * Gets playlist visibility transport.
   *
   * @param playlistToken the playlist token
   *
   * @return the playlist visibility transport
   */
  public static HttpTransport getPlaylistVisibilityTransport(final String playlistToken) {
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, final String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );

            String fileName = "/responses/playlists/" + playlistToken + "/visible.json";
            result.setStatusCode( HttpStatus.SC_NO_CONTENT ); // <-- this is crazy :P
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


  /**
   * Gets playlist media files transport.
   *
   * @param playlistToken the playlist token
   *
   * @return the playlist media files transport
   */
  public static HttpTransport getPlaylistMediaFilesTransport(final String playlistToken) {
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, final String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );

            String fileName = "/responses/playlists/" + playlistToken + "/media_files.json";

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


  /**
   * Gets playlist creation 201.
   *
   * @return the playlist creation 201
   */
  public static HttpTransport getPlaylistCreation201() {
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType( Json.MEDIA_TYPE );
            result.setStatusCode( HttpStatus.SC_CREATED );
            result.setContent( IOUtils.toString( this.getClass().getResourceAsStream( "/responses/playlists/test_playlist_201_created.json" ), "UTF-8" ) );
            return result;
          }
        };
      }
    };
  }


  /**
   * Gets playlist deletion 204.
   *
   * @return the playlist deletion 204
   */
  public static HttpTransport getPlaylistDeletion204() {
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
}
