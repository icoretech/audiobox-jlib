package fm.audiobox.core.exceptions;
public class AudioBoxException
  extends java.io.IOException
{
public  AudioBoxException(java.lang.String message) { throw new RuntimeException("Stub!"); }
public  AudioBoxException(com.google.api.client.http.HttpResponse response) { throw new RuntimeException("Stub!"); }
public  com.google.api.client.http.HttpResponse getResponse() { throw new RuntimeException("Stub!"); }
public  int getErrorCode() { throw new RuntimeException("Stub!"); }
protected static org.slf4j.Logger logger;
protected int statusCode;
}
