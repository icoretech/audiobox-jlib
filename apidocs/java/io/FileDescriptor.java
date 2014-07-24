package java.io;
public final class FileDescriptor
{
public  FileDescriptor() { throw new RuntimeException("Stub!"); }
public  boolean valid() { throw new RuntimeException("Stub!"); }
public native  void sync() throws java.io.SyncFailedException;
public static final java.io.FileDescriptor in;
public static final java.io.FileDescriptor out;
public static final java.io.FileDescriptor err;
static { in = null; out = null; err = null; }
}
