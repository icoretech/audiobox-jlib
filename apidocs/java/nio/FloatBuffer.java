package java.nio;
public abstract class FloatBuffer
  extends java.nio.Buffer
{
FloatBuffer() { throw new RuntimeException("Stub!"); }
public static  java.nio.FloatBuffer allocate(int arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.FloatBuffer wrap(float[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public static  java.nio.FloatBuffer wrap(float[] arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.FloatBuffer slice();
public abstract  java.nio.FloatBuffer duplicate();
public abstract  java.nio.FloatBuffer asReadOnlyBuffer();
public abstract  float get();
public abstract  java.nio.FloatBuffer put(float arg0);
public abstract  float get(int arg0);
public abstract  java.nio.FloatBuffer put(int arg0, float arg1);
public  java.nio.FloatBuffer get(float[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  java.nio.FloatBuffer get(float[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.FloatBuffer put(java.nio.FloatBuffer arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.FloatBuffer put(float[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.FloatBuffer put(float[] arg0) { throw new RuntimeException("Stub!"); }
public final  boolean hasArray() { throw new RuntimeException("Stub!"); }
public final  float[] array() { throw new RuntimeException("Stub!"); }
public final  int arrayOffset() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.FloatBuffer compact();
public abstract  boolean isDirect();
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int compareTo(java.nio.FloatBuffer arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ByteOrder order();
public  java.lang.Object array() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
}
