package java.nio;
public abstract class Buffer
{
Buffer() { throw new RuntimeException("Stub!"); }
public final  int capacity() { throw new RuntimeException("Stub!"); }
public final  int position() { throw new RuntimeException("Stub!"); }
public final  java.nio.Buffer position(int arg0) { throw new RuntimeException("Stub!"); }
public final  int limit() { throw new RuntimeException("Stub!"); }
public final  java.nio.Buffer limit(int arg0) { throw new RuntimeException("Stub!"); }
public final  java.nio.Buffer mark() { throw new RuntimeException("Stub!"); }
public final  java.nio.Buffer reset() { throw new RuntimeException("Stub!"); }
public final  java.nio.Buffer clear() { throw new RuntimeException("Stub!"); }
public final  java.nio.Buffer flip() { throw new RuntimeException("Stub!"); }
public final  java.nio.Buffer rewind() { throw new RuntimeException("Stub!"); }
public final  int remaining() { throw new RuntimeException("Stub!"); }
public final  boolean hasRemaining() { throw new RuntimeException("Stub!"); }
public abstract  boolean isReadOnly();
public abstract  boolean hasArray();
public abstract  java.lang.Object array();
public abstract  int arrayOffset();
public abstract  boolean isDirect();
}
