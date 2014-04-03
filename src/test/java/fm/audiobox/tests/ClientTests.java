/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 *
 * This file is part of the AudioBox-Jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.tests;


import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.store.DataStore;
import fm.audiobox.core.exceptions.AuthorizationException;
import fm.audiobox.tests.mocks.MockHttp;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ClientTests extends AudioBoxTests {

  /**
   * Test wrong authorization.
   *
   * @throws IOException the iO exception
   */
  @Test( expected = AuthorizationException.class )
  public void testWrongAuthorization() throws IOException {
    c.getConf().setHttpTransport( MockHttp.getWrongAccountHttpTransport() );
    c.authorize( "wrong@email.com", "fakepasswd" );
  }


  /**
   * Test authorization.
   *
   * @throws ConfigurationException the configuration exception
   */
  @Test
  public void testRightAuthorization() throws ConfigurationException {
    try {
      TokenResponse r = c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
      assertEquals( "aaa", r.getAccessToken() );
      assertEquals( "rrr", r.getRefreshToken() );
      logger.debug( r.getAccessToken() );
    } catch ( IOException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test stored credential.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testStoredCredentialWithWrongRefreshToken() throws IOException {
    c.getConf().setHttpTransport( MockHttp.getInvalidRefreshTokenHttpTransport() );
    DataStore<StoredCredential> udb = StoredCredential.getDefaultDataStore( c.getConf().getDataStoreFactory() );
    assertFalse( "Data store should not be empty", udb.isEmpty() );
    try {
      c.getUser();
      fail( "AuthorizationException expected" );
    } catch ( AuthorizationException e ) {
      assertEquals( "invalid_grant: invalid refresh token", e.getMessage() );
    } catch ( Exception e ) {
      fail( "AuthorizationException expected, got " + e.getClass().getCanonicalName() );
    }
  }


  /**
   * Test malformed request.
   *
   * @throws IOException the iO exception
   */
  @Test( expected = AuthorizationException.class )
  public void testMalformedRequest() throws IOException {
    c.getConf().setHttpTransport( MockHttp.getMalformedHttpTransport() );
    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
  }

}
