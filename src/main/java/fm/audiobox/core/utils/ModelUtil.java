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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Handful util class for various models tasks.
 */
public class ModelUtil {

  public static final String TOKEN_PLACEHOLDER = ":token:";

  public static final String ID_PLACEHOLDER = ":id:";

  public static final String AUDIOBOX_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


  /**
   * Performs models urls interpolation with the {@link fm.audiobox.core.utils.ModelUtil#TOKEN_PLACEHOLDER}.
   *
   * @param url   the url
   * @param token the token
   *
   * @return the computed url interpolation
   */
  public static String interpolate(String url, String token) {
    return StringUtils.replace( url, TOKEN_PLACEHOLDER, token );
  }


  /**
   * Performs models urls interpolation with the {@link fm.audiobox.core.utils.ModelUtil#ID_PLACEHOLDER}.
   *
   * @param url the url
   * @param id  the id
   *
   * @return the computed url interpolation
   */
  public static String interpolate(String url, long id) {
    return StringUtils.replace( url, ID_PLACEHOLDER, String.valueOf( id ) );
  }


  /**
   * Given a string representing an UTC date provided by AudioBox server,
   * this method returns a unix timestamp (always in UTC).
   *
   * @param simpleDateFormat the simple date format
   * @param audioboxDate     the string date provided by audiobox server.
   *
   * @return unix timestamp
   *
   * @throws ParseException the parse exception
   */
  public static long toUnixTime(SimpleDateFormat simpleDateFormat, String audioboxDate) throws ParseException {

    simpleDateFormat.applyPattern( AUDIOBOX_DATE_FORMAT );
    simpleDateFormat.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
    Date date = simpleDateFormat.parse( audioboxDate );
    return date.getTime();
  }


}
