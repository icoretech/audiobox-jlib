package java.nio.file.spi;
public abstract class FileSystemProvider
{
protected  FileSystemProvider() { throw new RuntimeException("Stub!"); }
public static  java.util.List<java.nio.file.spi.FileSystemProvider> installedProviders() { throw new RuntimeException("Stub!"); }
public abstract  java.lang.String getScheme();
public abstract  java.nio.file.FileSystem newFileSystem(java.net.URI arg0, java.util.Map<java.lang.String, ?> arg1) throws java.io.IOException;
public abstract  java.nio.file.FileSystem getFileSystem(java.net.URI arg0);
public abstract  java.nio.file.Path getPath(java.net.URI arg0);
public  java.nio.file.FileSystem newFileSystem(java.nio.file.Path arg0, java.util.Map<java.lang.String, ?> arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.InputStream newInputStream(java.nio.file.Path arg0, java.nio.file.OpenOption... arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.OutputStream newOutputStream(java.nio.file.Path arg0, java.nio.file.OpenOption... arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.nio.channels.FileChannel newFileChannel(java.nio.file.Path arg0, java.util.Set<? extends java.nio.file.OpenOption> arg1, java.nio.file.attribute.FileAttribute<?>... arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.nio.channels.AsynchronousFileChannel newAsynchronousFileChannel(java.nio.file.Path arg0, java.util.Set<? extends java.nio.file.OpenOption> arg1, java.util.concurrent.ExecutorService arg2, java.nio.file.attribute.FileAttribute<?>... arg3) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  java.nio.channels.SeekableByteChannel newByteChannel(java.nio.file.Path arg0, java.util.Set<? extends java.nio.file.OpenOption> arg1, java.nio.file.attribute.FileAttribute<?>... arg2) throws java.io.IOException;
public abstract  java.nio.file.DirectoryStream<java.nio.file.Path> newDirectoryStream(java.nio.file.Path arg0, java.nio.file.DirectoryStream.Filter<? super java.nio.file.Path> arg1) throws java.io.IOException;
public abstract  void createDirectory(java.nio.file.Path arg0, java.nio.file.attribute.FileAttribute<?>... arg1) throws java.io.IOException;
public  void createSymbolicLink(java.nio.file.Path arg0, java.nio.file.Path arg1, java.nio.file.attribute.FileAttribute<?>... arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  void createLink(java.nio.file.Path arg0, java.nio.file.Path arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  void delete(java.nio.file.Path arg0) throws java.io.IOException;
public  boolean deleteIfExists(java.nio.file.Path arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.nio.file.Path readSymbolicLink(java.nio.file.Path arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public abstract  void copy(java.nio.file.Path arg0, java.nio.file.Path arg1, java.nio.file.CopyOption... arg2) throws java.io.IOException;
public abstract  void move(java.nio.file.Path arg0, java.nio.file.Path arg1, java.nio.file.CopyOption... arg2) throws java.io.IOException;
public abstract  boolean isSameFile(java.nio.file.Path arg0, java.nio.file.Path arg1) throws java.io.IOException;
public abstract  boolean isHidden(java.nio.file.Path arg0) throws java.io.IOException;
public abstract  java.nio.file.FileStore getFileStore(java.nio.file.Path arg0) throws java.io.IOException;
public abstract  void checkAccess(java.nio.file.Path arg0, java.nio.file.AccessMode... arg1) throws java.io.IOException;
public abstract <V extends java.nio.file.attribute.FileAttributeView> V getFileAttributeView(java.nio.file.Path arg0, java.lang.Class<V> arg1, java.nio.file.LinkOption... arg2);
public abstract <A extends java.nio.file.attribute.BasicFileAttributes> A readAttributes(java.nio.file.Path arg0, java.lang.Class<A> arg1, java.nio.file.LinkOption... arg2) throws java.io.IOException;
public abstract  java.util.Map<java.lang.String, java.lang.Object> readAttributes(java.nio.file.Path arg0, java.lang.String arg1, java.nio.file.LinkOption... arg2) throws java.io.IOException;
public abstract  void setAttribute(java.nio.file.Path arg0, java.lang.String arg1, java.lang.Object arg2, java.nio.file.LinkOption... arg3) throws java.io.IOException;
}
