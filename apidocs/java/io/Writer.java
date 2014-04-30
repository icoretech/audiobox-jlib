package java.io;
public abstract class Writer
  implements java.lang.Appendable
{
protected  Writer() { throw new RuntimeException("Stub!"); }
protected  Writer(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  void write(int arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  void write(char[] arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  void write(char[] arg0, int arg1, int arg2) throws java.io.IOException;
public  void write(java.lang.String arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  void write(java.lang.String arg0, int arg1, int arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.Writer append(java.lang.CharSequence arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.Writer append(java.lang.CharSequence arg0, int arg1, int arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.Writer append(char arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  void flush() throws java.io.IOException;
public abstract  void close() throws java.io.IOException;
public  java.lang.Appendable append(char arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.Appendable append(java.lang.CharSequence arg0, int arg1, int arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.Appendable append(java.lang.CharSequence arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
protected java.lang.Object lock;
}
