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

import com.google.api.client.auth.oauth2.ClientCredentialsTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import fm.audiobox.core.config.Configuration;

import javax.naming.ConfigurationException;

/**
 * Created by keytwo on 17/01/14.
 */
public class Client {

  private static final String TOKEN_SERVER_URL = "";
  private static final String AUTHORIZATION_SERVER_URL = "";
  private static final String[] SCOPE = {"all"};

  private Configuration conf;

  public Client(Configuration conf) throws ConfigurationException {
    conf.checkConfiguration();
    this.conf = conf;
  }

  public Configuration getConf() {
    return conf;
  }


  /** Authorizes the installed application to access user's protected data. */
  private TokenResponse authorize() throws Exception {
    // set up authorization code flow
    ClientCredentialsTokenRequest flow = new ClientCredentialsTokenRequest(getConf().getHttpTransport(), getConf().getJsonFactory(), new GenericUrl(TOKEN_SERVER_URL));
    flow.setClientAuthentication(new BasicAuthentication("", ""));
    TokenResponse response = flow.execute();
    return response;
  }


}
