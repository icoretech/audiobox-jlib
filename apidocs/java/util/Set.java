package java.util;
public interface Set<E>
  extends java.util.Collection<E>
{
public abstract  int size();
public abstract  boolean isEmpty();
public abstract  boolean contains(java.lang.Object arg0);
public abstract  java.util.Iterator<E> iterator();
public abstract  java.lang.Object[] toArray();
public abstract <T> T[] toArray(T[] arg0);
public abstract  boolean add(E arg0);
public abstract  boolean remove(java.lang.Object arg0);
public abstract  boolean containsAll(java.util.Collection<?> arg0);
public abstract  boolean addAll(java.util.Collection<? extends E> arg0);
public abstract  boolean retainAll(java.util.Collection<?> arg0);
public abstract  boolean removeAll(java.util.Collection<?> arg0);
public abstract  void clear();
public abstract  boolean equals(java.lang.Object arg0);
public abstract  int hashCode();
}
