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

package fm.audiobox.core.net;

/**
 * This interface is intended to be used together with the {@link fm.audiobox.core.net.Download}
 * and or {@link fm.audiobox.core.utils.MediaContent} objects in order to monitor
 * upload/download progress.
 */
public interface NetworkProgressListener {

  /**
   * The implementation of this method will receive the total amount and the current
   * bytes being pulled/pushed from/to the socket stream.
   *
   * @param total must indicate the total
   * @param current must indicate the current state
   */
  public void onProgressUpdate(long total, long current);

}
