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

package fm.audiobox.core.utils;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Based on: http://javarevisited.blogspot.com/2013/06/how-to-generate-md5-checksum-for-files.html#ixzz38QFXWUu0
 */
public class MD5Checksum {

  private static final Logger logger = LoggerFactory.getLogger( MD5Checksum.class );


  /**
   * Calculate checksum of a File using MD5 algorithm
   */
  public static String checkSum(InputStream is) throws NoSuchAlgorithmException {
    String checksum = null;
    try {
      MessageDigest md = MessageDigest.getInstance( "MD5" );
      md.reset();

      // Using MessageDigest update() method to provide input
      byte[] buffer = new byte[ 8192 ];
      int numOfBytesRead;
      while ( ( numOfBytesRead = is.read( buffer ) ) > 0 ) {
        md.update( buffer, 0, numOfBytesRead );
      }
      byte[] hash = md.digest();
      checksum = new String( Hex.encodeHex( hash ) );
    } catch ( IOException ex ) {
      logger.error( ex.getMessage() );
    }

    return checksum;
  }

}
