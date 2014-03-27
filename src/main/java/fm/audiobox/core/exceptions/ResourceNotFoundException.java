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

package fm.audiobox.core.exceptions;

import com.google.api.client.http.HttpResponse;

/**
 * This exception is thrown when a 404 is returned by the service.
 * It generally means that the requested resource is not found or
 * it doesn't exists anymore (or yet).
 * <p/>
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
