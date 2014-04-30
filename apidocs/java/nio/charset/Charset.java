package java.nio.charset;
public abstract class Charset
{
protected  Charset(java.lang.String arg0, java.lang.String[] arg1) { throw new RuntimeException("Stub!"); }
public static  boolean isSupported(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.charset.Charset forName(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static  java.util.SortedMap<java.lang.String, java.nio.charset.Charset> availableCharsets() { throw new RuntimeException("Stub!"); }
public static  java.nio.charset.Charset defaultCharset() { throw new RuntimeException("Stub!"); }
public final  java.lang.String name() { throw new RuntimeException("Stub!"); }
public final  java.util.Set<java.lang.String> aliases() { throw new RuntimeException("Stub!"); }
public  java.lang.String displayName() { throw new RuntimeException("Stub!"); }
public final  boolean isRegistered() { throw new RuntimeException("Stub!"); }
public  java.lang.String displayName(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public abstract  boolean contains(java.nio.charset.Charset arg0);
public abstract  java.nio.charset.CharsetDecoder newDecoder();
public abstract  java.nio.charset.CharsetEncoder newEncoder();
public  boolean canEncode() { throw new RuntimeException("Stub!"); }
public final  java.nio.CharBuffer decode(java.nio.ByteBuffer arg0) { throw new RuntimeException("Stub!"); }
public final  java.nio.ByteBuffer encode(java.nio.CharBuffer arg0) { throw new RuntimeException("Stub!"); }
public final  java.nio.ByteBuffer encode(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  int compareTo(java.nio.charset.Charset arg0) { throw new RuntimeException("Stub!"); }
public final  int hashCode() { throw new RuntimeException("Stub!"); }
public final  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
}
