package com.google.api.client.http;
public class MultipartContent
  extends com.google.api.client.http.AbstractHttpContent
{
public static final class Part
{
public  Part() { throw new RuntimeException("Stub!"); }
public  Part(com.google.api.client.http.HttpContent arg0) { throw new RuntimeException("Stub!"); }
public  Part(com.google.api.client.http.HttpHeaders arg0, com.google.api.client.http.HttpContent arg1) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartContent.Part setContent(com.google.api.client.http.HttpContent arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpContent getContent() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartContent.Part setHeaders(com.google.api.client.http.HttpHeaders arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpHeaders getHeaders() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartContent.Part setEncoding(com.google.api.client.http.HttpEncoding arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpEncoding getEncoding() { throw new RuntimeException("Stub!"); }
}
public  MultipartContent() { super((com.google.api.client.http.HttpMediaType)null); throw new RuntimeException("Stub!"); }
public  void writeTo(java.io.OutputStream arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean retrySupported() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartContent setMediaType(com.google.api.client.http.HttpMediaType arg0) { throw new RuntimeException("Stub!"); }
public final  java.util.Collection<com.google.api.client.http.MultipartContent.Part> getParts() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartContent addPart(com.google.api.client.http.MultipartContent.Part arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartContent setParts(java.util.Collection<com.google.api.client.http.MultipartContent.Part> arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartContent setContentParts(java.util.Collection<? extends com.google.api.client.http.HttpContent> arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String getBoundary() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartContent setBoundary(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.AbstractHttpContent setMediaType(com.google.api.client.http.HttpMediaType arg0) { throw new RuntimeException("Stub!"); }
}
