package fm.audiobox.core.config;
public class Configuration
{
public static enum Env
{
development(),
production(),
staging();
}
public  Configuration() { throw new RuntimeException("Stub!"); }
public  Configuration(fm.audiobox.core.config.Configuration.Env environment) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setApiKey(java.lang.String apiKey) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setApiSecret(java.lang.String apiSecret) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setHttpTransport(com.google.api.client.http.HttpTransport httpTransport) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setJsonFactory(com.google.api.client.json.JsonFactory jsonFactory) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setDataStoreFactory(com.google.api.client.util.store.DataStoreFactory dataStoreFactory) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setMediaFilesWrapperClass(java.lang.Class<? extends fm.audiobox.core.models.MediaFiles> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setAlbumsWrapperClass(java.lang.Class<? extends fm.audiobox.core.models.Albums> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setGenresWrapperClass(java.lang.Class<? extends fm.audiobox.core.models.Genres> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setArtistsWrapperClass(java.lang.Class<? extends fm.audiobox.core.models.Artists> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setMediaFileClass(java.lang.Class<? extends fm.audiobox.core.models.MediaFileWrapper> klass) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setEnvironment(fm.audiobox.core.config.Configuration.Env environment) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setApplicationName(java.lang.String applicationName) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration setVersion(java.lang.String version) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.util.store.DataStoreFactory getDataStoreFactory() { throw new RuntimeException("Stub!"); }
public  com.typesafe.config.Config getEnvironmentConfiguration(fm.audiobox.core.config.Configuration.Env environment) { throw new RuntimeException("Stub!"); }
public  java.lang.String getEnvBaseUrl() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.GenericUrl getEnvTokenUrl() { throw new RuntimeException("Stub!"); }
public  java.lang.String getApiKey() { throw new RuntimeException("Stub!"); }
public  java.lang.String getApiSecret() { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration.Env getEnvironment() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpTransport getHttpTransport() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.json.JsonFactory getJsonFactory() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.MediaFiles> getMediaFilesWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.MediaFileWrapper> getMediaFileWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.Albums> getAlbumsWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.Genres> getGenresWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<? extends fm.audiobox.core.models.Artists> getArtistsWrapperClass() { throw new RuntimeException("Stub!"); }
public  java.lang.String getUserAgent() { throw new RuntimeException("Stub!"); }
public  java.lang.String getApplicationName() { throw new RuntimeException("Stub!"); }
public  java.lang.String getVersion() { throw new RuntimeException("Stub!"); }
public  void checkConfiguration() throws javax.naming.ConfigurationException { throw new RuntimeException("Stub!"); }
}
