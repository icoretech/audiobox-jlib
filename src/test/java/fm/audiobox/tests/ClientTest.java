/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 *
 * This file is part of the AudioBox-Jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.tests;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.ValidationException;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.models.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by keytwo on 20/01/14.
 */
public class ClientTest extends AudioBoxTest {

  private static final File DATA_STORE_DIR = new File(System.getProperty("user.home"), ".audiobox/abx");

  private static final File CACHE_DIR = new File(System.getProperty("user.home"), ".audiobox/http");

  protected Client c;


  @Before
  public void setUp() {
    super.setUp();

    try {

      final long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
      final File httpCacheDir = CACHE_DIR;
      HttpResponseCache.install(httpCacheDir, httpCacheSize);

      Configuration config = new Configuration(Configuration.Env.staging);
      config.setDataStoreFactory(new FileDataStoreFactory(DATA_STORE_DIR));

      config.setApiKey(fixtures.getString("authentication.client_id"));
      config.setApiSecret(fixtures.getString("authentication.client_secret"));
      config.setHttpTransport(new NetHttpTransport());
      JacksonFactory jf = new JacksonFactory();
      config.setJsonFactory(jf);

      c = new Client(config);
    } catch (IOException e) {
      fail(e.getMessage());
    } catch (ConfigurationException e) {
      fail(e.getMessage());
    }
  }


  /**
   * Test authorization.
   *
   * @throws ConfigurationException the configuration exception
   */
  @Test
  public void testAuthorization() throws ConfigurationException {
    try {
      TokenResponse r = c.authorize(fixtures.getString("authentication.email"), fixtures.getString("authentication.password"));
      logger.debug(r.getAccessToken());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Test stored credential.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testStoredCredential() throws IOException {
    DataStore<StoredCredential> udb = StoredCredential.getDefaultDataStore(c.getConf().getDataStoreFactory());
    assertFalse(udb.isEmpty());
    User u = c.getUser();
    assertNotNull(u);
  }


}
