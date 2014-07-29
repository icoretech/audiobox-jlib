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

package fm.audiobox.core.exceptions;

/**
 * An exception handler is used to perform further operations when an
 * {@link fm.audiobox.core.exceptions.AudioBoxException} is thrown.
 * <p>
 * You can set your ExceptionHandler via {@link fm.audiobox.core.config.Configuration#setExceptionHandler(ExceptionHandler)}.
 * </p>
 * <p>
 * Classes that implements an ExceptionHandler must respond to <code>onException</code> method that passes an
 * AudioBoxException as argument.
 * </p>
 * <p>
 * If the return value of <code>onException</code> is <code>true</code> then the exception is considered "handled" and
 * no further actions will be taken. If <code>false</code> is returned then the exception is thrown to upper stack.
 * </p>
 */
public interface ExceptionHandler {

  /**
   * This method implementation must return <code>true</code> if the given {@link fm.audiobox.core.exceptions.AudioBoxException}
   * was handled, <code>false</code> otherwise.
   * <p>
   * If the {@link fm.audiobox.core.exceptions.AudioBoxException} was not handled then the exception is thrown to
   * the next level.
   * </p>
   *
   * @return true if the exception is handled, false otherwise.
   */
  public boolean onException(AudioBoxException e);

}
