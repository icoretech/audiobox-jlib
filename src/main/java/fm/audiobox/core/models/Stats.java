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
public class Stats {

  @Key
  private long dataServedThisMonth;

  @Key
  private long dataServedOverall;

  @Key
  private long cloudDataStoredOverall;

  @Key
  private long cloudDataStoredThisMonth;

  @Key
  private long localDataStoredOverall;

  @Key
  private long localDataStoredThisMonth;

  @Key
  private long dropboxDataStoredOverall;

  @Key
  private long dropboxDataStoredThisMonth;

  @Key
  private long gdriveDataStoredThisMonth;

  @Key
  private long gdriveDataStoredOverall;

  @Key
  private long skydriveDataStoredThisMonth;

  @Key
  private long skydriveDataStoredOverall;

  @Key
  private long boxDataStoredThisMonth;

  @Key
  private long boxDataStoredOverall;

  @Key
  private long soundcloudDataStoredThisMonth;

  @Key
  private long soundcloudDataStoredOverall;

  @Key
  private long ubuntuDataStoredThisMonth;

  @Key
  private long ubuntuDataStoredOverall;

  @Key
  private long youtubeDataStoredThisMonth;

  @Key
  private long youtubeDataStoredOverall;

  public long getDataServedThisMonth() {
    return dataServedThisMonth;
  }

  public long getDataServedOverall() {
    return dataServedOverall;
  }

  public long getCloudDataStoredOverall() {
    return cloudDataStoredOverall;
  }

  public long getCloudDataStoredThisMonth() {
    return cloudDataStoredThisMonth;
  }

  public long getLocalDataStoredOverall() {
    return localDataStoredOverall;
  }

  public long getLocalDataStoredThisMonth() {
    return localDataStoredThisMonth;
  }

  public long getDropboxDataStoredOverall() {
    return dropboxDataStoredOverall;
  }

  public long getDropboxDataStoredThisMonth() {
    return dropboxDataStoredThisMonth;
  }

  public long getGdriveDataStoredThisMonth() {
    return gdriveDataStoredThisMonth;
  }

  public long getGdriveDataStoredOverall() {
    return gdriveDataStoredOverall;
  }

  public long getSkydriveDataStoredThisMonth() {
    return skydriveDataStoredThisMonth;
  }

  public long getSkydriveDataStoredOverall() {
    return skydriveDataStoredOverall;
  }

  public long getBoxDataStoredThisMonth() {
    return boxDataStoredThisMonth;
  }

  public long getBoxDataStoredOverall() {
    return boxDataStoredOverall;
  }

  public long getSoundcloudDataStoredThisMonth() {
    return soundcloudDataStoredThisMonth;
  }

  public long getSoundcloudDataStoredOverall() {
    return soundcloudDataStoredOverall;
  }

  public long getUbuntuDataStoredThisMonth() {
    return ubuntuDataStoredThisMonth;
  }

  public long getUbuntuDataStoredOverall() {
    return ubuntuDataStoredOverall;
  }

  public long getYoutubeDataStoredThisMonth() {
    return youtubeDataStoredThisMonth;
  }

  public long getYoutubeDataStoredOverall() {
    return youtubeDataStoredOverall;
  }
}
