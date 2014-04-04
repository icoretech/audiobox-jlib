/*
 * Copyright 2009-2014 iCoreTech, Inc.
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

package fm.audiobox.tests.config;


import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.tests.AudioBoxTests;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


/**
 * The type Configuration tests.
 */
public class ConfigurationTests extends AudioBoxTests {


  /**
   * Test should rise configuration exception on missing environment.
   *
   * @throws ConfigurationException the configuration exception
   */
  @Test
  public void testShouldRiseConfigurationExceptionOnMissingEnvironment() throws ConfigurationException {
    try {
      Configuration c = new Configuration( null );
      c.checkConfiguration();
    } catch ( IllegalArgumentException e ) {
      assertEquals( "Environment cannot be null", e.getMessage() );
      return;
    }
    fail( "Exception message was not the one expected" );
  }


  /**
   * Test should rise configuration exception on missing api key.
   *
   * @throws ConfigurationException the configuration exception
   */
  @Test
  public void testShouldRiseConfigurationExceptionOnMissingApiKey() throws ConfigurationException {
    Configuration c = new Configuration( Configuration.Env.development );
    try {
      c.checkConfiguration();
    } catch ( ConfigurationException e ) {
      assertEquals( "API Key (secret) is missing, please provide one.", e.getMessage() );
      return;
    }
    fail( "Exception message was not the one expected" );
  }


  /**
   * Test should rise configuration exception on missing client id.
   *
   * @throws ConfigurationException the configuration exception
   */
  @Test
  public void testShouldRiseConfigurationExceptionOnMissingClientId() throws ConfigurationException {
    Configuration c = new Configuration( Configuration.Env.development );
    try {
      c.setApiKey( fixtures.getString( "authentication.client_secret" ) );
      c.checkConfiguration();
    } catch ( ConfigurationException e ) {
      assertEquals( "Client ID is missing, please provide one.", e.getMessage() );
      return;
    }
    fail( "Exception message was not the one expected" );
  }


  /**
   * Test should rise configuration exception on missing data store.
   *
   * @throws ConfigurationException the configuration exception
   */
  @Test
  public void testShouldRiseConfigurationExceptionOnMissingDataStore() throws ConfigurationException {
    Configuration c = new Configuration( Configuration.Env.development );
    try {
      c.setApiKey( fixtures.getString( "authentication.client_id" ) );
      c.setApiSecret( fixtures.getString( "authentication.client_secret" ) );
      c.checkConfiguration();
    } catch ( ConfigurationException e ) {
      assertEquals( "Data store must be set.", e.getMessage() );
      return;
    }
    fail( "Exception message was not the one expected" );
  }


  /**
   * Test should rise configuration exception on missing http transport.
   *
   * @throws ConfigurationException the configuration exception
   */
  @Test
  public void testShouldRiseConfigurationExceptionOnMissingHttpTransport() throws ConfigurationException {
    Configuration c = new Configuration( Configuration.Env.development );
    try {
      c.setApiKey( fixtures.getString( "authentication.client_id" ) );
      c.setApiSecret( fixtures.getString( "authentication.client_secret" ) );
      c.setDataStoreFactory( new FileDataStoreFactory( new File( System.getProperty( "user.home" ), ".audiobox/abx" ) ) );
      c.checkConfiguration();
    } catch ( ConfigurationException e ) {
      assertEquals( "Http transport type must be set", e.getMessage() );
      return;
    } catch ( IOException e ) {
      fail( e.getMessage() );
    }
    fail( "Exception message was not the one expected" );
  }


  /**
   * Test should rise configuration exception on missing json factory.
   *
   * @throws ConfigurationException the configuration exception
   */
  @Test
  public void testShouldRiseConfigurationExceptionOnMissingJsonFactory() throws ConfigurationException {
    Configuration c = new Configuration( Configuration.Env.development );
    try {
      c.setApiKey( fixtures.getString( "authentication.client_id" ) );
      c.setApiSecret( fixtures.getString( "authentication.client_secret" ) );
      c.setDataStoreFactory( new FileDataStoreFactory( new File( System.getProperty( "user.home" ), ".audiobox/abx" ) ) );
      c.setHttpTransport( new NetHttpTransport() );
      c.checkConfiguration();
    } catch ( ConfigurationException e ) {
      assertEquals( "JSON factory must be set", e.getMessage() );
      return;
    } catch ( IOException e ) {
      fail( e.getMessage() );
    }
    fail( "Exception message was not the one expected" );
  }


  /**
   * Test audio box url should be prod in prod.
   */
  @Test
  public void testAudioBoxUrlShouldBeProdInProd() {
    Configuration c = new Configuration( Configuration.Env.production );
    assertSame( c.getEnvironment(), Configuration.Env.production );
    assertEquals( "https://audiobox.fm:443", c.getEnvBaseUrl() );
  }


  /**
   * Test audio box url should be staging in staging.
   */
  @Test
  public void testAudioBoxUrlShouldBeStagingInStaging() {
    Configuration c = new Configuration( Configuration.Env.staging );
    assertSame( c.getEnvironment(), Configuration.Env.staging );
    assertEquals( "https://staging.audiobox.fm:443", c.getEnvBaseUrl() );
  }


  /**
   * Test audio box url should be dev in dev.
   */
  @Test
  public void testAudioBoxUrlShouldBeDevInDev() {
    Configuration c = new Configuration( Configuration.Env.development );
    assertSame( c.getEnvironment(), Configuration.Env.development );
    assertEquals( "http://dev.audiobox.fm:5000", c.getEnvBaseUrl() );
  }


  /**
   * Test env enum.
   */
  @Test
  public void testEnvEnum() {
    assertEquals( 3, Configuration.Env.values().length );
    assertEquals( Configuration.Env.development, Configuration.Env.valueOf("development") );
    assertEquals( Configuration.Env.staging, Configuration.Env.valueOf("staging") );
    assertEquals( Configuration.Env.production, Configuration.Env.valueOf("production") );
  }
}
