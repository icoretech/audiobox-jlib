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

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.util.Key;
import fm.audiobox.core.Client;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.utils.HttpStatus;

import java.io.IOException;

/**
 * A User can interact with his own files in different ways, depending on the remote storage in play.
 * <p/>
 * However AudioBox is built with homogeneity in mind and therefore you will end up
 * calling the same methods for different, powerful actions.
 * <p/>
 * For the most part the returned attributes are straightforward, such as media_files_count,
 * however there are special attributes to explain:
 * <p/>
 * <dl>
 * <dt>auth_token:</dt>
 * <dd>
 * The current authentication token for the user. This attribute can change and might be rotated at any time.
 * </dd>
 * <dt>permissions: {...}</dt>
 * <dd>
 * A user can subscribe and manage multiple remote storage services. This hash defines the boolean permissions the user has access to, depending on subscription state if it's a paid feature. In case the User has been created through a Partner it will inherit permissions depending on the Partner requested features.
 * Such attributes are automatically set by the system and cannot be changed manually.
 * </dd>
 * <dt>external_tokens: {...}</dt>
 * <dd>
 * For each of the external services the boolean value indicates if we have stored a OAuth token for the user.
 * Such attributes are automatically set by the system and cannot be changed manually.
 * </dd>
 * <dt>stats: {...}</dt>
 * <dd>
 * Bytes long values showing the storage used for each supported storage service and the amount of data that has been streamed.
 * </dd>
 * <dt>subscription_state:</dt>
 * <dd>
 * String identifying the paid subscription states for this user. Some features cannot be accessed if the subscription is not valid. We suggest to not use logic in your own application against this attribute, but rather query the permissions: {} data to have more fine-grained control over the available actions.
 * Possible values are: active, trialing, past_due, canceled and unpaid.
 * </dd>
 * <dt>plan:</dt>
 * <dd>
 * The plan name the user is subscribed to, if any.
 * </dd>
 * <dt>comet_channel:</dt>
 * <dd>
 * The unique push engine channel for this user. AudioBox employ push notifications, by making the application subscribe to this channel name it will be able to receive actions to perform in the user interface.
 * Using push messages is a great way to keep all the applications in sync when an action is performed, such as media add. Further documentation on how to connect to the push server will be provided soon.
 * </dd>
 * <dt>preferences: {...}</dt>
 * <dd>
 * General preferences of the user, mostly used in the Cloud Web Player.
 * </dd>
 * </dl>
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
   * Gets the user creation time (in form of an UTC String).
   *
   * @return the UTC String of the user creation time
   */
  public String getCreatedAt() {
    return created_at;
  }


  /**
   * Gets the last time (in form of an UTC String) this account have been changed.
   * <br/>
   * NOTE: Since client timezone may differ significantly you are strongly invited
   * to always trust and use this datetime in case you are building a sync tool.
   *
   * @return the UTC String of the last update on this account
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
   * Gets the current authentication token for the user.
   * This attribute can change and might be rotated at any time.
   *
   * @return the auth token
   */
  public String getAuthToken() {
    return auth_token;
  }


  /**
   * Gets total media files count.
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
   * Gets the unique push engine channel for this user. AudioBox employ push notifications, by making the application
   * subscribe to this channel name it will be able to receive actions to perform in the user interface.
   * <p/>
   * Using push messages is a great way to keep all the applications in sync when an action is performed, such as media
   * add. Further documentation on how to connect to the push server will be provided soon.
   *
   * @return the comet channel
   */
  public String getCometChannel() {
    return comet_channel;
  }


  /**
   * Gets the string identifying the paid subscription states for this user. Some features cannot be accessed if the
   * subscription is not valid. We suggest to not use logic in your own application against this attribute, but rather
   * query the permissions: {} data to have more fine-grained control over the available actions.
   * <p/>
   * Possible values are: active, trialing, past_due, canceled and unpaid.
   *
   * @return the subscription state
   */
  public String getSubscriptionState() {
    return subscription_state;
  }


  /**
   * Gets the plan name the user is subscribed to, if any..
   *
   * @return the plan
   */
  public String getPlan() {
    return plan;
  }


  /**
   * Gets offline playlist token.
   *
   * @return the offline playlist token
   */
  public String getOfflinePlaylist() {
    return offline_playlist;
  }


  /**
   * Gets the hash that defines the boolean permissions the user has access to, depending on subscription state if it's
   * a paid feature.
   * <p/>
   * In case the User has been created through a Partner it will inherit permissions depending on the Partner requested
   * features.
   * <p/>
   * Such attributes are automatically set by the system and cannot be changed manually.
   *
   * @return the user's {@link fm.audiobox.core.models.Permissions}
   */
  public Permissions getPermissions() {
    return permissions;
  }


  /**
   * Gets the user's {@link fm.audiobox.core.models.ExternalTokens} that indicates if AudioBox have stored an OAuth
   * token for the user.
   * <p/>
   * Such attributes are automatically set by the system and cannot be changed manually.
   *
   * @return the external tokens
   */
  public ExternalTokens getExternalTokens() {
    return external_tokens;
  }


  /**
   * Gets the user's {@link fm.audiobox.core.models.Stats}
   *
   * @return the stats
   */
  public Stats getStats() {
    return stats;
  }


  /**
   * Gets the user's {@link fm.audiobox.core.models.Preferences}
   *
   * @return the preferences
   */
  public Preferences getPreferences() {
    return preferences;
  }


  /**
   * Saves user preferences
   *
   * @param client the client
   *
   * @return true if operation succeed throws an exception if something goes wrong
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException in case of 402, 403, 404 or 422 response codes.
   */
  public boolean savePreferences(Client client) throws IOException {
    HttpResponse rsp = client.doPUT( Preferences.PATH, new JsonHttpContent( client.getConf().getJsonFactory(), new UserWrapper(this)) );
    return rsp.getStatusCode() == HttpStatus.SC_NO_CONTENT;
  }

}
