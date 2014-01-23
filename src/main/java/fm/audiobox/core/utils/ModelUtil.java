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

package fm.audiobox.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Handful util class for various models tasks.
 */
public class ModelUtil {

  public static final String TOKEN_PLACEHOLDER = ":token:";


  /**
   * Performs models urls interpolation with the TOKEN_PLACEHOLDER.
   *
   * @param url   the url
   * @param token the token
   *
   * @return the computed url interpolation
   */
  public static String interpolate(String url, String token) {
    return StringUtils.replace( url, TOKEN_PLACEHOLDER, token );
  }

}
