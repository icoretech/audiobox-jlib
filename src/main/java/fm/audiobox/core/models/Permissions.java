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
public class Permissions {

  @Key
  private boolean player;

  @Key
  private boolean local;

  @Key
  private boolean cloud;

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

  @Key
  private boolean musixmatch;

  @Key
  private boolean songkick;


  /**
   * Has player.
   *
   * @return the boolean
   */
  public boolean hasPlayer() {
    return player;
  }


  /**
   * Has local.
   *
   * @return the boolean
   */
  public boolean hasLocal() {
    return local;
  }


  /**
   * Has cloud.
   *
   * @return the boolean
   */
  public boolean hasCloud() {
    return cloud;
  }


  /**
   * Has dropbox.
   *
   * @return the boolean
   */
  public boolean hasDropbox() {
    return dropbox;
  }


  /**
   * Has gdrive.
   *
   * @return the boolean
   */
  public boolean hasGdrive() {
    return gdrive;
  }


  /**
   * Has skydrive.
   *
   * @return the boolean
   */
  public boolean hasSkydrive() {
    return skydrive;
  }


  /**
   * Has ubuntu.
   *
   * @return the boolean
   */
  public boolean hasUbuntu() {
    return ubuntu;
  }


  /**
   * Has soundcloud.
   *
   * @return the boolean
   */
  public boolean hasSoundcloud() {
    return soundcloud;
  }


  /**
   * Has youtube.
   *
   * @return the boolean
   */
  public boolean hasYoutube() {
    return youtube;
  }


  /**
   * Has box.
   *
   * @return the boolean
   */
  public boolean hasBox() {
    return box;
  }


  /**
   * Has lastfm.
   *
   * @return the boolean
   */
  public boolean hasLastfm() {
    return lastfm;
  }


  /**
   * Has twitchtv.
   *
   * @return the boolean
   */
  public boolean hasTwitchtv() {
    return twitchtv;
  }


  /**
   * Has facebook.
   *
   * @return the boolean
   */
  public boolean hasFacebook() {
    return facebook;
  }


  /**
   * Has twitter.
   *
   * @return the boolean
   */
  public boolean hasTwitter() {
    return twitter;
  }


  /**
   * Has musixmatch.
   *
   * @return the boolean
   */
  public boolean hasMusixmatch() {
    return musixmatch;
  }


  /**
   * Has songkick.
   *
   * @return the boolean
   */
  public boolean hasSongkick() {
    return songkick;
  }
}
