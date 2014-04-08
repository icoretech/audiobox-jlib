/*
 * Copyright 2009-2014 iCoreTech, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.audiobox.core;


import com.google.api.client.auth.oauth2.*;
import com.google.api.client.http.*;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.store.DataStore;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.*;
import fm.audiobox.core.models.*;
import fm.audiobox.core.utils.Credential;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.core.utils.ModelUtil;
import fm.audiobox.core.utils.PlainTextContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;


/**
 * TODO: On MediaFiles:
 * <ul>
 * <li>GET /api/v1/playlists/:playlist_token/media_files/fingerprints.json</li>
 * <li>PUT /api/v1/media_files/multiupdate.json?tokens[]=</li>
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

  private static final String META_VERB_PARAM = "_method";


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
  public TokenResponse authorize(String username, String password) throws IOException {
    try {
      PasswordTokenRequest ptr = new PasswordTokenRequest(
          getConf().getHttpTransport(),
          getConf().getJsonFactory(),
          getConf().getEnvTokenUrl(),
          username,
          password );

      ptr.setClientAuthentication( new BasicAuthentication( getConf().getApiKey(), getConf().getApiSecret() ) );

      TokenResponse response = ptr.execute();

      StoredCredential sc = new StoredCredential( ( Credential ) createCredentialWithRefreshToken( response ) );
      userDb.set( ACCOUNT_TOKENS, sc );

      return response;
    } catch ( TokenResponseException e ) {
      throw new AuthorizationException( e );
    }
  }


  /**
   * Returns information about the currently logged in user.
   *
   * @return the user
   *
   * @throws AudioBoxException the audio box exception
   */
  public User getUser() throws IOException {
    HttpResponse rsp = doGET( UserWrapper.getPath() );
    return rsp.parseAs( UserWrapper.class ).getUser();
  }


  /**
   * Gets user's playlists.
   *
   * @return the playlists
   *
   * @throws AudioBoxException the audio box exception
   */
  public List<Playlist> getPlaylists() throws IOException {
    HttpResponse rsp = doGET( Playlists.getPath() );
    return rsp.parseAs( Playlists.class ).getPlaylists();
  }


  /**
   * Gets the specified playlist.
   * Triggers Smart Playlist compilation if the requested playlist is a SmartPlaylist.
   * <br/>
   * NOTE: this method will always perform a request against AudioBox servers.
   *
   * @param token the token of the playlist to get.
   *
   * @return the playlist
   *
   * @throws AudioBoxException the audio box exception
   */
  public Playlist getPlaylist(String token) throws IOException {
    HttpResponse rsp = doGET( ModelUtil.interpolate( Playlist.getPath(), token ) );
    return rsp.parseAs( PlaylistWrapper.class ).getPlaylist();
  }


  /**
   * Gets user's notifications.
   *
   * @return the notifications
   *
   * @throws AudioBoxException the audio box exception
   */
  public Notifications getNotifications() throws IOException {
    HttpResponse rsp = doGET( Notifications.getPath() );
    return rsp.parseAs( Notifications.class );
  }


  /**
   * Uploads media files in AudioBox Cloud.
   * <p/>
   * If the subscription is not valid {@link fm.audiobox.core.exceptions.ForbiddenException} is thrown.
   * If a media file already exists on AudioBox Cloud {@link fm.audiobox.core.exceptions.FileAlreadyUploaded} is thrown.
   * <p/>
   * Other errors might include {@link fm.audiobox.core.exceptions.ValidationException},
   * {@link fm.audiobox.core.exceptions.SystemOverloadedException} or {@link fm.audiobox.core.exceptions.RemoteMessageException}
   * with additional information in the exception body.
   * <p/>
   * The application should ensure to accept those errors and retry accordingly after few minutes.
   * <p/>
   * On successful upload the server returns a 202 with additional information in the response's body,
   * including the token assigned to a single media file.
   * <p/>
   * Files uploaded through this method will go directly into the CloudPlaylist.
   * </p>
   *
   * @param file the file to upload on AudioBox
   *
   * @return true if upload succeed, false or throws exception on any other case.
   *
   * @throws IOException if 422, 503 or 500 errors occurs (additional information in the exception body).
   */
  public MediaFile upload(final File file) throws IOException {

    FileContent fileContent = new FileContent( URLConnection.guessContentTypeFromName( file.getAbsolutePath() ), file );
    HttpContent pathContent = new PlainTextContent( file.getAbsolutePath() );

    MultipartFormDataContent multipart = new MultipartFormDataContent();
    multipart.addPart( new MultipartContent.Part( pathContent ), "remote_path", null );
    multipart.addPart( new MultipartContent.Part( fileContent ), "files[]", file.getName() );

    HttpResponse rsp = doPOST( UPLOAD_PATH, multipart );
    return rsp.parseAs( MediaFileWrapper.class ).getMediaFile();
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
    return doDELETE( path, null, parser );
  }


  /**
   * Performs a DELETE request to the given path.
   *
   * @param path   the path
   * @param data   the data
   * @param parser the parser
   *
   * @return the http response
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doDELETE(String path, HttpContent data, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.DELETE, path, data, parser );
  }


  /**
   * Perform signed POST requests and returns the response.
   *
   * @param path the url to make the request against
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public HttpResponse doPOST(String path) throws IOException {
    return doPOST( path, null, null );
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

      /*
      if ( HttpMethods.DELETE.equals( method ) ) {
        path += "?_method=" + method;
        method = HttpMethods.POST;
      }
      */

      HttpResponse response = getRequestFactory( parser ).buildRequest( method, new GenericUrl( getConf().getEnvBaseUrl() + path ), data ).execute();
      validateResponse( response );
      return response;

    } catch ( AudioBoxException e ) {
      throw e; // Relaunch exception

    } catch ( TokenResponseException e ) {
      throw new AuthorizationException( e );
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
  private HttpRequestInitializer createCredentialWithRefreshToken() throws IOException {
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
  private StoredCredential getStoredCredential() throws IOException {
    return userDb.get( ACCOUNT_TOKENS );
  }


  /**
   * Validates the given response and eventually throws the corresponding exception.
   *
   * @param response the response to validate
   *
   * @throws AudioBoxException in case of 402, 403, 404, 409, 422, 500 or 503 response codes.
   */
  private void validateResponse(HttpResponse response) throws AudioBoxException {
    switch ( response.getStatusCode() ) {
      case HttpStatus.SC_BAD_REQUEST: // 400
        throw new AuthorizationException( response );

      case HttpStatus.SC_PAYMENT_REQUIRED: // 402
      case HttpStatus.SC_FORBIDDEN: // 403
        throw new ForbiddenException( response );

      case HttpStatus.SC_NOT_FOUND: // 404
        // Due to technical issues 404 is also given for not editable resources.
        throw new ResourceNotFoundException( response );

      case HttpStatus.SC_CONFLICT: // 409 (uploads)
        throw new FileAlreadyUploaded( response );

      case HttpStatus.SC_UNPROCESSABLE_ENTITY: // 422
        throw new ValidationException( response );

      case HttpStatus.SC_INTERNAL_SERVER_ERROR: // 500
        throw new RemoteMessageException( response );

      case HttpStatus.SC_SERVICE_UNAVAILABLE: // 503
        throw new SystemOverloadedException( response );

    }
  }


}