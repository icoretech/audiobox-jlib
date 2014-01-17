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


  public boolean isDropboxEnabled() {
    return dropbox;
  }

  public boolean isGdriveEnabled() {
    return gdrive;
  }

  public boolean isSkydriveEnabled() {
    return skydrive;
  }

  public boolean isUbuntuEnabled() {
    return ubuntu;
  }

  public boolean isSoundcloudEnabled() {
    return soundcloud;
  }

  public boolean isYoutubeEnabled() {
    return youtube;
  }

  public boolean isBoxEnabled() {
    return box;
  }

  public boolean isLastfmEnabled() {
    return lastfm;
  }

  public boolean isTwitchtvEnabled() {
    return twitchtv;
  }

  public boolean isFacebookEnabled() {
    return facebook;
  }

  public boolean isTwitterEnabled() {
    return twitter;
  }
}
