package com.google.api.client.http;
public abstract class LowLevelHttpResponse
{
public  LowLevelHttpResponse() { throw new RuntimeException("Stub!"); }
public abstract  java.io.InputStream getContent() throws java.io.IOException;
public abstract  java.lang.String getContentEncoding() throws java.io.IOException;
public abstract  long getContentLength() throws java.io.IOException;
public abstract  java.lang.String getContentType() throws java.io.IOException;
public abstract  java.lang.String getStatusLine() throws java.io.IOException;
public abstract  int getStatusCode() throws java.io.IOException;
public abstract  java.lang.String getReasonPhrase() throws java.io.IOException;
public abstract  int getHeaderCount() throws java.io.IOException;
public abstract  java.lang.String getHeaderName(int arg0) throws java.io.IOException;
public abstract  java.lang.String getHeaderValue(int arg0) throws java.io.IOException;
public  void disconnect() throws java.io.IOException { throw new RuntimeException("Stub!"); }
}
