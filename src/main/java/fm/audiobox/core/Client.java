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

package fm.audiobox.core;

import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.PasswordTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import fm.audiobox.core.config.Configuration;

import javax.naming.ConfigurationException;
import java.io.IOException;

/**
 * Created by keytwo on 17/01/14.
 */
public class Client {

  private Configuration conf;

  public Client(Configuration conf) throws ConfigurationException {
    conf.checkConfiguration();
    this.conf = conf;
  }

  public Configuration getConf() {
    return conf;
  }


  public TokenResponse authorize(String username, String password) throws IOException {
    PasswordTokenRequest ptr = new PasswordTokenRequest(getConf().getHttpTransport(), getConf().getJsonFactory(), new GenericUrl(conf.getEnvTokenUrl()), username, password);
    ptr.setClientAuthentication(new ClientParametersAuthentication(conf.getApiKey(), conf.getApiSecret()));
    TokenResponse response = ptr.execute();
    return response;
  }


}
