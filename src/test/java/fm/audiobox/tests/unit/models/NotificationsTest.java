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

package fm.audiobox.tests.unit.models;

import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.ResourceNotFoundException;
import fm.audiobox.core.models.Notification;
import fm.audiobox.core.models.Notifications;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class NotificationsTest extends AudioBoxTests {


  /**
   * Test notifications.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testNotificationsParsing() throws IOException {
    Notifications n = c.getNotifications();
    assertNotNull( n );
    assertNotNull( n.getNotifications() );
    assertEquals( 1, n.getPage() );
    assertEquals( 17, n.getTotal() );
    assertEquals( 17, n.getNotifications().size() );

    Notification noty = n.getNotifications().get( 0 );
    assertEquals( 84, noty.getId() );
    assertEquals( "[Mega] Credentials are missing. Please authenticate with the service.", noty.getBody() );
    assertEquals( "error", noty.getLevel() );
    assertEquals( "2014-01-16T13:39:49.038Z", noty.getCreatedAt() );
  }


  /**
   * Test invalid notification deletion.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test( expected = ResourceNotFoundException.class )
  public void testInvalidNotificationDeletion() throws IOException {
    Notification n = new Notification();
    n.delete( c );
  }


  /**
   * Test valid notification deletion.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testValidNotificationDeletion() throws IOException {
    Notifications ns = c.getNotifications();
    Notification n = ns.getNotifications().get( 0 );
    assertSame( n, n.delete( c ) );
  }
}
