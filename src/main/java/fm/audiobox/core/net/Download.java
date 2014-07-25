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

package fm.audiobox.core.net;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Downloads the media to specified file
 */
public class Download {

  private enum State {ready, started, completed}

  private static final Logger logger = LoggerFactory.getLogger( Upload.class );

  private static final int CHUNK = 4096;

  private OutputStream outputStream;

  private InputStream inputStream;

  private NetworkProgressListener listener;

  private State state = State.ready;

  private long length;


  /**
   * Instantiates a new Upload.
   *
   * @param inputStream  the input stream
   * @param outputStream the output stream to send data to
   * @param listener     the listener
   */
  public Download(InputStream inputStream, OutputStream outputStream, NetworkProgressListener listener, long length) {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
    this.length = length;
    this.setDownloadProgressListener( listener );
  }


  /**
   * Sets download progress listener.
   *
   * @param listener the listener
   */
  public void setDownloadProgressListener(NetworkProgressListener listener) {
    this.listener = listener;
  }


  /**
   * Start download
   */
  public OutputStream start() {

    if ( this.state != State.ready ) {
      throw new IllegalStateException( "Download has already started or completed" );
    }

    this.state = State.started;

    try {
      int read;
      byte[] bytes = new byte[ CHUNK ];
      int progress = 0;
      while ( ( read = inputStream.read( bytes ) ) != -1 ) {
        outputStream.write( bytes, 0, read );
        if ( listener != null ) {
          progress += read;

          listener.onProgressUpdate( length, progress );
        }
      }

      logger.debug( "Download completed" );

    } catch ( IOException e ) {
      logger.error( e.getMessage() );

    } finally {

      if ( inputStream != null ) {
        try {
          inputStream.close();
        } catch ( IOException e ) {
          logger.error( e.getMessage() );
        }
      }

      if ( outputStream != null ) {
        try {
          outputStream.close();
        } catch ( IOException e ) {
          logger.error( e.getMessage() );
        }
      }

      this.state = State.completed;
    }

    return outputStream;
  }

}
