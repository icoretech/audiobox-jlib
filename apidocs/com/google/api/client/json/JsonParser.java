package com.google.api.client.json;
public abstract class JsonParser
{
public  JsonParser() { throw new RuntimeException("Stub!"); }
public abstract  com.google.api.client.json.JsonFactory getFactory();
public abstract  void close() throws java.io.IOException;
public abstract  com.google.api.client.json.JsonToken nextToken() throws java.io.IOException;
public abstract  com.google.api.client.json.JsonToken getCurrentToken();
public abstract  java.lang.String getCurrentName() throws java.io.IOException;
public abstract  com.google.api.client.json.JsonParser skipChildren() throws java.io.IOException;
public abstract  java.lang.String getText() throws java.io.IOException;
public abstract  byte getByteValue() throws java.io.IOException;
public abstract  short getShortValue() throws java.io.IOException;
public abstract  int getIntValue() throws java.io.IOException;
public abstract  float getFloatValue() throws java.io.IOException;
public abstract  long getLongValue() throws java.io.IOException;
public abstract  double getDoubleValue() throws java.io.IOException;
public abstract  java.math.BigInteger getBigIntegerValue() throws java.io.IOException;
public abstract  java.math.BigDecimal getDecimalValue() throws java.io.IOException;
public final <T> T parseAndClose(java.lang.Class<T> arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> T parseAndClose(java.lang.Class<T> arg0, com.google.api.client.json.CustomizeJsonParser arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  void skipToKey(java.lang.String arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  java.lang.String skipToKey(java.util.Set<java.lang.String> arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  void parseAndClose(java.lang.Object arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  void parseAndClose(java.lang.Object arg0, com.google.api.client.json.CustomizeJsonParser arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> T parse(java.lang.Class<T> arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> T parse(java.lang.Class<T> arg0, com.google.api.client.json.CustomizeJsonParser arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.Object parse(java.lang.reflect.Type arg0, boolean arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.Object parse(java.lang.reflect.Type arg0, boolean arg1, com.google.api.client.json.CustomizeJsonParser arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  void parse(java.lang.Object arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  void parse(java.lang.Object arg0, com.google.api.client.json.CustomizeJsonParser arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> java.util.Collection<T> parseArrayAndClose(java.lang.Class<?> arg0, java.lang.Class<T> arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> java.util.Collection<T> parseArrayAndClose(java.lang.Class<?> arg0, java.lang.Class<T> arg1, com.google.api.client.json.CustomizeJsonParser arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> void parseArrayAndClose(java.util.Collection<? super T> arg0, java.lang.Class<T> arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> void parseArrayAndClose(java.util.Collection<? super T> arg0, java.lang.Class<T> arg1, com.google.api.client.json.CustomizeJsonParser arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> java.util.Collection<T> parseArray(java.lang.Class<?> arg0, java.lang.Class<T> arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> java.util.Collection<T> parseArray(java.lang.Class<?> arg0, java.lang.Class<T> arg1, com.google.api.client.json.CustomizeJsonParser arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> void parseArray(java.util.Collection<? super T> arg0, java.lang.Class<T> arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> void parseArray(java.util.Collection<? super T> arg0, java.lang.Class<T> arg1, com.google.api.client.json.CustomizeJsonParser arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
}
