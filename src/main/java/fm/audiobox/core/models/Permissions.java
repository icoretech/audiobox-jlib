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
 * A user can subscribe and manage multiple remote storage services.
 * <p/>
 * This hash defines the boolean permissions the user has access to, depending on
 * subscription state if it's a paid feature.
 * <p/>
 * In case the User has been created through a Partner it will inherit permissions
 * depending on the Partner requested features.
 * <p/>
 * Such attributes are automatically set by the system and cannot be changed manually.
 */
public class Permissions extends Model {

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
  private boolean lyrics;

  @Key
  private boolean songkick;


  /**
   * Checks if user can access the Cloud Web Player.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasPlayer() {
    return player;
  }


  /**
   * Checks if user can access the AudioBox Desktop feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasLocal() {
    return local;
  }


  /**
   * Checks if user can access the AudioBox Cloud feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasCloud() {
    return cloud;
  }


  /**
   * Checks if user can access the Dropbox feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasDropbox() {
    return dropbox;
  }


  /**
   * Checks if user can access the Google Drive feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasGdrive() {
    return gdrive;
  }


  /**
   * Checks if user can access the SkyDrive feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasSkydrive() {
    return skydrive;
  }


  /**
   * Checks if user can access the Ubuntu One Music feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasUbuntu() {
    return ubuntu;
  }


  /**
   * Checks if user can access the Soundcloud feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasSoundcloud() {
    return soundcloud;
  }


  /**
   * Checks if user can access the YouTube feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasYoutube() {
    return youtube;
  }


  /**
   * Checks if user can access the Box feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasBox() {
    return box;
  }


  /**
   * Checks if user can access the Last.fm feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasLastfm() {
    return lastfm;
  }


  /**
   * Checks if user can access the Twitch.tv feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasTwitchtv() {
    return twitchtv;
  }


  /**
   * Checks if user can access the Facebook feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasFacebook() {
    return facebook;
  }


  /**
   * Checks if user can access the Twitter feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasTwitter() {
    return twitter;
  }


  /**
   * Checks if user can access the lyrics feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasLyrics() {
    return lyrics;
  }


  /**
   * Checks if user can access the Songkick feature.
   *
   * @return the true if access is granted, false otherwise.
   */
  public boolean hasSongkick() {
    return songkick;
  }
}
