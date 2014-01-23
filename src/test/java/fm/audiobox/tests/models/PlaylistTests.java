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
import fm.audiobox.core.exceptions.*;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.tests.AudioBoxTests;
import fm.audiobox.tests.mocks.PlaylistsMockHttpTransportFactory;
import org.junit.Before;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by keytwo on 21/01/14.
 */
public class PlaylistTests extends AudioBoxTests {

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
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = ResourceNotFoundException.class )
  public void testResourceNotFoundIsThrownIfPlaylistIfTokenIsInvalid() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "asd" ) );
    c.getPlaylist( "asd" );
  }


  /**
   * Test playlist should be null if token is invalid.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testPlaylistShouldNotBeNullIfTokenIsValid() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistsTransport() );
    List<Playlist> list = c.getPlaylists();
    Playlist p1 = list.get( 0 );
    assertNotNull( p1 );

    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( p1.getToken() ) );
    Playlist p2 = c.getPlaylist( p1.getToken() );
    assertEquals( "Playlists should be equals", p1, p2 );
  }


  /**
   * Test brand new playlist deletion should rise error.
   *
   * @throws AuthorizationException the authorization exception
   */
  @Test( expected = IllegalStateException.class )
  public void testBrandNewPlaylistDeletionShouldRiseError() throws AudioBoxException {
    Playlist p = new Playlist( "Hello" );
    p.delete( c );
  }


  /**
   * Test playlist creation with empty name should rise error.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = IllegalStateException.class )
  public void testPlaylistCreationWithEmptyNameShouldRiseError() throws AudioBoxException {
    Playlist p = new Playlist( "" );
    p.create( c );
  }


  /**
   * Test playlist creation with same name as another should result in validation error.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = ValidationException.class )
  public void testPlaylistCreationWithSameNameAsAnotherShouldResultInValidationError() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistsFourOFourTransport() );
    Playlist p = new Playlist( "Dropbox" );
    p.create( c );
  }


  /**
   * Test new playlist modification should result in illegal state exception.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = IllegalStateException.class )
  public void testNewPlaylistModificationShouldResultInIllegalStateException() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistsFourOFourTransport() );
    Playlist p = new Playlist( "Invalid" );
    p.update( c );
  }


  /**
   * Test new playlist sync should rise illegal state exception.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = IllegalStateException.class )
  public void testNewPlaylistSyncShouldRiseIllegalStateException() throws AudioBoxException {
    Playlist p = new Playlist( "Invalid" );
    p.sync( c );
  }


  /**
   * Test new playlist creation success.
   */
  @Test
  public void testNewPlaylistCreationSuccess() {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistCreation201() );
    Playlist p = new Playlist( "Test playlist" );
    try {
      Playlist p2 = p.create( c );
      assertNotNull( p2 );
    } catch ( AudioBoxException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test new playlist deletion success.
   */
  @Test
  public void testNewPlaylistDeletion() {

    try {
      c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "test_playlist_201_created" ) );
      Playlist p = c.getPlaylist( "test_playlist_201_created" );

      c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistDeletion204() );
      assertTrue( "Playlist should be deleted", p.delete( c ) );
    } catch ( AudioBoxException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test local playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testLocalPlaylistSync() throws AudioBoxException {
    try {
      c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_local" ) );
      Playlist local = c.getPlaylist( "000_local" );
      local.sync( c );
      fail( "local playlist should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( e.getErrorCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY );
      logger.info( "[ OK ] local drive not syncable: " + e.getMessage() );
    }
  }


  /**
   * Test cloud playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testCloudPlaylistSync() throws AudioBoxException {
    try {
      c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_cloud" ) );
      Playlist cloud = c.getPlaylist( "000_cloud" );
      cloud.sync( c );
      fail( "cloud playlist should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getErrorCode() );
      logger.info( "[ OK ] cloud drive not syncable: " + e.getMessage() );
    }
  }


  /**
   * Test unsyncable playlists sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testUnsyncablePlaylistsSync() throws AudioBoxException {
    try {
      c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_smart" ) );
      Playlist smart = c.getPlaylist( "000_smart" );
      smart.sync( c );
      fail( "smart playlists should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getErrorCode() );
      logger.info( "[ OK ] smart playlist not syncable: " + e.getMessage() );
    }

    try {
      c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_custom" ) );
      Playlist custom = c.getPlaylist( "000_custom" );
      custom.sync( c );
      fail( "custom playlists should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getErrorCode() );
      logger.info( "[ OK ] custom playlist not syncable: " + e.getMessage() );
    }

    try {
      c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_offline" ) );
      Playlist offline = c.getPlaylist( "000_offline" );
      offline.sync( c );
      fail( "offline playlist should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getErrorCode() );
      logger.info( "[ OK ] offline playlist not syncable: " + e.getMessage() );
    }
  }


  /**
   * Test dropbox playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testDropboxPlaylistSync() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_dropbox" ) );
    Playlist dropbox = c.getPlaylist( "000_dropbox" );
    try {
      dropbox.sync( c );
      fail( "dropbox should not be syncable because it's not authenticated" );
    } catch ( SyncException e ) {
      assertEquals( e.getErrorCode(), HttpStatus.SC_FORBIDDEN );
    }
  }


  /**
   * Test skydrive playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testSkydrivePlaylistSync() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_skydrive" ) );
    Playlist skydrive = c.getPlaylist( "000_skydrive" );
    try {
      assertTrue( skydrive.sync( c ) );
    } catch ( SyncException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test box playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testBoxPlaylistSync() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_box" ) );
    Playlist box = c.getPlaylist( "000_box" );
    try {
      assertTrue( box.sync( c ) );
    } catch ( SyncException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test gdrive playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testGdrivePlaylistSync() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_gdrive" ) );
    Playlist gdrive = c.getPlaylist( "000_gdrive" );
    try {
      assertTrue( gdrive.sync( c ) );
    } catch ( SyncException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test youtube playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testYoutubePlaylistSync() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_youtube" ) );
    Playlist youtube = c.getPlaylist( "000_youtube" );
    try {
      assertTrue( youtube.sync( c ) );
    } catch ( SyncException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test soundcloud playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testSoundcloudPlaylistSync() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_soundcloud" ) );
    Playlist soundcloud = c.getPlaylist( "000_soundcloud" );
    try {
      soundcloud.sync( c );
      fail( "soundcloud should not be syncable because it's not authenticated" );
    } catch ( SyncException e ) {
      assertEquals( e.getErrorCode(), HttpStatus.SC_FORBIDDEN );
    }
  }


  /**
   * Test ubuntu playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testUbuntuPlaylistSync() throws AudioBoxException {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "000_ubuntu" ) );
    Playlist ubuntu = c.getPlaylist( "000_ubuntu" );
    try {
      ubuntu.sync( c );
      fail( "ubuntu should not be syncable because it's not authenticated" );
    } catch ( SyncException e ) {
      assertEquals( e.getErrorCode(), HttpStatus.SC_FORBIDDEN );
    }
  }
}
