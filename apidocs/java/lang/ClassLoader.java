package java.lang;
public abstract class ClassLoader
{
protected  ClassLoader(java.lang.ClassLoader arg0) { throw new RuntimeException("Stub!"); }
protected  ClassLoader() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<?> loadClass(java.lang.String arg0) throws java.lang.ClassNotFoundException { throw new RuntimeException("Stub!"); }
protected  java.lang.Class<?> loadClass(java.lang.String arg0, boolean arg1) throws java.lang.ClassNotFoundException { throw new RuntimeException("Stub!"); }
protected  java.lang.Object getClassLoadingLock(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
protected  java.lang.Class<?> findClass(java.lang.String arg0) throws java.lang.ClassNotFoundException { throw new RuntimeException("Stub!"); }
protected final  java.lang.Class<?> defineClass(byte[] arg0, int arg1, int arg2) throws java.lang.ClassFormatError { throw new RuntimeException("Stub!"); }
protected final  java.lang.Class<?> defineClass(java.lang.String arg0, byte[] arg1, int arg2, int arg3) throws java.lang.ClassFormatError { throw new RuntimeException("Stub!"); }
protected final  java.lang.Class<?> defineClass(java.lang.String arg0, byte[] arg1, int arg2, int arg3, java.security.ProtectionDomain arg4) throws java.lang.ClassFormatError { throw new RuntimeException("Stub!"); }
protected final  java.lang.Class<?> defineClass(java.lang.String arg0, java.nio.ByteBuffer arg1, java.security.ProtectionDomain arg2) throws java.lang.ClassFormatError { throw new RuntimeException("Stub!"); }
protected final  void resolveClass(java.lang.Class<?> arg0) { throw new RuntimeException("Stub!"); }
protected final  java.lang.Class<?> findSystemClass(java.lang.String arg0) throws java.lang.ClassNotFoundException { throw new RuntimeException("Stub!"); }
protected final  java.lang.Class<?> findLoadedClass(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
protected final  void setSigners(java.lang.Class<?> arg0, java.lang.Object[] arg1) { throw new RuntimeException("Stub!"); }
public  java.net.URL getResource(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Enumeration<java.net.URL> getResources(java.lang.String arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
protected  java.net.URL findResource(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
protected  java.util.Enumeration<java.net.URL> findResources(java.lang.String arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
protected static  boolean registerAsParallelCapable() { throw new RuntimeException("Stub!"); }
public static  java.net.URL getSystemResource(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static  java.util.Enumeration<java.net.URL> getSystemResources(java.lang.String arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.InputStream getResourceAsStream(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static  java.io.InputStream getSystemResourceAsStream(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.ClassLoader getParent() { throw new RuntimeException("Stub!"); }
public static  java.lang.ClassLoader getSystemClassLoader() { throw new RuntimeException("Stub!"); }
protected  java.lang.Package definePackage(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6, java.net.URL arg7) throws java.lang.IllegalArgumentException { throw new RuntimeException("Stub!"); }
protected  java.lang.Package getPackage(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
protected  java.lang.Package[] getPackages() { throw new RuntimeException("Stub!"); }
protected  java.lang.String findLibrary(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  void setDefaultAssertionStatus(boolean arg0) { throw new RuntimeException("Stub!"); }
public  void setPackageAssertionStatus(java.lang.String arg0, boolean arg1) { throw new RuntimeException("Stub!"); }
public  void setClassAssertionStatus(java.lang.String arg0, boolean arg1) { throw new RuntimeException("Stub!"); }
public  void clearAssertionStatus() { throw new RuntimeException("Stub!"); }
}
