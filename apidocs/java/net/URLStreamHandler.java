package java.net;
public abstract class URLStreamHandler
{
public  URLStreamHandler() { throw new RuntimeException("Stub!"); }
protected abstract  java.net.URLConnection openConnection(java.net.URL arg0) throws java.io.IOException;
protected  java.net.URLConnection openConnection(java.net.URL arg0, java.net.Proxy arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
protected  void parseURL(java.net.URL arg0, java.lang.String arg1, int arg2, int arg3) { throw new RuntimeException("Stub!"); }
protected  int getDefaultPort() { throw new RuntimeException("Stub!"); }
protected  boolean equals(java.net.URL arg0, java.net.URL arg1) { throw new RuntimeException("Stub!"); }
protected  int hashCode(java.net.URL arg0) { throw new RuntimeException("Stub!"); }
protected  boolean sameFile(java.net.URL arg0, java.net.URL arg1) { throw new RuntimeException("Stub!"); }
protected synchronized  java.net.InetAddress getHostAddress(java.net.URL arg0) { throw new RuntimeException("Stub!"); }
protected  boolean hostsEqual(java.net.URL arg0, java.net.URL arg1) { throw new RuntimeException("Stub!"); }
protected  java.lang.String toExternalForm(java.net.URL arg0) { throw new RuntimeException("Stub!"); }
protected  void setURL(java.net.URL arg0, java.lang.String arg1, java.lang.String arg2, int arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7, java.lang.String arg8) { throw new RuntimeException("Stub!"); }
protected  void setURL(java.net.URL arg0, java.lang.String arg1, java.lang.String arg2, int arg3, java.lang.String arg4, java.lang.String arg5) { throw new RuntimeException("Stub!"); }
}
