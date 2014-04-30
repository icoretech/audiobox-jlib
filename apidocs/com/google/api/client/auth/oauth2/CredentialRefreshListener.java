package com.google.api.client.auth.oauth2;
public interface CredentialRefreshListener
{
public abstract  void onTokenResponse(com.google.api.client.auth.oauth2.Credential arg0, com.google.api.client.auth.oauth2.TokenResponse arg1) throws java.io.IOException;
public abstract  void onTokenErrorResponse(com.google.api.client.auth.oauth2.Credential arg0, com.google.api.client.auth.oauth2.TokenErrorResponse arg1) throws java.io.IOException;
}
