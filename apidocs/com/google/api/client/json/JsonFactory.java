package com.google.api.client.json;
public abstract class JsonFactory
{
public  JsonFactory() { throw new RuntimeException("Stub!"); }
public abstract  com.google.api.client.json.JsonParser createJsonParser(java.io.InputStream arg0) throws java.io.IOException;
public abstract  com.google.api.client.json.JsonParser createJsonParser(java.io.InputStream arg0, java.nio.charset.Charset arg1) throws java.io.IOException;
public abstract  com.google.api.client.json.JsonParser createJsonParser(java.lang.String arg0) throws java.io.IOException;
public abstract  com.google.api.client.json.JsonParser createJsonParser(java.io.Reader arg0) throws java.io.IOException;
public abstract  com.google.api.client.json.JsonGenerator createJsonGenerator(java.io.OutputStream arg0, java.nio.charset.Charset arg1) throws java.io.IOException;
public abstract  com.google.api.client.json.JsonGenerator createJsonGenerator(java.io.Writer arg0) throws java.io.IOException;
public final  com.google.api.client.json.JsonObjectParser createJsonObjectParser() { throw new RuntimeException("Stub!"); }
public final  java.lang.String toString(java.lang.Object arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  java.lang.String toPrettyString(java.lang.Object arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final  byte[] toByteArray(java.lang.Object arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> T fromString(java.lang.String arg0, java.lang.Class<T> arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> T fromInputStream(java.io.InputStream arg0, java.lang.Class<T> arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> T fromInputStream(java.io.InputStream arg0, java.nio.charset.Charset arg1, java.lang.Class<T> arg2) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public final <T> T fromReader(java.io.Reader arg0, java.lang.Class<T> arg1) throws java.io.IOException { throw new RuntimeException("Stub!"); }
}
