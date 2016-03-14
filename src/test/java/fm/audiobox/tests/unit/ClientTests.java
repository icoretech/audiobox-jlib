/*
 * Copyright 2009-2016 iCoreTech, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.audiobox.tests.unit;


import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.store.DataStore;
import fm.audiobox.core.exceptions.AuthorizationException;
import fm.audiobox.tests.mocks.MockHttp;
import fm.audiobox.tests.support.FileCredentialStore;
import fm.audiobox.tests.unit.base.AudioBoxTests;
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
    c.authorize( "wrong@email.com", "fakepasswd", true );
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
    DataStore<StoredCredential> udb = (( FileCredentialStore) c.getConf().getCredentialDataStore() ).getDB();
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
    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ), true );
  }


  @Test
  public void testAuthExceptionOnApiCall() throws IOException {
    try {
      c.getConf().setHttpTransport( MockHttp.getInvalidRefreshTokenHttpTransport() );
      c.getPlaylists();
      fail("AuthorizationException should be thrown");
    } catch ( Exception e ) {
      e.printStackTrace();
      assertTrue( e instanceof AuthorizationException);
      AuthorizationException ae = (AuthorizationException) e;
      assertNotNull(ae.getErrors());
      assertEquals( "invalid_grant", ae.getErrors().getError() );
      assertEquals( "invalid refresh token", ae.getErrors().getErrorDescription() );
    }

  }

  //@Test
  //public void testDaemonIp() throws IOException {
  //  c.getConf().setHttpTransport( new NetHttpTransport() );
  //  c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
  //  c.isDaemonRunning();
  //}

}
