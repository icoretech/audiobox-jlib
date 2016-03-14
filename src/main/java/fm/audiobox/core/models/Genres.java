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

package fm.audiobox.core.models;

import com.google.api.client.util.Key;
import fm.audiobox.core.utils.ModelUtil;

import java.util.List;

/**
 * Genres wrapper model.
 * <p>
 * This is a simple wrapper model to handle genre-grouped media files.
 */
public class Genres extends Model {

  private static final String PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files/genres.json";

  @Key
  protected List<? extends Genre> genres;


  /**
   * Use this method to get the unique path of the genre.
   *
   * @param playlistToken the token of the playlist to query for the genre.
   *
   * @return the path of the genres of this playlist
   */
  public static String getPath(String playlistToken) {
    return ModelUtil.interpolate( PATH, playlistToken );
  }


  /**
   * Gets genres.
   *
   * @return the genres
   */
  public List<? extends Genre> getGenres() {
    return this.genres;
  }
}
