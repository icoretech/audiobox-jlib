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
 * This class is used as simple {@link sun.nio.fs.UnixUserPrincipals.User} wrapper
 * for those JSON parser that do not support root elements.
 */
public class UserWrapper {

  private static final String PATH = "/api/v1/user.json";

  @Key
  private User user;


  /**
   * Gets path.
   *
   * @return the path
   */
  public static String getPath() {
    return PATH;
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
