package java.net;
public class InetAddress
  implements java.io.Serializable
{
InetAddress() { throw new RuntimeException("Stub!"); }
public  boolean isMulticastAddress() { throw new RuntimeException("Stub!"); }
public  boolean isAnyLocalAddress() { throw new RuntimeException("Stub!"); }
public  boolean isLoopbackAddress() { throw new RuntimeException("Stub!"); }
public  boolean isLinkLocalAddress() { throw new RuntimeException("Stub!"); }
public  boolean isSiteLocalAddress() { throw new RuntimeException("Stub!"); }
public  boolean isMCGlobal() { throw new RuntimeException("Stub!"); }
public  boolean isMCNodeLocal() { throw new RuntimeException("Stub!"); }
public  boolean isMCLinkLocal() { throw new RuntimeException("Stub!"); }
public  boolean isMCSiteLocal() { throw new RuntimeException("Stub!"); }
public  boolean isMCOrgLocal() { throw new RuntimeException("Stub!"); }
public  boolean isReachable(int arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean isReachable(java.net.NetworkInterface arg0, int arg1, int arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.String getHostName() { throw new RuntimeException("Stub!"); }
public  java.lang.String getCanonicalHostName() { throw new RuntimeException("Stub!"); }
public  byte[] getAddress() { throw new RuntimeException("Stub!"); }
public  java.lang.String getHostAddress() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public static  java.net.InetAddress getByAddress(java.lang.String arg0, byte[] arg1) throws java.net.UnknownHostException { throw new RuntimeException("Stub!"); }
public static  java.net.InetAddress getByName(java.lang.String arg0) throws java.net.UnknownHostException { throw new RuntimeException("Stub!"); }
public static  java.net.InetAddress[] getAllByName(java.lang.String arg0) throws java.net.UnknownHostException { throw new RuntimeException("Stub!"); }
public static  java.net.InetAddress getLoopbackAddress() { throw new RuntimeException("Stub!"); }
public static  java.net.InetAddress getByAddress(byte[] arg0) throws java.net.UnknownHostException { throw new RuntimeException("Stub!"); }
public static  java.net.InetAddress getLocalHost() throws java.net.UnknownHostException { throw new RuntimeException("Stub!"); }
}
