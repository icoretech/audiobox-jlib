package java.util;
public interface List<E>
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
public abstract  boolean addAll(int arg0, java.util.Collection<? extends E> arg1);
public abstract  boolean removeAll(java.util.Collection<?> arg0);
public abstract  boolean retainAll(java.util.Collection<?> arg0);
public abstract  void clear();
public abstract  boolean equals(java.lang.Object arg0);
public abstract  int hashCode();
public abstract  E get(int arg0);
public abstract  E set(int arg0, E arg1);
public abstract  void add(int arg0, E arg1);
public abstract  E remove(int arg0);
public abstract  int indexOf(java.lang.Object arg0);
public abstract  int lastIndexOf(java.lang.Object arg0);
public abstract  java.util.ListIterator<E> listIterator();
public abstract  java.util.ListIterator<E> listIterator(int arg0);
public abstract  java.util.List<E> subList(int arg0, int arg1);
}
