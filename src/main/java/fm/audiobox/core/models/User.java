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

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by keytwo on 17/01/14.
 */
public class User extends GenericJson {

  @Key
  private long id;

  @Key
  private String createdAt;

  @Key
  private String updatedAt;

  @Key
  private String realName;

  @Key
  private String email;

  @Key
  private String authToken;

  @Key
  private long mediaFilesCount;

  @Key
  private long playlistsCount;

  @Key
  private long totalPlayCount;

  @Key
  private String country;

  @Key
  private String timeZone;

  @Key
  private String acceptedExtensions;

  @Key
  private String acceptedFormats;

  @Key
  private String cometChannel;

  @Key
  private String subscriptionState;

  @Key
  private String plan;

  @Key
  private String offlinePlaylist;

  @Key
  private Permissions permissions;

  @Key
  private ExternalTokens externalTokens;

  @Key
  private Stats stats;

  @Key
  private Preferences preferences;


  public long getId() {
    return id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public String getRealName() {
    return realName;
  }

  public String getEmail() {
    return email;
  }

  public String getAuthToken() {
    return authToken;
  }

  public long getMediaFilesCount() {
    return mediaFilesCount;
  }

  public long getPlaylistsCount() {
    return playlistsCount;
  }

  public long getTotalPlayCount() {
    return totalPlayCount;
  }

  public String getCountry() {
    return country;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public String getAcceptedExtensions() {
    return acceptedExtensions;
  }

  public String getAcceptedFormats() {
    return acceptedFormats;
  }

  public String getCometChannel() {
    return cometChannel;
  }

  public String getSubscriptionState() {
    return subscriptionState;
  }

  public String getPlan() {
    return plan;
  }

  public String getOfflinePlaylist() {
    return offlinePlaylist;
  }

  public Permissions getPermissions() {
    return permissions;
  }

  public ExternalTokens getExternalTokens() {
    return externalTokens;
  }

  public Stats getStats() {
    return stats;
  }

  public Preferences getPreferences() {
    return preferences;
  }

}
