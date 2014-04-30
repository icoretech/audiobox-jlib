package com.google.api.client.http;
public abstract class HttpTransport
{
public  HttpTransport() { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpRequestFactory createRequestFactory() { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpRequestFactory createRequestFactory(com.google.api.client.http.HttpRequestInitializer arg0) { throw new RuntimeException("Stub!"); }
public  boolean supportsMethod(java.lang.String arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
protected abstract  com.google.api.client.http.LowLevelHttpRequest buildRequest(java.lang.String arg0, java.lang.String arg1) throws java.io.IOException;
public  void shutdown() throws java.io.IOException { throw new RuntimeException("Stub!"); }
}
