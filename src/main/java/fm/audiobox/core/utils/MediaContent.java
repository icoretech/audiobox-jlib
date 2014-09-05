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

package fm.audiobox.core.utils;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.util.Preconditions;
import fm.audiobox.core.net.NetworkProgressListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A wrapper content type for MediaFile.
 * Used for upload purpose.
 */
public class MediaContent extends AbstractInputStreamContent {

  private final File file;

  private ProgressInputStream pis;

  private NetworkProgressListener upl;


  /**
   * Instantiates a new Media content.
   *
   * @param type Content type or
   *             for none
   * @param file file
   *
   * @throws FileNotFoundException the file not found exception
   */
  public MediaContent(String type, File file) throws FileNotFoundException {
    super( type );
    this.file = Preconditions.checkNotNull( file );
    this.pis = new ProgressInputStream( file, file.length() );
  }


  /**
   * Sets upload progress listener.
   *
   * @param networkProgressListener the upload progress listener
   */
  public void setUploadProgressListener(NetworkProgressListener networkProgressListener) {
    this.upl = networkProgressListener;
    this.pis.addPropertyChangeListener( new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if ( ProgressInputStream.TOTAL_NUM_BYTE_READ.equals( evt.getPropertyName() ) ) {
          upl.onProgressUpdate( ( ( ProgressInputStream ) evt.getSource() ).getMaxNumBytes(), ( Long ) evt.getNewValue() );
        }
      }
    } );
  }


  @Override
  public long getLength() {
    return file.length();
  }


  @Override
  public boolean retrySupported() {
    return true;
  }


  @Override
  public InputStream getInputStream() throws FileNotFoundException {
    return this.pis;
  }


  /**
   * Gets file.
   *
   * @return the file
   */
  public File getFile() {
    return file;
  }

}
