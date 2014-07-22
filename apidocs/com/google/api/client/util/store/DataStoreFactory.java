package com.google.api.client.util.store;
public interface DataStoreFactory
{
public abstract <V extends java.io.Serializable> com.google.api.client.util.store.DataStore<V> getDataStore(java.lang.String arg0) throws java.io.IOException;
}
