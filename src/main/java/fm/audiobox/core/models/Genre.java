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

import java.util.List;

/**
 * Container class for a collection of {@link MediaFile} grouped by album.
 * <p>
 * It includes media file sorted by the media files' artist, album and position attributes.
 */
public class Genre extends Model {

  @Key
  private String token;


  @Key
  private String genre;

  @Key
  private List<? extends MediaFile> media_files;


  /**
   * Gets genre.
   *
   * @return the genre
   */
  public String getGenre() {
    return genre;
  }


  /**
   * Gets token.
   *
   * @return the token
   */
  public String getToken() {
    return token;
  }


  /**
   * Gets media files.
   *
   * @return the media files
   */
  public List<? extends MediaFile> getMediaFiles() {
    return media_files;
  }

}
