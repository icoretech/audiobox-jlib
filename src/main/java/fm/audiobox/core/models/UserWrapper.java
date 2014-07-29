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
 * This class is used as simple {@link User} wrapper
 * for those JSON parser that do not support root elements.
 */
public class UserWrapper extends Model {

  private static final String PATH = "/api/v1/user.json";

  @Key
  private User user;


  /**
   * Instantiates a new User wrapper.
   */
  @SuppressWarnings("unused")
  public UserWrapper() {
  }


  /**
   * Instantiates a new User wrapper.
   *
   * @param user the user to wrap
   */
  public UserWrapper(User user) {
    this.user = user;
  }


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
