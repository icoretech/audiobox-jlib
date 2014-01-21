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

import fm.audiobox.core.utils.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by keytwo on 21/01/14.
 */
public class SyncException extends RuntimeException {

  private static final Map<Integer, String> MESSAGES = new HashMap<>( 4 );

  private int statusCode = 0;


  static {
    MESSAGES.put( HttpStatus.SC_PAYMENT_REQUIRED, "Action requires a valid subscription." );
    MESSAGES.put( HttpStatus.SC_FORBIDDEN, "Playlist requires a valid account link to the remote service." );
    MESSAGES.put( HttpStatus.SC_NOT_FOUND, "Playlist not found." );
    MESSAGES.put( HttpStatus.SC_UNPROCESSABLE_ENTITY, "Playlist not suitable for syncing." );
  }


  /**
   * Instantiates a new Sync Exception.
   *
   * @param statusCode the status code
   */
  public SyncException(int statusCode) {
    super( MESSAGES.get( statusCode ) );
    this.statusCode = statusCode;
  }


  /**
   * Gets error code.
   *
   * @return the error code
   */
  public int getErrorCode() {
    return statusCode;
  }
}