package java.nio.file;
public interface WatchEvent<T>
{
public static interface Kind<T>
{
public abstract  java.lang.String name();
public abstract  java.lang.Class<T> type();
}
public static interface Modifier
{
public abstract  java.lang.String name();
}
public abstract  java.nio.file.WatchEvent.Kind<T> kind();
public abstract  int count();
public abstract  T context();
}
