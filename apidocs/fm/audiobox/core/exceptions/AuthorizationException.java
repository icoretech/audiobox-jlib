package fm.audiobox.core.exceptions;
public class AuthorizationException
  extends fm.audiobox.core.exceptions.RemoteMessageException
{
public  AuthorizationException(com.google.api.client.http.HttpResponse response) { super((fm.audiobox.core.exceptions.Errors)null,0); throw new RuntimeException("Stub!"); }
public  AuthorizationException(com.google.api.client.auth.oauth2.TokenResponseException tokenException) { super((fm.audiobox.core.exceptions.Errors)null,0); throw new RuntimeException("Stub!"); }
public  AuthorizationException(fm.audiobox.core.exceptions.Errors errors, int statusCode) { super((fm.audiobox.core.exceptions.Errors)null,0); throw new RuntimeException("Stub!"); }
}
