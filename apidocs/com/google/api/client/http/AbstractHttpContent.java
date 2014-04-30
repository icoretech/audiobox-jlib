package com.google.api.client.http;
public abstract class AbstractHttpContent
  implements com.google.api.client.http.HttpContent
{
protected  AbstractHttpContent(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
protected  AbstractHttpContent(com.google.api.client.http.HttpMediaType arg0) { throw new RuntimeException("Stub!"); }
public  long getLength() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.http.HttpMediaType getMediaType() { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.AbstractHttpContent setMediaType(com.google.api.client.http.HttpMediaType arg0) { throw new RuntimeException("Stub!"); }
protected final  java.nio.charset.Charset getCharset() { throw new RuntimeException("Stub!"); }
public  java.lang.String getType() { throw new RuntimeException("Stub!"); }
protected  long computeLength() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean retrySupported() { throw new RuntimeException("Stub!"); }
public static  long computeLength(com.google.api.client.http.HttpContent arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
}
