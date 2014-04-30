package java.nio.channels;
public abstract class FileLock
{
protected  FileLock(java.nio.channels.FileChannel arg0, long arg1, long arg2, boolean arg3) { throw new RuntimeException("Stub!"); }
protected  FileLock(java.nio.channels.AsynchronousFileChannel arg0, long arg1, long arg2, boolean arg3) { throw new RuntimeException("Stub!"); }
public final  java.nio.channels.FileChannel channel() { throw new RuntimeException("Stub!"); }
public  java.nio.channels.Channel acquiredBy() { throw new RuntimeException("Stub!"); }
public final  long position() { throw new RuntimeException("Stub!"); }
public final  long size() { throw new RuntimeException("Stub!"); }
public final  boolean isShared() { throw new RuntimeException("Stub!"); }
public final  boolean overlaps(long arg0, long arg1) { throw new RuntimeException("Stub!"); }
public abstract  boolean isValid();
public abstract  void release() throws java.io.IOException;
public final  void close() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  java.lang.String toString() { throw new RuntimeException("Stub!"); }
}
