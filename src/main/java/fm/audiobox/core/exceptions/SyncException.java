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
import fm.audiobox.core.utils.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * This exception is thrown whenever an error occurs while trying to sync with the remote service.
 * <p/>
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
    super(MESSAGES.get( statusCode ));
  }


  @Override
  public String getMessage() {
    return MESSAGES.get( getErrorCode() );
  }
}