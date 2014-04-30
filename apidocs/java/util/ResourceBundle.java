package java.util;
public abstract class ResourceBundle
{
public static class Control
{
protected  Control() { throw new RuntimeException("Stub!"); }
public static final  java.util.ResourceBundle.Control getControl(java.util.List<java.lang.String> arg0) { throw new RuntimeException("Stub!"); }
public static final  java.util.ResourceBundle.Control getNoFallbackControl(java.util.List<java.lang.String> arg0) { throw new RuntimeException("Stub!"); }
public  java.util.List<java.lang.String> getFormats(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.util.List<java.util.Locale> getCandidateLocales(java.lang.String arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public  java.util.Locale getFallbackLocale(java.lang.String arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public  java.util.ResourceBundle newBundle(java.lang.String arg0, java.util.Locale arg1, java.lang.String arg2, java.lang.ClassLoader arg3, boolean arg4) throws java.lang.IllegalAccessException, java.lang.InstantiationException, java.io.IOException { throw new RuntimeException("Stub!"); }
public  long getTimeToLive(java.lang.String arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public  boolean needsReload(java.lang.String arg0, java.util.Locale arg1, java.lang.String arg2, java.lang.ClassLoader arg3, java.util.ResourceBundle arg4, long arg5) { throw new RuntimeException("Stub!"); }
public  java.lang.String toBundleName(java.lang.String arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public final  java.lang.String toResourceName(java.lang.String arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public static final java.util.List<java.lang.String> FORMAT_DEFAULT;
public static final java.util.List<java.lang.String> FORMAT_CLASS;
public static final java.util.List<java.lang.String> FORMAT_PROPERTIES;
public static final long TTL_DONT_CACHE = -1L;
public static final long TTL_NO_EXPIRATION_CONTROL = -2L;
static { FORMAT_DEFAULT = null; FORMAT_CLASS = null; FORMAT_PROPERTIES = null; }
}
public  ResourceBundle() { throw new RuntimeException("Stub!"); }
public final  java.lang.String getString(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String[] getStringArray(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.Object getObject(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Locale getLocale() { throw new RuntimeException("Stub!"); }
protected  void setParent(java.util.ResourceBundle arg0) { throw new RuntimeException("Stub!"); }
public static final  java.util.ResourceBundle getBundle(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static final  java.util.ResourceBundle getBundle(java.lang.String arg0, java.util.ResourceBundle.Control arg1) { throw new RuntimeException("Stub!"); }
public static final  java.util.ResourceBundle getBundle(java.lang.String arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public static final  java.util.ResourceBundle getBundle(java.lang.String arg0, java.util.Locale arg1, java.util.ResourceBundle.Control arg2) { throw new RuntimeException("Stub!"); }
public static  java.util.ResourceBundle getBundle(java.lang.String arg0, java.util.Locale arg1, java.lang.ClassLoader arg2) { throw new RuntimeException("Stub!"); }
public static  java.util.ResourceBundle getBundle(java.lang.String arg0, java.util.Locale arg1, java.lang.ClassLoader arg2, java.util.ResourceBundle.Control arg3) { throw new RuntimeException("Stub!"); }
public static final  void clearCache() { throw new RuntimeException("Stub!"); }
public static final  void clearCache(java.lang.ClassLoader arg0) { throw new RuntimeException("Stub!"); }
protected abstract  java.lang.Object handleGetObject(java.lang.String arg0);
public abstract  java.util.Enumeration<java.lang.String> getKeys();
public  boolean containsKey(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Set<java.lang.String> keySet() { throw new RuntimeException("Stub!"); }
protected  java.util.Set<java.lang.String> handleKeySet() { throw new RuntimeException("Stub!"); }
protected java.util.ResourceBundle parent;
}
