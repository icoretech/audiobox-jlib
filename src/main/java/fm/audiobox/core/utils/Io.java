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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Simple IO utils for plain response contents.
 */
public class Io {

  private static final Logger logger = LoggerFactory.getLogger( Io.class );


  /**
   * Content to string.
   *
   * @param inputStream the input stream
   *
   * @return the string
   */
  public static String contentToString(InputStream inputStream) {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();

    try {
      br = new BufferedReader( new InputStreamReader( inputStream ) );
      String address;
      while ( ( address = br.readLine() ) != null ) {
        sb.append( address );
      }

    } catch ( IOException e ) {

      logger.error( e.getMessage() );

    } finally {

      if ( br != null ) {
        try {
          br.close();

        } catch ( IOException e ) {
          logger.error( e.getMessage() );

        }
      }
    }

    return sb.toString();
  }
}
