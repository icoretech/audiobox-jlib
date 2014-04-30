package com.google.api.client.json;
public abstract class JsonGenerator
{
public  JsonGenerator() { throw new RuntimeException("Stub!"); }
public abstract  com.google.api.client.json.JsonFactory getFactory();
public abstract  void flush() throws java.io.IOException;
public abstract  void close() throws java.io.IOException;
public abstract  void writeStartArray() throws java.io.IOException;
public abstract  void writeEndArray() throws java.io.IOException;
public abstract  void writeStartObject() throws java.io.IOException;
public abstract  void writeEndObject() throws java.io.IOException;
public abstract  void writeFieldName(java.lang.String arg0) throws java.io.IOException;
public abstract  void writeNull() throws java.io.IOException;
public abstract  void writeString(java.lang.String arg0) throws java.io.IOException;
public abstract  void writeBoolean(boolean arg0) throws java.io.IOException;
public abstract  void writeNumber(int arg0) throws java.io.IOException;
public abstract  void writeNumber(long arg0) throws java.io.IOException;
public abstract  void writeNumber(java.math.BigInteger arg0) throws java.io.IOException;
public abstract  void writeNumber(float arg0) throws java.io.IOException;
public abstract  void writeNumber(double arg0) throws java.io.IOException;
public abstract  void writeNumber(java.math.BigDecimal arg0) throws java.io.IOException;
public abstract  void writeNumber(java.lang.String arg0) throws java.io.IOException;
public final  void serialize(java.lang.Object arg0) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  void enablePrettyPrint() throws java.io.IOException { throw new RuntimeException("Stub!"); }
}
