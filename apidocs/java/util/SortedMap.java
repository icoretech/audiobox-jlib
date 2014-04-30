package java.util;
public interface SortedMap<K, V>
  extends java.util.Map<K, V>
{
public abstract  java.util.Comparator<? super K> comparator();
public abstract  java.util.SortedMap<K, V> subMap(K arg0, K arg1);
public abstract  java.util.SortedMap<K, V> headMap(K arg0);
public abstract  java.util.SortedMap<K, V> tailMap(K arg0);
public abstract  K firstKey();
public abstract  K lastKey();
public abstract  java.util.Set<K> keySet();
public abstract  java.util.Collection<V> values();
public abstract  java.util.Set<java.util.Map.Entry<K, V>> entrySet();
}
