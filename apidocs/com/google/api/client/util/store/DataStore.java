package com.google.api.client.util.store;
@com.google.api.client.util.Beta()
public interface DataStore<V extends java.io.Serializable>
{
public abstract  com.google.api.client.util.store.DataStoreFactory getDataStoreFactory();
public abstract  java.lang.String getId();
public abstract  int size() throws java.io.IOException;
public abstract  boolean isEmpty() throws java.io.IOException;
public abstract  boolean containsKey(java.lang.String arg0) throws java.io.IOException;
public abstract  boolean containsValue(V arg0) throws java.io.IOException;
public abstract  java.util.Set<java.lang.String> keySet() throws java.io.IOException;
public abstract  java.util.Collection<V> values() throws java.io.IOException;
public abstract  V get(java.lang.String arg0) throws java.io.IOException;
public abstract  com.google.api.client.util.store.DataStore<V> set(java.lang.String arg0, V arg1) throws java.io.IOException;
public abstract  com.google.api.client.util.store.DataStore<V> clear() throws java.io.IOException;
public abstract  com.google.api.client.util.store.DataStore<V> delete(java.lang.String arg0) throws java.io.IOException;
}
