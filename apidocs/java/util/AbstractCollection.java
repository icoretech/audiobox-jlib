package java.util;
public abstract class AbstractCollection<E>
  implements java.util.Collection<E>
{
protected  AbstractCollection() { throw new RuntimeException("Stub!"); }
public abstract  java.util.Iterator<E> iterator();
public abstract  int size();
public  boolean isEmpty() { throw new RuntimeException("Stub!"); }
public  boolean contains(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.Object[] toArray() { throw new RuntimeException("Stub!"); }
public <T> T[] toArray(T[] arg0) { throw new RuntimeException("Stub!"); }
public  boolean add(E arg0) { throw new RuntimeException("Stub!"); }
public  boolean remove(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  boolean containsAll(java.util.Collection<?> arg0) { throw new RuntimeException("Stub!"); }
public  boolean addAll(java.util.Collection<? extends E> arg0) { throw new RuntimeException("Stub!"); }
public  boolean removeAll(java.util.Collection<?> arg0) { throw new RuntimeException("Stub!"); }
public  boolean retainAll(java.util.Collection<?> arg0) { throw new RuntimeException("Stub!"); }
public  void clear() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
}
