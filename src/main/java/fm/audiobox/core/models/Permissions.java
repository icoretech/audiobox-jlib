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

  public boolean hasPlayer() {
    return player;
  }

  public boolean hasLocal() {
    return local;
  }

  public boolean hasCloud() {
    return cloud;
  }

  public boolean hasDropbox() {
    return dropbox;
  }

  public boolean hasGdrive() {
    return gdrive;
  }

  public boolean hasSkydrive() {
    return skydrive;
  }

  public boolean hasUbuntu() {
    return ubuntu;
  }

  public boolean hasSoundcloud() {
    return soundcloud;
  }

  public boolean hasYoutube() {
    return youtube;
  }

  public boolean hasBox() {
    return box;
  }

  public boolean hasLastfm() {
    return lastfm;
  }

  public boolean hasTwitchtv() {
    return twitchtv;
  }

  public boolean hasFacebook() {
    return facebook;
  }

  public boolean hasTwitter() {
    return twitter;
  }

  public boolean hasMusixmatch() {
    return musixmatch;
  }

  public boolean hasSongkick() {
    return songkick;
  }
}
