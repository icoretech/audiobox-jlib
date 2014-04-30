package fm.audiobox.core.exceptions;
public class SystemOverloadedException
  extends fm.audiobox.core.exceptions.RemoteMessageException
{
public  SystemOverloadedException(com.google.api.client.http.HttpResponse response) { super((fm.audiobox.core.exceptions.Errors)null,0); throw new RuntimeException("Stub!"); }
public  java.lang.String getRetryAfter() { throw new RuntimeException("Stub!"); }
public  int getRetryAfterInSeconds() { throw new RuntimeException("Stub!"); }
}
