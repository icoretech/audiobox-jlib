package java.util.logging;
public abstract class Handler
{
protected  Handler() { throw new RuntimeException("Stub!"); }
public abstract  void publish(java.util.logging.LogRecord arg0);
public abstract  void flush();
public abstract  void close() throws java.lang.SecurityException;
public  void setFormatter(java.util.logging.Formatter arg0) throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.util.logging.Formatter getFormatter() { throw new RuntimeException("Stub!"); }
public  void setEncoding(java.lang.String arg0) throws java.lang.SecurityException, java.io.UnsupportedEncodingException { throw new RuntimeException("Stub!"); }
public  java.lang.String getEncoding() { throw new RuntimeException("Stub!"); }
public  void setFilter(java.util.logging.Filter arg0) throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.util.logging.Filter getFilter() { throw new RuntimeException("Stub!"); }
public  void setErrorManager(java.util.logging.ErrorManager arg0) { throw new RuntimeException("Stub!"); }
public  java.util.logging.ErrorManager getErrorManager() { throw new RuntimeException("Stub!"); }
protected  void reportError(java.lang.String arg0, java.lang.Exception arg1, int arg2) { throw new RuntimeException("Stub!"); }
public synchronized  void setLevel(java.util.logging.Level arg0) throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public synchronized  java.util.logging.Level getLevel() { throw new RuntimeException("Stub!"); }
public  boolean isLoggable(java.util.logging.LogRecord arg0) { throw new RuntimeException("Stub!"); }
}
