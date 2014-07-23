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
import com.google.api.client.json.JsonObjectParser;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.*;
import fm.audiobox.core.models.*;
import fm.audiobox.core.store.CredentialDataStore;
import fm.audiobox.core.utils.HttpStatus;
import fm.audiobox.core.utils.ModelUtil;
import fm.audiobox.core.utils.PlainTextContent;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;


/**
 * {@link Client} is the main object of this library and allows you to perform requests and
 * operations on AudioBox.
 * <p/>
 * AudioBox let developers access to API in order to build any kind of modern applications,
 * libraries and integrations on top of the platform.
 * <br/>
 * AudioBox exposes basic and special API endpoints aimed at fast iteration of code and easiness.
 * <p/>
 * All the HTTP/HTTPS are performed using the JSON data format and content type.
 * <p/>
 * This library supports the OAuth2 Resource Owner Password Credentials Grant Type, thus, in order to
 * work with it, you first need to register your application here (you need a valid AudioBox account):
 * <p/>
 * <a href="https://audiobox.fm/oauth2/applications">https://audiobox.fm/oauth2/applications</a>
 * <p/>
 * Once you registered your application you have to properly configure the client as follows:
 * <code><pre>
 * Configuration config = new Configuration()
 *   .setApiKey( "[Your Consumer Key]" )
 *   .setApiSecret( "[Your Consumer Secret]" );
 * </pre></code>
 * Through the {@link fm.audiobox.core.config.Configuration} object you can configure many aspects
 * of the library behaviors; some are trivial such as application name, version, etc. and
 * other are more complex such as HttpTransport or JSON parser.
 * <p/>
 * This library does not offer a data store for credentials storage out of the box. You should provide
 * an implementation of the {@link fm.audiobox.core.store.CredentialDataStore}.
 * <br/>
 * This data store should be used to store credentials so you should be really carefully with it.
 * <p/>
 * To set it use the configuration:
 * <code><pre>
 *  config.setCredentialDataStore( new MyCredentialDataStore() );
 * </pre></code>
 * To comply with OAuth standard you also have to provide a {@link com.google.api.client.auth.oauth2.CredentialRefreshListener}
 * in order to keep tokens up to date.
 * <code><pre>
 *   config.setCredentialRefreshListener( new MyCredentialRefreshListener() );
 * </pre></code>
 * Since this library wants to be as much agnostic as possible regarding the HTTP client and
 * the JSON parser libraries you should set them at this moment by choosing amongst:
 * <p/>
 * <ul>
 * <li><strong>NetHttpTransport:</strong> based on HttpURLConnection that is found in all Java SDKs, and thus usually the simplest choice.</li>
 * <li><strong>ApacheHttpTransport:</strong> based on the popular Apache HttpClient that allows for more customization.</li>
 * <li><strong>UrlFetchTransport:</strong> based on URL Fetch Java API in the Google App Engine SDK</li>
 * </ul>
 * <p/>
 * as HTTP transport, and:
 * <p/>
 * <ul>
 * <li><strong>JacksonFactory:</strong> based on the popular Jackson library which is considered the fastest in terms of parsing/serialization speed</li>
 * <li><strong>GsonFactory:</strong> based on the Google GSON library which is a lighter-weight option (small size) that is pretty fast also (though not quite as fast as Jackson)</li>
 * <li><strong>AndroidJsonFactory:</strong> based on the JSON library built-in to Android Honeycomb (SDK 3.0) or higher that is identical to the Google GSON library</li>
 * </ul>
 * <p/>
 * as JSON parser library.
 * <p/>
 * There are no defaults that's why you must provide them through the configuration:
 * <code><pre>
 *  config
 *    .setHttpTransport( new NetHttpTransport() )
 *    .setJsonFactory( new JacksonFactory() );
 * </pre></code>
 * <p/>
 * This is the basic configuration and once the setup is completed you can create your
 * Client, authorize the application and start performing any kind of operation supported
 * by AudioBox API through it:
 * <code><pre>
 *   Client client = new Client( config );
 *   client.authorize( "username", "password" );
 *   List&lt;Playlist&gt; playlists = client.getPlaylists();
 *   ...
 * </pre></code>
 * <p/>
 * <strong>NOTE:</strong> {@link Client#authorize(String, String)} is only needed once
 * to get and store the OAuth2 grant token; password is never (and it never should be) stored.
 * <p/>
 * <strong>NOTE:</strong> grant tokens may expires at any time. A request against AudioBox with
 * an expired token will result in an
 * {@link fm.audiobox.core.exceptions.AuthorizationException AuthorizationException}.
 * Your application should be ready to trap it in order to present a new login form.
 * <p/>
 * <br/>
 * <br/>
 * For a complete list of API endpoints you can consult the AudioBox API handbook at this address:
 * <p/>
 * <a href="http://audiobox.fm/apidocs">http://audiobox.fm/apidocs</a>
 * <p/>
 */
public class Client {

  private Configuration conf;

