package java.lang;
public class Thread
  implements java.lang.Runnable
{
public static enum State
{
BLOCKED(),
NEW(),
RUNNABLE(),
TERMINATED(),
TIMED_WAITING(),
WAITING();
}
public static interface UncaughtExceptionHandler
{
public abstract  void uncaughtException(java.lang.Thread arg0, java.lang.Throwable arg1);
}
public  Thread() { throw new RuntimeException("Stub!"); }
public  Thread(java.lang.Runnable arg0) { throw new RuntimeException("Stub!"); }
public  Thread(java.lang.ThreadGroup arg0, java.lang.Runnable arg1) { throw new RuntimeException("Stub!"); }
public  Thread(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  Thread(java.lang.ThreadGroup arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public  Thread(java.lang.Runnable arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public  Thread(java.lang.ThreadGroup arg0, java.lang.Runnable arg1, java.lang.String arg2) { throw new RuntimeException("Stub!"); }
public  Thread(java.lang.ThreadGroup arg0, java.lang.Runnable arg1, java.lang.String arg2, long arg3) { throw new RuntimeException("Stub!"); }
public static native  java.lang.Thread currentThread();
public static native  void yield();
public static native  void sleep(long arg0) throws java.lang.InterruptedException;
public static  void sleep(long arg0, int arg1) throws java.lang.InterruptedException { throw new RuntimeException("Stub!"); }
protected  java.lang.Object clone() throws java.lang.CloneNotSupportedException { throw new RuntimeException("Stub!"); }
public synchronized  void start() { throw new RuntimeException("Stub!"); }
public  void run() { throw new RuntimeException("Stub!"); }
public final  void stop() { throw new RuntimeException("Stub!"); }
public final synchronized  void stop(java.lang.Throwable arg0) { throw new RuntimeException("Stub!"); }
public  void interrupt() { throw new RuntimeException("Stub!"); }
public static  boolean interrupted() { throw new RuntimeException("Stub!"); }
public  boolean isInterrupted() { throw new RuntimeException("Stub!"); }
public  void destroy() { throw new RuntimeException("Stub!"); }
public final native  boolean isAlive();
public final  void suspend() { throw new RuntimeException("Stub!"); }
public final  void resume() { throw new RuntimeException("Stub!"); }
public final  void setPriority(int arg0) { throw new RuntimeException("Stub!"); }
public final  int getPriority() { throw new RuntimeException("Stub!"); }
public final  void setName(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String getName() { throw new RuntimeException("Stub!"); }
public final  java.lang.ThreadGroup getThreadGroup() { throw new RuntimeException("Stub!"); }
public static  int activeCount() { throw new RuntimeException("Stub!"); }
public static  int enumerate(java.lang.Thread[] arg0) { throw new RuntimeException("Stub!"); }
public native  int countStackFrames();
public final synchronized  void join(long arg0) throws java.lang.InterruptedException { throw new RuntimeException("Stub!"); }
public final synchronized  void join(long arg0, int arg1) throws java.lang.InterruptedException { throw new RuntimeException("Stub!"); }
public final  void join() throws java.lang.InterruptedException { throw new RuntimeException("Stub!"); }
public static  void dumpStack() { throw new RuntimeException("Stub!"); }
public final  void setDaemon(boolean arg0) { throw new RuntimeException("Stub!"); }
public final  boolean isDaemon() { throw new RuntimeException("Stub!"); }
public final  void checkAccess() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  java.lang.ClassLoader getContextClassLoader() { throw new RuntimeException("Stub!"); }
public  void setContextClassLoader(java.lang.ClassLoader arg0) { throw new RuntimeException("Stub!"); }
public static native  boolean holdsLock(java.lang.Object arg0);
public  java.lang.StackTraceElement[] getStackTrace() { throw new RuntimeException("Stub!"); }
public static  java.util.Map<java.lang.Thread, java.lang.StackTraceElement[]> getAllStackTraces() { throw new RuntimeException("Stub!"); }
public  long getId() { throw new RuntimeException("Stub!"); }
public  java.lang.Thread.State getState() { throw new RuntimeException("Stub!"); }
public static  void setDefaultUncaughtExceptionHandler(java.lang.Thread.UncaughtExceptionHandler arg0) { throw new RuntimeException("Stub!"); }
public static  java.lang.Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() { throw new RuntimeException("Stub!"); }
public  java.lang.Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() { throw new RuntimeException("Stub!"); }
public  void setUncaughtExceptionHandler(java.lang.Thread.UncaughtExceptionHandler arg0) { throw new RuntimeException("Stub!"); }
public static final int MIN_PRIORITY = 1;
public static final int NORM_PRIORITY = 5;
public static final int MAX_PRIORITY = 10;
}
