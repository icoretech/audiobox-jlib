package java.nio;
public abstract class DoubleBuffer
  extends java.nio.Buffer
{
DoubleBuffer() { throw new RuntimeException("Stub!"); }
public static  java.nio.DoubleBuffer allocate(int arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.DoubleBuffer wrap(double[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public static  java.nio.DoubleBuffer wrap(double[] arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.DoubleBuffer slice();
public abstract  java.nio.DoubleBuffer duplicate();
public abstract  java.nio.DoubleBuffer asReadOnlyBuffer();
public abstract  double get();
public abstract  java.nio.DoubleBuffer put(double arg0);
public abstract  double get(int arg0);
public abstract  java.nio.DoubleBuffer put(int arg0, double arg1);
public  java.nio.DoubleBuffer get(double[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  java.nio.DoubleBuffer get(double[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.DoubleBuffer put(java.nio.DoubleBuffer arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.DoubleBuffer put(double[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.DoubleBuffer put(double[] arg0) { throw new RuntimeException("Stub!"); }
public final  boolean hasArray() { throw new RuntimeException("Stub!"); }
public final  double[] array() { throw new RuntimeException("Stub!"); }
public final  int arrayOffset() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.DoubleBuffer compact();
public abstract  boolean isDirect();
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int compareTo(java.nio.DoubleBuffer arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ByteOrder order();
public  java.lang.Object array() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
}
