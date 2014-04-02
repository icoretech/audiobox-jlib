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

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.FileAlreadyUploaded;
import fm.audiobox.core.exceptions.ResourceNotFoundException;
import fm.audiobox.core.models.MediaFile;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.models.Playlists;
import fm.audiobox.tests.AudioBoxTests;
import fm.audiobox.tests.mocks.AudioBoxMockHttpTransportFactory;
import fm.audiobox.tests.mocks.MediaFilesMockHttpTransportFactory;
import fm.audiobox.tests.mocks.PlaylistsMockHttpTransportFactory;
import org.junit.Before;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
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


  @Test( expected = ResourceNotFoundException.class )
  public void testLoadMediaFileShouldFailIfWrongTokenGiven() throws IOException {
    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getFourOFourTransport() );
    MediaFile.load( c, "AAA" );
  }


  @Test
  public void testLoadMediaFileShouldSucceedWithRightToken() throws IOException {
    //c.authorize( fixtures.getString( "authentication.staging.email" ), fixtures.getString( "authentication.staging.password" ) );
    c.getConf().setHttpTransport( MediaFilesMockHttpTransportFactory.getMediaFileTransport("c_ddcf6876debeb3cb365bcc") );
    MediaFile mf = MediaFile.load( c, "c_ddcf6876debeb3cb365bcc" );

    assertNotNull( mf );
    assertEquals( "/api/v1/stream/c_ddcf6876debeb3cb365bcc", mf.getStreamPath() );
    assertEquals( "/api/v1/download/c_ddcf6876debeb3cb365bcc", mf.getDownloadPath() );
    assertEquals( "Lyrics not available", mf.getLyrics( c ) );
    assertEquals( "AudioFile", mf.getType() );
    assertEquals( "c_ddcf6876debeb3cb365bcc", mf.getToken() );
    assertEquals( "Me", mf.getArtist() );
    assertEquals( "Me", mf.getAlbum() );
    assertEquals( "Other", mf.getGenre() );
    assertEquals( 2006, mf.getReleaseYear() );
    assertEquals( "Test of MP3 File", mf.getTitle() );
    assertEquals( "0:12", mf.getLenStr() );
    assertEquals( 12, mf.getLenInt() );
    assertEquals( 1, mf.getPosition() );
    assertEquals( "c_ddcf6876debeb3cb365bcc.mp3", mf.getFilename() );
    assertFalse( mf.isLoved() );
    assertEquals( 1, mf.getDiscNumber() );
    assertEquals( "audio/mpeg", mf.getMime() );
    assertEquals( "/absolute/path/to/file/mpthreetest.mp3", mf.getRemotePath() );
    assertEquals( "cloud", mf.getSource() );
    assertEquals( "30763732320307a6c56286f5b60547f2", mf.getShareToken() );
    assertEquals( "missing.png", mf.getArtwork() );
    assertEquals( 198658, mf.getSize() );
    assertEquals( "", mf.getAlbumArtist() );
    assertEquals( "eebc1b05345cffe3cf7a80b15f6251f6", mf.getHash() );
    assertEquals( "", mf.getComposer() );
    assertEquals( "", mf.getComment() );
    assertEquals( "", mf.getVideoBitrate() );
    assertEquals( "", mf.getVideoCodec() );
    assertEquals( "", mf.getVideoResolution() );
    assertEquals( "", mf.getVideoFps() );
    assertEquals( "", mf.getVideoAspect() );
    assertEquals( "", mf.getVideoContainer() );
    assertEquals( "128", mf.getAudioBitrate() );
    assertEquals( "", mf.getAudioCodec() );
    assertEquals( "44100", mf.getAudioSampleRate() );
  }


  /**
   * Test media files.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testMediaFiles() throws IOException {
    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getPlaylistsTransport() );
    Playlist p = Playlists.getDropboxPlaylist( c );
    c.getConf().setHttpTransport( PlaylistsMockHttpTransportFactory.getPlaylistMediaFilesTransport( p.getToken() ) );
    List<? extends MediaFile> m = p.getMediaFiles( c );
    assertNotNull( m );
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

    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getUploadTransport( false ) );
    assertNotNull( c.upload( file ) );
  }


  /**
   * Test upload.
   *
   * @throws IOException the iO exception
   */
  @Test( expected = FileAlreadyUploaded.class )
  public void testUploadFailure() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getUploadTransport( true ) );
    assertNotNull( c.upload( file ) );
  }


  /**
   * Test destroy success.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testDestroySuccess() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getUploadTransport( false ) );
    MediaFile mf = c.upload( file );

    assertNotNull( mf );

    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getTwoOFourHttpTransport() );
    assertTrue( mf.destroy( c ) );

  }


  /**
   * Test destroy failure.
   *
   * @throws IOException the iO exception
   */
  @Test( expected = ResourceNotFoundException.class )
  public void testDestroyFailure() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getUploadTransport( false ) );
    MediaFile mf = c.upload( file );

    assertNotNull( mf );

    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getFourOFourTransport() );
    mf.destroy( c );
    fail( "Should rise ResourceNotFoundException" );

  }

}
