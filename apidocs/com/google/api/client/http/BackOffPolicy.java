package com.google.api.client.http;
@java.lang.Deprecated()
@com.google.api.client.util.Beta()
public interface BackOffPolicy
{
public abstract  boolean isBackOffRequired(int arg0);
public abstract  void reset();
public abstract  long getNextBackOffMillis() throws java.io.IOException;
public static final long STOP = -1L;
}
