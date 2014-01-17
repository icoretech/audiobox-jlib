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

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.tests.AudioBoxTest;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.IOException;

/**
 * Created by keytwo on 17/01/14.
 */
public class ConfigurationTest extends AudioBoxTest {

  @Test
  public void testConfiguration() throws ConfigurationException {
    Configuration config = new Configuration(Configuration.Env.staging);
    config.build();
    config.setApiKey("3ebef5f53a6d7f01fb052ca89ef17a37");
    config.setApiSecret("da75257c0070a8405e029a48b2e05bd1");
    config.setHttpTransport(new NetHttpTransport());
    config.setJsonFactory(new JacksonFactory());

    logger.debug("Token URL: " + config.getEnvTokenUrl());

    Client c = new Client(config);
    try {
      TokenResponse r = c.authorize("valerio@icorete.ch", "?!M3rill");
      logger.debug(r.getAccessToken());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
