package java.nio.file;
public interface WatchService
{
public abstract  void close() throws java.io.IOException;
public abstract  java.nio.file.WatchKey poll();
public abstract  java.nio.file.WatchKey poll(long arg0, java.util.concurrent.TimeUnit arg1) throws java.lang.InterruptedException;
public abstract  java.nio.file.WatchKey take() throws java.lang.InterruptedException;
}
