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

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.models.Notifications;
import fm.audiobox.tests.AudioBoxTests;
import fm.audiobox.tests.mocks.AudioBoxMockHttpTransportFactory;
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
      config.setHttpTransport( new NetHttpTransport() );
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
  public void testNotifications() throws AudioBoxException {
    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getNotificationsHttpTransport() );
    Notifications n = c.getNotifications();
    assertNotNull( n );
  }
}
