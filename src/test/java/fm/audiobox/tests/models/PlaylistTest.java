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

import fm.audiobox.core.exceptions.SyncException;
import fm.audiobox.core.exceptions.ValidationException;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.tests.ClientTest;
import fm.audiobox.tests.mocks.PlaylistsMockHttpTransportFactory;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by keytwo on 21/01/14.
 */
public class PlaylistTest extends ClientTest {


  /**
   * Test playlists.
   *
   * @throws java.io.IOException the iO exception
   */
  @Test
  @Ignore
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
  @Ignore
  public void testPlaylistShouldBeNullIfTokenIsInvalid() {
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistTransport( "asd" ) );
    assertNull( c.getPlaylist( "asd" ) );
  }


  /**
   * Test playlist should be null if token is invalid.
   */
  @Test
  @Ignore
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


  @Test
  public void testPlaylistSync() {
    List<Playlist> list = c.getPlaylists();

    Playlist local = new Playlist();
    Playlist cloud = new Playlist();
    Playlist dropbox = new Playlist();
    Playlist skydrive = new Playlist();
    Playlist box = new Playlist();
    Playlist gdrive = new Playlist();
    Playlist youtube = new Playlist();
    Playlist soundcloud = new Playlist();
    Playlist ubuntu = new Playlist();

    for ( Playlist p : list ) {
      switch ( p.getSystemName() ) {
        case "local":
          local = p;
          break;
        case "cloud":
          cloud = p;
          break;
        case "dropbox":
          dropbox = p;
          break;
        case "skydrive":
          skydrive = p;
          break;
        case "box":
          box = p;
          break;
        case "gdrive":
          gdrive = p;
          break;
        case "youtube":
          youtube = p;
          break;
        case "soundcloud":
          soundcloud = p;
          break;
        case "ubuntu":
          ubuntu = p;
          break;
      }

    }

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
