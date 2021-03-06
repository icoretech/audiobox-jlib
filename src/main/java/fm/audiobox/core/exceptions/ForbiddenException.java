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
 * This exception is thrown when the user tries to access
 * a resource he's not allowed to access, because of ownership
 * or account state.
 * <p>
 * Its main usage is to handle 402 and 403 {@link fm.audiobox.core.utils.HttpStatus} messages.
 */
public class ForbiddenException extends AudioBoxException {


  /**
   * Instantiates a new Forbidden exception starting from the response.
   *
   * @param response the response
   */
  public ForbiddenException(HttpResponse response) {
    super( response );
  }
}
