package java.nio.file;
public interface Watchable
{
public abstract  java.nio.file.WatchKey register(java.nio.file.WatchService arg0, java.nio.file.WatchEvent.Kind<?>[] arg1, java.nio.file.WatchEvent.Modifier... arg2) throws java.io.IOException;
public abstract  java.nio.file.WatchKey register(java.nio.file.WatchService arg0, java.nio.file.WatchEvent.Kind<?>... arg1) throws java.io.IOException;
}
