/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 * This file is part of the audiobox-jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.tests.models;

import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.ResourceNotFoundException;
import fm.audiobox.core.models.Notification;
import fm.audiobox.core.models.Notifications;
import fm.audiobox.tests.AudioBoxTests;
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
    assertEquals( "[Ubuntu One Music] Credentials are missing. Please authenticate with the service.", noty.getBody() );
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
