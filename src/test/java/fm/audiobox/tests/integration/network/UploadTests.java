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

package fm.audiobox.tests.integration.network;

import com.google.api.client.http.javanet.NetHttpTransport;
import fm.audiobox.core.exceptions.FileAlreadyUploaded;
import fm.audiobox.core.models.MediaFile;
import fm.audiobox.core.net.NetworkProgressListener;
import fm.audiobox.core.net.Upload;
import fm.audiobox.tests.integration.AudioBoxTests;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Upload tests
 * <p/>
 * Created by keytwo on 25/07/14.
 */
public class UploadTests extends AudioBoxTests {

  /**
   * Test upload success.
   *
   * @throws java.io.IOException the iO exception
   */
  @Test
  @Ignore
  public void testUploadSuccess() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    c.getConf().setHttpTransport( new NetHttpTransport() );
    Upload u = c.newUpload( file );
    u.setListener( new NetworkProgressListener() {
      @Override
      public void onProgressUpdate(long total, long current) {
        assertTrue( "Current progress cannot be bigger than total", total >= current );
        logger.info( "Total: " + total + " | Actual: " + current );
      }
    } );

    MediaFile m = u.start();
    assertEquals( file.getAbsolutePath(), m.getRemotePath() );
    assertTrue( "File must be destroyed", m.destroy( c ) );

  }


  /**
   * Test upload
   *
   * @throws IOException the iO exception
   */
  @Test
  @Ignore
  public void testUploadFailure() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    c.getConf().setHttpTransport( new NetHttpTransport() );
    Upload u = c.newUpload( file );
    u.setListener( new NetworkProgressListener() {
      @Override
      public void onProgressUpdate(long total, long current) {
        assertTrue( "Current progress cannot be bigger than total", total >= current );
        logger.info( "Total: " + total + " | Actual: " + current );
      }
    } );

    // First upload should succeed
    MediaFile m = u.start();
    assertEquals( file.getAbsolutePath(), m.getRemotePath() );

    try {
      // Second upload must fail
      u = c.newUpload( file );
      u.start();
      fail( "Exception should be thrown" );

    } catch ( Exception e ) {
      assertTrue( e instanceof FileAlreadyUploaded );

    } finally {
      assertTrue( "File must be destroyed", m.destroy( c ) );

    }

  }


  /**
   * Test upload should fails if it was already complete.
   *
   * @throws IOException the iO exception
   */
  @Test
  @Ignore
  public void testUploadShouldFailsIfItWasAlreadyComplete() throws IOException {
    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    c.getConf().setHttpTransport( new NetHttpTransport() );
    Upload u = c.newUpload( file );
    u.setListener( new NetworkProgressListener() {
      @Override
      public void onProgressUpdate(long total, long current) {
        assertTrue( "Current progress cannot be bigger than total", total >= current );
        logger.info( "Total: " + total + " | Actual: " + current );
      }
    } );

    // First upload should succeed
    MediaFile m = u.start();
    assertEquals( file.getAbsolutePath(), m.getRemotePath() );

    try {
      // Second upload must fail
      u.start();
      fail( "Exception should be thrown" );

    } catch ( Exception e ) {
      assertTrue( e instanceof IllegalStateException );
      assertEquals( "Upload is already started or completed.", e.getMessage() );

    } finally {
      assertTrue( "File must be destroyed", m.destroy( c ) );

    }
  }

}
