/*
 * Copyright 2009-2014 iCoreTech, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.audiobox.tests.unit.models;


import fm.audiobox.core.exceptions.*;
import fm.audiobox.core.models.*;
import fm.audiobox.core.models.collections.EventedModelList;
import fm.audiobox.core.models.collections.ModelEvent;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.core.utils.ModelUtil;
import fm.audiobox.tests.mocks.MockHttp;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.*;


/**
 * The type Playlists tests.
 */
public class PlaylistsTests extends AudioBoxTests {

  /**
   * Test equality.
   */
  @Test
  public void testEquality() {
    EqualsVerifier.forClass( Playlist.class )
        .suppress( Warning.NONFINAL_FIELDS )
        .verify();
  }


  /**
   * Test playlists.
   *
   * @throws java.io.IOException the iO exception
   */
  @Test
  public void testPlaylists() throws IOException {
    List<Playlist> list = c.getPlaylists();
    assertNotNull( list );
    assertFalse( list.isEmpty() );
    Playlist p = list.get( 0 );
    assertNotNull( p.getToken() );
    assertEquals( "AudioBox Desktop", p.getName() );
    p.setName( "test" );
    assertEquals( "test", p.getName() );
    p.setDescription( "Test description" );
    assertEquals( "Test description", p.getDescription() );
    assertEquals( "local", p.getSystemName() );
    assertEquals( "LocalPlaylist", p.getType() );
    assertEquals( 0, p.getMediaFilesCount() );
    assertEquals( 1, p.getPosition() );
    p.setPosition( 10 );
    assertEquals( 10, p.getPosition() );
    assertEquals( false, p.isEmbeddable() );
    p.setEmbeddable( true );
    assertEquals( true, p.isEmbeddable() );
    assertEquals( true, p.isVisible() );
    p.setVisible( false );
    assertEquals( false, p.isVisible() );
    assertEquals( false, p.isLastAccessed() );
    assertEquals( "2013-08-29T18:25:53.517Z", p.getUpdatedAt() );
    assertEquals( false, p.isSyncable() );
    assertTrue( p.isDrive() );
    assertFalse( p.isCustom() );
    assertFalse( p.isSmart() );
    assertFalse( p.isOffline() );
  }


  /**
   * Test playlist should be null if token is invalid.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = ResourceNotFoundException.class )
  public void testResourceNotFoundIsThrownIfPlaylistIfTokenIsInvalid() throws IOException {
    c.getPlaylist( "asd" );
  }


  /**
   * Test playlist should be null if token is invalid.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testPlaylistShouldNotBeNullIfTokenIsValid() throws IOException {
    List<Playlist> list = c.getPlaylists();
    Playlist p1 = list.get( 0 );
    assertNotNull( p1 );

    Playlist p2 = c.getPlaylist( p1.getToken() );
    assertEquals( "Playlists should be equals", p1, p2 );
  }


  /**
   * Test brand new playlist deletion should rise error.
   *
   * @throws AuthorizationException the authorization exception
   */
  @Test( expected = IllegalStateException.class )
  public void testBrandNewPlaylistDeletionShouldRiseError() throws IOException {
    Playlist p = new Playlist( "Hello" );
    p.destroy( c );
  }


