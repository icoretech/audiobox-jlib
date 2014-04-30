package fm.audiobox.core.exceptions;
public class RemoteMessageException
  extends fm.audiobox.core.exceptions.AudioBoxException
{
public  RemoteMessageException(com.google.api.client.http.HttpResponse response) { super((com.google.api.client.http.HttpResponse)null); throw new RuntimeException("Stub!"); }
public  RemoteMessageException(fm.audiobox.core.exceptions.Errors errors, int statusCode) { super((com.google.api.client.http.HttpResponse)null); throw new RuntimeException("Stub!"); }
public  java.lang.String getMessage() { throw new RuntimeException("Stub!"); }
public  int getErrorCode() { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.exceptions.Errors getErrors() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public static  java.lang.String errorsToString(fm.audiobox.core.exceptions.Errors errors) { throw new RuntimeException("Stub!"); }
}
