package java.util;
public interface ListIterator<E>
  extends java.util.Iterator<E>
{
public abstract  boolean hasNext();
public abstract  E next();
public abstract  boolean hasPrevious();
public abstract  E previous();
public abstract  int nextIndex();
public abstract  int previousIndex();
public abstract  void remove();
public abstract  void set(E arg0);
public abstract  void add(E arg0);
}
