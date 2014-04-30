package com.google.api.client.auth.oauth2;
public class Credential
  implements com.google.api.client.http.HttpExecuteInterceptor, com.google.api.client.http.HttpRequestInitializer, com.google.api.client.http.HttpUnsuccessfulResponseHandler
{
public static class Builder
{
public  Builder(com.google.api.client.auth.oauth2.Credential.AccessMethod arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential build() { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.auth.oauth2.Credential.AccessMethod getMethod() { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpTransport getTransport() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder setTransport(com.google.api.client.http.HttpTransport arg0) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.util.Clock getClock() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder setClock(com.google.api.client.util.Clock arg0) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.json.JsonFactory getJsonFactory() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder setJsonFactory(com.google.api.client.json.JsonFactory arg0) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.GenericUrl getTokenServerUrl() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder setTokenServerUrl(com.google.api.client.http.GenericUrl arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder setTokenServerEncodedUrl(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpExecuteInterceptor getClientAuthentication() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder setClientAuthentication(com.google.api.client.http.HttpExecuteInterceptor arg0) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpRequestInitializer getRequestInitializer() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder setRequestInitializer(com.google.api.client.http.HttpRequestInitializer arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder addRefreshListener(com.google.api.client.auth.oauth2.CredentialRefreshListener arg0) { throw new RuntimeException("Stub!"); }
public final  java.util.Collection<com.google.api.client.auth.oauth2.CredentialRefreshListener> getRefreshListeners() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential.Builder setRefreshListeners(java.util.Collection<com.google.api.client.auth.oauth2.CredentialRefreshListener> arg0) { throw new RuntimeException("Stub!"); }
}
public static interface AccessMethod
{
public abstract  void intercept(com.google.api.client.http.HttpRequest arg0, java.lang.String arg1) throws java.io.IOException;
public abstract  java.lang.String getAccessTokenFromRequest(com.google.api.client.http.HttpRequest arg0);
}
public  Credential(com.google.api.client.auth.oauth2.Credential.AccessMethod arg0) { throw new RuntimeException("Stub!"); }
protected  Credential(com.google.api.client.auth.oauth2.Credential.Builder arg0) { throw new RuntimeException("Stub!"); }
public  void intercept(com.google.api.client.http.HttpRequest arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean handleResponse(com.google.api.client.http.HttpRequest arg0, com.google.api.client.http.HttpResponse arg1, boolean arg2) { throw new RuntimeException("Stub!"); }
public  void initialize(com.google.api.client.http.HttpRequest arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  java.lang.String getAccessToken() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential setAccessToken(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.auth.oauth2.Credential.AccessMethod getMethod() { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.util.Clock getClock() { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpTransport getTransport() { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.json.JsonFactory getJsonFactory() { throw new RuntimeException("Stub!"); }
public final  java.lang.String getTokenServerEncodedUrl() { throw new RuntimeException("Stub!"); }
public final  java.lang.String getRefreshToken() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential setRefreshToken(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.Long getExpirationTimeMilliseconds() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential setExpirationTimeMilliseconds(java.lang.Long arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.Long getExpiresInSeconds() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential setExpiresInSeconds(java.lang.Long arg0) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpExecuteInterceptor getClientAuthentication() { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpRequestInitializer getRequestInitializer() { throw new RuntimeException("Stub!"); }
public final  boolean refreshToken() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.auth.oauth2.Credential setFromTokenResponse(com.google.api.client.auth.oauth2.TokenResponse arg0) { throw new RuntimeException("Stub!"); }
protected  com.google.api.client.auth.oauth2.TokenResponse executeRefreshToken() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  java.util.Collection<com.google.api.client.auth.oauth2.CredentialRefreshListener> getRefreshListeners() { throw new RuntimeException("Stub!"); }
}
