package fm.audiobox.core.store;
public interface CredentialDataStore
{
public abstract  void saveCredentials(java.lang.String label, com.google.api.client.auth.oauth2.Credential credential) throws java.io.IOException;
public abstract  com.google.api.client.auth.oauth2.StoredCredential getCredentials(java.lang.String label) throws java.io.IOException;
}
