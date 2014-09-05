/*
 * Copyright 2009-2014 iCoreTech, Inc.
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

package fm.audiobox.core.config;

/**
 * This exception is thrown when there is a configuration problem.
 * This can arise if there are configuration problems with the
 * client, or if configuration information required to access
 * AudioBox is malformed or missing.
 */
public class ConfigurationException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new instance of ConfigurationException using an
   * explanation. All other fields default to null.
   *
   * @param explanation A possibly null string containing additional detail about this exception.
   *
   * @see java.lang.Throwable#getMessage
   */
  public ConfigurationException(String explanation) {
    super( explanation );
  }

}
