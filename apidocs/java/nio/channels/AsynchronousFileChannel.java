package java.nio.channels;
public abstract class AsynchronousFileChannel
{
protected  AsynchronousFileChannel() { throw new RuntimeException("Stub!"); }
public static  java.nio.channels.AsynchronousFileChannel open(java.nio.file.Path arg0, java.util.Set<? extends java.nio.file.OpenOption> arg1, java.util.concurrent.ExecutorService arg2, java.nio.file.attribute.FileAttribute<?>... arg3) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public static  java.nio.channels.AsynchronousFileChannel open(java.nio.file.Path arg0, java.nio.file.OpenOption... arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  long size() throws java.io.IOException;
public abstract  java.nio.channels.AsynchronousFileChannel truncate(long arg0) throws java.io.IOException;
public abstract  void force(boolean arg0) throws java.io.IOException;
public abstract <A> void lock(long arg0, long arg1, boolean arg2, A arg3, java.nio.channels.CompletionHandler<java.nio.channels.FileLock, ? super A> arg4);
public final <A> void lock(A arg0, java.nio.channels.CompletionHandler<java.nio.channels.FileLock, ? super A> arg1) { throw new RuntimeException("Stub!"); }
public abstract  java.util.concurrent.Future<java.nio.channels.FileLock> lock(long arg0, long arg1, boolean arg2);
public final  java.util.concurrent.Future<java.nio.channels.FileLock> lock() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.channels.FileLock tryLock(long arg0, long arg1, boolean arg2) throws java.io.IOException;
public final  java.nio.channels.FileLock tryLock() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract <A> void read(java.nio.ByteBuffer arg0, long arg1, A arg2, java.nio.channels.CompletionHandler<java.lang.Integer, ? super A> arg3);
public abstract  java.util.concurrent.Future<java.lang.Integer> read(java.nio.ByteBuffer arg0, long arg1);
public abstract <A> void write(java.nio.ByteBuffer arg0, long arg1, A arg2, java.nio.channels.CompletionHandler<java.lang.Integer, ? super A> arg3);
public abstract  java.util.concurrent.Future<java.lang.Integer> write(java.nio.ByteBuffer arg0, long arg1);
}
