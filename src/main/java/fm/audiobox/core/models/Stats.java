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

package fm.audiobox.core.models;

import com.google.api.client.util.Key;


/**
 * Bytes long values showing the storage used for each supported
 * storage service and the amount of data that has been streamed.
 */
public class Stats extends Model {

  @Key
  private long total_play_count;

  @Key
  private long data_served_overall;

  @Key
  private long data_served_this_month;

  @Key
  private long box_data_stored_overall;

  @Key
  private long cloud_data_stored_overall;

  @Key
  private long local_data_stored_overall;

  @Key
  private long box_data_stored_this_month;

  @Key
  private long gdrive_data_stored_overall;

  @Key
  private long ubuntu_data_stored_overall;

  @Key
  private long dropbox_data_stored_overall;

  @Key
  private long youtube_data_stored_overall;

  @Key
  private long cloud_data_stored_this_month;

  @Key
  private long local_data_stored_this_month;

  @Key
  private long skydrive_data_stored_overall;

  @Key
  private long gdrive_data_stored_this_month;

  @Key
  private long ubuntu_data_stored_this_month;

  @Key
  private long dropbox_data_stored_this_month;

  @Key
  private long soundcloud_data_stored_overall;

  @Key
  private long youtube_data_stored_this_month;

  @Key
  private long skydrive_data_stored_this_month;

  @Key
  private long soundcloud_data_stored_this_month;


  /**
   * Gets total play count.
   *
   * @return the total play count in bytes
   */
  public long getTotalPlayCount() {
    return total_play_count;
  }


  /**
   * Gets data served overall.
   *
   * @return the data served overall in bytes
   */
  public long getDataServedOverall() {
    return data_served_overall;
  }


  /**
   * Gets data served this month.
   *
   * @return the data served this month in bytes
   */
  public long getDataServedThisMonth() {
    return data_served_this_month;
  }


  /**
   * Gets box data stored overall.
   *
   * @return the box data stored overall in bytes
   */
  public long getBoxDataStoredOverall() {
    return box_data_stored_overall;
  }


  /**
   * Gets cloud data stored overall.
   *
   * @return the cloud data stored overall in bytes
   */
  public long getCloudDataStoredOverall() {
    return cloud_data_stored_overall;
  }


  /**
   * Gets local data stored overall.
   *
   * @return the local data stored overall in bytes
   */
  public long getLocalDataStoredOverall() {
    return local_data_stored_overall;
  }


  /**
   * Gets box data stored this month.
   *
   * @return the box data stored this month in bytes
   */
  public long getBoxDataStoredThisMonth() {
    return box_data_stored_this_month;
  }


  /**
   * Gets gdrive data stored overall.
   *
   * @return the gdrive data stored overall in bytes
   */
  public long getGdriveDataStoredOverall() {
    return gdrive_data_stored_overall;
  }


  /**
   * Gets ubuntu data stored overall.
   *
   * @return the ubuntu data stored overall in bytes
   */
  public long getUbuntuDataStoredOverall() {
    return ubuntu_data_stored_overall;
  }


  /**
   * Gets dropbox data stored overall.
   *
   * @return the dropbox data stored overall in bytes
   */
  public long getDropboxDataStoredOverall() {
    return dropbox_data_stored_overall;
  }


  /**
   * Gets youtube data stored overall.
   *
   * @return the youtube data stored overall in bytes
   */
  public long getYoutubeDataStoredOverall() {
    return youtube_data_stored_overall;
  }


  /**
   * Gets cloud data stored this month.
   *
   * @return the cloud data stored this month in bytes
   */
  public long getCloudDataStoredThisMonth() {
    return cloud_data_stored_this_month;
  }


  /**
   * Gets local data stored this month.
   *
   * @return the local data stored this month in bytes
   */
  public long getLocalDataStoredThisMonth() {
    return local_data_stored_this_month;
  }


  /**
   * Gets skydrive data stored overall.
   *
   * @return the skydrive data stored overall in bytes
   */
  public long getSkydriveDataStoredOverall() {
    return skydrive_data_stored_overall;
  }


  /**
   * Gets gdrive data stored this month.
   *
   * @return the gdrive data stored this month in bytes
   */
  public long getGdriveDataStoredThisMonth() {
    return gdrive_data_stored_this_month;
  }


  /**
   * Gets ubuntu data stored this month.
   *
   * @return the ubuntu data stored this month in bytes
   */
  public long getUbuntuDataStoredThisMonth() {
    return ubuntu_data_stored_this_month;
  }


  /**
   * Gets dropbox data stored this month.
   *
   * @return the dropbox data stored this month in bytes
   */
  public long getDropboxDataStoredThisMonth() {
    return dropbox_data_stored_this_month;
  }


  /**
   * Gets soundcloud data stored overall.
   *
   * @return the soundcloud data stored overall in bytes
   */
  public long getSoundcloudDataStoredOverall() {
    return soundcloud_data_stored_overall;
  }


  /**
   * Gets youtube data stored this month.
   *
   * @return the youtube data stored this month in bytes
   */
  public long getYoutubeDataStoredThisMonth() {
    return youtube_data_stored_this_month;
  }


  /**
   * Gets skydrive data stored this month.
   *
   * @return the skydrive data stored this month in bytes
   */
  public long getSkydriveDataStoredThisMonth() {
    return skydrive_data_stored_this_month;
  }


  /**
   * Gets soundcloud data stored this month.
   *
   * @return the soundcloud data stored this month in bytes
   */
  public long getSoundcloudDataStoredThisMonth() {
    return soundcloud_data_stored_this_month;
  }



}
