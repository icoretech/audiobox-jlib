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

/**
 * This exception is thrown when AudioBox servers are overloaded or same job submitted too fast.
 * The application should ensure to accept those errors and retry accordingly after few minutes.
 * <p>
 * Its main usage is to handle 503 {@link fm.audiobox.core.utils.HttpStatus} messages.
 */
public class SystemOverloadedException extends RemoteMessageException {

  private String retryAfter;


  /**
   * Instantiates a new SystemOverloadedException exception.
   *
   * @param response the response
   */
  public SystemOverloadedException(HttpResponse response) {
    super( response );
    if ( response != null ) {
      this.retryAfter = response.getHeaders().getRetryAfter();
    }
  }


  /**
   * Gets the number in seconds in form of a string of the period to wait in order to try the next request.
   *
   * @return the retry after time in seconds in form of a string
   */
  public String getRetryAfter() {
    return this.retryAfter;
  }


  /**
   * Gets the number in second of the period to wait in order to try the next request.
   *
   * @return the retry after time in seconds
   */
  public int getRetryAfterInSeconds() {
    return getRetryAfter() != null ? Integer.parseInt( this.retryAfter, 10 ) : -1;
  }
}
