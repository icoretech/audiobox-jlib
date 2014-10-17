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

package fm.audiobox.tests.unit.base;

import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.DataStoreCredentialRefreshListener;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fm.audiobox.core.AudioBoxClient;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.config.ConfigurationException;
import fm.audiobox.tests.mocks.MockHttp;
import fm.audiobox.tests.support.FileCredentialStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Generic test case class.
 */
@SuppressWarnings( "deprecation" )
public class AudioBoxTests {

  protected static final File DATA_STORE_DIR = new File( System.getProperty( "user.home" ), ".audiobox/abx" );

  protected static final File CACHE_DIR = new File( System.getProperty( "user.home" ), ".audiobox/http" );

  protected Logger logger = LoggerFactory.getLogger( this.getClass().getSimpleName() );

  protected Config fixtures = ConfigFactory.load( "travis" );

  protected AudioBoxClient c;

  private long time_start = 0;

  private boolean printTimingLog = false;

  public static final Configuration.Env env = Configuration.Env.development;


  @Rule
  public TestName name = new TestName();


  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    // Remove existing handlers attached to j.u.l root logger
    SLF4JBridgeHandler.removeHandlersForRootLogger();

    // add SLF4JBridgeHandler to j.u.l's root logger
    SLF4JBridgeHandler.install();

    printTimingLog = true;
    time_start = System.currentTimeMillis();
    logger.debug( "*** Tests started: " + name.getMethodName() );

    try {

      final long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
      final File httpCacheDir = CACHE_DIR;
      HttpResponseCache.install( httpCacheDir, httpCacheSize );

      JacksonFactory jf = new JacksonFactory();

      Configuration config = new Configuration()
          .setCredentialDataStore( new FileCredentialStore( DATA_STORE_DIR ) )
          .setApiKey( fixtures.getString( "authentication.client_id" ) )
          .setApiSecret( fixtures.getString( "authentication.client_secret" ) )
          .setHttpTransport( MockHttp.getTransport() )
          .setJsonFactory( jf )
          .setApplicationName( "Tests" )
          .setVersion( "1.0" );

      CredentialRefreshListener crl = new DataStoreCredentialRefreshListener( AudioBoxClient.ACCOUNT_TOKENS, ((FileCredentialStore)config.getCredentialDataStore()).getDB());
      config.setCredentialRefreshListener( crl );

      config.setEnvironment( env );
      c = new AudioBoxClient( config );
    } catch ( ConfigurationException | IOException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Tear down.
   */
  @After
  public void tearDown() {
    long millis_taken = System.currentTimeMillis() - time_start;
    double seconds_taken = ( double ) millis_taken / 1000D;
    if ( printTimingLog ) {
      logger.debug( "*** Test completed in " + seconds_taken + " seconds" );
    }
  }



  /* ================= */
  /* Protected methods */
  /* ================= */


  /**
   * Use this method if you need to make requests against real AudioBox
   * staging environment.
   * <p/>
   * This is useful only to register a real API transaction. Use with cautions.
   */
  @SuppressWarnings( "unused" )
  protected void prepareForStaging() throws IOException {
    fixtures = ConfigFactory.load( "fixtures" );
    c.getConf().setEnvironment( Configuration.Env.staging );
    c.getConf().setHttpTransport( new NetHttpTransport() );
    c.getConf().setApiKey( fixtures.getString( "authentication.staging.client_id" ) );
    c.getConf().setApiSecret( fixtures.getString( "authentication.staging.client_secret" ) );
    c.authorize( fixtures.getString( "authentication.staging.email" ), fixtures.getString( "authentication.staging.password" ) );
  }


  /**
   * Use this method if you need to make requests against real AudioBox
   * staging environment.
   * <p/>
   * This is useful only to register a real API transaction. Use with cautions.
   */
  @SuppressWarnings( "unused" )
  protected void prepareForLocalDevelopment() throws IOException {
    fixtures = ConfigFactory.load( "fixtures" );
    c.getConf().setEnvironment( Configuration.Env.development );
    c.getConf().setHttpTransport( new NetHttpTransport() );
    c.getConf().setApiKey( fixtures.getString( "authentication.local.client_id" ) );
    c.getConf().setApiSecret( fixtures.getString( "authentication.local.client_secret" ) );
    c.authorize( fixtures.getString( "authentication.local.email" ), fixtures.getString( "authentication.local.password" ) );
  }

}
