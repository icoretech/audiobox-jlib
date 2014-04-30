package java.nio.channels;
public abstract class FileChannel
  extends java.nio.channels.spi.AbstractInterruptibleChannel
  implements java.nio.channels.SeekableByteChannel
{
public static class MapMode
{
MapMode() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public static final java.nio.channels.FileChannel.MapMode READ_ONLY;
public static final java.nio.channels.FileChannel.MapMode READ_WRITE;
public static final java.nio.channels.FileChannel.MapMode PRIVATE;
static { READ_ONLY = null; READ_WRITE = null; PRIVATE = null; }
}
protected  FileChannel() { throw new RuntimeException("Stub!"); }
public static  java.nio.channels.FileChannel open(java.nio.file.Path arg0, java.util.Set<? extends java.nio.file.OpenOption> arg1, java.nio.file.attribute.FileAttribute<?>... arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public static  java.nio.channels.FileChannel open(java.nio.file.Path arg0, java.nio.file.OpenOption... arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  int read(java.nio.ByteBuffer arg0) throws java.io.IOException;
public abstract  long read(java.nio.ByteBuffer[] arg0, int arg1, int arg2) throws java.io.IOException;
public final  long read(java.nio.ByteBuffer[] arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  int write(java.nio.ByteBuffer arg0) throws java.io.IOException;
public abstract  long write(java.nio.ByteBuffer[] arg0, int arg1, int arg2) throws java.io.IOException;
public final  long write(java.nio.ByteBuffer[] arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  long position() throws java.io.IOException;
public abstract  java.nio.channels.FileChannel position(long arg0) throws java.io.IOException;
public abstract  long size() throws java.io.IOException;
public abstract  java.nio.channels.FileChannel truncate(long arg0) throws java.io.IOException;
public abstract  void force(boolean arg0) throws java.io.IOException;
public abstract  long transferTo(long arg0, long arg1, java.nio.channels.WritableByteChannel arg2) throws java.io.IOException;
public abstract  long transferFrom(java.nio.channels.ReadableByteChannel arg0, long arg1, long arg2) throws java.io.IOException;
public abstract  int read(java.nio.ByteBuffer arg0, long arg1) throws java.io.IOException;
public abstract  int write(java.nio.ByteBuffer arg0, long arg1) throws java.io.IOException;
public abstract  java.nio.MappedByteBuffer map(java.nio.channels.FileChannel.MapMode arg0, long arg1, long arg2) throws java.io.IOException;
public abstract  java.nio.channels.FileLock lock(long arg0, long arg1, boolean arg2) throws java.io.IOException;
public final  java.nio.channels.FileLock lock() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  java.nio.channels.FileLock tryLock(long arg0, long arg1, boolean arg2) throws java.io.IOException;
public final  java.nio.channels.FileLock tryLock() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.nio.channels.SeekableByteChannel truncate(long arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.nio.channels.SeekableByteChannel position(long arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
}
