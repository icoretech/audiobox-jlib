package java.nio;
public abstract class ShortBuffer
  extends java.nio.Buffer
{
ShortBuffer() { throw new RuntimeException("Stub!"); }
public static  java.nio.ShortBuffer allocate(int arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.ShortBuffer wrap(short[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public static  java.nio.ShortBuffer wrap(short[] arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ShortBuffer slice();
public abstract  java.nio.ShortBuffer duplicate();
public abstract  java.nio.ShortBuffer asReadOnlyBuffer();
public abstract  short get();
public abstract  java.nio.ShortBuffer put(short arg0);
public abstract  short get(int arg0);
public abstract  java.nio.ShortBuffer put(int arg0, short arg1);
public  java.nio.ShortBuffer get(short[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  java.nio.ShortBuffer get(short[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.ShortBuffer put(java.nio.ShortBuffer arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.ShortBuffer put(short[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.ShortBuffer put(short[] arg0) { throw new RuntimeException("Stub!"); }
public final  boolean hasArray() { throw new RuntimeException("Stub!"); }
public final  short[] array() { throw new RuntimeException("Stub!"); }
public final  int arrayOffset() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ShortBuffer compact();
public abstract  boolean isDirect();
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int compareTo(java.nio.ShortBuffer arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ByteOrder order();
public  java.lang.Object array() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
}
