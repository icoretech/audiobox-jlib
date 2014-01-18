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
 * Created by keytwo on 18/01/14.
 */
public class UserWrapper {

  private static String path = "/api/v1/user.json";

  @Key
  private User user;


  /**
   * Gets path.
   *
   * @return the path
   */
  public static String getPath() {
    return path;
  }


  /**
   * Gets user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }


}
