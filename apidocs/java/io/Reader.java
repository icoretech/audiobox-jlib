package java.io;
public abstract class Reader
{
protected  Reader() { throw new RuntimeException("Stub!"); }
protected  Reader(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int read(java.nio.CharBuffer arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  int read() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  int read(char[] arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  int read(char[] arg0, int arg1, int arg2) throws java.io.IOException;
public  long skip(long arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean ready() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean markSupported() { throw new RuntimeException("Stub!"); }
public  void mark(int arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  void reset() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  void close() throws java.io.IOException;
protected java.lang.Object lock;
}
