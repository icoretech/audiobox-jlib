package java.nio;
public abstract class IntBuffer
  extends java.nio.Buffer
{
IntBuffer() { throw new RuntimeException("Stub!"); }
public static  java.nio.IntBuffer allocate(int arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.IntBuffer wrap(int[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public static  java.nio.IntBuffer wrap(int[] arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.IntBuffer slice();
public abstract  java.nio.IntBuffer duplicate();
public abstract  java.nio.IntBuffer asReadOnlyBuffer();
public abstract  int get();
public abstract  java.nio.IntBuffer put(int arg0);
public abstract  int get(int arg0);
public abstract  java.nio.IntBuffer put(int arg0, int arg1);
public  java.nio.IntBuffer get(int[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  java.nio.IntBuffer get(int[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.IntBuffer put(java.nio.IntBuffer arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.IntBuffer put(int[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.IntBuffer put(int[] arg0) { throw new RuntimeException("Stub!"); }
public final  boolean hasArray() { throw new RuntimeException("Stub!"); }
public final  int[] array() { throw new RuntimeException("Stub!"); }
public final  int arrayOffset() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.IntBuffer compact();
public abstract  boolean isDirect();
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int compareTo(java.nio.IntBuffer arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ByteOrder order();
public  java.lang.Object array() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
}
