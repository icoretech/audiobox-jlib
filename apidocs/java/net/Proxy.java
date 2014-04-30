package java.net;
public class Proxy
{
public static enum Type
{
DIRECT(),
HTTP(),
SOCKS();
}
public  Proxy(java.net.Proxy.Type arg0, java.net.SocketAddress arg1) { throw new RuntimeException("Stub!"); }
public  java.net.Proxy.Type type() { throw new RuntimeException("Stub!"); }
public  java.net.SocketAddress address() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public final  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public final  int hashCode() { throw new RuntimeException("Stub!"); }
public static final java.net.Proxy NO_PROXY;
static { NO_PROXY = null; }
}
