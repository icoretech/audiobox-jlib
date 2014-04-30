package com.google.api.client.http;
@com.google.api.client.util.Beta()
@javax.annotation.concurrent.NotThreadSafe()
public class MultipartFormDataContent
  extends com.google.api.client.http.MultipartContent
{
public  MultipartFormDataContent() { throw new RuntimeException("Stub!"); }
protected static final  com.google.api.client.http.HttpMediaType getMultipartFormDataMediaType() { throw new RuntimeException("Stub!"); }
public  void writeTo(java.io.OutputStream out) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartFormDataContent addPart(com.google.api.client.http.MultipartContent.Part part, java.lang.String dispositionName, java.lang.String dispositionFilename) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartFormDataContent addPart(com.google.api.client.http.MultipartContent.Part part) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartFormDataContent setParts(java.util.Collection<com.google.api.client.http.MultipartContent.Part> parts) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartFormDataContent setContentParts(java.util.Collection<? extends com.google.api.client.http.HttpContent> contentParts) { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.MultipartFormDataContent setMediaType(com.google.api.client.http.HttpMediaType mediaType) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.MultipartFormDataContent setBoundary(java.lang.String boundary) { throw new RuntimeException("Stub!"); }
protected static final java.lang.String DEFAULT_BOUNDARY = "__0xKhTmLbOuNdArY__";
}
