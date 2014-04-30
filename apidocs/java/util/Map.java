package java.util;
public interface Map<K, V>
{
public static interface Entry<K, V>
{
public abstract  K getKey();
public abstract  V getValue();
public abstract  V setValue(V arg0);
public abstract  boolean equals(java.lang.Object arg0);
public abstract  int hashCode();
}
public abstract  int size();
public abstract  boolean isEmpty();
public abstract  boolean containsKey(java.lang.Object arg0);
public abstract  boolean containsValue(java.lang.Object arg0);
public abstract  V get(java.lang.Object arg0);
public abstract  V put(K arg0, V arg1);
public abstract  V remove(java.lang.Object arg0);
public abstract  void putAll(java.util.Map<? extends K, ? extends V> arg0);
public abstract  void clear();
public abstract  java.util.Set<K> keySet();
public abstract  java.util.Collection<V> values();
public abstract  java.util.Set<java.util.Map.Entry<K, V>> entrySet();
public abstract  boolean equals(java.lang.Object arg0);
public abstract  int hashCode();
}
