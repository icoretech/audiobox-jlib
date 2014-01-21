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

import com.google.api.client.http.HttpStatusCodes;

import java.util.Map;

/**
 * Created by keytwo on 20/01/14.
 */
public class ValidationException extends RuntimeException {

  private int statusCode = HttpStatusCodes.STATUS_CODE_UNAUTHORIZED;

  private Errors errors;


  /**
   * Instantiates a new Validation exception.
   *
   * @param errors the error
   */
  public ValidationException(Errors errors) {
    this(ValidationException.errorsToString(errors), errors);
  }


  /**
   * Instantiates a new Validation exception.
   *
   * @param message the message
   * @param errors the errors
   */
  public ValidationException(String message, Errors errors) {
    super(message);
    this.errors = errors;
  }


  /**
   * Instantiates a new Validation exception.
   *
   * @param errors the errors
   * @param statusCode the status code
   */
  public ValidationException(Errors errors, int statusCode) {
    this(errors);
    this.statusCode = statusCode;
  }


  @Override
  public String getMessage() {
    return super.getMessage();
  }


  /**
   * Gets errors.
   *
   * @return the errors
   */
  public Errors getErrors() {
    return errors;
  }


  /**
   * Errors to string.
   *
   * @param errors the errors
   * @return the string
   */
  public static String errorsToString(Errors errors) {
    String msg = "";
    for (Map.Entry<String, Object> error : errors.getUnknownKeys().entrySet()) {
      msg = error.getKey() + ": " + error.getValue();
      break;
    }

    return msg;
  }
}
