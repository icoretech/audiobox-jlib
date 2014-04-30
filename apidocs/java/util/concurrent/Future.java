package java.util.concurrent;
public interface Future<V>
{
public abstract  boolean cancel(boolean arg0);
public abstract  boolean isCancelled();
public abstract  boolean isDone();
public abstract  V get() throws java.lang.InterruptedException, java.util.concurrent.ExecutionException;
public abstract  V get(long arg0, java.util.concurrent.TimeUnit arg1) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException;
}
