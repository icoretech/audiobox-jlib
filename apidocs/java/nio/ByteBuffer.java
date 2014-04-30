package java.nio;
public abstract class ByteBuffer
  extends java.nio.Buffer
{
ByteBuffer() { throw new RuntimeException("Stub!"); }
public static  java.nio.ByteBuffer allocateDirect(int arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.ByteBuffer allocate(int arg0) { throw new RuntimeException("Stub!"); }
public static  java.nio.ByteBuffer wrap(byte[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public static  java.nio.ByteBuffer wrap(byte[] arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ByteBuffer slice();
public abstract  java.nio.ByteBuffer duplicate();
public abstract  java.nio.ByteBuffer asReadOnlyBuffer();
public abstract  byte get();
public abstract  java.nio.ByteBuffer put(byte arg0);
public abstract  byte get(int arg0);
public abstract  java.nio.ByteBuffer put(int arg0, byte arg1);
public  java.nio.ByteBuffer get(byte[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  java.nio.ByteBuffer get(byte[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.ByteBuffer put(java.nio.ByteBuffer arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.ByteBuffer put(byte[] arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.ByteBuffer put(byte[] arg0) { throw new RuntimeException("Stub!"); }
public final  boolean hasArray() { throw new RuntimeException("Stub!"); }
public final  byte[] array() { throw new RuntimeException("Stub!"); }
public final  int arrayOffset() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.ByteBuffer compact();
public abstract  boolean isDirect();
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int compareTo(java.nio.ByteBuffer arg0) { throw new RuntimeException("Stub!"); }
public final  java.nio.ByteOrder order() { throw new RuntimeException("Stub!"); }
public final  java.nio.ByteBuffer order(java.nio.ByteOrder arg0) { throw new RuntimeException("Stub!"); }
public abstract  char getChar();
public abstract  java.nio.ByteBuffer putChar(char arg0);
public abstract  char getChar(int arg0);
public abstract  java.nio.ByteBuffer putChar(int arg0, char arg1);
public abstract  java.nio.CharBuffer asCharBuffer();
public abstract  short getShort();
public abstract  java.nio.ByteBuffer putShort(short arg0);
public abstract  short getShort(int arg0);
public abstract  java.nio.ByteBuffer putShort(int arg0, short arg1);
public abstract  java.nio.ShortBuffer asShortBuffer();
public abstract  int getInt();
public abstract  java.nio.ByteBuffer putInt(int arg0);
public abstract  int getInt(int arg0);
public abstract  java.nio.ByteBuffer putInt(int arg0, int arg1);
public abstract  java.nio.IntBuffer asIntBuffer();
public abstract  long getLong();
public abstract  java.nio.ByteBuffer putLong(long arg0);
public abstract  long getLong(int arg0);
public abstract  java.nio.ByteBuffer putLong(int arg0, long arg1);
public abstract  java.nio.LongBuffer asLongBuffer();
public abstract  float getFloat();
public abstract  java.nio.ByteBuffer putFloat(float arg0);
public abstract  float getFloat(int arg0);
public abstract  java.nio.ByteBuffer putFloat(int arg0, float arg1);
public abstract  java.nio.FloatBuffer asFloatBuffer();
public abstract  double getDouble();
public abstract  java.nio.ByteBuffer putDouble(double arg0);
public abstract  double getDouble(int arg0);
public abstract  java.nio.ByteBuffer putDouble(int arg0, double arg1);
public abstract  java.nio.DoubleBuffer asDoubleBuffer();
public  java.lang.Object array() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
}
