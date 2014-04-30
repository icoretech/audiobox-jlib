package java.nio;
public abstract class CharBuffer
  extends java.nio.Buffer
  implements java.lang.Appendable, java.lang.CharSequence
{
CharBuffer() { throw new RuntimeException("Stub!"); }
public static  java.nio.CharBuffer allocate(int arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.CharBuffer wrap(char[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public static  java.nio.CharBuffer wrap(char[] arg0) { throw new RuntimeException("Stub!"); }
public  int read(java.nio.CharBuffer arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public static  java.nio.CharBuffer wrap(java.lang.CharSequence arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public static  java.nio.CharBuffer wrap(java.lang.CharSequence arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.CharBuffer slice();
public abstract  java.nio.CharBuffer duplicate();
public abstract  java.nio.CharBuffer asReadOnlyBuffer();
public abstract  char get();
public abstract  java.nio.CharBuffer put(char arg0);
public abstract  char get(int arg0);
public abstract  java.nio.CharBuffer put(int arg0, char arg1);
public  java.nio.CharBuffer get(char[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  java.nio.CharBuffer get(char[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.CharBuffer put(java.nio.CharBuffer arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.CharBuffer put(char[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.CharBuffer put(char[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.CharBuffer put(java.lang.String arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.CharBuffer put(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  boolean hasArray() { throw new RuntimeException("Stub!"); }
public final  char[] array() { throw new RuntimeException("Stub!"); }
public final  int arrayOffset() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.CharBuffer compact();
public abstract  boolean isDirect();
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int compareTo(java.nio.CharBuffer arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public final  int length() { throw new RuntimeException("Stub!"); }
public final  char charAt(int arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.CharBuffer subSequence(int arg0, int arg1);
public  java.nio.CharBuffer append(java.lang.CharSequence arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.CharBuffer append(java.lang.CharSequence arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  java.nio.CharBuffer append(char arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ByteOrder order();
public  java.lang.Object array() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.Appendable append(char arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.Appendable append(java.lang.CharSequence arg0, int arg1, int arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.Appendable append(java.lang.CharSequence arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.CharSequence subSequence(int arg0, int arg1) { throw new RuntimeException("Stub!"); }
}
