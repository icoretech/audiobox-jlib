package java.nio.file;
public interface Path
  extends java.lang.Iterable<java.nio.file.Path>, java.nio.file.Watchable
{
public abstract  java.nio.file.FileSystem getFileSystem();
public abstract  boolean isAbsolute();
public abstract  java.nio.file.Path getRoot();
public abstract  java.nio.file.Path getFileName();
public abstract  java.nio.file.Path getParent();
public abstract  int getNameCount();
public abstract  java.nio.file.Path getName(int arg0);
public abstract  java.nio.file.Path subpath(int arg0, int arg1);
public abstract  boolean startsWith(java.nio.file.Path arg0);
public abstract  boolean startsWith(java.lang.String arg0);
public abstract  boolean endsWith(java.nio.file.Path arg0);
public abstract  boolean endsWith(java.lang.String arg0);
public abstract  java.nio.file.Path normalize();
public abstract  java.nio.file.Path resolve(java.nio.file.Path arg0);
public abstract  java.nio.file.Path resolve(java.lang.String arg0);
public abstract  java.nio.file.Path resolveSibling(java.nio.file.Path arg0);
public abstract  java.nio.file.Path resolveSibling(java.lang.String arg0);
public abstract  java.nio.file.Path relativize(java.nio.file.Path arg0);
public abstract  java.net.URI toUri();
public abstract  java.nio.file.Path toAbsolutePath();
public abstract  java.nio.file.Path toRealPath(java.nio.file.LinkOption... arg0) throws java.io.IOException;
public abstract  java.io.File toFile();
public abstract  java.nio.file.WatchKey register(java.nio.file.WatchService arg0, java.nio.file.WatchEvent.Kind<?>[] arg1, java.nio.file.WatchEvent.Modifier... arg2) throws java.io.IOException;
public abstract  java.nio.file.WatchKey register(java.nio.file.WatchService arg0, java.nio.file.WatchEvent.Kind<?>... arg1) throws java.io.IOException;
public abstract  java.util.Iterator<java.nio.file.Path> iterator();
public abstract  int compareTo(java.nio.file.Path arg0);
public abstract  boolean equals(java.lang.Object arg0);
public abstract  int hashCode();
public abstract  java.lang.String toString();
}
