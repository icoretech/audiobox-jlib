package java.util;
public class Observable
{
public  Observable() { throw new RuntimeException("Stub!"); }
public synchronized  void addObserver(java.util.Observer arg0) { throw new RuntimeException("Stub!"); }
public synchronized  void deleteObserver(java.util.Observer arg0) { throw new RuntimeException("Stub!"); }
public  void notifyObservers() { throw new RuntimeException("Stub!"); }
public  void notifyObservers(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public synchronized  void deleteObservers() { throw new RuntimeException("Stub!"); }
protected synchronized  void setChanged() { throw new RuntimeException("Stub!"); }
protected synchronized  void clearChanged() { throw new RuntimeException("Stub!"); }
public synchronized  boolean hasChanged() { throw new RuntimeException("Stub!"); }
public synchronized  int countObservers() { throw new RuntimeException("Stub!"); }
}
