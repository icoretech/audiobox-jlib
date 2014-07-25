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

package fm.audiobox.tests.integration;

import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.DataStoreCredentialRefreshListener;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.FileAlreadyUploaded;
import fm.audiobox.core.models.MediaFile;
import fm.audiobox.core.net.Upload;
import fm.audiobox.core.net.UploadProgressListener;
import fm.audiobox.core.store.CredentialDataStore;
import fm.audiobox.tests.mocks.MockHttp;
import fm.audiobox.tests.support.FileCredentialStore;
import org.junit.*;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Generic test case class.
 * <p/>
 * Created by keytwo on 16/01/14.
 */
public class AudioBoxTests {

  protected static final File DATA_STORE_DIR = new File( System.getProperty( "user.home" ), ".audiobox/abx" );

  protected static final File CACHE_DIR = new File( System.getProperty( "user.home" ), ".audiobox/http" );

  protected Logger logger = LoggerFactory.getLogger( this.getClass().getSimpleName() );

  protected Config fixtures = ConfigFactory.load( "travis" );

  protected Client c;

  private long time_start = 0;

  private boolean printTimingLog = true;

  public static final Configuration.Env env = Configuration.Env.staging;


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

      Configuration config = new Configuration( env )
          .setCredentialDataStore( new FileCredentialStore( DATA_STORE_DIR ) )
          .setApiKey( fixtures.getString( "authentication.client_id" ) )
          .setApiSecret( fixtures.getString( "authentication.client_secret" ) )
          .setHttpTransport( new NetHttpTransport() )
          .setJsonFactory( jf );

      CredentialRefreshListener crl = new DataStoreCredentialRefreshListener( Client.ACCOUNT_TOKENS, ( ( FileCredentialStore ) config.getCredentialDataStore() ).getDB() );
      config.setCredentialRefreshListener( crl );

      c = new Client( config );
    } catch ( ConfigurationException | IOException e ) {
      fail( e.getMessage() );
    }
  }

  @Test
  @Ignore
  public void testUploadSuccess() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    c.getConf().setHttpTransport( new NetHttpTransport() );
    c.getConf().setUploadProgressListener( new UploadProgressListener() {
      @Override
      public void onProgressUpdate(long total, long current) {
        assertTrue( "Current progress cannot be bigger than total", total >= current );
        logger.info( "Total: " + total + " | Actual: " + current );
      }
    } );

    Upload u = c.newUpload( file );
    MediaFile m = u.start();
    assertEquals( file.getAbsolutePath(), m.getRemotePath() );
    assertTrue( "File must be destroyed", m.destroy( c ) );

  }

  /**
   * Test upload [WORK IN PROGRESS].
   *
   * @throws IOException the iO exception
   */
  @Test
  @Ignore
  public void testUploadFailure() throws IOException {

    File file = new File( this.getClass().getResource( "/mpthreetest.mp3" ).getFile() );
    assertNotNull( file );
    assertTrue( file.exists() );

    c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    c.getConf().setHttpTransport( new NetHttpTransport() );
    c.getConf().setUploadProgressListener( new UploadProgressListener() {
      @Override
      public void onProgressUpdate(long total, long current) {
        assertTrue( "Current progress cannot be bigger than total", total >= current );
        logger.info( "Total: " + total + " | Actual: " + current );
      }
    } );

    // First upload should succeed
    Upload u = c.newUpload( file );
    MediaFile m = u.start();
    assertEquals( file.getAbsolutePath(), m.getRemotePath() );

    try {
      // Second upload must fail
      u = c.newUpload( file );
      u.start();
      fail("Exception should be thrown");

    } catch ( Exception e ) {
      assertTrue( e instanceof FileAlreadyUploaded );

    } finally {
      assertTrue( "File must be destroyed", m.destroy( c ) );

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


}
