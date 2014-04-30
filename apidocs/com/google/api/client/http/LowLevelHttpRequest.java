package com.google.api.client.http;
public abstract class LowLevelHttpRequest
{
public  LowLevelHttpRequest() { throw new RuntimeException("Stub!"); }
public abstract  void addHeader(java.lang.String arg0, java.lang.String arg1) throws java.io.IOException;
public final  void setContentLength(long arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  long getContentLength() { throw new RuntimeException("Stub!"); }
public final  void setContentEncoding(java.lang.String arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  java.lang.String getContentEncoding() { throw new RuntimeException("Stub!"); }
public final  void setContentType(java.lang.String arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  java.lang.String getContentType() { throw new RuntimeException("Stub!"); }
public final  void setStreamingContent(com.google.api.client.util.StreamingContent arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  com.google.api.client.util.StreamingContent getStreamingContent() { throw new RuntimeException("Stub!"); }
public  void setTimeout(int arg0, int arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  com.google.api.client.http.LowLevelHttpResponse execute() throws java.io.IOException;
}