  /**
   * Test playlist creation with empty name should rise error.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = IllegalStateException.class )
  public void testPlaylistCreationWithEmptyNameShouldRiseError() throws IOException {
    Playlist p = new Playlist( "" );
    p.create( c );
  }


  /**
   * Test playlist creation with same name as another should result in validation error.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testPlaylistCreationWithSameNameAsAnotherShouldResultInValidationError() throws IOException {
    c.getConf().setHttpTransport( MockHttp.getTransport( HttpStatus.SC_UNPROCESSABLE_ENTITY, "playlists/name_already_taken.json" ) );
    //c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    Playlist p = new Playlist( "Dropbox" );
    try {
      p.create( c );
      fail( "Should raise exception" );
    } catch ( ValidationException e ) {

      assertEquals( e.toString(), e.getMessage() );
      assertEquals( HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getErrorCode() );
      assertNotNull( e.getErrors() );
      assertNotNull( RemoteMessageException.errorsToString( e.getErrors() ) );
      assertEquals( RemoteMessageException.errorsToString( e.getErrors() ), e.toString() );

    } catch ( Exception e ) {
      fail( "ValidationException expected, got " + e.getClass().getSimpleName() );
    }
  }


  /**
   * Test playlist creation with no return body.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testPlaylistCreationWithNoReturnBody() throws IOException {
    Playlist p = new Playlist( "Dropbox" );
    try {
      c.getConf().setHttpTransport( MockHttp.getTransport( 404, "" ) );
      p.create( c );
      fail( "Should raise exception" );
    } catch ( ResourceNotFoundException e ) {
      assertEquals( HttpStatus.SC_NOT_FOUND, e.getErrorCode() );

    } catch ( Exception e ) {
      fail( "ValidationException expected, got " + e.getClass().getSimpleName() );
    }
  }


  /**
   * Test new playlist modification should result in illegal state exception.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = IllegalStateException.class )
  public void testNewPlaylistModificationShouldResultInIllegalStateException() throws IOException {
    Playlist p = new Playlist( "Invalid" );
    p.update( c );
  }


  /**
   * Test new playlist sync should rise illegal state exception.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = IllegalStateException.class )
  public void testNewPlaylistSyncShouldRiseIllegalStateException() throws IOException {
    Playlist p = new Playlist( "Invalid" );
    p.sync( c );
  }


  /**
   * Test new playlist creation success.
   */
  @Test
  public void testNewPlaylistCreationSuccess() {
    c.getConf().setHttpTransport( MockHttp.getTransport( HttpStatus.SC_CREATED, "playlists/test_playlist_201_created.json" ) );
    Playlist p = new Playlist( "Test playlist" );
    try {
      Playlist p2 = p.create( c );
      assertNotNull( p2 );
    } catch ( IOException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test new playlist deletion success.
   */
  @Test
  public void testNewPlaylistDeletion() {

    try {
      Playlist p = c.getPlaylist( "test_playlist_201_created" );

      assertTrue( "Playlist should be deleted", p.destroy( c ) );
    } catch ( IOException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test local playlist visibility.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testLocalPlaylistVisibility() throws IOException {
    Playlist local = c.getPlaylist( "000_local" );
    assertTrue( local.toggleVisibility( c ) );
  }


  /**
   * Test local playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testLocalPlaylistSync() throws IOException {
    try {
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
  public void testCloudPlaylistSync() throws IOException {
    try {
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
  public void testUnsyncablePlaylistsSync() throws IOException {
    try {
      Playlist smart = c.getPlaylist( "000_smart" );
      smart.sync( c );
      fail( "smart playlists should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getErrorCode() );
      logger.info( "[ OK ] smart playlist not syncable: " + e.getMessage() );
    }

    try {
      Playlist custom = c.getPlaylist( "000_custom" );
      custom.sync( c );
      fail( "custom playlists should not be syncable" );
    } catch ( SyncException e ) {
      assertEquals( HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getErrorCode() );
      logger.info( "[ OK ] custom playlist not syncable: " + e.getMessage() );
    }

    try {
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
  public void testDropboxPlaylistSync() throws IOException {
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
  public void testSkydrivePlaylistSync() throws IOException {
    Playlist skydrive = c.getPlaylist( "000_skydrive" );
    assertTrue( skydrive.sync( c ) );
  }


  /**
   * Test box playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testBoxPlaylistSync() throws IOException {
    Playlist box = c.getPlaylist( "000_box" );
    assertTrue( box.sync( c ) );
  }


  /**
   * Test gdrive playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testGdrivePlaylistSync() throws IOException {
    Playlist gdrive = c.getPlaylist( "000_gdrive" );
    assertTrue( gdrive.sync( c ) );
  }


  /**
   * Test youtube playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testYoutubePlaylistSync() throws IOException {
    Playlist youtube = c.getPlaylist( "000_youtube" );
    assertTrue( youtube.sync( c ) );
  }


  /**
   * Test soundcloud playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testSoundcloudPlaylistSync() throws IOException {
    Playlist soundcloud = c.getPlaylist( "000_soundcloud" );
    try {
      soundcloud.sync( c );
      fail( "soundcloud should not be syncable because it's not authenticated" );
    } catch ( SyncException e ) {
      assertEquals( e.getErrorCode(), HttpStatus.SC_FORBIDDEN );
    }
  }


  /**
   * Test mega playlist sync.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testMegaPlaylistSync() throws IOException {
    Playlist mega = c.getPlaylist( "000_mega" );
    try {
      mega.sync( c );
      fail( "mega should not be syncable because it's not authenticated" );
    } catch ( SyncException e ) {
      assertEquals( e.getErrorCode(), HttpStatus.SC_FORBIDDEN );
    }
  }


  /**
   * Test dropbox playlist album.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testDropboxPlaylistAlbum() throws IOException {
    Playlist dropbox = c.getPlaylist( "000_dropbox" );
    Albums albs = dropbox.getAlbums( c );

    assertNotNull( albs );
    assertFalse( albs.getAlbums().isEmpty() );

    Album a = albs.getAlbums().get( 0 );
    assertFalse( a.getMediaFiles().isEmpty() );

    assertEquals( "Album", a.getAlbum() );
    assertEquals( "t0k3n", a.getToken() );
    assertEquals( 2000, a.getReleaseYear() );
    assertEquals( "a/001/255/art.png", a.getArtwork() );
    assertEquals( "Artist", a.getArtist() );
  }


  /**
   * Test dropbox playlist genre.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testDropboxPlaylistGenre() throws IOException {
    Playlist dropbox = c.getPlaylist( "000_dropbox" );
    Genres genres = dropbox.getGenres( c );

    assertNotNull( genres );
    assertFalse( genres.getGenres().isEmpty() );

    Genre g = genres.getGenres().get( 0 );
    assertFalse( g.getMediaFiles().isEmpty() );
    assertEquals( "Genre", g.getGenre() );
    assertEquals( "t0k3333n", g.getToken() );
  }


  /**
   * Test dropbox playlist artist.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testDropboxPlaylistArtist() throws IOException {
    Playlist dropbox = c.getPlaylist( "000_dropbox" );
    Artists artists = dropbox.getArtists( c );

    assertNotNull( artists );
    assertFalse( artists.getArtists().isEmpty() );

    Artist a = artists.getArtists().get( 0 );
    assertFalse( a.getMediaFiles().isEmpty() );
    assertEquals( "Artist", a.getArtist() );
    assertEquals( "t0k3333n", a.getToken() );
  }


  /**
   * Test playlists by type.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testPlaylistsByType() throws IOException {
    List<Playlist> pls = c.getPlaylists();
    for ( Playlist p : pls ) {
      Playlist p2 = null;
      switch ( p.getSystemName() ) {

        case "box":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getBoxPlaylist( c );
          assertEquals( p, p2 );
          break;

        case "dropbox":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getDropboxPlaylist( c );
          assertEquals( p, p2 );
          break;

        case "local":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getLocalPlaylist( c );
          assertEquals( p, p2 );
          break;

        case "cloud":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getCloudPlaylist( c );
          assertEquals( p, p2 );
          break;

        case "gdrive":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getGdrivePlaylist( c );
          assertEquals( p, p2 );
          break;

        case "mega":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getMegaPlaylist( c );
          assertEquals( p, p2 );
          break;

        case "skydrive":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getOneDrivePlaylist( c );
          assertEquals( p, p2 );
          break;

        case "offline":
          assertNotEquals( p, p2 );
          assertTrue( p.isOffline() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isDrive() );
          p2 = Playlists.getOfflinePlaylist( c );
          assertEquals( p, p2 );
          break;

        case "youtube":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getYoutubePlaylist( c );
          assertEquals( p, p2 );
          break;

        case "soundcloud":
          assertNotEquals( p, p2 );
          assertTrue( p.isDrive() );
          assertFalse( p.isCustom() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          p2 = Playlists.getSoundcloudPlaylist( c );
          assertEquals( p, p2 );
          break;

        case "smart":
          assertTrue( p.isSmart() );
          assertFalse( p.isCustom() );
          assertFalse( p.isDrive() );
          assertFalse( p.isOffline() );
          break;

        case "custom":
          assertTrue( p.isCustom() );
          assertFalse( p.isDrive() );
          assertFalse( p.isSmart() );
          assertFalse( p.isOffline() );
          break;
      }

    }
  }


  /**
   * Test add media files to not existing playlist.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testAddMediaFilesToNotExistingPlaylist() throws IOException {
    Playlist p = new Playlist( "Hello" );

    Playlist dropbox = Playlists.getDropboxPlaylist( c );

    List<? extends MediaFile> mfs = dropbox.getMediaFiles( c );

    List<String> tokens = new ArrayList<>( mfs.size() );
    for ( MediaFile m : mfs ) {
      tokens.add( m.getToken() );
    }

    try {
      p.addMediaFiles( c, tokens );
      fail( "Expected InvalidStateException got success" );
    } catch ( Exception e ) {
      assertTrue( "Exception should be of type IllegalStateException", e instanceof IllegalStateException );
    }
  }


  /**
   * Test add media files to not custom playlist should fail with 404.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testAddMediaFilesToNotCustomPlaylistShouldFailWith404() throws IOException {
    Playlist p = Playlists.getLocalPlaylist( c );

    Playlist dropbox = Playlists.getDropboxPlaylist( c );

    List<? extends MediaFile> mfs = dropbox.getMediaFiles( c );

    List<String> tokens = new ArrayList<>( mfs.size() );
    for ( MediaFile m : mfs ) {
      tokens.add( m.getToken() );
    }
    try {
      p.addMediaFiles( c, tokens );
      fail();
    } catch ( Exception e ) {
      assertTrue( e instanceof ResourceNotFoundException );
    }
  }


  /**
   * Test add media files custom playlist.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testAddMediaFilesCustomPlaylist() throws IOException {

    Playlist p = new Playlist( "test" );
    c.getConf().setHttpTransport( MockHttp.getTransport( HttpStatus.SC_CREATED, "playlists/test_playlist_201_created.json" ) );
    p = p.create( c );

    c.getConf().setHttpTransport( MockHttp.getTransport() );
    Playlist cloud = Playlists.getCloudPlaylist( c );

    List<? extends MediaFile> mfs = cloud.getMediaFiles( c );

    List<String> tokens = new ArrayList<>( mfs.size() );
    for ( MediaFile m : mfs ) {
      tokens.add( m.getToken() );
    }

    Playlist p2 = p.addMediaFiles( c, tokens );
    assertEquals( p2, p );
  }


  /**
   * Test should rise error on not existing playlist media file removal.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testShouldRiseErrorOnNotExistingPlaylistMediaFileRemoval() throws IOException {
    Playlist p = new Playlist( "test" );
    List<String> t = new ArrayList<>( 1 );
    t.add( "c_ddcf6876debeb3cb365bcc" );
    try {
      p.removeMediaFiles( c, t );
      fail( "Should raise exception" );
    } catch ( Exception e ) {
      assertTrue( e instanceof IllegalStateException );
      assertEquals( "Playlist must be remotely created before performing the requested action.", e.getMessage() );
    }
  }


  /**
   * Test playlist should rise error on not existing media file removal.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testShouldNotRiseErrorOnNotExistingMediaFileRemoval() throws IOException {

    Playlist testPlaylist = c.getPlaylist( "000_custom" );

    List<String> t = new ArrayList<>( 4 );
    t.add( "aaa" );
    t.add( "bbb" );
    t.add( "CCC" );
    t.add( "bbb" );

    assertEquals( testPlaylist, testPlaylist.removeMediaFiles( c, t ) );
  }


  /**
   * Test should rise error on not custom playlist file removal.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testShouldRiseErrorOnNotCustomPlaylistFileRemoval() throws IOException {
    Playlist testPlaylist = c.getPlaylist( "000_cloud" );

    List<String> t = new ArrayList<>( 4 );
    t.add( "aaa" );
    t.add( "bbb" );
    t.add( "CCC" );
    t.add( "bbb" );

    try {
      testPlaylist.removeMediaFiles( c, t );
      fail( "Immutable playlist should rise exception" );
    } catch ( Exception e ) {
      assertEquals( ResourceNotFoundException.class, e.getClass() );
    }

  }


  /**
   * Test update should rise error on not persisted playlist.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testUpdateShouldRiseErrorOnNotPersistedPlaylist() throws IOException {
    Playlist pls = new Playlist( "test" );
    try {
      pls.update( c );
      fail( "This test should rise a IllegalStateException" );
    } catch ( Exception e ) {
      assertTrue( e instanceof IllegalStateException );
      assertEquals( "Playlist must be remotely created before performing the requested action.", e.getMessage() );
    }
  }


  /**
   * Test update should rise error on rename not editable playlist.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testUpdateShouldRiseErrorOnRenameNotEditablePlaylist() throws IOException {
    Playlist cloudPlaylist = ModelUtil.findPlaylistByType( c.getPlaylists(), Playlists.PLAYLIST_CLOUD );

    cloudPlaylist
        .setName( "Test Rename" )
        .setDescription( "Test description" )
        .setVisible( false )
        .setEmbeddable( false )
        .setPosition( 100 );

    try {
      cloudPlaylist.update( c );
      fail( "This test should rise a ResourceNotFoundException" );
    } catch ( Exception e ) {
      assertTrue( e instanceof ResourceNotFoundException );
      assertEquals( "Not Found", e.getMessage() );
    }

  }


  /**
   * Test update should succeed on custom types.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testUpdateShouldSucceedOnCustomTypes() throws IOException {

    Playlist pls = c.getPlaylist( "000_custom" );
    assertNotNull( pls );
    pls
        .setName( "Test 2" )
        .setDescription( "Test description" )
        .setVisible( true )
        .setEmbeddable( false )
        .setPosition( 100 );

    assertEquals( pls, pls.update( c ) );
  }


  /**
   * Test set param in get media files.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testSetParamInGetMediaFiles() throws IOException {

    Playlist pls = Playlists.getLocalPlaylist( c );
    List<? extends MediaFile> mfs = pls.getMediaFiles( c, 0, "token" );
    assertNotNull( mfs );
    assertFalse( mfs.isEmpty() );
    for ( MediaFile f : mfs ) {
      assertTrue( StringUtils.isNotBlank( f.getToken() ) );
      assertTrue( StringUtils.isBlank( f.getArtist() ) );
    }
  }


  /**
   * Test since param in get media files.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testSinceParamInGetMediaFiles() throws IOException {

    Playlist pls = c.getPlaylist( "000_cloud" );
    List<? extends MediaFile> mfs1 = pls.getMediaFiles( c, 0, null );
    List<? extends MediaFile> mfs2 = pls.getMediaFiles( c, 1387575475, "token" );
    assertNotNull( mfs1 );
    assertNotNull( mfs2 );
    assertFalse( mfs1.isEmpty() );
    assertFalse( mfs2.isEmpty() );

    assertNotEquals( mfs1.size(), mfs2.size() );
    assertTrue( mfs1.size() > mfs2.size() );

    for ( MediaFile f : mfs1 ) {
      assertTrue( StringUtils.isNotBlank( f.getToken() ) );
    }
  }


  /**
   * Test fingerprints fails on not cloud or local playlist.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testFingerprintsFailsOnNotCloudOrLocalPlaylist() throws IOException {
    Playlist pls = c.getPlaylist( "000_custom" );
    try {
      pls.getFingerprints( c );
      fail( "Should throw ResourceNotFoundException" );
    } catch ( Exception e ) {
      assertTrue( e instanceof ResourceNotFoundException );
    }
  }


  /**
   * Test fingerprints succeed on local playlist.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testFingerprintsSucceedOnLocalPlaylist() throws IOException {
    final Playlist pls = Playlists.getLocalPlaylist( c );

    pls.addObserver( new Observer() {

      @Override
      public void update(Observable o, Object arg) {
        assertSame( o, pls );
        assertTrue( arg instanceof ModelEvent );
        ModelEvent e = ( ModelEvent ) arg;
        assertTrue( e.source instanceof MediaFile );
        assertTrue( e.target instanceof EventedModelList );
      }

    } );

    List<? extends MediaFile> mfs = pls.getFingerprints( c );
    assertNotNull( mfs );
    assertFalse( mfs.isEmpty() );

    for ( MediaFile m : mfs ) {
      assertNotNull( m.getToken() );
      assertNotNull( m.getHash() );
      assertNull( m.getTitle() );
    }
  }
}
