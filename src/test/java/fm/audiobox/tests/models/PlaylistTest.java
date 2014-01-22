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

package fm.audiobox.tests.models;


import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.SyncException;
import fm.audiobox.core.exceptions.ValidationException;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.tests.AudioBoxTest;
import fm.audiobox.tests.mocks.PlaylistsMockHttpTransportFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by keytwo on 21/01/14.
 */
public class PlaylistTest extends AudioBoxTest {

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
    } catch ( IOException e ) {
      fail( e.getMessage() );
    } catch ( ConfigurationException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test playlists.
   *
   * @throws java.io.IOException the iO exception
   */
  @Test
  public void testPlaylists() throws IOException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistsTransport() );
    List<Playlist> list = c.getPlaylists();
    assertNotNull( list );
    assertFalse( list.isEmpty() );
  }


  /**
   * Test playlist should be null if token is invalid.
   */
  @Test
  public void testPlaylistShouldBeNullIfTokenIsInvalid() {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "asd" ) );
    assertNull( c.getPlaylist( "asd" ) );
  }


  /**
   * Test playlist should be null if token is invalid.
   */
  @Test
  public void testPlaylistShouldNotBeNullIfTokenIsValid() {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistsTransport() );
    List<Playlist> list = c.getPlaylists();
    Playlist p1 = list.get( 0 );
    assertNotNull( p1 );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( p1.getToken() ) );
    Playlist p2 = c.getPlaylist( p1.getToken() );
    assertEquals( "Playlists should be equals", p1, p2 );
  }


  /**
   * Test playlist creation and deletion.
   */
  @Test
  @Ignore
  public void testPlaylistCreationAndDeletion() {
    try {
      Playlist p = c.createNewPlaylist( "My test playlist" );
      assertFalse( p.isVisible() );
      p.setVisible( true );
      assertTrue( p.isVisible() );

      assertTrue( p.save( c ) );

      Playlist p2 = c.getPlaylist( p.getToken() );
      assertTrue( p2.isVisible() );

      assertTrue( p2.delete( c ) );

    } catch ( ValidationException e ) {
      // Cleanup code (in case of any failure)

      List<Playlist> list = c.getPlaylists();
      for ( Playlist p : list ) {
        if ( "My test playlist".equals( p.getName() ) ) {
          assertTrue( p.delete( c ) );
          break;
        }
      }
      // Retry
      testPlaylistCreationAndDeletion();
    }
  }


  /**
   * Test playlist sync.
   */
  @Test
  public void testPlaylistSync() {

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_local" ) );
    Playlist local = c.getPlaylist( "000_local" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_cloud" ) );
    Playlist cloud = c.getPlaylist( "000_cloud" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_dropbox" ) );
    Playlist dropbox = c.getPlaylist( "000_dropbox" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_skydrive" ) );
    Playlist skydrive = c.getPlaylist( "000_skydrive" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_box" ) );
    Playlist box = c.getPlaylist( "000_box" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_gdrive" ) );
    Playlist gdrive = c.getPlaylist( "000_gdrive" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_youtube" ) );
    Playlist youtube = c.getPlaylist( "000_youtube" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_soundcloud" ) );
    Playlist soundcloud = c.getPlaylist( "000_soundcloud" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_ubuntu" ) );
    Playlist ubuntu = c.getPlaylist( "000_ubuntu" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_smart" ) );
    Playlist smart = c.getPlaylist( "000_smart" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_custom" ) );
    Playlist custom = c.getPlaylist( "000_custom" );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_offline" ) );
    Playlist offline = c.getPlaylist( "000_offline" );


    try {
      local.sync( c );
      fail( "local playlist should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( e.getErrorCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY );
      logger.info( "[ OK ] local not syncable: " + e.getMessage() );
    }

    try {
      cloud.sync( c );
      fail( "cloud playlist should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( e.getErrorCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY );
      logger.info( "[ OK ] drive not syncable: " + e.getMessage() );
    }


  }
}
