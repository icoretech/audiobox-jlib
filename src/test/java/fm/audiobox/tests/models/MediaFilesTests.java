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

package fm.audiobox.tests.models;

import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.FileAlreadyUploaded;
import fm.audiobox.core.exceptions.ResourceNotFoundException;
import fm.audiobox.core.models.MediaFile;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.models.Playlists;
import fm.audiobox.tests.AudioBoxTests;
import fm.audiobox.tests.mocks.MockHttp;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class MediaFilesTests extends AudioBoxTests {

  @Test( expected = ResourceNotFoundException.class )
  public void testLoadMediaFileShouldFailIfWrongTokenGiven() throws IOException {
    MediaFile.load( c, "AAA" );
  }


  @Test
  public void testLoadMediaFileShouldSucceedWithRightToken() throws IOException {
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

    mf.setArtist("String");
    mf.setAlbum("String");
    mf.setGenre("String");
    mf.setReleaseYear(2000);
    mf.setTitle("String");
    mf.setPosition(99);
    mf.setLoved(true);
    mf.setDiscNumber(2);
    mf.setArtwork("String");
    mf.setAlbumArtist("String");
    mf.setComposer("String");
    mf.setComment("String");

    assertEquals("String", mf.getArtist());
    assertEquals("String", mf.getAlbum());
    assertEquals("String", mf.getGenre());
    assertEquals(2000, mf.getReleaseYear());
    assertEquals("String", mf.getTitle());
    assertEquals(99, mf.getPosition());
    assertEquals(true, mf.isLoved());
    assertEquals(2, mf.getDiscNumber());
    assertEquals("String", mf.getArtwork());
    assertEquals("String", mf.getAlbumArtist());
    assertEquals("String", mf.getComposer());
    assertEquals("String", mf.getComment());
  }


  /**
   * Test media files.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testMediaFiles() throws IOException {
    Playlist p = Playlists.getDropboxPlaylist( c );
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

    c.getConf().setHttpTransport( MockHttp.getUploadTransport( false ) );
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

    c.getConf().setHttpTransport( MockHttp.getUploadTransport( true ) );
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

    c.getConf().setHttpTransport( MockHttp.getUploadTransport( false ) );
    MediaFile mf = c.upload( file );

    assertNotNull( mf );

    assertTrue( mf.destroy( c ) );

  }


  /**
   * Test destroy failure.
   *
   * @throws IOException the iO exception
   */
  @Test( expected = ResourceNotFoundException.class )
  public void testDestroyFailure() throws IOException {

    MediaFile mf = new MediaFile();
    assertNotNull( mf );
    mf.destroy( c );
    fail( "Should rise ResourceNotFoundException" );

  }

}
