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
 * This exception is thrown when AudioBox servers are overloaded or same job submitted too fast.
 * The application should ensure to accept those errors and retry accordingly after few minutes.
 * <p/>
 * Its main usage is to handle 503 {@link fm.audiobox.core.utils.HttpStatus} messages.
 */
public class SystemOverloadedException extends RemoteMessageException {

  /**
   * Instantiates a new SystemOverloadedException exception.
   *
   * @param response the response
   */
  public SystemOverloadedException(HttpResponse response) {
    super( response );
  }
}
