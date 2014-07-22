package com.google.api.client.http;
public final class HttpRequest
{
HttpRequest() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpTransport getTransport() { throw new RuntimeException("Stub!"); }
public  java.lang.String getRequestMethod() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setRequestMethod(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.GenericUrl getUrl() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setUrl(com.google.api.client.http.GenericUrl arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpContent getContent() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setContent(com.google.api.client.http.HttpContent arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpEncoding getEncoding() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setEncoding(com.google.api.client.http.HttpEncoding arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.BackOffPolicy getBackOffPolicy() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setBackOffPolicy(com.google.api.client.http.BackOffPolicy arg0) { throw new RuntimeException("Stub!"); }
public  int getContentLoggingLimit() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setContentLoggingLimit(int arg0) { throw new RuntimeException("Stub!"); }
public  boolean isLoggingEnabled() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setLoggingEnabled(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean isCurlLoggingEnabled() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setCurlLoggingEnabled(boolean arg0) { throw new RuntimeException("Stub!"); }
public  int getConnectTimeout() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setConnectTimeout(int arg0) { throw new RuntimeException("Stub!"); }
public  int getReadTimeout() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setReadTimeout(int arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpHeaders getHeaders() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setHeaders(com.google.api.client.http.HttpHeaders arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpHeaders getResponseHeaders() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setResponseHeaders(com.google.api.client.http.HttpHeaders arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpExecuteInterceptor getInterceptor() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setInterceptor(com.google.api.client.http.HttpExecuteInterceptor arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setUnsuccessfulResponseHandler(com.google.api.client.http.HttpUnsuccessfulResponseHandler arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpIOExceptionHandler getIOExceptionHandler() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setIOExceptionHandler(com.google.api.client.http.HttpIOExceptionHandler arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponseInterceptor getResponseInterceptor() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setResponseInterceptor(com.google.api.client.http.HttpResponseInterceptor arg0) { throw new RuntimeException("Stub!"); }
public  int getNumberOfRetries() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setNumberOfRetries(int arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setParser(com.google.api.client.util.ObjectParser arg0) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.util.ObjectParser getParser() { throw new RuntimeException("Stub!"); }
public  boolean getFollowRedirects() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setFollowRedirects(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean getThrowExceptionOnExecuteError() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setThrowExceptionOnExecuteError(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean getRetryOnExecuteIOException() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setRetryOnExecuteIOException(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean getSuppressUserAgentSuffix() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setSuppressUserAgentSuffix(boolean arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse execute() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.concurrent.Future<com.google.api.client.http.HttpResponse> executeAsync(java.util.concurrent.Executor arg0) { throw new RuntimeException("Stub!"); }
public  java.util.concurrent.Future<com.google.api.client.http.HttpResponse> executeAsync() { throw new RuntimeException("Stub!"); }
public  boolean handleRedirect(int arg0, com.google.api.client.http.HttpHeaders arg1) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.util.Sleeper getSleeper() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpRequest setSleeper(com.google.api.client.util.Sleeper arg0) { throw new RuntimeException("Stub!"); }
public static final java.lang.String VERSION = "1.19.0";
public static final java.lang.String USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/1.19.0 (gzip)";
}
