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

import java.util.List;

/**
 * Returns a paginated collection of user's {@link Notification Notifications}.
 * <p/>
 * Optional request parameters:
 * <dl>
 * <dt>page</dt>
 * <dd>Number of the page. Default, non-changeable limit is 30 notifications per page.</dd>
 * </dl>
 */
public class Notifications {

  private static final String PATH = "/api/v1/notifications.json";

  @Key
  private long total;

  @Key
  private long page;

  @Key( "notifications" )
  private List<Notification> notifications;


  /**
   * Gets the generic remote resource collection path.
   *
   * @return the path
   */
  public static String getPath() {
    return PATH;
  }


  /**
   * Gets total notification numbers.
   *
   * @return the total notification numbers
   */
  public long getTotal() {
    return total;
  }


  /**
   * Gets the umber of the page. Default, non-changeable limit is 30 notifications per page.
   *
   * @return the actual page
   */
  public long getPage() {
    return page;
  }


  /**
   * Gets notifications on this page (see {@link Notifications#getPage()}).
   *
   * @return the notifications
   */
  public List<Notification> getNotifications() {
    return notifications;
  }
}
