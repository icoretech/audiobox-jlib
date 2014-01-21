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
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.store.DataStore;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.ErrorsWrapper;
import fm.audiobox.core.exceptions.ValidationException;
import fm.audiobox.core.models.*;
import fm.audiobox.core.utils.ModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * == User:
 * <ul>
 * <li>GET /api/v1/user.json</li>
 * </ul>
 * <p/>
 * == Playlists:
 * <ul>
 * <li>GET /api/v1/playlists.json</li>
 * <li>GET /api/v1/playlists/:token.json</li>
 * <li>POST /api/v1/playlists.json</li>
 * <li>PUT /api/v1/playlists/:token.json</li>
 * <li>DELETE /api/v1/playlists/:token.json</li>
 * <li>PUT /api/v1/playlists/:token/visible.json</li>
 * <li>PUT /api/v1/playlists/:token/sync.json</li>
 * <li>PUT /api/v1/playlists/sync_all.json</li>
 * </ul>
 * <p/>
 * == MediaFiles:
 * <ul>
 * <li>POST /api/v1/upload</li>
 * <li>OPTIONS /api/v1/upload</li>
 * <li>GET /api/v1/stream/:token</li>
 * <li>GET /api/v1/download/:token</li>
 * <li>GET /api/v1/media_files/:token.json</li>
 * <li>GET /api/v1/playlists/:playlist_token/media_files.json?set=type,token,artist,genre,title,loved,share_token,release_year,len_str,[...]&amp;since=</li>
 * <li>GET /api/v1/playlists/:token/media_files/albums.json</li>
 * <li>GET /api/v1/playlists/:token/media_files/genres.json</li>
 * <li>GET /api/v1/playlists/:token/media_files/artists.json</li>
 * <li>GET /api/v1/playlists/:playlist_token/media_files/fingerprints.json</li>
 * <li>POST /api/v1/playlists/:playlist_token/media_files/add.json?tokens[]=</li>
 * <li>DELETE /api/v1/playlists/:playlist_token/media_files/remove.json?tokens[]=</li>
 * <li>PUT /api/v1/media_files/:token.json</li>
 * <li>PUT /api/v1/media_files/multiupdate.json?tokens[]=</li>
 * <li>DELETE /api/v1/media_files/:token.json</li>
 * <li>DELETE /api/v1/media_files/multidestroy.json?tokens[]=</li>
 * </ul>
 * <p/>
 * == Special Actions
 * <ul>
 * <li>POST /api/v1/media_files/:token/scrobble.json</li>
 * <li>GET /api/v1/media_files/:token/lyrics.json</li>
 * <li>POST /api/v1/media_files/:token/love.json</li>
 * <li>POST /api/v1/media_files/:token/unlove.json</li>
 * <li>POST /api/v1/media_files/:token/toggle_love.json</li>
 * </ul>
 * <p/>
 * == Notifications
 * <ul>
 * <li>GET /api/v1/notifications.json</li>
 * <li>DELETE /api/v1/notifications/:id.json</li>
 * </ul>
 * <p/>
 * Created by keytwo on 17/01/14.
 */
public class Client {

  private Configuration conf;

  private Logger logger = LoggerFactory.getLogger(Client.class.getSimpleName());

  private DataStore<StoredCredential> userDb;

  private static final String ACCOUNT_TOKENS = "_audiobox_account_tokens";

  private DataStoreCredentialRefreshListener refreshListener;

  private JsonObjectParser jsonObjectParser;


