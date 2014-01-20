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
import fm.audiobox.core.models.*;
import fm.audiobox.core.utils.ModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by keytwo on 17/01/14.
 */
public class Client {

  private Configuration conf;

  private Logger logger = LoggerFactory.getLogger(Client.class.getSimpleName());

  private DataStore<StoredCredential> userDb;

  private static final String ACCOUNT_TOKENS = "_audiobox_account_tokens";

  private DataStoreCredentialRefreshListener refreshListener;


  /**
   * Instantiates a new Client.
   *
   * @param conf the conf
   * @throws ConfigurationException the configuration exception
   * @throws ConfigurationException the configuration exception
   */
  public Client(Configuration conf) throws ConfigurationException, IOException {
    conf.checkConfiguration();
    this.conf = conf;
    this.userDb = StoredCredential.getDefaultDataStore(conf.getDataStoreFactory());
    this.refreshListener = new DataStoreCredentialRefreshListener(ACCOUNT_TOKENS, getConf().getDataStoreFactory());
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
      HttpResponse rsp = perform(Playlists.getPath());
      if (rsp != null) {
        return rsp.parseAs(UserWrapper.class).getUser();
      }
    } catch (IOException e) {
      logger.error("Unable to parse user: " + e.getMessage());
    }
    return null;
  }


  /**
   * Gets playlists.
   *
   * @return the playlists
   */
  public List<Playlist> getPlaylists() {
    try {
      HttpResponse rsp = perform(Playlists.getPath());
      if (rsp != null) {
        return rsp.parseAs(Playlists.class).getPlaylists();
      }
    } catch (IOException e) {
      logger.error("Unable to parse playlists: " + e.getMessage());
    }

    return null;
  }


  /**
   * Gets the specified playlist.
   *
   * @param token the token of the playlist to get.
   * @return the playlist
   */
  public Playlist getPlaylist(String token) {
    try {
      HttpResponse rsp = perform(ModelUtil.interpolate(Playlist.getPath(), token));
      if (rsp != null) {
        return rsp.parseAs(PlaylistWrapper.class).getPlaylist();
      }
    } catch (IOException e) {
      logger.error("Unable to parse playlists: " + e.getMessage());
    }

    return null;
  }


  /* ================ */
  /*  Private methods */
  /* ================ */


  /**
   * Perform signed http requests and returns the response.
   *
   * @param url the url to make the request against
   * @return the http response, may be null if any error occurs during the request.
   */
  private HttpResponse perform(String url) {
    try {

      HttpRequestFactory rf = getConf().getHttpTransport().createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) throws IOException {
          Credential c = createCredentialWithRefreshToken();
          c.initialize(request);
          request.setParser(new JsonObjectParser(getConf().getJsonFactory()));
        }
      });

      HttpRequest request = rf.buildGetRequest(new GenericUrl(getConf().getEnvBaseUrl() + url));
      return request.execute();

    } catch (IOException e) {
      logger.error("Unable to perform request due to IO Exception: " + e.getMessage());
    }
    return null;
  }


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
        .addRefreshListener(this.refreshListener)
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