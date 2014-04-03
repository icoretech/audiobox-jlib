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

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.exceptions.ResourceNotFoundException;
import fm.audiobox.core.models.Notification;
import fm.audiobox.core.models.Notifications;
import fm.audiobox.tests.AudioBoxTests;
import fm.audiobox.tests.mocks.MockHttp;
import org.junit.Before;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by keytwo on 24/01/14.
 */
public class NotificationsTest extends AudioBoxTests {

  @Before
  public void setUp() {
    super.setUp();

    try {

      final long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
      final File httpCacheDir = CACHE_DIR;
      HttpResponseCache.install( httpCacheDir, httpCacheSize );

      Configuration config = new Configuration( Configuration.Env.staging );
      config.setDataStoreFactory( new FileDataStoreFactory( DATA_STORE_DIR ) );

      config.setApiKey( fixtures.getString( "authentication.client_id" ) );
      config.setApiSecret( fixtures.getString( "authentication.client_secret" ) );
      config.setHttpTransport( MockHttp.getTransport() );
      JacksonFactory jf = new JacksonFactory();
      config.setJsonFactory( jf );

      c = new Client( config );
    } catch ( IOException | ConfigurationException e ) {
      fail( e.getMessage() );
    }
  }


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
    assertTrue( n.delete( c ) );
  }
}
