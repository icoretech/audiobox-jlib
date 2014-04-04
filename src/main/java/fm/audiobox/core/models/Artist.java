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

import java.util.List;

/**
 * Represent a regrouped media files by artist.
 * <p/>
 * Also includes the same media file entities in other collection views, sorted by the media files' album and position attribute.
 */
public class Artist {

  @Key
  private String token;


  @Key
  private String artist;

  @Key
  private List<? extends MediaFile> media_files;


  /**
   * Gets token.
   *
   * @return the token
   */
  public String getToken() {
    return token;
  }


  /**
   * Gets artist.
   *
   * @return the artist
   */
  public String getArtist() {
    return artist;
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
