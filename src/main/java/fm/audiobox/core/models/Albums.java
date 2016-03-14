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
import fm.audiobox.core.utils.ModelUtil;

import java.util.List;

/**
 * Albums wrapper model.
 * <p>
 * This is a simple wrapper model to handle album-grouped media files.
 */
public class Albums extends Model {

  private static final String PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + "/media_files/albums.json";

  @Key
  protected List<? extends Album> albums;


  /**
   * Use this method to get the unique path of the album.
   *
   * @param playlistToken the token of the playlist to query for the album.
   *
   * @return the path of the albums of this playlist
   */
  public static String getPath(String playlistToken) {
    return ModelUtil.interpolate( PATH, playlistToken );
  }


  /**
   * Gets albums.
   *
   * @return the albums
   */
  public List<? extends Album> getAlbums() {
    return this.albums;
  }

}
