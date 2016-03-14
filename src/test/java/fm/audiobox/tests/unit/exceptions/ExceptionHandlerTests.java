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


import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.ExceptionHandler;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.tests.mocks.MockHttp;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Exception Handler tests
 */
public class ExceptionHandlerTests extends AudioBoxTests {

  @Test
  public void testNotHandledExceptionShouldBeRethrown() {
    ExceptionHandler eh = new ExceptionHandler() {

      @Override
      public boolean onException(AudioBoxException e) {
        return false;
      }
    };

    c.getConf().setHttpTransport( MockHttp.getInvalidRefreshTokenHttpTransport() );
    c.getConf().setExceptionHandler( eh );

    try {
      c.getPlaylists();
      fail("Exception should be thrown");
    } catch ( IOException e ) {
       assertTrue( e instanceof AudioBoxException );
    }
  }

  @Test
  public void testHandledExceptionShouldNotBeRethrown() {
    ExceptionHandler eh = new ExceptionHandler() {

      @Override
      public boolean onException(AudioBoxException e) {
        return true;
      }
    };

    c.getConf().setHttpTransport( MockHttp.getInvalidRefreshTokenHttpTransport() );
    c.getConf().setExceptionHandler( eh );

    try {
      List<Playlist> pls = c.getPlaylists();
      assertNull("Loaded playlist should be null", pls);
    } catch ( IOException e ) {
      fail( "Exception should not be thrown" );
    }
  }
}
