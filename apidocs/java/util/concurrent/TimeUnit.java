package java.util.concurrent;
public abstract enum TimeUnit
{
DAYS(),
HOURS(),
MICROSECONDS(),
MILLISECONDS(),
MINUTES(),
NANOSECONDS(),
SECONDS();
public  long convert(long arg0, java.util.concurrent.TimeUnit arg1) { throw new RuntimeException("Stub!"); }
public  long toNanos(long arg0) { throw new RuntimeException("Stub!"); }
public  long toMicros(long arg0) { throw new RuntimeException("Stub!"); }
public  long toMillis(long arg0) { throw new RuntimeException("Stub!"); }
public  long toSeconds(long arg0) { throw new RuntimeException("Stub!"); }
public  long toMinutes(long arg0) { throw new RuntimeException("Stub!"); }
public  long toHours(long arg0) { throw new RuntimeException("Stub!"); }
public  long toDays(long arg0) { throw new RuntimeException("Stub!"); }
public  void timedWait(java.lang.Object arg0, long arg1) throws java.lang.InterruptedException { throw new RuntimeException("Stub!"); }
public  void timedJoin(java.lang.Thread arg0, long arg1) throws java.lang.InterruptedException { throw new RuntimeException("Stub!"); }
public  void sleep(long arg0) throws java.lang.InterruptedException { throw new RuntimeException("Stub!"); }
}
