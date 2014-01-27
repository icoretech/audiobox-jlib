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

package fm.audiobox.tests.models;


import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.integralblue.httpresponsecache.HttpResponseCache;
import fm.audiobox.core.Client;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.models.*;
import fm.audiobox.core.utils.ModelUtil;
import fm.audiobox.tests.AudioBoxTests;
import fm.audiobox.tests.mocks.AudioBoxMockHttpTransportFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;


/**
 * Created by keytwo on 22/01/14.
 */
public class UserTests extends AudioBoxTests {

  @Before
  public void setUp() {
    super.setUp();

    try {

      final long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
      final File httpCacheDir = CACHE_DIR;
      HttpResponseCache.install( httpCacheDir, httpCacheSize );

      Configuration config = new Configuration( Configuration.Env.development );
      config.setDataStoreFactory( new FileDataStoreFactory( DATA_STORE_DIR ) );

      config.setApiKey( fixtures.getString( "authentication.client_id" ) );
      config.setApiSecret( fixtures.getString( "authentication.client_secret" ) );
      config.setHttpTransport( new NetHttpTransport() );
      JacksonFactory jf = new JacksonFactory();
      config.setJsonFactory( jf );

      c = new Client( config );
    } catch ( IOException | ConfigurationException e ) {
      fail( e.getMessage() );
    }
  }


