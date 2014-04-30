package java.security;
public abstract class Permission
  implements java.io.Serializable
{
public  Permission(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  void checkGuard(java.lang.Object arg0) throws java.lang.SecurityException { throw new RuntimeException("Stub!"); }
public abstract  boolean implies(java.security.Permission arg0);
public abstract  boolean equals(java.lang.Object arg0);
public abstract  int hashCode();
public final  java.lang.String getName() { throw new RuntimeException("Stub!"); }
public abstract  java.lang.String getActions();
public  java.security.PermissionCollection newPermissionCollection() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
}
