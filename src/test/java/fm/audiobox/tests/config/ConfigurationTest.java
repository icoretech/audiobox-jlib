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

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.tests.AudioBoxTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by keytwo on 17/01/14.
 */
public class ConfigurationTest extends AudioBoxTest {

  private Configuration c;

  @Before
  public void setUp() {
    super.setUp();
    c = new Configuration(Configuration.Env.development);
  }


  @Test
  public void testShouldRiseConfigurationExceptionOnMissingApiKey() throws ConfigurationException {
    try {
      c.checkConfiguration();
    } catch (ConfigurationException e) {
      assertEquals("API Key (secret) is missing, please provide one.", e.getMessage());
      return;
    }
    fail("Exception message was not the one expected");
  }

  @Test
  public void testShouldRiseConfigurationExceptionOnMissingClientId() throws ConfigurationException {
    try {
      c.setApiKey(fixtures.getString("authentication.client_secret"));
      c.checkConfiguration();
    } catch (ConfigurationException e) {
      assertEquals("Client ID is missing, please provide one.", e.getMessage());
      return;
    }
    fail("Exception message was not the one expected");
  }

  @Test
  public void testShouldRiseConfigurationExceptionOnMissingDataStore() throws ConfigurationException {
    try {
      c.setApiKey(fixtures.getString("authentication.client_id"));
      c.setApiSecret(fixtures.getString("authentication.client_secret"));
      c.checkConfiguration();
    } catch (ConfigurationException e) {
      assertEquals("Data store must be set.", e.getMessage());
      return;
    }
    fail("Exception message was not the one expected");
  }


  @Test
  public void testShouldRiseConfigurationExceptionOnMissingHttpTransport() throws ConfigurationException {
    try {
      c.setApiKey(fixtures.getString("authentication.client_id"));
      c.setApiSecret(fixtures.getString("authentication.client_secret"));
      c.setDataStoreFactory(new FileDataStoreFactory(new File(System.getProperty("user.home"), ".audiobox/abx")));
      c.checkConfiguration();
    } catch (ConfigurationException e) {
      assertEquals("Http transport type must be set", e.getMessage());
      return;
    } catch (IOException e) {
      fail(e.getMessage());
    }
    fail("Exception message was not the one expected");
  }

  @Test
  public void testShouldRiseConfigurationExceptionOnMissingJsonFactory() throws ConfigurationException {
    try {
      c.setApiKey(fixtures.getString("authentication.client_id"));
      c.setApiSecret(fixtures.getString("authentication.client_secret"));
      c.setDataStoreFactory(new FileDataStoreFactory(new File(System.getProperty("user.home"), ".audiobox/abx")));
      c.setHttpTransport(new NetHttpTransport());
      c.checkConfiguration();
    } catch (ConfigurationException e) {
      assertEquals("JSON factory must be set", e.getMessage());
      return;
    } catch (IOException e) {
      fail(e.getMessage());
    }
    fail("Exception message was not the one expected");
  }

}
