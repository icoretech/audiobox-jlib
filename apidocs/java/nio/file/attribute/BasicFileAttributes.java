package java.nio.file.attribute;
public interface BasicFileAttributes
{
public abstract  java.nio.file.attribute.FileTime lastModifiedTime();
public abstract  java.nio.file.attribute.FileTime lastAccessTime();
public abstract  java.nio.file.attribute.FileTime creationTime();
public abstract  boolean isRegularFile();
public abstract  boolean isDirectory();
public abstract  boolean isSymbolicLink();
public abstract  boolean isOther();
public abstract  long size();
public abstract  java.lang.Object fileKey();
}
