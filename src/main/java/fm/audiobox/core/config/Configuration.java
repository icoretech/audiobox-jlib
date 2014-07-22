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

package fm.audiobox.core.config;


import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fm.audiobox.core.exceptions.ExceptionHandler;
import fm.audiobox.core.models.*;
import org.apache.commons.lang3.StringUtils;

import javax.naming.ConfigurationException;


/**
 * Through this class you can control several behavior of the client such as HTTP client or JSON parser libraries.
 * <p/>
 * There are the mandatory fields that must be set in order to set up the client:
 * <ul>
 * <li>{@link fm.audiobox.core.config.Configuration#setApiKey(String) API Key}</li>
 * <li>{@link fm.audiobox.core.config.Configuration#setApiSecret(String) API Secret}</li>
 * <li>{@link fm.audiobox.core.config.Configuration#setDataStoreFactory(com.google.api.client.util.store.DataStoreFactory) Data Store Factory}</li>
 * <li>{@link fm.audiobox.core.config.Configuration#setHttpTransport(com.google.api.client.http.HttpTransport) HTTP Transport}</li>
 * <li>{@link fm.audiobox.core.config.Configuration#setJsonFactory(com.google.api.client.json.JsonFactory) JSON Factory}</li>
 * </ul>
 * <p/>
 * Not configuring these parameters will result in a {@link javax.naming.ConfigurationException}.
 * <p/>
 * <strong>TIP:</strong> Setter methods of this class are chainable.
 */
public class Configuration {

  private static final String APP_NAME_PLACEHOLDER = ":appname:";

  private static final String VERSION_PLACEHOLDER = ":appversion:";

  private String apiKey;

  private String apiSecret;

  private Env environment;

  private HttpTransport httpTransport;

  private JsonFactory jsonFactory;

  private Config config;

  private Config envConfig;

  private GenericUrl tokenUrl;

  private DataStoreFactory db;

  private Class<? extends MediaFiles> mediaFilesWrapperClass;

  private Class<? extends Albums> albumsWrapperClass;

  private Class<? extends Genres> genresWrapperClass;

  private Class<? extends Artists> artistsWrapperClass;

  private Class<? extends MediaFileWrapper> mediaFileClass;

  private String userAgent;

  private String applicationName;

  private String version;

  private ExceptionHandler eh;


  /**
   * AudioBox environments.
   */
  public enum Env {
    /**
     * The AudioBox production environment, use this as default or in release builds.
     */
    production,

    /**
     * The environment to use for experimental features and future APIs.
     */
    staging,

    /**
     * The test and the development environment.
     */
    development
  }


  /**
   * Initiates a new Configuration ready for production.
   */
  public Configuration() {
    this( Env.production );
  }


  /**
   * Initiates a new Configuration.
   *
   * @param environment the
   *                    to use.
   */
  public Configuration(Env environment) {

    if ( environment == null ) {
      throw new IllegalArgumentException( "Environment cannot be null" );
    }

    this.environment = environment;
    this.config = ConfigFactory.load( "lib" );

    // Set default parser classes
    setMediaFilesWrapperClass( MediaFiles.class );
    setAlbumsWrapperClass( Albums.class );
    setGenresWrapperClass( Genres.class );
    setArtistsWrapperClass( Artists.class );
    setMediaFileClass( MediaFileWrapper.class );

    // Used for default user agent
    setApplicationName( "AudioBox-JLib" );
    setVersion( "1.0" );

    userAgent = "AudioBox.fm (Java; " +
        System.getProperty( "os.name" ) + " " +
        System.getProperty( "os.arch" ) + "; " +
        System.getProperty( "user.language" ) + "; " +
        System.getProperty( "java.runtime.version" ) + ") " +
        System.getProperty( "java.vm.name" ) + "/" +
        System.getProperty( "java.vm.version" ) +
        " " + APP_NAME_PLACEHOLDER + "/" + VERSION_PLACEHOLDER;
  }


