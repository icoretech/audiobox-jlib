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

package fm.audiobox.core.models;

import com.google.api.client.util.Key;

/**
 * This class is used as simple {@link fm.audiobox.core.models.Playlist} wrapper
 * for those JSON parser that do not support root elements.
 */
public class PlaylistWrapper extends Model {

  @Key
  private Playlist playlist;


  /**
   * Gets {@link fm.audiobox.core.models.Playlist}.
   *
   * @return the wrapped playlist
   */
  public Playlist getPlaylist() {
    return playlist;
  }
}
