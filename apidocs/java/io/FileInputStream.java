package java.io;
public class FileInputStream
  extends java.io.InputStream
{
public  FileInputStream(java.lang.String arg0) throws java.io.FileNotFoundException { throw new RuntimeException("Stub!"); }
public  FileInputStream(java.io.File arg0) throws java.io.FileNotFoundException { throw new RuntimeException("Stub!"); }
public  FileInputStream(java.io.FileDescriptor arg0) { throw new RuntimeException("Stub!"); }
public  int read() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  int read(byte[] arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  int read(byte[] arg0, int arg1, int arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public native  long skip(long arg0) throws java.io.IOException;
public native  int available() throws java.io.IOException;
public  void close() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  java.io.FileDescriptor getFD() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.nio.channels.FileChannel getChannel() { throw new RuntimeException("Stub!"); }
protected  void finalize() throws java.io.IOException { throw new RuntimeException("Stub!"); }
}
