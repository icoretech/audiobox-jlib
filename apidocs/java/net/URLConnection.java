package java.net;
public abstract class URLConnection
{
protected  URLConnection(java.net.URL arg0) { throw new RuntimeException("Stub!"); }
public static synchronized  java.net.FileNameMap getFileNameMap() { throw new RuntimeException("Stub!"); }
public static  void setFileNameMap(java.net.FileNameMap arg0) { throw new RuntimeException("Stub!"); }
public abstract  void connect() throws java.io.IOException;
public  void setConnectTimeout(int arg0) { throw new RuntimeException("Stub!"); }
public  int getConnectTimeout() { throw new RuntimeException("Stub!"); }
public  void setReadTimeout(int arg0) { throw new RuntimeException("Stub!"); }
public  int getReadTimeout() { throw new RuntimeException("Stub!"); }
public  java.net.URL getURL() { throw new RuntimeException("Stub!"); }
public  int getContentLength() { throw new RuntimeException("Stub!"); }
public  long getContentLengthLong() { throw new RuntimeException("Stub!"); }
public  java.lang.String getContentType() { throw new RuntimeException("Stub!"); }
public  java.lang.String getContentEncoding() { throw new RuntimeException("Stub!"); }
public  long getExpiration() { throw new RuntimeException("Stub!"); }
public  long getDate() { throw new RuntimeException("Stub!"); }
public  long getLastModified() { throw new RuntimeException("Stub!"); }
public  java.lang.String getHeaderField(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Map<java.lang.String, java.util.List<java.lang.String>> getHeaderFields() { throw new RuntimeException("Stub!"); }
public  int getHeaderFieldInt(java.lang.String arg0, int arg1) { throw new RuntimeException("Stub!"); }
public  long getHeaderFieldLong(java.lang.String arg0, long arg1) { throw new RuntimeException("Stub!"); }
public  long getHeaderFieldDate(java.lang.String arg0, long arg1) { throw new RuntimeException("Stub!"); }
public  java.lang.String getHeaderFieldKey(int arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String getHeaderField(int arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.Object getContent() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.Object getContent(java.lang.Class[] arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.security.Permission getPermission() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.InputStream getInputStream() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.OutputStream getOutputStream() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  void setDoInput(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean getDoInput() { throw new RuntimeException("Stub!"); }
public  void setDoOutput(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean getDoOutput() { throw new RuntimeException("Stub!"); }
public  void setAllowUserInteraction(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean getAllowUserInteraction() { throw new RuntimeException("Stub!"); }
public static  void setDefaultAllowUserInteraction(boolean arg0) { throw new RuntimeException("Stub!"); }
public static  boolean getDefaultAllowUserInteraction() { throw new RuntimeException("Stub!"); }
public  void setUseCaches(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean getUseCaches() { throw new RuntimeException("Stub!"); }
public  void setIfModifiedSince(long arg0) { throw new RuntimeException("Stub!"); }
public  long getIfModifiedSince() { throw new RuntimeException("Stub!"); }
public  boolean getDefaultUseCaches() { throw new RuntimeException("Stub!"); }
public  void setDefaultUseCaches(boolean arg0) { throw new RuntimeException("Stub!"); }
public  void setRequestProperty(java.lang.String arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public  void addRequestProperty(java.lang.String arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public  java.lang.String getRequestProperty(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Map<java.lang.String, java.util.List<java.lang.String>> getRequestProperties() { throw new RuntimeException("Stub!"); }
public static  void setDefaultRequestProperty(java.lang.String arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public static  java.lang.String getDefaultRequestProperty(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static synchronized  void setContentHandlerFactory(java.net.ContentHandlerFactory arg0) { throw new RuntimeException("Stub!"); }
public static  java.lang.String guessContentTypeFromName(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static  java.lang.String guessContentTypeFromStream(java.io.InputStream arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
protected java.net.URL url;
protected boolean doInput;
protected boolean doOutput;
protected boolean allowUserInteraction;
protected boolean useCaches;
protected long ifModifiedSince;
protected boolean connected;
}
