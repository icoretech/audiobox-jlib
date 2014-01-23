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

package fm.audiobox.core.models;

import com.google.api.client.util.Key;

/**
 * For each of the external services the boolean value indicates if we have stored a OAuth token for the user.
 * <p/>
 * Such attributes are automatically set by the system and cannot be changed manually.
 */
public class ExternalTokens {

  @Key
  private boolean dropbox;

  @Key
  private boolean gdrive;

  @Key
  private boolean skydrive;

  @Key
  private boolean ubuntu;

  @Key
  private boolean soundcloud;

  @Key
  private boolean youtube;

  @Key
  private boolean box;

  @Key
  private boolean lastfm;

  @Key
  private boolean twitchtv;

  @Key
  private boolean facebook;

  @Key
  private boolean twitter;


  /**
   * Checks if Dropbox is enabled.
   *
   * @return the true if a Dropbox account is linked, false otherwise
   */
  public boolean isDropboxEnabled() {
    return dropbox;
  }


  /**
   * Checks if Google Drive is enabled.
   *
   * @return the true if a Google Drive account is linked, false otherwise
   */
  public boolean isGdriveEnabled() {
    return gdrive;
  }


  /**
   * Checks if SkyDrive is enabled.
   *
   * @return the true if a SkyDrive account is linked, false otherwise
   */
  public boolean isSkydriveEnabled() {
    return skydrive;
  }


  /**
   * Checks if Ubuntu One Music is enabled.
   *
   * @return the true if a Ubuntu One Music account is linked, false otherwise
   */
  public boolean isUbuntuEnabled() {
    return ubuntu;
  }


  /**
   * Checks if Soundcloud is enabled.
   *
   * @return the true if a Soundcloud account is linked, false otherwise
   */
  public boolean isSoundcloudEnabled() {
    return soundcloud;
  }


  /**
   * Checks if YouTube is enabled.
   *
   * @return the true if a YouTube account is linked, false otherwise
   */
  public boolean isYoutubeEnabled() {
    return youtube;
  }


  /**
   * Checks if Box is enabled.
   *
   * @return the true if a Box account is linked, false otherwise
   */
  public boolean isBoxEnabled() {
    return box;
  }


  /**
   * Checks if Last.fm is enabled.
   *
   * @return the true if a Last.fm account is linked, false otherwise
   */
  public boolean isLastfmEnabled() {
    return lastfm;
  }


  /**
   * Checks if Twitch.tv is enabled.
   *
   * @return the true if a Twitch.tv account is linked, false otherwise
   */
  public boolean isTwitchtvEnabled() {
    return twitchtv;
  }


  /**
   * Checks if Facebook is enabled.
   *
   * @return the true if a Facebook account is linked, false otherwise
   */
  public boolean isFacebookEnabled() {
    return facebook;
  }


  /**
   * Checks if Twitter is enabled.
   *
   * @return the true if a Twitter account is linked, false otherwise
   */
  public boolean isTwitterEnabled() {
    return twitter;
  }
}
