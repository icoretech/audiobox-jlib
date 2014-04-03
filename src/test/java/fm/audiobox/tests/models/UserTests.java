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


import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.models.*;
import fm.audiobox.core.utils.ModelUtil;
import fm.audiobox.tests.AudioBoxTests;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;


public class UserTests extends AudioBoxTests {


  /**
   * Test all user keys are well parsed.
   *
   * @throws AudioBoxException the audio box exception
   * @throws ParseException    the parse exception
   */
  @Test
  public void testAllUserKeysAreWellParsed() throws IOException, ParseException {
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

    try {
      prefs.setColor( "invalid-value" );
      fail("Invalid color name should rise an exception");
    } catch ( Exception e ) {
      assertEquals( IllegalArgumentException.class, e.getClass() );
    }
    
    prefs.setColor( "flock-blue" );
    prefs.setRepeat( false );
    prefs.setShuffle( true );
    prefs.setAutoplay( true );
    prefs.setPrebuffer( false );
    prefs.setJsDemuxer( true );
    prefs.setTopBarBg( "String" );
    prefs.setVolumeLevel( "String" );
    prefs.setAcceptEmails( false );
    prefs.setHideTooltips( true );



    assertEquals( "flock-blue", prefs.getColor() );
    assertEquals( "#4096EE", prefs.getColorValue() );
    assertEquals( false, prefs.isRepeatEnabled() );
    assertEquals( true, prefs.isShuffleEnabled() );
    assertEquals( true, prefs.isAutoplayEnabled() );
    assertEquals( false, prefs.isPrebufferEnabled() );
    assertEquals( true, prefs.isJsDemuxerEnabled() );
    assertEquals( "String", prefs.getTopBarBg() );
    assertEquals( "String", prefs.getVolumeLevel() );
    assertEquals( false, prefs.doesAcceptsEmails() );
    assertEquals( true, prefs.areTooltipsHidden() );
  }


  /**
   * Test user update.
   *
   * @throws AudioBoxException the audio box exception
   */
  @Test
  public void testUserUpdate() throws IOException {
    User u = c.getUser();
    assertNotNull( u );
    assertNotNull( u.getPreferences() );
    u.getPreferences().setVolumeLevel( "100" );
    assertNotNull( u.savePreferences( c ) );
  }
}
