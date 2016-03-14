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

package fm.audiobox.core.exceptions;

import com.google.api.client.http.HttpResponse;

/**
 * This exception is thrown when a 404 is returned by the service.
 * It generally means that the requested resource is not found or
 * it doesn't exists anymore (or yet).
 * <p>
 * Its main usage is to handle 404 {@link fm.audiobox.core.utils.HttpStatus} messages.
 */
public class ResourceNotFoundException extends AudioBoxException {


  /**
   * Instantiates a new Resource not found exception.
   *
   * @param response the response
   */
  public ResourceNotFoundException(HttpResponse response) {
    super( response );
  }

}
