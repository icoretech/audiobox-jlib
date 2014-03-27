/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 * This file is part of the AudioBox-Jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.tests.models;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.FileAlreadyUploaded;
import fm.audiobox.core.models.MediaFile;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.models.Playlists;
import fm.audiobox.tests.AudioBoxTests;
import fm.audiobox.tests.mocks.AudioBoxMockHttpTransportFactory;
import fm.audiobox.tests.mocks.PlaylistsMockHttpTransportFactory;
import org.junit.Before;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by keytwo on 17/03/14.
 */
public class MediaFilesTests extends AudioBoxTests {

  @Before
  public void setUp() {
    super.setUp();

    try {
      final long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
      final File httpCacheDir = CACHE_DIR;
      HttpResponseCache.install( httpCacheDir, httpCacheSize );

      Configuration config = new Configuration( Configuration.Env.staging );
      config.setDataStoreFactory( new FileDataStoreFactory( DATA_STORE_DIR ) );

      config.setApiKey( fixtures.getString( "authentication.client_id" ) );
      config.setApiSecret( fixtures.getString( "authentication.client_secret" ) );
      config.setHttpTransport( new NetHttpTransport() );
      JacksonFactory jf = new JacksonFactory();
      config.setJsonFactory( jf );

      c = new Client( config );
    } catch ( IOException | ConfigurationException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test media files.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testMediaFiles() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistsTransport() );
    Playlist p = Playlists.getDropboxPlaylist(c);
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistMediaFilesTransport(p.getToken()) );
    List<? extends MediaFile> m = p.getMediaFiles( c );
    assertNotNull(m);
  }


  /**
   * Test upload.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testUploadSuccess() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    //TokenResponse r = c.authorize( fixtures.getString( "authentication.staging.email" ), fixtures.getString( "authentication.staging.password" ) );
    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getUploadTransport( false ) );
    assertNotNull( c.upload( file ) );
  }

  /**
   * Test upload.
   *
   * @throws IOException the iO exception
   */
  @Test(expected = FileAlreadyUploaded.class)
  public void testUploadFailure() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    //TokenResponse r = c.authorize( fixtures.getString( "authentication.staging.email" ), fixtures.getString( "authentication.staging.password" ) );
    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getUploadTransport( true ) );
    assertNotNull( c.upload( file ) );
  }
}
