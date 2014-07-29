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


import com.google.api.client.json.CustomizeJsonParser;
import com.google.api.client.util.Key;
import fm.audiobox.core.Client;
import fm.audiobox.core.models.collections.EventedModelList;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;


/**
 * Used to get a list of all media files in a specific user's playlist.
 */
public class MediaFiles extends Model {

  /*
   TODO: PUT /api/v1/media_files/multiupdate.json?tokens[]=
   */

  private static final String JSON_TOKEN = "media_files";

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
  public static final String PARAM_TOKENS = "tokens[]";

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
  @Key( JSON_TOKEN )
  protected EventedModelList<MediaFile> media_files;


  /**
   * Gets playlists.
   *
   * @return the playlists collection.
   */
  public EventedModelList<MediaFile> getMediaFiles() {
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
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   */
  public static boolean destroyAll(Client client, List<String> tokens) throws IOException {
    String url = DESTROY_MEDIA_FILES_PATH + "?utf8=true";
    for ( String tk : tokens ) url += "&" + MediaFiles.PARAM_TOKENS + "=" + tk;
    client.doDELETE( url );
    return true;
  }


  /**
   * This method is not intended to be used directly, it exists for parsing technical reason.
   * <p>
   * It builds a new {@link fm.audiobox.core.models.collections.EventedModelList} that is used
   * by the parser and assigned to the underlying list of the instance of this very class.
   * </p>
   * <p>
   * ...yes, black magic happens! :P
   * </p>
   *
   * @param context the observable parent object (typically a {@link fm.audiobox.core.models.Playlist}).
   *
   * @return the collection
   */
  public static Collection<Object> newList(Object context) {
    return new EventedModelList<>( ( Model ) context );
  }


  /**
   * The Media collection custom parser.
   * <p>
   * This object, used together with {@link fm.audiobox.core.parsers.AudioBoxObjectParser}, allow to customize some
   * aspects of the parser. See {@link com.google.api.client.json.CustomizeJsonParser} for more information.
   * </p>
   */
  public static class MediaCollectionCustomParser extends CustomizeJsonParser {

    private Model parent;


    /**
     * Instantiates a new Media collection custom parser.
     *
     * @param observable the observable
     */
    public MediaCollectionCustomParser(Model observable) {
      this.parent = observable;
    }


    @Override
    public Collection<Object> newInstanceForArray(Object context, Field field) {
      if ( JSON_TOKEN.equals( field.getName() ) ) {
        return MediaFiles.newList( this.parent );
      } else {
        return super.newInstanceForArray( context, field );
      }
    }
  }

}
