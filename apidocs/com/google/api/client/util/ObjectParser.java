package com.google.api.client.util;
public interface ObjectParser
{
public abstract <T> T parseAndClose(java.io.InputStream arg0, java.nio.charset.Charset arg1, java.lang.Class<T> arg2) throws java.io.IOException;
public abstract  java.lang.Object parseAndClose(java.io.InputStream arg0, java.nio.charset.Charset arg1, java.lang.reflect.Type arg2) throws java.io.IOException;
public abstract <T> T parseAndClose(java.io.Reader arg0, java.lang.Class<T> arg1) throws java.io.IOException;
public abstract  java.lang.Object parseAndClose(java.io.Reader arg0, java.lang.reflect.Type arg1) throws java.io.IOException;
}
