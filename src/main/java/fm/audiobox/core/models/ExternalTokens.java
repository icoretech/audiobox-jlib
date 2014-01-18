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
 * Created by keytwo on 17/01/14.
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
   * Is dropbox enabled.
   *
   * @return the boolean
   */
  public boolean isDropboxEnabled() {
    return dropbox;
  }


  /**
   * Is gdrive enabled.
   *
   * @return the boolean
   */
  public boolean isGdriveEnabled() {
    return gdrive;
  }


  /**
   * Is skydrive enabled.
   *
   * @return the boolean
   */
  public boolean isSkydriveEnabled() {
    return skydrive;
  }


  /**
   * Is ubuntu enabled.
   *
   * @return the boolean
   */
  public boolean isUbuntuEnabled() {
    return ubuntu;
  }


  /**
   * Is soundcloud enabled.
   *
   * @return the boolean
   */
  public boolean isSoundcloudEnabled() {
    return soundcloud;
  }


  /**
   * Is youtube enabled.
   *
   * @return the boolean
   */
  public boolean isYoutubeEnabled() {
    return youtube;
  }


  /**
   * Is box enabled.
   *
   * @return the boolean
   */
  public boolean isBoxEnabled() {
    return box;
  }


  /**
   * Is lastfm enabled.
   *
   * @return the boolean
   */
  public boolean isLastfmEnabled() {
    return lastfm;
  }


  /**
   * Is twitchtv enabled.
   *
   * @return the boolean
   */
  public boolean isTwitchtvEnabled() {
    return twitchtv;
  }


  /**
   * Is facebook enabled.
   *
   * @return the boolean
   */
  public boolean isFacebookEnabled() {
    return facebook;
  }


  /**
   * Is twitter enabled.
   *
   * @return the boolean
   */
  public boolean isTwitterEnabled() {
    return twitter;
  }
}
