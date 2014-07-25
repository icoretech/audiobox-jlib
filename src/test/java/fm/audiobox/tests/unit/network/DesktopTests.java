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

package fm.audiobox.tests.unit.network;

import fm.audiobox.tests.mocks.MockHttp;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Desktop Tests
 *
 * Created by keytwo on 25/07/14.
 */
public class DesktopTests extends AudioBoxTests {

  /**
   * Test daemon running.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testDaemonRunning() throws IOException {
    assertTrue( c.isDaemonRunning() );
  }


  /**
   * Test daemon not running.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testDaemonNotRunning() throws IOException {
    c.getConf().setHttpTransport( MockHttp.getDaemonNotRunningTransport() );
    assertFalse( c.isDaemonRunning() );
  }
}