  /**
   * Test all user keys are well parsed.
   *
   * @throws AudioBoxException the audio box exception
   * @throws ParseException    the parse exception
   */
  @Test
  public void testAllUserKeysAreWellParsed() throws AudioBoxException, ParseException {
    c.getConf().setHttpTransport( AudioBoxMockHttpTransportFactory.getRightUserHttpTransport() );
    User user = c.getUser();
    assertNotNull( user );

    assertEquals( 3, user.getId() );

    assertEquals( "2013-08-29T18:25:52.079Z", user.getCreatedAt() );
    long millisCreated = ModelUtil.toUnixTime( new SimpleDateFormat( ModelUtil.AUDIOBOX_DATE_FORMAT ), user.getCreatedAt() );
    assertEquals( 1377800752079L, millisCreated );

    assertEquals( "2014-01-21T21:48:58.850Z", user.getUpdatedAt() );
    long millisUpdated = ModelUtil.toUnixTime( new SimpleDateFormat( ModelUtil.AUDIOBOX_DATE_FORMAT ), user.getUpdatedAt() );
    assertEquals( 1390340938850L, millisUpdated );

    assertEquals( "Real Name", user.getRealName() );
    assertEquals( "test@test.com", user.getEmail() );
    assertEquals( "4u770k3n", user.getAuthToken() );
    assertEquals( 60, user.getMediaFilesCount() );
    assertEquals( 13, user.getPlaylistsCount() );
    assertEquals( 5, user.getTotalPlayCount() );
    assertEquals( StringUtils.EMPTY, user.getCountry() );
    assertEquals( "UTC", user.getTimeZone() );
    assertEquals( "aac,mp3,m4a,flac,mp4,flv,webm", user.getAcceptedExtensions() );
    assertEquals( "audio/aac,audio/mpeg,audio/mp4,audio/flac,video/mp4,video/x-flv,video/webm", user.getAcceptedFormats() );
    assertEquals( "private-abc", user.getCometChannel() );
    assertEquals( "active", user.getSubscriptionState() );
    assertEquals( "audiobox_50", user.getPlan() );
    assertEquals( "000_offline", user.getOfflinePlaylist() );

    Permissions perms = user.getPermissions();
    assertNotNull( perms );

    assertTrue( perms.hasPlayer() );
    assertTrue( perms.hasLocal() );
    assertTrue( perms.hasCloud() );
    assertTrue( perms.hasDropbox() );
    assertTrue( perms.hasGdrive() );
    assertTrue( perms.hasSkydrive() );
    assertTrue( perms.hasUbuntu() );
    assertTrue( perms.hasBox() );
    assertTrue( perms.hasSoundcloud() );
    assertTrue( perms.hasYoutube() );
    assertTrue( perms.hasLastfm() );
    assertTrue( perms.hasTwitchtv() );
    assertTrue( perms.hasFacebook() );
    assertTrue( perms.hasTwitter() );
    assertTrue( perms.hasLyrics() );
    assertTrue( perms.hasSongkick() );

    ExternalTokens tks = user.getExternalTokens();
    assertNotNull( tks );

    assertFalse( tks.isDropboxEnabled() );
    assertTrue( tks.isGdriveEnabled() );
    assertTrue( tks.isSkydriveEnabled() );
    assertFalse( tks.isUbuntuEnabled() );
    assertFalse( tks.isSoundcloudEnabled() );
    assertTrue( tks.isYoutubeEnabled() );
    assertTrue( tks.isBoxEnabled() );
    assertFalse( tks.isLastfmEnabled() );
    assertFalse( tks.isTwitchtvEnabled() );
    assertFalse( tks.isFacebookEnabled() );
    assertFalse( tks.isTwitterEnabled() );

    Stats stats = user.getStats();
    assertNotNull( stats );

    assertEquals( 5, stats.getTotalPlayCount() );
    assertEquals( 777662290, stats.getDataServedOverall() );
    assertEquals( 777662290, stats.getDataServedThisMonth() );
    assertEquals( 5533908, stats.getBoxDataStoredOverall() );
    assertEquals( 0, stats.getCloudDataStoredOverall() );
    assertEquals( 0, stats.getLocalDataStoredOverall() );
    assertEquals( 5533908, stats.getBoxDataStoredThisMonth() );
    assertEquals( 170368034, stats.getGdriveDataStoredOverall() );
    assertEquals( 0, stats.getUbuntuDataStoredOverall() );
    assertEquals( 110981727, stats.getDropboxDataStoredOverall() );
    assertEquals( 1500, stats.getYoutubeDataStoredOverall() );
    assertEquals( 2175615, stats.getCloudDataStoredThisMonth() );
    assertEquals( 0, stats.getLocalDataStoredThisMonth() );
    assertEquals( 95088577, stats.getSkydriveDataStoredOverall() );
    assertEquals( 170368034, stats.getGdriveDataStoredThisMonth() );
    assertEquals( 0, stats.getUbuntuDataStoredThisMonth() );
    assertEquals( 110981727, stats.getDropboxDataStoredThisMonth() );
    assertEquals( 0, stats.getSoundcloudDataStoredOverall() );
    assertEquals( 1500, stats.getYoutubeDataStoredThisMonth() );
    assertEquals( 95088577, stats.getSkydriveDataStoredThisMonth() );
    assertEquals( 0, stats.getSoundcloudDataStoredThisMonth() );

    Preferences prefs = user.getPreferences();
    assertNotNull( prefs );

    assertEquals( "audiobox-fm-blue", prefs.getColor() );
    assertTrue( prefs.isRepeatEnabled() );
    assertFalse( prefs.isShuffleEnabled() );
    assertFalse( prefs.isAutoplayEnabled() );
    assertTrue( prefs.isPrebufferEnabled() );
    assertFalse( prefs.isJsDemuxerEnabled() );
    assertEquals( "default", prefs.getTopBarBg() );
    assertEquals( "50", prefs.getVolumeLevel() );
    assertTrue( prefs.doesAcceptsEmails() );
    assertFalse( prefs.areTooltipsHidden() );
  }


  /**
   * Test user update.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  @Ignore
  public void testUserUpdate() throws IOException {
    // c.authorize( fixtures.getString( "authentication.email" ), fixtures.getString( "authentication.password" ) );
    User u = c.getUser();
    assertNotNull( u );
    assertNotNull( u.getPreferences() );
    u.getPreferences().setVolumeLevel( "54" );
    assertNotNull( u.savePreferences( c ) );
  }
}
