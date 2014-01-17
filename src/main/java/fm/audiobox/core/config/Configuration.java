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

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by keytwo on 17/01/14.
 */
public class Configuration {

  private static final File DATA_STORE_DIR = new File(System.getProperty("user.home"), ".audiobox/abx");

  private String apiKey;

  private String apiSecret;

  private DataStoreFactory dataStore;

  private Credential credential;

  private Env environment;

  private HttpTransport httpTransport;

  private JsonFactory jsonFactory;

  private Logger logger = LoggerFactory.getLogger(Configuration.class);

  private boolean ready = false;

  public enum Env { production, staging, development }

  public Configuration(Env environment) {
    this.environment = environment;
  }

  public void build() throws ConfigurationException {

    try {
      setDataStore(new FileDataStoreFactory(DATA_STORE_DIR));
    } catch (IOException e) {
      logger.error("Unable to set data store: " + e.getMessage());
    }

  }

  public boolean isReady() {
    return ready;
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

  public DataStoreFactory getDataStore() {
    return dataStore;
  }

  public void setDataStore(DataStoreFactory dataStore) {
    this.dataStore = dataStore;
  }

  public Credential getCredential() {
    return credential;
  }

  public void setCredential(Credential credential) {
    this.credential = credential;
  }

  public Env getEnvironment() {
    return environment;
  }

  public void setEnvironment(Env environment) {
    this.environment = environment;
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
    if (StringUtils.isBlank(getApiKey())) {
      throw new ConfigurationException("API Key (secret) is missing, please provide one and try again.");
    }

    if (StringUtils.isBlank(getApiSecret())) {
      throw new ConfigurationException("Client ID is missing, please provide one and try again.");
    }

    if (getDataStore() == null) {
      throw new ConfigurationException("Data store cannot be null.");
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
