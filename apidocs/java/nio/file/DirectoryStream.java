package java.nio.file;
public interface DirectoryStream<T>
  extends java.lang.Iterable<T>
{
public static interface Filter<T>
{
public abstract  boolean accept(T arg0) throws java.io.IOException;
}
public abstract  java.util.Iterator<T> iterator();
}
