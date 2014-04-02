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

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import org.apache.commons.lang3.StringUtils;

/**
 * This class is used to parse error messages.
 */
public class Errors extends GenericJson {

  @Key
  private Errors errors;

  @Key
  private String error;

  @Key
  private String error_description;


  /**
   * Gets errors (generic type).
   *
   * @return the errors
   */
  public Errors getErrors() {
    return this;
  }


  /**
   * Gets error description.
   *
   * @return the error description
   */
  public String getErrorDescription() {
    return error_description;
  }


  /**
   * Gets error.
   *
   * @return the error
   */
  public String getError() {
    return error;
  }


  @Override
  public String toString() {
    if ( error == null ) {
      return StringUtils.EMPTY;
    } else {
      return error + ": " + error_description;
    }
  }
}