  /**
   * Sets the API Consumer Key.
   * <p/>
   * To get one register your application here:
   * <a href="https://audiobox.fm/oauth2/applications">https://audiobox.fm/oauth2/applications</a>
   *
   * @param apiKey the API Consumer Key
   *
   * @return the {@link Configuration}
   */
  public Configuration setApiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }


  /**
   * Sets the API Consumer Secret.
   * <p/>
   * To get one register your application here:
   * <a href="https://audiobox.fm/oauth2/applications">https://audiobox.fm/oauth2/applications</a>
   *
   * @param apiSecret the API Consumer Secret
   *
   * @return the {@link Configuration}
   */
  public Configuration setApiSecret(String apiSecret) {
    this.apiSecret = apiSecret;
    return this;
  }


  /**
   * Sets the HTTP transport.
   * <p/>
   * Should be one of:
   * <ul>
   * <li><strong>NetHttpTransport:</strong> based on HttpURLConnection that is found in all Java SDKs, and thus usually the simplest choice.</li>
   * <li><strong>ApacheHttpTransport:</strong> based on the popular Apache HttpClient that allows for more customization.</li>
   * <li><strong>UrlFetchTransport:</strong> based on URL Fetch Java API in the Google App Engine SDK</li>
   * </ul>
   *
   * @param httpTransport the http transport
   *
   * @return the {@link Configuration}
   */
  public Configuration setHttpTransport(HttpTransport httpTransport) {
    this.httpTransport = httpTransport;
    return this;
  }


  /**
   * Sets the JSON factory.
   * <p/>
   * Should be one of:
   * <ul>
   * <li><strong>JacksonFactory:</strong> based on the popular Jackson library which is considered the fastest in terms of parsing/serialization speed</li>
   * <li><strong>GsonFactory:</strong> based on the Google GSON library which is a lighter-weight option (small size) that is pretty fast also (though not quite as fast as Jackson)</li>
   * <li><strong>AndroidJsonFactory:</strong> based on the JSON library built-in to Android Honeycomb (SDK 3.0) or higher that is identical to the Google GSON library</li>
   * </ul>
   *
   * @param jsonFactory the json factory
   *
   * @return the {@link Configuration}
   */
  public Configuration setJsonFactory(JsonFactory jsonFactory) {
    this.jsonFactory = jsonFactory;
    return this;
  }


  /**
   * Sets the data store factory.
   * <p/>
   * You should provide one like FileDataStoreFactory, MemoryDataStoreFactory or implementing one
   * by extending the {@link com.google.api.client.util.store.AbstractDataStoreFactory}.
   *
   * @param dataStoreFactory the data store factory
   *
   * @return the {@link Configuration}
   */
  public Configuration setDataStoreFactory(DataStoreFactory dataStoreFactory) {
    this.db = dataStoreFactory;
    return this;
  }


  /**
   * Sets the media files wrapper class.
   * <p/>
   * Use this configuration method to set your own class to use as media files parser.
   * <p/>
   * Default is {@link fm.audiobox.core.models.MediaFiles}.
   *
   * @param klass the class to use for media files parsing.
   *
   * @return the {@link Configuration}
   */
  public Configuration setMediaFilesWrapperClass(Class<? extends MediaFiles> klass) {
    this.mediaFilesWrapperClass = klass;
    return this;
  }


  /**
   * Sets the albums wrapper class.
   * <p/>
   * Use this configuration method to set your own class to use as albums parser.
   * <p/>
   * Default is {@link fm.audiobox.core.models.Albums}.
   *
   * @param klass the class to use for albums parsing.
   *
   * @return the {@link Configuration}
   */
  public Configuration setAlbumsWrapperClass(Class<? extends Albums> klass) {
    this.albumsWrapperClass = klass;
    return this;
  }


  /**
   * Sets the genres wrapper class.
   * <p/>
   * Use this configuration method to set your own class to use as genres parser.
   * <p/>
   * Default is {@link fm.audiobox.core.models.Genres}.
   *
   * @param klass the class to use for genres parsing.
   *
   * @return the {@link Configuration}
   */
  public Configuration setGenresWrapperClass(Class<? extends Genres> klass) {
    this.genresWrapperClass = klass;
    return this;
  }


  /**
   * Sets the artists wrapper class.
   * <p/>
   * Use this configuration method to set your own class to use as artists parser.
   * <p/>
   * Default is {@link fm.audiobox.core.models.Artists}.
   *
   * @param klass the class to use for artists parsing.
   *
   * @return the {@link Configuration}
   */
  public Configuration setArtistsWrapperClass(Class<? extends Artists> klass) {
    this.artistsWrapperClass = klass;
    return this;
  }


  /**
   * Sets the media file class.
   * <p/>
   * Use this configuration method to set your own class to use for media file parsing.
   * <p/>
   * Default is {@link fm.audiobox.core.models.MediaFileWrapper}.
   *
   * @param klass the class to use for artists parsing.
   *
   * @return the {@link Configuration}
   */
  public Configuration setMediaFileClass(Class<? extends MediaFileWrapper> klass) {
    this.mediaFileClass = klass;
    return this;
  }


  /**
   * Changes the AudioBox environment (only useful in API development mode).
   * <p/>
   * Do not use this method if you are working on a production application.
   *
   * @return the {@link Configuration}
   */
  @Deprecated
  public Configuration setEnvironment(Env environment) {
    this.environment = environment;
    return this;
  }


  /**
   * Sets the  application name.
   *
   * @param applicationName the application name
   *
   * @return the {@link Configuration}
   */
  public Configuration setApplicationName(String applicationName) {
    this.applicationName = applicationName;
    return this;
  }


  /**
   * Sets your application version.
   *
   * @param version the version string
   *
   * @return the {@link Configuration}
   */
  public Configuration setVersion(String version) {
    this.version = version;
    return this;
  }


  /**
   * Sets a custom {@link ExceptionHandler}
   *
   * @param handler the ExceptionHandler to use for next (and subsequent) request.
   */
  public synchronized void setExceptionHandler(ExceptionHandler handler) {
    this.eh = handler;
  }


  /**
   * Gets data store factory.
   *
   * @return the data store factory
   */
  public DataStoreFactory getDataStoreFactory() {
    return db;
  }


  /**
   * Gets environment configuration.
   *
   * @param environment the environment
   *
   * @return the environment configuration
   */
  public Config getEnvironmentConfiguration(Env environment) {
    if ( envConfig == null ) {
      envConfig = config.getConfig( "abx." + environment.name() );
    }
    return envConfig;
  }


  /**
   * Gets env base url.
   *
   * @return the env base url (no trailing slash)
   */
  public String getEnvBaseUrl() {

    Config envConf = getEnvironmentConfiguration( getEnvironment() );

    String protocol = envConf.getString( "api.protocol" );
    String host = envConf.getString( "api.host" );
    String port = envConf.getString( "api.port" );

    return protocol + "://" + host + ":" + port;
  }


  /**
   * Gets env token url.
   *
   * @return the env token url
   */
  public GenericUrl getEnvTokenUrl() {
    if ( tokenUrl == null ) {
      tokenUrl = new GenericUrl( getEnvBaseUrl() + getEnvironmentConfiguration( getEnvironment() ).getString( "api.oauth.tokenPath" ) );
    }
    return tokenUrl;
  }


  /**
   * Gets the API Consumer Key.
   *
   * @return the API Consumer Key
   */
  public String getApiKey() {
    return apiKey;
  }


  /**
   * Gets the API Consumer Secret.
   *
   * @return the API Consumer Secret
   */
  public String getApiSecret() {
    return apiSecret;
  }


  /**
   * Gets the {@link fm.audiobox.core.config.Configuration.Env environment}.
   *
   * @return the environment
   */
  public Env getEnvironment() {
    return environment;
  }


  /**
   * Gets HTTP transport.
   *
   * @return the HTTP transport
   */
  public HttpTransport getHttpTransport() {
    return httpTransport;
  }


  /**
   * Gets JSON factory.
   *
   * @return the JSON factory
   */
  public JsonFactory getJsonFactory() {
    return jsonFactory;
  }


  /**
   * Gets the media files wrapper class.
   *
   * @return the class to use for media files parsing.
   */
  public Class<? extends MediaFiles> getMediaFilesWrapperClass() {
    return this.mediaFilesWrapperClass;
  }


  /**
   * Gets the media file class.
   *
   * @return the class to use for media file parsing.
   */
  public Class<? extends MediaFileWrapper> getMediaFileWrapperClass() {
    return this.mediaFileClass;
  }


  /**
   * Gets the albums wrapper class.
   *
   * @return the class to use for albums parsing.
   */
  public Class<? extends Albums> getAlbumsWrapperClass() {
    return this.albumsWrapperClass;
  }


  /**
   * Gets the genres wrapper class.
   *
   * @return the class to use for genres parsing.
   */
  public Class<? extends Genres> getGenresWrapperClass() {
    return this.genresWrapperClass;
  }


  /**
   * Gets the artists wrapper class.
   *
   * @return the class to use for artists parsing.
   */
  public Class<? extends Artists> getArtistsWrapperClass() {
    return this.artistsWrapperClass;
  }


  /**
   * Gets the user agent.
   *
   * @return the user agent
   */
  public String getUserAgent() {
    userAgent = userAgent
        .replace( APP_NAME_PLACEHOLDER, getApplicationName() )
        .replace( VERSION_PLACEHOLDER, getVersion() );
    return userAgent;
  }


  /**
   * Gets the application name.
   *
   * @return the application name
   */
  public String getApplicationName() {
    return applicationName;
  }


  /**
   * Gets version.
   *
   * @return the version
   */
  public String getVersion() {
    return version;
  }


  /**
   * Gets the configured {@link fm.audiobox.core.exceptions.ExceptionHandler}
   * @return the {@link fm.audiobox.core.exceptions.ExceptionHandler}
   */
  public synchronized ExceptionHandler getExceptionHandler() {
    return this.eh;
  }


  /**
   * Check configuration.
   *
   * @throws ConfigurationException the configuration exception
   */
  public void checkConfiguration() throws ConfigurationException {

    config.checkValid( ConfigFactory.defaultReference(), "abx" );

    if ( StringUtils.isBlank( getApiKey() ) ) {
      throw new ConfigurationException( "API Key (secret) is missing, please provide one." );
    }

    if ( StringUtils.isBlank( getApiSecret() ) ) {
      throw new ConfigurationException( "Client ID is missing, please provide one." );
    }

    if ( getDataStoreFactory() == null ) {
      throw new ConfigurationException( "Data store must be set." );
    }

    if ( getHttpTransport() == null ) {
      throw new ConfigurationException( "Http transport type must be set" );
    }

    if ( getJsonFactory() == null ) {
      throw new ConfigurationException( "JSON factory must be set" );
    }
  }
}
