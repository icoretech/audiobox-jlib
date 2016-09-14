/*
 * Copyright 2009-2016 iCoreTech, Inc.
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

import fm.audiobox.core.models.MediaFile;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.models.Playlists;
import fm.audiobox.core.net.NetworkProgressListener;
import fm.audiobox.core.utils.ModelUtil;
import fm.audiobox.tests.integration.AudioBoxIntegrationTests;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Download tests
 * <p/>
 * Created by keytwo on 25/07/14.
 */
public class DownloadTests extends AudioBoxIntegrationTests {

  /**
   * Test download success.
   *
   * @throws IOException the iO exception
   */
  @Test
  @Ignore
  public void testDownloadSuccess() throws IOException {
    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    List<Playlist> pls = c.getPlaylists();
    Playlist p = ModelUtil.findPlaylistByType( pls, Playlists.PLAYLIST_CLOUD );

    List<MediaFile> mfs = p.getMediaFiles( c );
    MediaFile m = mfs.get( 0 );

    File f = new File( "test.mp3" );
    FileOutputStream fos = new FileOutputStream( f );
    try {
      OutputStream fos2 = m.download( c, fos );

      assertSame( fos, fos2 );
    } finally {
      try {
        f.delete();
      } catch ( Exception e ) {
        ;
      }
    }

  }


  /**
   * Test download progress.
   *
   * @throws IOException the iO exception
   */
  @Test
  @Ignore
  public void testDownloadProgress() throws IOException {
    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    List<Playlist> pls = c.getPlaylists();
    Playlist p = ModelUtil.findPlaylistByType( pls, Playlists.PLAYLIST_CLOUD );

    List<MediaFile> mfs = p.getMediaFiles( c );
    MediaFile m = mfs.get( 0 );

    NetworkProgressListener l = new NetworkProgressListener() {
      @Override
      public void onProgressUpdate(long total, long current) {
        assertTrue( total >= current );
      }
    };

    File f = new File( "test.mp3" );
    FileOutputStream fos = new FileOutputStream( f );
    try {
      OutputStream fos2 = m.download( c, fos );
      assertSame( fos, fos2 );
    } finally {
      try {
        f.delete();
      } catch ( Exception e ) {
        ;
      }
    }
  }
}
