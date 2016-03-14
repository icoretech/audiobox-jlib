/*
 * Copyright 2009-2016 iCoreTech, Inc.
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

package fm.audiobox.core.exceptions;

import com.google.api.client.http.HttpResponse;
import fm.audiobox.core.utils.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * This exception is thrown whenever an error occurs while trying to
 * {@link fm.audiobox.core.models.Playlist#sync(fm.audiobox.core.AudioBoxClient) sync a playlist} with the remote service.
 * <p>
 * Errors in this case indicates a state with AudioBox account:
 * <ul>
 * <li>Subscription not in a good standing.</li>
 * <li>Account link to the remote service expired or not working for a remote problem.</li>
 * <li>The playlist was not found.</li>
 * <li>The playlist is not suitable for syncing.</li>
 * </ul>
 */
public class SyncException extends AudioBoxException {

  private static final Map<Integer, String> MESSAGES = new HashMap<>( 4 );


  static {
    MESSAGES.put( HttpStatus.SC_PAYMENT_REQUIRED, "Action requires a valid subscription." );
    MESSAGES.put( HttpStatus.SC_FORBIDDEN, "Playlist requires a valid account link to the remote service." );
    MESSAGES.put( HttpStatus.SC_NOT_FOUND, "Playlist not found." );
    MESSAGES.put( HttpStatus.SC_UNPROCESSABLE_ENTITY, "Playlist not suitable for syncing." );
  }


  /**
   * Instantiates a new Sync Exception.
   *
   * @param response the response
   */
  public SyncException(HttpResponse response) {
    super( response );
  }


  /**
   * Instantiates a new Sync exception.
   *
   * @param statusCode the status code
   */
  public SyncException(int statusCode) {
    super( MESSAGES.get( statusCode ) );
    this.statusCode = statusCode;
  }


  @Override
  public String getMessage() {
    return MESSAGES.get( getErrorCode() );
  }
}