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

package fm.audiobox.tests.unit.exceptions;

import fm.audiobox.core.exceptions.SystemOverloadedException;
import fm.audiobox.core.models.MediaFile;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.tests.mocks.MockHttp;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * The type System overloaded exception tests.
 */
public class SystemOverloadedExceptionTests extends AudioBoxTests {

  /**
   * Test system overloaded exception.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testSystemOverloadedException() throws IOException {
    try {
      MediaFile m = MediaFile.load(c, "c_1813602a05000a1756b914");
      c.getConf().setHttpTransport( MockHttp.getTransport(HttpStatus.SC_SERVICE_UNAVAILABLE, "503") );
      m.scrobble( c );
      fail( "Should rise system overloaded exception" );
    } catch ( Exception e ) {
      assertTrue( e instanceof SystemOverloadedException);
      SystemOverloadedException soe = (SystemOverloadedException) e;
      assertEquals( 10, soe.getRetryAfterInSeconds() );
    }
  }


  /**
   * Test void system overloaded exception.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testVoidSystemOverloadedException() throws IOException {
    SystemOverloadedException e = new SystemOverloadedException( null );
    assertEquals( -1, e.getRetryAfterInSeconds() );
  }

}
