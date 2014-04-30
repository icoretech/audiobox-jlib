package java.nio.file;
public interface WatchKey
{
public abstract  boolean isValid();
public abstract  java.util.List<java.nio.file.WatchEvent<?>> pollEvents();
public abstract  boolean reset();
public abstract  void cancel();
public abstract  java.nio.file.Watchable watchable();
}