  /**
   * Instantiates a new Client.
   *
   * @param conf the conf
   *
   * @throws ConfigurationException the configuration exception
   * @throws ConfigurationException the configuration exception
   */
  public Client(Configuration conf) throws ConfigurationException, IOException {
    conf.checkConfiguration();
    this.conf = conf;
    this.userDb = StoredCredential.getDefaultDataStore(conf.getDataStoreFactory());
    this.refreshListener = new DataStoreCredentialRefreshListener(ACCOUNT_TOKENS, getConf().getDataStoreFactory());
    this.jsonObjectParser = new JsonObjectParser(getConf().getJsonFactory());
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
   *
   * @return the token response
   *
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
      HttpResponse rsp = doGET(Playlists.getPath());
      if (rsp.isSuccessStatusCode()) {
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
      HttpResponse rsp = doGET(Playlists.getPath());
      if (rsp.isSuccessStatusCode()) {
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
   *
   * @return the playlist
   */
  public Playlist getPlaylist(String token) {
    try {
      HttpResponse rsp = doGET(ModelUtil.interpolate(Playlist.getPath(), token));
      if (rsp.isSuccessStatusCode()) {
        return rsp.parseAs(PlaylistWrapper.class).getPlaylist();
      }
    } catch (IOException e) {
      logger.error("Unable to parse playlists: " + e.getMessage());
    }

    return null;
  }


  /**
   * Create new playlist.
   *
   * @param name the name
   *
   * @return the playlist
   *
   * @throws ValidationException if an error occurs while trying to save the playlist
   */
  public Playlist createNewPlaylist(String name) throws ValidationException {
    try {
      Playlist p = new Playlist(name);
      HttpResponse rsp = doPOST(Playlists.getPath(), new JsonHttpContent(getConf().getJsonFactory(), p));
      if (rsp.isSuccessStatusCode()) {
        return rsp.parseAs(PlaylistWrapper.class).getPlaylist();
      }
    } catch (IOException e) {
      logger.error("Unable to perform request due to IO Exception: " + e.getMessage());
    }
    return null;
  }


  /**
   * Perform signed http requests and returns the response.
   *
   * @param path the url to make the request against
   * @param data the data to send with the post request
   *
   * @return the http response, may be null if any error occurs during the request.
   */
  public HttpResponse doPUT(String path, HttpContent data) {
    try {
      return getRequestFactory().buildPutRequest(new GenericUrl(getConf().getEnvBaseUrl() + path), data).execute();
    } catch (IOException e) {
      logger.error("Unable to perform PUT due to IO Exception: " + e.getMessage());
    }
    return null;
  }


  /**
   * Performs a DELETE request to the given path.
   *
   * @param path the url
   *
   * @return the http response
   */
  public HttpResponse doDELETE(String path) {
    try {
      HttpResponse response = getRequestFactory().buildDeleteRequest(new GenericUrl(getConf().getEnvBaseUrl() + path)).execute();
      if (response.isSuccessStatusCode()) {
        return response;
      }
    } catch (IOException e) {
      logger.error("Unable to perform DELETE due to IO Exception: " + e.getMessage());
    }
    return null;
  }




  /* ================ */
  /*  Private methods */
  /* ================ */


  /**
   * Perform signed http requests and returns the response.
   *
   * @param path the url to make the request against
   *
   * @return the http response, may be null if any error occurs during the request.
   */
  private HttpResponse doGET(String path) {
    try {
      return getRequestFactory().buildGetRequest(new GenericUrl(getConf().getEnvBaseUrl() + path)).execute();
    } catch (IOException e) {
      logger.error("Unable to perform GET due to IO Exception: " + e.getMessage());
    }
    return null;
  }


  /**
   * Perform signed http requests and returns the response.
   *
   * @param path the url to make the request against
   * @param data the data to send with the post request
   *
   * @return the http response, may be null if any error occurs during the request.
   */
  private HttpResponse doPOST(String path, HttpContent data) {
    try {
      HttpResponse response = getRequestFactory().buildPostRequest(new GenericUrl(getConf().getEnvBaseUrl() + path), data).execute();
      if (response.getStatusCode() == 422) {
        throw new ValidationException(response.parseAs(ErrorsWrapper.class).getErrors(), response.getStatusCode());
      }
      return response;

    } catch (IOException e) {
      logger.error("Unable to perform POST due to IO Exception: " + e.getMessage());
    }
    return null;
  }


  /**
   * Gets request factory.
   *
   * @return the request factory
   */
  private HttpRequestFactory getRequestFactory() {
    return getConf().getHttpTransport().createRequestFactory(new HttpRequestInitializer() {
      @Override
      public void initialize(HttpRequest request) throws IOException {
        Credential c = createCredentialWithRefreshToken();
        c.initialize(request);
        request.setParser(jsonObjectParser);
        request.setThrowExceptionOnExecuteError(false);
      }
    });
  }


  /**
   * Build not signed credential.
   *
   * @return the credential
   *
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
   *
   * @return the credential
   *
   * @throws IOException the iO exception
   */
  private Credential createCredentialWithRefreshToken(TokenResponse tokenResponse) throws IOException {
    return buildNotSignedCredential().setFromTokenResponse(tokenResponse);
  }


  /**
   * Create credential with refresh token.
   *
   * @param storedCredential the stored credential
   *
   * @return the credential
   *
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
   *
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