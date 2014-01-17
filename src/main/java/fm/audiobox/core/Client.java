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

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.DataStore;
import fm.audiobox.core.config.Configuration;

import javax.naming.ConfigurationException;
import java.io.IOException;

/**
 * Created by keytwo on 17/01/14.
 */
public class Client {

  private Configuration conf;

  private DataStore<StoredCredential> userDb;


  public Client(Configuration conf) throws ConfigurationException, IOException {
    conf.checkConfiguration();
    this.conf = conf;
    this.userDb = StoredCredential.getDefaultDataStore(conf.getDataStoreFactory());
  }


  public Configuration getConf() {
    return conf;
  }


  public TokenResponse authorize(String username, String password) throws IOException {

    PasswordTokenRequest ptr = new PasswordTokenRequest(
      getConf().getHttpTransport(),
      getConf().getJsonFactory(),
      getConf().getEnvTokenUrl(),
      username,
      password);

    ptr.setClientAuthentication(new BasicAuthentication(getConf().getApiKey(), getConf().getApiSecret()));
    TokenResponse response = ptr.execute();

    createCredentialWithRefreshToken(
        getConf().getHttpTransport(),
        getConf().getJsonFactory(),
        response
    );

    return response;
  }

  public Credential createCredentialWithRefreshToken(HttpTransport transport, JsonFactory jsonFactory, TokenResponse tokenResponse) {
    return new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
        .setTransport(transport)
        .setJsonFactory(jsonFactory)
        .setTokenServerUrl(getConf().getEnvTokenUrl())
        .setClientAuthentication(new BasicAuthentication(getConf().getApiKey(), getConf().getApiSecret()))
        .build()
        .setFromTokenResponse(tokenResponse);
  }

}