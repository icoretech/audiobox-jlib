/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 * This file is part of the audiobox-jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.core.models;

import com.google.api.client.util.Key;

/**
 * Notifications are system messages that the AudioBox will send the user, for example when a Cloud Drive needs
 * to be re-authenticated or if some operation fails.
 * <p/>
 * There are four different notification levels: `error`, `success`, `info` and `warning`.
 */
public class Notification {

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
  public String getCreated_at() {
    return created_at;
  }

}
