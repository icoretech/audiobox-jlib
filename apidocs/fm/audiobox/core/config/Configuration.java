package fm.audiobox.core.config;
public class Configuration
{
public static enum Env
{
development(),
production(),
staging();
}
public static enum Channels
{
api(),
daemon(),
upload();
}
public  Configuration() { throw new RuntimeException("Stub!"); }
public  Configuration(fm.audiobox.core.config.Configuration.Env environment) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setApiKey(java.lang.String apiKey) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setApiSecret(java.lang.String apiSecret) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setHttpTransport(com.google.api.client.http.HttpTransport httpTransport) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setJsonFactory(com.google.api.client.json.JsonFactory jsonFactory) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setCredentialDataStore(fm.audiobox.core.store.CredentialDataStore credentialDataStore) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setCredentialRefreshListener(com.google.api.client.auth.oauth2.CredentialRefreshListener refreshListener) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setMediaFilesWrapperClass(java.lang.Class<fm.audiobox.core.models.MediaFiles> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setAlbumsWrapperClass(java.lang.Class<fm.audiobox.core.models.Albums> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setGenresWrapperClass(java.lang.Class<fm.audiobox.core.models.Genres> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setArtistsWrapperClass(java.lang.Class<fm.audiobox.core.models.Artists> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setMediaFileClass(java.lang.Class<fm.audiobox.core.models.MediaFileWrapper> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setEnvironment(fm.audiobox.core.config.Configuration.Env environment) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setApplicationName(java.lang.String applicationName) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setVersion(java.lang.String version) { throw new RuntimeException("Stub!"); }
public synchronized  void setExceptionHandler(fm.audiobox.core.exceptions.ExceptionHandler handler) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.store.CredentialDataStore getCredentialDataStore() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.CredentialRefreshListener getRefreshListener() { throw new RuntimeException("Stub!"); }
public  com.typesafe.config.Config getEnvironmentConfiguration(fm.audiobox.core.config.Configuration.Env environment) { throw new RuntimeException("Stub!"); }
public  java.lang.String getEnvBaseUrl(fm.audiobox.core.config.Configuration.Channels channel) { throw new RuntimeException("Stub!"); }
public  java.lang.String getEnvHost(fm.audiobox.core.config.Configuration.Channels channel) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.GenericUrl getEnvTokenUrl() { throw new RuntimeException("Stub!"); }
public  java.lang.String getApiKey() { throw new RuntimeException("Stub!"); }
public  java.lang.String getApiSecret() { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration.Env getEnvironment() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpTransport getHttpTransport() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.json.JsonFactory getJsonFactory() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<fm.audiobox.core.models.MediaFiles> getMediaFilesWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.MediaFileWrapper> getMediaFileWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.Albums> getAlbumsWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.Genres> getGenresWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.Artists> getArtistsWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.String getUserAgent() { throw new RuntimeException("Stub!"); }
public  java.lang.String getApplicationName() { throw new RuntimeException("Stub!"); }
public  java.lang.String getVersion() { throw new RuntimeException("Stub!"); }
public synchronized  fm.audiobox.core.exceptions.ExceptionHandler getExceptionHandler() { throw new RuntimeException("Stub!"); }
public  void checkConfiguration() throws fm.audiobox.core.config.ConfigurationException { throw new RuntimeException("Stub!"); }
}
