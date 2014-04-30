package java.nio.file;
public abstract class FileStore
{
protected  FileStore() { throw new RuntimeException("Stub!"); }
public abstract  java.lang.String name();
public abstract  java.lang.String type();
public abstract  boolean isReadOnly();
public abstract  long getTotalSpace() throws java.io.IOException;
public abstract  long getUsableSpace() throws java.io.IOException;
public abstract  long getUnallocatedSpace() throws java.io.IOException;
public abstract  boolean supportsFileAttributeView(java.lang.Class<? extends java.nio.file.attribute.FileAttributeView> arg0);
public abstract  boolean supportsFileAttributeView(java.lang.String arg0);
public abstract <V extends java.nio.file.attribute.FileStoreAttributeView> V getFileStoreAttributeView(java.lang.Class<V> arg0);
public abstract  java.lang.Object getAttribute(java.lang.String arg0) throws java.io.IOException;
}
