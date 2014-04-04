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
import fm.audiobox.core.Client;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.utils.ModelUtil;

import java.io.IOException;

/**
 * Notifications are system messages that the AudioBox will send the user, for example when a Cloud Drive needs
 * to be re-authenticated or if some operation fails.
 * <p/>
 * There are four different notification levels: `error`, `success`, `info` and `warning`.
 */
public class Notification {

  private static final String PATH = "/api/v1/notifications/" + ModelUtil.ID_PLACEHOLDER + ".json";

  @Key
  private long id;

  @Key
  private String body;

  @Key
  private String level;

  @Key
  private String created_at;


  /**
   * Gets the notification id.
   *
   * @return the id of the notification
   */
  public long getId() {
    return id;
  }


  /**
   * Gets the content body of the notification message.
   *
   * @return the message body
   */
  public String getBody() {
    return body;
  }


  /**
   * Gets the severity of the message.
   *
   * @return the level (one of `error`, `success`, `info` and `warning`)
   */
  public String getLevel() {
    return level;
  }


  /**
   * Gets the UTC creation time in String format.
   *
   * @return the UTC creation time
   */
  public String getCreatedAt() {
    return created_at;
  }


  /**
   * Gets the generic remote resource path (ID interpolation is needed).
   *
   * @return the path String
   */
  public static String getPath() {
    return PATH;
  }


  /**
   * Performs a Notification deletion. If deletion cannot be accomplished an exception is thrown.
   *
   * @return true if deletion succeeds
   *
   * @throws AudioBoxException in case of 401, 402, 403, 404 or 422 response codes.
   */
  public Notification delete(Client client) throws IOException {
    client.doDELETE( ModelUtil.interpolate( Notification.getPath(), getId() ) );
    return this;
  }
}
