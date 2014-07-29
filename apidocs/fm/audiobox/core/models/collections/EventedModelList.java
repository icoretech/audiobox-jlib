package fm.audiobox.core.models.collections;
public class EventedModelList<T>
  extends java.util.LinkedList<T>
{
public class Event
{
public  Event(int what, T source, fm.audiobox.core.models.collections.EventedModelList target) { throw new RuntimeException("Stub!"); }
public static final int ADD = 1;
public static final int REMOVE = -1;
public static final int CLEAR = 0;
public int what;
public T source;
public fm.audiobox.core.models.collections.EventedModelList target;
}
public  EventedModelList(fm.audiobox.core.models.Model parent) { throw new RuntimeException("Stub!"); }
public  boolean add(T t) { throw new RuntimeException("Stub!"); }
public  void add(int index, T element) { throw new RuntimeException("Stub!"); }
public  void addFirst(T t) { throw new RuntimeException("Stub!"); }
public  void addLast(T t) { throw new RuntimeException("Stub!"); }
public  boolean remove(java.lang.Object o) { throw new RuntimeException("Stub!"); }
public  T remove(int index) { throw new RuntimeException("Stub!"); }
public  T remove() { throw new RuntimeException("Stub!"); }
public  T removeFirst() { throw new RuntimeException("Stub!"); }
public  T removeLast() { throw new RuntimeException("Stub!"); }
public  void clear() { throw new RuntimeException("Stub!"); }
}
