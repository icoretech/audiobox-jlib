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

package fm.audiobox.core.config;


import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.sun.istack.internal.NotNull;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fm.audiobox.core.models.*;
import org.apache.commons.lang3.StringUtils;

import javax.naming.ConfigurationException;


/**
 * This is the main configuration for the AudioBox client.
 * <p/>
 * All configurable parameters are here.
 */
public class Configuration {

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

  private Class<? extends MediaFile> mediaFileClass;

  private Class<? extends Album> albumClass;

  private Class<? extends Genre> genreClass;

  private Class<? extends Artist> artistClass;

  /**
   * AudioBox environments.
   */
  public enum Env {
    /**
     * The AudioBox production environment, use this as default or in release builds.
     */
    production,

    /**
     * Staging if the environment to use for experimental features and future APIs.
     */
    staging,

    /**
     * The test and the development environment.
     */
    development
  }


  /**
   * Initiates a new Configuration.
   *
   * @param environment the {@link fm.audiobox.core.config.Configuration.Env environment} to use.
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
  }


  /**
   * Sets api key.
   *
   * @param apiKey the api key
   */
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }


  /**
   * Sets api secret.
   *
   * @param apiSecret the api secret
   */
  public void setApiSecret(String apiSecret) {
    this.apiSecret = apiSecret;
  }


  /**
   * Sets http transport.
   *
   * @param httpTransport the http transport
   */
  public void setHttpTransport(HttpTransport httpTransport) {
    this.httpTransport = httpTransport;
  }


  /**
   * Sets json factory.
   *
   * @param jsonFactory the json factory
   */
  public void setJsonFactory(JsonFactory jsonFactory) {
    this.jsonFactory = jsonFactory;
  }


  /**
   * Sets data store factory.
   *
   * @param dataStoreFactory the data store factory
   */
  public void setDataStoreFactory(DataStoreFactory dataStoreFactory) {
    this.db = dataStoreFactory;
  }


  /**
   * Sets the media files wrapper class.
   * <p/>
   * Use this configuration method to set your own class to use as media files parser.
   * <p/>
   * Default is {@link fm.audiobox.core.models.MediaFiles}.
   *
   * @param klass the class to use for media files parsing.
   */
  public void setMediaFilesWrapperClass(Class<? extends MediaFiles> klass) {
    this.mediaFilesWrapperClass = klass;
  }


  /**
   * Sets the albums wrapper class.
   * <p/>
   * Use this configuration method to set your own class to use as albums parser.
   * <p/>
   * Default is {@link fm.audiobox.core.models.Albums}.
   *
   * @param klass the class to use for albums parsing.
   */
  public void setAlbumsWrapperClass(Class<? extends Albums> klass) {
    this.albumsWrapperClass = klass;
  }


  /**
   * Sets the genres wrapper class.
   * <p/>
   * Use this configuration method to set your own class to use as genres parser.
   * <p/>
   * Default is {@link fm.audiobox.core.models.Genres}.
   *
   * @param klass the class to use for genres parsing.
   */
  public void setGenresWrapperClass(Class<? extends Genres> klass) {
    this.genresWrapperClass = klass;
  }


  /**
   * Sets the artists wrapper class.
   * <p/>
   * Use this configuration method to set your own class to use as artists parser.
   * <p/>
   * Default is {@link fm.audiobox.core.models.Artists}.
   *
   * @param klass the class to use for artists parsing.
   */
  public void setArtistsWrapperClass(Class<? extends Artists> klass) {
    this.artistsWrapperClass = klass;
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
  public Config getEnvironmentConfiguration(@NotNull Env environment) {
    if ( envConfig == null ) {
      envConfig = config.getConfig( "abx." + environment.name() );
    }
    return envConfig;
  }


  /**
   * Gets env base url.
   *
   * @return the env base url
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
   * Gets api key.
   *
   * @return the api key
   */
  public String getApiKey() {
    return apiKey;
  }


  /**
   * Gets api secret.
   *
   * @return the api secret
   */
  public String getApiSecret() {
    return apiSecret;
  }


  /**
   * Gets environment.
   *
   * @return the environment
   */
  public Env getEnvironment() {
    return environment;
  }


  /**
   * Gets http transport.
   *
   * @return the http transport
   */
  public HttpTransport getHttpTransport() {
    return httpTransport;
  }


  /**
   * Gets json factory.
   *
   * @return the json factory
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
  public Class<? extends MediaFile> getMediaFileClass() {
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
   * Gets the album class.
   *
   * @return the class to use for album parsing.
   */
  public Class<? extends Album> getAlbumClass() {
    return this.albumClass;
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
   * Gets the genre class.
   *
   * @return the class to use for genre parsing.
   */
  public Class<? extends Genre> getGenreClass() {
    return this.genreClass;
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
   * Gets the artist class.
   *
   * @return the class to use for artist parsing.
   */
  public Class<? extends Artist> getArtistClass() {
    return this.artistClass;
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
