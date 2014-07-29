package fm.audiobox.core;
public class Client
{
public  Client(fm.audiobox.core.config.Configuration conf) throws fm.audiobox.core.config.ConfigurationException, java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.config.Configuration getConf() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpHeaders getDefaultHeaders() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.TokenResponse authorize(java.lang.String username, java.lang.String password) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean isDaemonRunning() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.String remoteDaemonIp() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.User getUser() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<fm.audiobox.core.models.Playlist> getPlaylists() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist getPlaylist(java.lang.String token) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Notifications getNotifications() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.net.Upload newUpload(java.io.File file) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.net.Upload newUpload(java.io.File file, fm.audiobox.core.net.NetworkProgressListener listener) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doGET(java.lang.String path) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doGET(java.lang.String path, com.google.api.client.json.JsonObjectParser parser) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doPUT(java.lang.String path, com.google.api.client.http.HttpContent data) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doPUT(java.lang.String path, com.google.api.client.http.HttpContent data, com.google.api.client.json.JsonObjectParser parser) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doDELETE(java.lang.String path) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doDELETE(java.lang.String path, com.google.api.client.json.JsonObjectParser parser) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doPOST(java.lang.String path) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doPOST(java.lang.String path, com.google.api.client.http.HttpContent data) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doPOST(java.lang.String path, com.google.api.client.http.HttpContent data, com.google.api.client.json.JsonObjectParser parser) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doRequest(java.lang.String method, java.lang.String path, com.google.api.client.http.HttpContent data, com.google.api.client.json.JsonObjectParser parser) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse doRequestToChannel(java.lang.String method, java.lang.String path, com.google.api.client.http.HttpContent data, com.google.api.client.json.JsonObjectParser parser, fm.audiobox.core.config.Configuration.Channels channel) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public static final java.lang.String ACCOUNT_TOKENS = "_audiobox_account_tokens";
}
