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


  public long getId() {
    return id;
  }


  public String getCreatedAt() {
    return created_at;
  }


  public String getUpdatedAt() {
    return updated_at;
  }


  public String getRealName() {
    return real_name;
  }


  public String getEmail() {
    return email;
  }


  public String getAuthToken() {
    return auth_token;
  }


  public long getMediaFilesCount() {
    return media_files_count;
  }


  public long getPlaylistsCount() {
    return playlists_count;
  }


  public long getTotalPlayCount() {
    return total_play_count;
  }


  public String getCountry() {
    return country;
  }


  public String getTimeZone() {
    return time_zone;
  }


  public String getAcceptedExtensions() {
    return accepted_extensions;
  }


  public String getAcceptedFormats() {
    return accepted_formats;
  }


  public String getCometChannel() {
    return comet_channel;
  }


  public String getSubscriptionState() {
    return subscription_state;
  }


  public String getPlan() {
    return plan;
  }


  public String getOfflinePlaylist() {
    return offline_playlist;
  }


  public Permissions getPermissions() {
    return permissions;
  }


  public ExternalTokens getExternalTokens() {
    return external_tokens;
  }


  public Stats getStats() {
    return stats;
  }


  public Preferences getPreferences() {
    return preferences;
  }

}