  private CredentialDataStore userDb;

  /**
   * The key under which tokens are stored in the DataStore
   */
  public static final String ACCOUNT_TOKENS = "_audiobox_account_tokens";

  private static final String UPLOAD_PATH = "/api/v1/upload";

  private CredentialRefreshListener refreshListener;

  private JsonObjectParser jsonObjectParser;

  private HttpHeaders defaultHeaders;


  /**
   * Instantiates a new Client.
   *
   * @param conf the conf
   *
   * @throws ConfigurationException the configuration exception
   * @throws java.io.IOException    if any problem occurs with the configured data store factory
   */
  public Client(Configuration conf) throws ConfigurationException, IOException {
    conf.checkConfiguration();
    this.conf = conf;
    this.userDb = conf.getCredentialDataStore();
    this.refreshListener = conf.getRefreshListener();
    this.jsonObjectParser = new JsonObjectParser( getConf().getJsonFactory() );
    this.defaultHeaders = new HttpHeaders();
    this.defaultHeaders.setUserAgent( getConf().getUserAgent() );
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
   * Starts the authorization flow.
   * <p/>
   * Given a username and a password if the request succeed this method will store the
   * grant token for future requests and return the response.
   *
   * @param username the username
   * @param password the password
   *
   * @return the token response, may be null
   *
   * @throws AuthorizationException in case the authorization fails.
   * @throws java.io.IOException    if any connection or configured data store problems occurs.
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
      ptr.setRequestInitializer( new HttpRequestInitializer() {

        @Override
        public void initialize(HttpRequest request) throws IOException {
          request.setSuppressUserAgentSuffix( true );
          request.setHeaders( defaultHeaders );
        }
      } );
      TokenResponse response = ptr.execute();
      userDb.saveCredentials( ACCOUNT_TOKENS, ( Credential ) createCredentialWithRefreshToken( response ) );

      return response;
    } catch ( TokenResponseException e ) {
      handleException( new AuthorizationException( e ) );
      return null;
    }
  }


  /**
   * Returns information about the authorized user.
   * <br/>
   * <strong>NOTE:</strong> this method always performs a request, use it wisely
   *
   * @return the {@link fm.audiobox.core.models.User user}
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public User getUser() throws IOException {
    HttpResponse rsp = doGET( UserWrapper.getPath() );
    return rsp.isSuccessStatusCode() ? rsp.parseAs( UserWrapper.class ).getUser() : null;
  }


  /**
   * Gets user's playlists.
   *
   * @return a {@link java.util.List} of {@link fm.audiobox.core.models.Playlist playlists}
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public List<Playlist> getPlaylists() throws IOException {
    HttpResponse rsp = doGET( Playlists.getPath() );
    return rsp.isSuccessStatusCode() ? rsp.parseAs( Playlists.class ).getPlaylists() : null;
  }


  /**
   * Gets the token-specified playlist.
   * Triggers Smart Playlist compilation if the requested playlist is a SmartPlaylist.
   * <br/>
   * <strong>NOTE:</strong> this method will always perform a request against AudioBox servers, for this reason be
   * smart and try to apply some sort of cache strategy.
   *
   * @param token the token of the playlist to get.
   *
   * @return the specified {@link fm.audiobox.core.models.Playlist playlist}
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Playlist getPlaylist(String token) throws IOException {
    HttpResponse rsp = doGET( ModelUtil.interpolate( Playlist.getPath(), token ) );
    return rsp.isSuccessStatusCode() ? rsp.parseAs( PlaylistWrapper.class ).getPlaylist() : null;
  }


  /**
   * Gets user's notifications.
   *
   * @return the {@link fm.audiobox.core.models.Notifications notifications}
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public Notifications getNotifications() throws IOException {
    HttpResponse rsp = doGET( Notifications.getPath() );
    return rsp.isSuccessStatusCode() ? rsp.parseAs( Notifications.class ) : null;
  }


  /**
   * Uploads media files to AudioBox Cloud.
   * <p/>
   * If the subscription is not valid {@link fm.audiobox.core.exceptions.ForbiddenException} is thrown.
   * If a media file already exists on AudioBox Cloud {@link fm.audiobox.core.exceptions.FileAlreadyUploaded} is thrown.
   * <p/>
   * Other errors may include {@link fm.audiobox.core.exceptions.ValidationException},
   * {@link fm.audiobox.core.exceptions.SystemOverloadedException} or {@link fm.audiobox.core.exceptions.RemoteMessageException}
   * with additional information in the exception body.
   * <p/>
   * The application should ensure to accept those errors and retry accordingly after few minutes.
   * <p/>
   * On successful upload the server returns a new {@link MediaFile} with additional information,
   * including the token assigned to the newly uploaded media file.
   * <p/>
   * Files uploaded through this method will go directly into the CloudPlaylist.
   * </p>
   *
   * @param file the file to upload on AudioBox
   *
   * @return a {@link fm.audiobox.core.models.MediaFile} containing additional information
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public MediaFile upload(final File file) throws IOException {

    FileContent fileContent = new FileContent( URLConnection.guessContentTypeFromName( file.getAbsolutePath() ), file );
    HttpContent pathContent = new PlainTextContent( file.getAbsolutePath() );

    MultipartFormDataContent multipart = new MultipartFormDataContent();
    multipart.addPart( new MultipartContent.Part( pathContent ), "remote_path", null );
    multipart.addPart( new MultipartContent.Part( fileContent ), "files[]", file.getName() );

    HttpResponse rsp = doPOST( UPLOAD_PATH, multipart );
    return rsp.isSuccessStatusCode() ? rsp.parseAs( MediaFileWrapper.class ).getMediaFile() : null;
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} GET requests and returns the response.
   *
   * @param path the AudioBox API path where to make the request to.
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doGET(String path) throws IOException {
    return doGET( path, null );
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} GET requests and returns the response.
   *
   * @param path   the AudioBox API path where to make the request to.
   * @param parser the {@link com.google.api.client.json.JsonObjectParser} to use to parse the response.
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doGET(String path, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.GET, path, null, parser );
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} PUT requests and returns the response.
   *
   * @param path the AudioBox API path where to make the request to.
   * @param data the data to send with the request
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doPUT(String path, HttpContent data) throws IOException {
    return doPUT( path, data, null );
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} PUT requests and returns the response.
   *
   * @param path   the AudioBox API path where to make the request to.
   * @param data   the data to send with the request
   * @param parser the {@link com.google.api.client.json.JsonObjectParser} to use to parse the response.
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doPUT(String path, HttpContent data, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.PUT, path, data, parser );
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} DELETE requests to the given path.
   *
   * @param path the AudioBox API path where to make the request to.
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doDELETE(String path) throws IOException {
    return doDELETE( path, null );
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} DELETE requests to the given path.
   *
   * @param path   the AudioBox API path where to make the request to.
   * @param parser the {@link com.google.api.client.json.JsonObjectParser} to use to parse the response.
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doDELETE(String path, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.DELETE, path, null, parser );
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} POST requests and returns the response.
   *
   * @param path the AudioBox API path where to make the request to.
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doPOST(String path) throws IOException {
    return doPOST( path, null, null );
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} POST requests and returns the response.
   *
   * @param path the AudioBox API path where to make the request to.
   * @param data the data to send with the request
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doPOST(String path, HttpContent data) throws IOException {
    return doPOST( path, data, null );
  }


  /**
   * Performs {@link Client#authorize(String, String) signed} POST requests and returns the response.
   *
   * @param path   the AudioBox API path where to make the request to.
   * @param data   the data to send with the request
   * @param parser the {@link com.google.api.client.json.JsonObjectParser} to use to parse the response.
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public HttpResponse doPOST(String path, HttpContent data, JsonObjectParser parser) throws IOException {
    return doRequest( HttpMethods.POST, path, data, parser );
  }


  /* ================ */
  /*  Private methods */
  /* ================ */


  /**
   * Executes the configured request by calling AudioBox API services.
   *
   * @param method the method to use
   * @param path   the AudioBox API path where to make the request to.
   * @param data   the data to send with the request
   * @param parser the parser to use for the resulting object
   *
   * @return the http response, may be null if any error occurs during the request.
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  private HttpResponse doRequest(String method, String path, HttpContent data, JsonObjectParser parser) throws IOException {
    HttpResponse response = getRequestFactory( parser ).buildRequest( method, new GenericUrl( getConf().getEnvBaseUrl() + path ), data ).execute();
    validateResponse( response );
    return response;
  }


  /**
   * Gets request factory.
   *
   * @param parser the parser to use for the resulting object
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
        request.setSuppressUserAgentSuffix( true );
        request.setHeaders( defaultHeaders );
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
   *
   * @throws java.io.IOException if any problem occurs with the configured data store.
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
   *
   * @throws java.io.IOException if any problem occurs with the configured data store.
   */
  private StoredCredential getStoredCredential() throws IOException {
    return userDb.getCredentials( ACCOUNT_TOKENS );
  }


  /**
   * Validates the given response and eventually throws the corresponding exception.
   *
   * @param response the response to validate
   *
   * @throws AudioBoxException in case of 400, 402, 403, 404, 409, 422, 500 or 503 response codes.
   */
  private void validateResponse(HttpResponse response) throws IOException {
    try {

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

    } catch ( AudioBoxException e ) {
      handleException( e );
    }
  }


  /**
   * Handles with {@link fm.audiobox.core.exceptions.ExceptionHandler} any given
   * {@link fm.audiobox.core.exceptions.AudioBoxException}
   *
   * @param e the exception to handle
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if exception is not handled by an ExceptionHandler
   */
  private void handleException(AudioBoxException e) throws AudioBoxException {
    ExceptionHandler eh = this.getConf().getExceptionHandler();
    if ( eh != null && eh.onException( e ) ) {
      return;
    }
    throw e;
  }


}