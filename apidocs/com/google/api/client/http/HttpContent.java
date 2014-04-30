package com.google.api.client.http;
public interface HttpContent
  extends com.google.api.client.util.StreamingContent
{
public abstract  long getLength() throws java.io.IOException;
public abstract  java.lang.String getType();
public abstract  boolean retrySupported();
public abstract  void writeTo(java.io.OutputStream arg0) throws java.io.IOException;
}
