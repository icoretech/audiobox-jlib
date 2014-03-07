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
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.store.DataStore;
import fm.audiobox.core.auth.Credential;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.*;
import fm.audiobox.core.models.*;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.core.utils.ModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
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
 * TODO: review exceptions javadoc: for each method explain the IO exception given.
 * TODO: better credential specifications.
 */
public class Client {

  private Configuration conf;

  private Logger logger = LoggerFactory.getLogger( Client.class.getSimpleName() );

  private DataStore<StoredCredential> userDb;

  private static final String ACCOUNT_TOKENS = "_audiobox_account_tokens";

  private static final String UPLOAD_PATH = "/api/v1/upload";

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
    this.userDb = StoredCredential.getDefaultDataStore( conf.getDataStoreFactory() );
    this.refreshListener = new DataStoreCredentialRefreshListener( ACCOUNT_TOKENS, getConf().getDataStoreFactory() );
    this.jsonObjectParser = new JsonObjectParser( getConf().getJsonFactory() );
  }


  /**
   * Gets the global client configuration.
   *
   * @return the configuration
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
   * @return the token response, may be null in case of any IOExceptions
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public TokenResponse authorize(String username, String password) throws AudioBoxException {
    try {
      PasswordTokenRequest ptr = new PasswordTokenRequest(
          getConf().getHttpTransport(),
          getConf().getJsonFactory(),
          getConf().getEnvTokenUrl(),
          username,
          password );

      ptr.setClientAuthentication( new BasicAuthentication( getConf().getApiKey(), getConf().getApiSecret() ) );

      TokenResponse response = ptr.execute();

      StoredCredential sc = new StoredCredential( ( com.google.api.client.auth.oauth2.Credential ) createCredentialWithRefreshToken( response ) );
      try {
        userDb.set( ACCOUNT_TOKENS, sc );
        logger.info( "Saved credentials: " + sc.toString() );
      } catch ( IOException e ) {
        logger.error( "Unable to save credentials: " + e.getMessage() );
      }

      return response;
    } catch ( TokenResponseException e ) {
      throw new AuthorizationException( e );
    } catch ( IOException e ) {
      logger.error( "Unable to fulfill the request due to an error: " + e.getMessage() );
      return null;
    }
  }


  /**
   * Returns information about the currently logged in user.
   *
   * @return the user
   *
   * @throws AudioBoxException the audio box exception
   */
  public User getUser() throws AudioBoxException {
    try {
      HttpResponse rsp = doGET( UserWrapper.getPath() );
      if ( rsp.isSuccessStatusCode() ) {
        return rsp.parseAs( UserWrapper.class ).getUser();
      }
    } catch ( AudioBoxException e ) {
      throw e; // Relaunch exception
    } catch ( IOException e ) {
      logger.error( "Unable to parse user: " + e.getMessage() );
    }
    return null;
  }


  /**
   * Gets user's playlists.
   *
   * @return the playlists
   *
   * @throws AudioBoxException the audio box exception
   */
  public List<Playlist> getPlaylists() throws AudioBoxException {
    try {
      HttpResponse rsp = doGET( Playlists.getPath() );
      if ( rsp.isSuccessStatusCode() ) {
        return rsp.parseAs( Playlists.class ).getPlaylists();
      }
    } catch ( AudioBoxException e ) {
      throw e; // Relaunch exception
    } catch ( IOException e ) {
      logger.error( "Unable to parse playlists: " + e.getMessage() );
    }

    return null;
  }


  /**
   * Gets the specified playlist.
   * Triggers Smart Playlist compilation if the requested playlist is a SmartPlaylist.
   *
   * @param token the token of the playlist to get.
   *
   * @return the playlist
   *
   * @throws AudioBoxException the audio box exception
   */
  public Playlist getPlaylist(String token) throws AudioBoxException {
    try {
      HttpResponse rsp = doGET( ModelUtil.interpolate( Playlist.getPath(), token ) );
      if ( rsp.isSuccessStatusCode() ) {
        return rsp.parseAs( PlaylistWrapper.class ).getPlaylist();
      }
    } catch ( AudioBoxException e ) {
      throw e; // Relaunch exception
    } catch ( IOException e ) {
      logger.error( "Unable to parse playlists: " + e.getMessage() );
    }

    return null;
  }


  /**
   * Gets user's notifications.
   *
   * @return the notifications
   *
   * @throws AudioBoxException the audio box exception
   */
  public Notifications getNotifications() throws AudioBoxException {
    try {
      HttpResponse rsp = doGET( Notifications.getPath() );
      if ( rsp.isSuccessStatusCode() ) {
        return rsp.parseAs( Notifications.class );
      }
    } catch ( AudioBoxException e ) {
      throw e; // Relaunch exception
    } catch ( IOException e ) {
      logger.error( "Unable to parse notifications: " + e.getMessage() );
    }

    return null;
  }


  /**
   * Upload media files in AudioBox Cloud.
   * <p>
   * If the subscription is not valid the upload server will refuse the uploads with a 402 HTTP status (payment required).
   * If a media file already exists on AudioBox Cloud the upload server will return a 409 HTTP status (conflict).
   * The application should ensure to accept those errors and retry accordingly after few minutes.
   * On successful upload the server returns a 202 with additional information in the response's body,
   * including the token assigned to a single media file.
   * If you are uploading multiple media files in a single pass this endpoint will return a JSON array in the response's body.
   * Files uploaded through this endpoint will go directly into the CloudPlaylist.
   * </p>
   *
   * @param file the file to upload on AudioBox
   *
   * @return true if upload succeed, false or throws exception on any other case.
   *
   * @throws IOException if 422, 503 or 500 errors occurs (additional information in the exception body).
   */
  public boolean upload(final File file) throws IOException {

    FileContent fileContent = new FileContent( URLConnection.guessContentTypeFromName( file.getAbsolutePath() ), file );
    MultipartFormDataContent multipart = new MultipartFormDataContent();
    multipart.addPart( new MultipartContent.Part( fileContent ), "files[]", file.getName() );
    HttpResponse rsp = doPOST( UPLOAD_PATH, multipart );

    return rsp.getStatusCode() == HttpStatus.SC_NO_CONTENT;
  }


  /**
   * Perform signed GET requests and returns the response.
   *
   * @param path the url to make the request against
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doGET(String path) throws IOException {
    return doGET( path, null );
  }


  /**
   * Perform signed GET requests and returns the response.
   *
   * @param path   the path
   * @param parser the parser
   *
   * @return the http response
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doGET(String path, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.GET, path, null, parser );
  }


  /**
   * Perform signed PUT requests and returns the response.
   *
   * @param path the url to make the request against
   * @param data the data to send with the post request
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doPUT(String path, HttpContent data) throws IOException {
    return doPUT( path, data, null );
  }


  /**
   * Perform signed PUT requests and returns the response.
   *
   * @param path   the path
   * @param data   the data
   * @param parser the parser
   *
   * @return the http response
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doPUT(String path, HttpContent data, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.PUT, path, data, parser );
  }


  /**
   * Performs a DELETE request to the given path.
   *
   * @param path the url
   *
   * @return the http response
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doDELETE(String path) throws IOException {
    return doDELETE( path, null );
  }


  /**
   * Performs a DELETE request to the given path.
   *
   * @param path   the path
   * @param parser the parser
   *
   * @return the http response
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doDELETE(String path, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.DELETE, path, null, parser );
  }


  /**
   * Perform signed POST requests and returns the response.
   *
   * @param path the url to make the request against
   * @param data the data to send with the post request
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doPOST(String path, HttpContent data) throws IOException {
    return doPOST( path, data, null );
  }


  /**
   * Perform signed POST requests and returns the response.
   *
   * @param path   the path
   * @param data   the data
   * @param parser the parser
   *
   * @return the http response
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doPOST(String path, HttpContent data, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.POST, path, data, parser );
  }


  /* ================ */
  /*  Private methods */
  /* ================ */


  /**
   * Do request.
   *
   * @param method the method to use
   * @param path   the path to call
   * @param data   the data to send
   * @param parser the parser to use for the resulting object
   *
   * @return the http response
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  private HttpResponse doRequest(String method, String path, HttpContent data, JsonObjectParser parser) throws IOException {
    try {
      HttpResponse response = getRequestFactory( parser ).buildRequest( method, new GenericUrl( getConf().getEnvBaseUrl() + path ), data ).execute();
      validateResponse( response );
      return response;

    } catch ( AudioBoxException e ) {
      throw e; // Relaunch exception

    } catch ( TokenResponseException e ) {
      throw new AuthorizationException( e );

    } catch ( IOException e ) {
      logger.error( "Unable to perform " + method + " due to IO Exception: " + e.getMessage() );
      throw e;
    }
  }


  /**
   * Gets request factory.
   *
   * @param parser the parser
   *
   * @return the request factory
   */
  private HttpRequestFactory getRequestFactory(final JsonObjectParser parser) {
    return getConf().getHttpTransport().createRequestFactory( new HttpRequestInitializer() {

      @Override
      public void initialize(HttpRequest request) throws IOException {
        HttpRequestInitializer c = createCredentialWithRefreshToken();
        c.initialize( request );
        request.setParser( parser == null ? jsonObjectParser : parser );
        request.setThrowExceptionOnExecuteError( false );
      }
    } );
  }


  /**
   * Build not signed credential.
   *
   * @return the credential
   */
  private Credential buildNotSignedCredential() {
    return new Credential.Builder( BearerToken.authorizationHeaderAccessMethod() )
        .setTransport( getConf().getHttpTransport() )
        .setJsonFactory( getConf().getJsonFactory() )
        .setTokenServerUrl( getConf().getEnvTokenUrl() )
        .setClientAuthentication( new BasicAuthentication( getConf().getApiKey(), getConf().getApiSecret() ) )
        .addRefreshListener( this.refreshListener )
        .build();
  }


  /**
   * Create credential with refresh token.
   *
   * @return the credential
   */
  private HttpRequestInitializer createCredentialWithRefreshToken() {
    return createCredentialWithRefreshToken( getStoredCredential() );
  }


  /**
   * Create credential starting from the token response with refresh token.
   *
   * @param tokenResponse the token response to use for Credential building
   *
   * @return the credential
   */
  private HttpRequestInitializer createCredentialWithRefreshToken(TokenResponse tokenResponse) {
    return buildNotSignedCredential().setFromTokenResponse( tokenResponse );
  }


  /**
   * Create credential starting from the stored credential with refresh token.
   *
   * @param storedCredential the stored credential to use for Credential building
   *
   * @return the credential
   */
  private HttpRequestInitializer createCredentialWithRefreshToken(StoredCredential storedCredential) {
    return buildNotSignedCredential()
        .setAccessToken( storedCredential.getAccessToken() )
        .setExpiresInSeconds( storedCredential.getExpirationTimeMilliseconds() )
        .setRefreshToken( storedCredential.getRefreshToken() );
  }


  /**
   * Gets stored credential.
   *
   * @return the stored credential
   */
  private StoredCredential getStoredCredential() {
    StoredCredential s = null;
    try {
      s = userDb.get( ACCOUNT_TOKENS );
    } catch ( IOException e ) {
      logger.error( "Unable to find stored account: " + e.getMessage() );
    }
    return s;
  }


  /**
   * Validates the given response and eventually throws the corresponding exception.
   *
   * @param response the response to validate
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  private void validateResponse(HttpResponse response) throws AudioBoxException {
    switch ( response.getStatusCode() ) {
      case HttpStatus.SC_NOT_FOUND: // 404
        throw new ResourceNotFoundException( response );

      case HttpStatus.SC_UNPROCESSABLE_ENTITY: // 422
        throw new ValidationException( response );

      case HttpStatus.SC_FORBIDDEN: // 403
      case HttpStatus.SC_PAYMENT_REQUIRED: // 402
        throw new ForbiddenException( response );

    }
  }


}