package java.nio.file.attribute;
public abstract class UserPrincipalLookupService
{
protected  UserPrincipalLookupService() { throw new RuntimeException("Stub!"); }
public abstract  java.nio.file.attribute.UserPrincipal lookupPrincipalByName(java.lang.String arg0) throws java.io.IOException;
public abstract  java.nio.file.attribute.GroupPrincipal lookupPrincipalByGroupName(java.lang.String arg0) throws java.io.IOException;
}
