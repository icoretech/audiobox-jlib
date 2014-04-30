package javax.naming;
public interface Name
  extends java.io.Serializable
{
public abstract  java.lang.Object clone();
public abstract  int compareTo(java.lang.Object arg0);
public abstract  int size();
public abstract  boolean isEmpty();
public abstract  java.util.Enumeration<java.lang.String> getAll();
public abstract  java.lang.String get(int arg0);
public abstract  javax.naming.Name getPrefix(int arg0);
public abstract  javax.naming.Name getSuffix(int arg0);
public abstract  boolean startsWith(javax.naming.Name arg0);
public abstract  boolean endsWith(javax.naming.Name arg0);
public abstract  javax.naming.Name addAll(javax.naming.Name arg0) throws javax.naming.InvalidNameException;
public abstract  javax.naming.Name addAll(int arg0, javax.naming.Name arg1) throws javax.naming.InvalidNameException;
public abstract  javax.naming.Name add(java.lang.String arg0) throws javax.naming.InvalidNameException;
public abstract  javax.naming.Name add(int arg0, java.lang.String arg1) throws javax.naming.InvalidNameException;
public abstract  java.lang.Object remove(int arg0) throws javax.naming.InvalidNameException;
public static final long serialVersionUID = -3617482732056931635L;
}
