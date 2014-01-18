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
public class User {

  @Key
  private long id;

  @Key
  private String created_at;

  @Key
  private String updated_at;

  @Key
  private String real_name;

  @Key
  private String email;

  @Key
  private String auth_token;

  @Key
  private long media_files_count;

  @Key
  private long playlists_count;

  @Key
  private long total_play_count;

  @Key
  private String country;

  @Key
  private String time_zone;

  @Key
  private String accepted_extensions;

  @Key
  private String accepted_formats;

  @Key
  private String comet_channel;

  @Key
  private String subscription_state;

  @Key
  private String plan;

  @Key
  private String offline_playlist;

  @Key
  private Permissions permissions;

  @Key
  private ExternalTokens external_tokens;

  @Key
  private Stats stats;

  @Key
  private Preferences preferences;


  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }


  /**
   * Gets created at.
   *
   * @return the created at
   */
  public String getCreatedAt() {
    return created_at;
  }


  /**
   * Gets updated at.
   *
   * @return the updated at
   */
  public String getUpdatedAt() {
    return updated_at;
  }


  /**
   * Gets real name.
   *
   * @return the real name
   */
  public String getRealName() {
    return real_name;
  }


  /**
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }


  /**
   * Gets auth token.
   *
   * @return the auth token
   */
  public String getAuthToken() {
    return auth_token;
  }


  /**
   * Gets media files count.
   *
   * @return the media files count
   */
  public long getMediaFilesCount() {
    return media_files_count;
  }


  /**
   * Gets playlists count.
   *
   * @return the playlists count
   */
  public long getPlaylistsCount() {
    return playlists_count;
  }


  /**
   * Gets total play count.
   *
   * @return the total play count
   */
  public long getTotalPlayCount() {
    return total_play_count;
  }


  /**
   * Gets country.
   *
   * @return the country
   */
  public String getCountry() {
    return country;
  }


  /**
   * Gets time zone.
   *
   * @return the time zone
   */
  public String getTimeZone() {
    return time_zone;
  }


  /**
   * Gets accepted extensions.
   *
   * @return the accepted extensions
   */
  public String getAcceptedExtensions() {
    return accepted_extensions;
  }


  /**
   * Gets accepted formats.
   *
   * @return the accepted formats
   */
  public String getAcceptedFormats() {
    return accepted_formats;
  }


  /**
   * Gets comet channel.
   *
   * @return the comet channel
   */
  public String getCometChannel() {
    return comet_channel;
  }


  /**
   * Gets subscription state.
   *
   * @return the subscription state
   */
  public String getSubscriptionState() {
    return subscription_state;
  }


  /**
   * Gets plan.
   *
   * @return the plan
   */
  public String getPlan() {
    return plan;
  }


  /**
   * Gets offline playlist.
   *
   * @return the offline playlist
   */
  public String getOfflinePlaylist() {
    return offline_playlist;
  }


  /**
   * Gets permissions.
   *
   * @return the permissions
   */
  public Permissions getPermissions() {
    return permissions;
  }


  /**
   * Gets external tokens.
   *
   * @return the external tokens
   */
  public ExternalTokens getExternalTokens() {
    return external_tokens;
  }


  /**
   * Gets stats.
   *
   * @return the stats
   */
  public Stats getStats() {
    return stats;
  }


  /**
   * Gets preferences.
   *
   * @return the preferences
   */
  public Preferences getPreferences() {
    return preferences;
  }

}
