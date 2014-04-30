package java.nio.file;
public abstract class FileSystem
{
protected  FileSystem() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.file.spi.FileSystemProvider provider();
public abstract  void close() throws java.io.IOException;
public abstract  boolean isOpen();
public abstract  boolean isReadOnly();
public abstract  java.lang.String getSeparator();
public abstract  java.lang.Iterable<java.nio.file.Path> getRootDirectories();
public abstract  java.lang.Iterable<java.nio.file.FileStore> getFileStores();
public abstract  java.util.Set<java.lang.String> supportedFileAttributeViews();
public abstract  java.nio.file.Path getPath(java.lang.String arg0, java.lang.String... arg1);
public abstract  java.nio.file.PathMatcher getPathMatcher(java.lang.String arg0);
public abstract  java.nio.file.attribute.UserPrincipalLookupService getUserPrincipalLookupService();
public abstract  java.nio.file.WatchService newWatchService() throws java.io.IOException;
}
