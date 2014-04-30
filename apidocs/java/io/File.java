package java.io;
public class File
  implements java.io.Serializable
{
public  File(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  File(java.lang.String arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public  File(java.io.File arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public  File(java.net.URI arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String getName() { throw new RuntimeException("Stub!"); }
public  java.lang.String getParent() { throw new RuntimeException("Stub!"); }
public  java.io.File getParentFile() { throw new RuntimeException("Stub!"); }
public  java.lang.String getPath() { throw new RuntimeException("Stub!"); }
public  boolean isAbsolute() { throw new RuntimeException("Stub!"); }
public  java.lang.String getAbsolutePath() { throw new RuntimeException("Stub!"); }
public  java.io.File getAbsoluteFile() { throw new RuntimeException("Stub!"); }
public  java.lang.String getCanonicalPath() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.File getCanonicalFile() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.net.URL toURL() throws java.net.MalformedURLException { throw new RuntimeException("Stub!"); }
public  java.net.URI toURI() { throw new RuntimeException("Stub!"); }
public  boolean canRead() { throw new RuntimeException("Stub!"); }
public  boolean canWrite() { throw new RuntimeException("Stub!"); }
public  boolean exists() { throw new RuntimeException("Stub!"); }
public  boolean isDirectory() { throw new RuntimeException("Stub!"); }
public  boolean isFile() { throw new RuntimeException("Stub!"); }
public  boolean isHidden() { throw new RuntimeException("Stub!"); }
public  long lastModified() { throw new RuntimeException("Stub!"); }
public  long length() { throw new RuntimeException("Stub!"); }
public  boolean createNewFile() throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean delete() { throw new RuntimeException("Stub!"); }
public  void deleteOnExit() { throw new RuntimeException("Stub!"); }
public  java.lang.String[] list() { throw new RuntimeException("Stub!"); }
public  java.lang.String[] list(java.io.FilenameFilter arg0) { throw new RuntimeException("Stub!"); }
public  java.io.File[] listFiles() { throw new RuntimeException("Stub!"); }
public  java.io.File[] listFiles(java.io.FilenameFilter arg0) { throw new RuntimeException("Stub!"); }
public  java.io.File[] listFiles(java.io.FileFilter arg0) { throw new RuntimeException("Stub!"); }
public  boolean mkdir() { throw new RuntimeException("Stub!"); }
public  boolean mkdirs() { throw new RuntimeException("Stub!"); }
public  boolean renameTo(java.io.File arg0) { throw new RuntimeException("Stub!"); }
public  boolean setLastModified(long arg0) { throw new RuntimeException("Stub!"); }
public  boolean setReadOnly() { throw new RuntimeException("Stub!"); }
public  boolean setWritable(boolean arg0, boolean arg1) { throw new RuntimeException("Stub!"); }
public  boolean setWritable(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean setReadable(boolean arg0, boolean arg1) { throw new RuntimeException("Stub!"); }
public  boolean setReadable(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean setExecutable(boolean arg0, boolean arg1) { throw new RuntimeException("Stub!"); }
public  boolean setExecutable(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean canExecute() { throw new RuntimeException("Stub!"); }
public static  java.io.File[] listRoots() { throw new RuntimeException("Stub!"); }
public  long getTotalSpace() { throw new RuntimeException("Stub!"); }
public  long getFreeSpace() { throw new RuntimeException("Stub!"); }
public  long getUsableSpace() { throw new RuntimeException("Stub!"); }
public static  java.io.File createTempFile(java.lang.String arg0, java.lang.String arg1, java.io.File arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public static  java.io.File createTempFile(java.lang.String arg0, java.lang.String arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  int compareTo(java.io.File arg0) { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  java.nio.file.Path toPath() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public static final char separatorChar;
public static final java.lang.String separator;
public static final char pathSeparatorChar;
public static final java.lang.String pathSeparator;
static { separatorChar = 0; separator = null; pathSeparatorChar = 0; pathSeparator = null; }
}
