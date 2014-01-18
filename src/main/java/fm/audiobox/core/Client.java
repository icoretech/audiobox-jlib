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
import com.google.api.client.http.*;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.store.DataStore;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.models.User;
import fm.audiobox.core.models.UserWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.io.IOException;

/**
 * Created by keytwo on 17/01/14.
 */
public class Client {

  private Configuration conf;

  private Logger logger = LoggerFactory.getLogger(Client.class.getSimpleName());

  private DataStore<StoredCredential> userDb;

  private static final String ACCOUNT_TOKENS = "_audiobox_account_tokens";


  /**
   * Instantiates a new Client.
   *
   * @param conf the conf
   * @throws ConfigurationException the configuration exception
   * @throws IOException the iO exception
   */
  public Client(Configuration conf) throws ConfigurationException, IOException {
    conf.checkConfiguration();
    this.conf = conf;
    this.userDb = StoredCredential.getDefaultDataStore(conf.getDataStoreFactory());
  }


  /**
   * Gets conf.
   *
   * @return the conf
   */
  public Configuration getConf() {
    return conf;
  }


  /**
   * Authorize token response.
   *
   * @param username the username
   * @param password the password
   * @return the token response
   * @throws IOException the iO exception
   */
  public TokenResponse authorize(String username, String password) throws IOException {

    PasswordTokenRequest ptr = new PasswordTokenRequest(
        getConf().getHttpTransport(),
        getConf().getJsonFactory(),
        getConf().getEnvTokenUrl(),
        username,
        password);

    ptr.setClientAuthentication(new BasicAuthentication(getConf().getApiKey(), getConf().getApiSecret()));
    TokenResponse response = ptr.execute();

    StoredCredential sc = new StoredCredential(createCredentialWithRefreshToken(response));
    userDb.set(ACCOUNT_TOKENS, sc);
    logger.info("Saved credentials: " + sc.toString());

    return response;
  }


  /**
   * Gets user.
   *
   * @return the user
   */
  public User getUser() {
    try {
      HttpRequestFactory rf = getConf().getHttpTransport().createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) throws IOException {
          Credential c = createCredentialWithRefreshToken();
          c.initialize(request);
          request.setParser(new JsonObjectParser(getConf().getJsonFactory()));
        }
      });

      String url = getConf().getEnvBaseUrl() + UserWrapper.getPath();
      HttpRequest request = rf.buildGetRequest(new GenericUrl(url));
      UserWrapper userWrapper = request.execute().parseAs(UserWrapper.class);

      return userWrapper.getUser();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


  /* ================ */
  /*  Private methods */
  /* ================ */


  /**
   * Build not signed credential.
   *
   * @return the credential
   * @throws IOException the iO exception
   */
  private Credential buildNotSignedCredential() throws IOException {
    return new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
        .setTransport(getConf().getHttpTransport())
        .setJsonFactory(getConf().getJsonFactory())
        .setTokenServerUrl(getConf().getEnvTokenUrl())
        .setClientAuthentication(new BasicAuthentication(getConf().getApiKey(), getConf().getApiSecret()))
        .addRefreshListener(new DataStoreCredentialRefreshListener(ACCOUNT_TOKENS, getConf().getDataStoreFactory()))
        .build();
  }


  /**
   * Create credential with refresh token.
   *
   * @param tokenResponse the token response
   * @return the credential
   * @throws IOException the iO exception
   */
  private Credential createCredentialWithRefreshToken(TokenResponse tokenResponse) throws IOException {
    return buildNotSignedCredential().setFromTokenResponse(tokenResponse);
  }


  /**
   * Create credential with refresh token.
   *
   * @param storedCredential the stored credential
   * @return the credential
   * @throws IOException the iO exception
   */
  private Credential createCredentialWithRefreshToken(StoredCredential storedCredential) throws IOException {
    return buildNotSignedCredential()
        .setAccessToken(storedCredential.getAccessToken())
        .setExpiresInSeconds(storedCredential.getExpirationTimeMilliseconds())
        .setRefreshToken(storedCredential.getRefreshToken());
  }


  /**
   * Create credential with refresh token.
   *
   * @return the credential
   * @throws IOException the iO exception
   */
  private Credential createCredentialWithRefreshToken() throws IOException {
    return createCredentialWithRefreshToken(getStoredCredential());
  }


  /**
   * Gets stored credential.
   *
   * @return the stored credential
   */
  private StoredCredential getStoredCredential() {
    StoredCredential s = null;
    try {
      s = userDb.get(ACCOUNT_TOKENS);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return s;
  }


}