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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private GenericUrl tokenUrl;

  private DataStoreFactory db;

  public enum Env {production, staging, development}


  public Configuration(Env environment) {
    this.environment = environment;
    this.config = ConfigFactory.load("lib");
  }


  public void setDataStoreFactory(DataStoreFactory dataStoreFactory) {
    this.db = dataStoreFactory;
  }


  public DataStoreFactory getDataStoreFactory() {
    return db;
  }


  public Config getEnvironmentConfiguration(Env environment) {
    return config.getConfig("abx." + environment.name());
  }


  public String getEnvBaseUrl() {

    Config envConf = getEnvironmentConfiguration(getEnvironment());

    String protocol = envConf.getString("api.protocol");
    String host = envConf.getString("api.host");
    String port = envConf.getString("api.port");

    return protocol + "://" + host + ":" + port;
  }


  public GenericUrl getEnvTokenUrl() {
    if (tokenUrl == null) {
      tokenUrl = new GenericUrl(getEnvBaseUrl() + getEnvironmentConfiguration(getEnvironment()).getString("api.oauth.tokenPath"));
    }
    return tokenUrl;
  }


  public String getApiKey() {
    return apiKey;
  }


  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }


  public String getApiSecret() {
    return apiSecret;
  }


  public void setApiSecret(String apiSecret) {
    this.apiSecret = apiSecret;
  }


  public Env getEnvironment() {
    return environment;
  }


  public HttpTransport getHttpTransport() {
    return httpTransport;
  }


  public void setHttpTransport(HttpTransport httpTransport) {
    this.httpTransport = httpTransport;
  }


  public JsonFactory getJsonFactory() {
    return jsonFactory;
  }


  public void setJsonFactory(JsonFactory jsonFactory) {
    this.jsonFactory = jsonFactory;
  }


  public void checkConfiguration() throws ConfigurationException {

    config.checkValid(ConfigFactory.defaultReference(), "abx");

    if (StringUtils.isBlank(getApiKey())) {
      throw new ConfigurationException("API Key (secret) is missing, please provide one and try again.");
    }

    if (StringUtils.isBlank(getApiSecret())) {
      throw new ConfigurationException("Client ID is missing, please provide one and try again.");
    }

    if (getDataStoreFactory() == null) {
      throw new ConfigurationException("Data store cannot be null, .");
    }

    if (getEnvironment() == null) {
      throw new ConfigurationException("Environment must be set");
    }

    if (getHttpTransport() == null) {
      throw new ConfigurationException("Http transport type must be set");
    }

    if (getJsonFactory() == null) {
      throw new ConfigurationException("JSON factory must be set");
    }
  }
}
