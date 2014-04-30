package java.lang;
public final class Class<T>
  implements java.io.Serializable, java.lang.reflect.GenericDeclaration, java.lang.reflect.Type
{
Class() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public static  java.lang.Class<?> forName(java.lang.String arg0) throws java.lang.ClassNotFoundException { throw new RuntimeException("Stub!"); }
public static  java.lang.Class<?> forName(java.lang.String arg0, boolean arg1, java.lang.ClassLoader arg2) throws java.lang.ClassNotFoundException { throw new RuntimeException("Stub!"); }
public  T newInstance() throws java.lang.InstantiationException, java.lang.IllegalAccessException { throw new RuntimeException("Stub!"); }
public native  boolean isInstance(java.lang.Object arg0);
public native  boolean isAssignableFrom(java.lang.Class<?> arg0);
public native  boolean isInterface();
public native  boolean isArray();
public native  boolean isPrimitive();
public  boolean isAnnotation() { throw new RuntimeException("Stub!"); }
public  boolean isSynthetic() { throw new RuntimeException("Stub!"); }
public  java.lang.String getName() { throw new RuntimeException("Stub!"); }
public  java.lang.ClassLoader getClassLoader() { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.TypeVariable<java.lang.Class<T>>[] getTypeParameters() { throw new RuntimeException("Stub!"); }
public native  java.lang.Class<? super T> getSuperclass();
public  java.lang.reflect.Type getGenericSuperclass() { throw new RuntimeException("Stub!"); }
public  java.lang.Package getPackage() { throw new RuntimeException("Stub!"); }
public native  java.lang.Class<?>[] getInterfaces();
public  java.lang.reflect.Type[] getGenericInterfaces() { throw new RuntimeException("Stub!"); }
public native  java.lang.Class<?> getComponentType();
public native  int getModifiers();
public native  java.lang.Object[] getSigners();
public  java.lang.reflect.Method getEnclosingMethod() { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Constructor<?> getEnclosingConstructor() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<?> getDeclaringClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<?> getEnclosingClass() { throw new RuntimeException("Stub!"); }
public  java.lang.String getSimpleName() { throw new RuntimeException("Stub!"); }
public  java.lang.String getCanonicalName() { throw new RuntimeException("Stub!"); }
public  boolean isAnonymousClass() { throw new RuntimeException("Stub!"); }
public  boolean isLocalClass() { throw new RuntimeException("Stub!"); }
public  boolean isMemberClass() { throw new RuntimeException("Stub!"); }
public  java.lang.Class<?>[] getClasses() { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Field[] getFields() throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Method[] getMethods() throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Constructor<?>[] getConstructors() throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Field getField(java.lang.String arg0) throws java.lang.NoSuchFieldException, java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Method getMethod(java.lang.String arg0, java.lang.Class<?>... arg1) throws java.lang.NoSuchMethodException, java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Constructor<T> getConstructor(java.lang.Class<?>... arg0) throws java.lang.NoSuchMethodException, java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.Class<?>[] getDeclaredClasses() throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Field[] getDeclaredFields() throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Method[] getDeclaredMethods() throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Constructor<?>[] getDeclaredConstructors() throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Field getDeclaredField(java.lang.String arg0) throws java.lang.NoSuchFieldException, java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Method getDeclaredMethod(java.lang.String arg0, java.lang.Class<?>... arg1) throws java.lang.NoSuchMethodException, java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.lang.reflect.Constructor<T> getDeclaredConstructor(java.lang.Class<?>... arg0) throws java.lang.NoSuchMethodException, java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public  java.io.InputStream getResourceAsStream(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.net.URL getResource(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.security.ProtectionDomain getProtectionDomain() { throw new RuntimeException("Stub!"); }
public  boolean desiredAssertionStatus() { throw new RuntimeException("Stub!"); }
public  boolean isEnum() { throw new RuntimeException("Stub!"); }
public  T[] getEnumConstants() { throw new RuntimeException("Stub!"); }
public  T cast(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public <U> java.lang.Class<? extends U> asSubclass(java.lang.Class<U> arg0) { throw new RuntimeException("Stub!"); }
public <A extends java.lang.annotation.Annotation> A getAnnotation(java.lang.Class<A> arg0) { throw new RuntimeException("Stub!"); }
public  boolean isAnnotationPresent(java.lang.Class<? extends java.lang.annotation.Annotation> arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.annotation.Annotation[] getAnnotations() { throw new RuntimeException("Stub!"); }
public  java.lang.annotation.Annotation[] getDeclaredAnnotations() { throw new RuntimeException("Stub!"); }
}
