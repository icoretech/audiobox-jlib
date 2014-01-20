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

package fm.audiobox.tests.config;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.models.User;
import fm.audiobox.tests.AudioBoxTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by keytwo on 17/01/14.
 */
public class ConfigurationTest extends AudioBoxTest {

  private Configuration c;

  @Before
  public void setUp() {
    c = new Configuration(Configuration.Env.development);
  }

  @Test(expected = ConfigurationException.class)
  public void testShouldRiseConfigurationExceptionOnMissingApiKey() throws ConfigurationException {
    c.checkConfiguration();
  }

}
