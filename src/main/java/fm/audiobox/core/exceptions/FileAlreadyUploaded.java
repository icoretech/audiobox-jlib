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
 * This exception is thrown while uploading if a media file already exists on AudioBox Cloud.
 * <p/>
 * Its main usage is to handle 409 {@link fm.audiobox.core.utils.HttpStatus} messages.
 */
public class FileAlreadyUploaded extends RemoteMessageException {

  /**
   * Instantiates a new FileAlreadyUploaded exception starting from the response.
   *
   * @param response the response
   */
  public FileAlreadyUploaded(HttpResponse response) {
    super( response );
  }
}
