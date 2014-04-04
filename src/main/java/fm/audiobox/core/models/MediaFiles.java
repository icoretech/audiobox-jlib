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


import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import fm.audiobox.core.Client;

import java.io.IOException;
import java.util.List;


/**
 * Used to get a list of all media files in a specific user's playlist.
 */
public class MediaFiles {


  /**
   * The constant PARAM_SET.
   */
  public static final String PARAM_SET = "set";

  /**
   * The constant PARAM_SINCE.
   */
  public static final String PARAM_SINCE = "since";

  /**
   * The constant PARAM_TOKENS.
   */
  public static final String PARAM_TOKENS = "tokens";

  /**
   * PUT
   */
  private static final String UPDATE_MEDIA_FILES_PATH = "/api/v1/media_files/multiupdate.json";

  /**
   * DELETE
   */
  private static final String DESTROY_MEDIA_FILES_PATH = "/api/v1/media_files/multidestroy.json";

  /**
   * The parsed media files list
   */
  @Key("media_files")
  protected List<? extends MediaFile> media_files;


  /**
   * Gets playlists.
   *
   * @return the playlists collection.
   */
  public List<? extends MediaFile> getMediaFiles() {
    return media_files;
  }


  /**
   * Destroy all media files identified by the tokens in the list.
   * <p/>
   * NOTE: this action is irreversible.
   *
   * @param client the client
   * @param tokens the tokens
   *
   * @return the boolean
   *
   * @throws IOException the iO exception
   */
  public static final boolean destroyAll(Client client, List<String> tokens) throws IOException {

    GenericData d = new GenericData();
    for ( String token : tokens ) {
      d.put( PARAM_TOKENS, token );
    }
    JsonHttpContent data = new JsonHttpContent( client.getConf().getJsonFactory(), d );
    client.doDELETE( DESTROY_MEDIA_FILES_PATH, data, null );

    return true;
  }

}
