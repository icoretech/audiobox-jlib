package org.slf4j;
public interface Marker
  extends java.io.Serializable
{
public abstract  java.lang.String getName();
public abstract  void add(org.slf4j.Marker arg0);
public abstract  boolean remove(org.slf4j.Marker arg0);
public abstract  boolean hasChildren();
public abstract  boolean hasReferences();
public abstract  java.util.Iterator<org.slf4j.Marker> iterator();
public abstract  boolean contains(org.slf4j.Marker arg0);
public abstract  boolean contains(java.lang.String arg0);
public abstract  boolean equals(java.lang.Object arg0);
public abstract  int hashCode();
public static final java.lang.String ANY_MARKER = "*";
public static final java.lang.String ANY_NON_NULL_MARKER = "+";
}
