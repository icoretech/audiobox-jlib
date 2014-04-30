package java.nio;
public abstract class LongBuffer
  extends java.nio.Buffer
{
LongBuffer() { throw new RuntimeException("Stub!"); }
public static  java.nio.LongBuffer allocate(int arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.LongBuffer wrap(long[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public static  java.nio.LongBuffer wrap(long[] arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.LongBuffer slice();
public abstract  java.nio.LongBuffer duplicate();
public abstract  java.nio.LongBuffer asReadOnlyBuffer();
public abstract  long get();
public abstract  java.nio.LongBuffer put(long arg0);
public abstract  long get(int arg0);
public abstract  java.nio.LongBuffer put(int arg0, long arg1);
public  java.nio.LongBuffer get(long[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  java.nio.LongBuffer get(long[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.LongBuffer put(java.nio.LongBuffer arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.LongBuffer put(long[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.LongBuffer put(long[] arg0) { throw new RuntimeException("Stub!"); }
public final  boolean hasArray() { throw new RuntimeException("Stub!"); }
public final  long[] array() { throw new RuntimeException("Stub!"); }
public final  int arrayOffset() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.LongBuffer compact();
public abstract  boolean isDirect();
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int compareTo(java.nio.LongBuffer arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ByteOrder order();
public  java.lang.Object array() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
}
