package java.util.logging;
public abstract class Formatter
{
protected  Formatter() { throw new RuntimeException("Stub!"); }
public abstract  java.lang.String format(java.util.logging.LogRecord arg0);
public  java.lang.String getHead(java.util.logging.Handler arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String getTail(java.util.logging.Handler arg0) { throw new RuntimeException("Stub!"); }
public synchronized  java.lang.String formatMessage(java.util.logging.LogRecord arg0) { throw new RuntimeException("Stub!"); }
}
