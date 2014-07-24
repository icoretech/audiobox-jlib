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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;

/**
 * Created based on http://stackoverflow.com/questions/1339437/inputstream-or-reader-wrapper-for-progress-reporting
 */
public class ProgressInputStream extends FileInputStream {

  private final PropertyChangeSupport propertyChangeSupport;

  private final long maxNumBytes;

  private volatile long totalNumBytesRead;

  public static final String TOTAL_NUM_BYTE_READ = "totalNumBytesRead";


  public ProgressInputStream(File in, long maxNumBytes) throws FileNotFoundException {
    super( in );
    this.propertyChangeSupport = new PropertyChangeSupport( this );
    this.maxNumBytes = maxNumBytes;
  }


  public long getMaxNumBytes() {
    return maxNumBytes;
  }


  public long getTotalNumBytesRead() {
    return totalNumBytesRead;
  }


  public void addPropertyChangeListener(PropertyChangeListener l) {
    propertyChangeSupport.addPropertyChangeListener( l );
  }


  public void removePropertyChangeListener(PropertyChangeListener l) {
    propertyChangeSupport.removePropertyChangeListener( l );
  }


  @Override
  public int read() throws IOException {
    int b = super.read();
    updateProgress( 1 );
    return b;
  }


  @Override
  public int read(byte[] b) throws IOException {
    return ( int ) updateProgress( super.read( b ) );
  }


  @Override
  public int read(byte[] b, int off, int len) throws IOException {
    return ( int ) updateProgress( super.read( b, off, len ) );
  }


  @Override
  public long skip(long n) throws IOException {
    return updateProgress( super.skip( n ) );
  }


  @Override
  public void mark(int readlimit) {
    throw new UnsupportedOperationException();
  }


  @Override
  public void reset() throws IOException {
    throw new UnsupportedOperationException();
  }


  @Override
  public boolean markSupported() {
    return false;
  }


  private long updateProgress(long numBytesRead) {
    if ( numBytesRead > 0 ) {
      long oldTotalNumBytesRead = this.totalNumBytesRead;
      this.totalNumBytesRead += numBytesRead;
      propertyChangeSupport.firePropertyChange( TOTAL_NUM_BYTE_READ, oldTotalNumBytesRead, this.totalNumBytesRead );
    }

    return numBytesRead;
  }
}