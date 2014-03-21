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
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;

import javax.naming.ConfigurationException;


/**
 * Created by keytwo on 17/01/14.
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

  /**
   * AudioBox environments.
   */
  public enum Env {
    /** The AudioBox production environment, use this as default or in release builds. */
    production,

    /** Staging if the environment to use for experimental features and future APIs. */
    staging,

    /** The test and the development environment. */
    development
  }


  /**
   * Initiates a new Configuration.
   *
   * @param environment the {@link fm.audiobox.core.config.Configuration.Env environment} to use.
   */
  public Configuration(Env environment) {

    if (environment == null) {
      throw new IllegalArgumentException("Environment cannot be null");
    }

    this.environment = environment;
    this.config = ConfigFactory.load("lib");
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
   * @return the environment configuration
   */
  public Config getEnvironmentConfiguration(Env environment) {
    if (envConfig == null) {
      envConfig = config.getConfig("abx." + environment.name());
    }
    return envConfig;
  }


  /**
   * Gets env base url.
   *
   * @return the env base url
   */
  public String getEnvBaseUrl() {

    Config envConf = getEnvironmentConfiguration(getEnvironment());

    String protocol = envConf.getString("api.protocol");
    String host = envConf.getString("api.host");
    String port = envConf.getString("api.port");

    return protocol + "://" + host + ":" + port;
  }


  /**
   * Gets env token url.
   *
   * @return the env token url
   */
  public GenericUrl getEnvTokenUrl() {
    if (tokenUrl == null) {
      tokenUrl = new GenericUrl(getEnvBaseUrl() + getEnvironmentConfiguration(getEnvironment()).getString("api.oauth.tokenPath"));
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
   * Check configuration.
   *
   * @throws ConfigurationException the configuration exception
   */
  public void checkConfiguration() throws ConfigurationException {

    config.checkValid(ConfigFactory.defaultReference(), "abx");

    if (StringUtils.isBlank(getApiKey())) {
      throw new ConfigurationException("API Key (secret) is missing, please provide one.");
    }

    if (StringUtils.isBlank(getApiSecret())) {
      throw new ConfigurationException("Client ID is missing, please provide one.");
    }

    if (getDataStoreFactory() == null) {
      throw new ConfigurationException("Data store must be set.");
    }

    if (getHttpTransport() == null) {
      throw new ConfigurationException("Http transport type must be set");
    }

    if (getJsonFactory() == null) {
      throw new ConfigurationException("JSON factory must be set");
    }
  }
}
