package java.util.concurrent;
public interface ExecutorService
  extends java.util.concurrent.Executor
{
public abstract  void shutdown();
public abstract  java.util.List<java.lang.Runnable> shutdownNow();
public abstract  boolean isShutdown();
public abstract  boolean isTerminated();
public abstract  boolean awaitTermination(long arg0, java.util.concurrent.TimeUnit arg1) throws java.lang.InterruptedException;
public abstract <T> java.util.concurrent.Future<T> submit(java.util.concurrent.Callable<T> arg0);
public abstract <T> java.util.concurrent.Future<T> submit(java.lang.Runnable arg0, T arg1);
public abstract  java.util.concurrent.Future<?> submit(java.lang.Runnable arg0);
public abstract <T> java.util.List<java.util.concurrent.Future<T>> invokeAll(java.util.Collection<? extends java.util.concurrent.Callable<T>> arg0) throws java.lang.InterruptedException;
public abstract <T> java.util.List<java.util.concurrent.Future<T>> invokeAll(java.util.Collection<? extends java.util.concurrent.Callable<T>> arg0, long arg1, java.util.concurrent.TimeUnit arg2) throws java.lang.InterruptedException;
public abstract <T> T invokeAny(java.util.Collection<? extends java.util.concurrent.Callable<T>> arg0) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException;
public abstract <T> T invokeAny(java.util.Collection<? extends java.util.concurrent.Callable<T>> arg0, long arg1, java.util.concurrent.TimeUnit arg2) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException;
}
