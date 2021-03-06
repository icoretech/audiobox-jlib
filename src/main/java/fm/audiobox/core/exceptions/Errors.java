/*
 * Copyright 2009-2016 iCoreTech, Inc.
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
   * Sets error description.
   *
   * @param errorDescription the error description
   */
  public void setErrorDescription(String errorDescription) {
    this.error_description = errorDescription;
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
   * Sets error.
   *
   * @param error the error
   */
  public void setError(String error) {
    this.error = error;
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
    if ( getError() == null ) {
      return StringUtils.EMPTY;
    } else {
      return getError() + ": " + getErrorDescription();
    }
  }
}
