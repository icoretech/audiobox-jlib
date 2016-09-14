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

package fm.audiobox.core.utils;

import com.google.api.client.util.Preconditions;
import fm.audiobox.core.models.Playlist;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Handful util class for various models tasks.
 */
public class ModelUtil {

  public static final String TOKEN_PLACEHOLDER = ":token:";

  public static final String ID_PLACEHOLDER = ":id:";

  public static final String AUDIOBOX_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


  /**
   * Performs models urls interpolation with the {@link fm.audiobox.core.utils.ModelUtil#TOKEN_PLACEHOLDER}.
   *
   * @param url   the url
   * @param token the token
   *
   * @return the computed url interpolation
   */
  public static String interpolate(String url, String token) {
    return StringUtils.replace( url, TOKEN_PLACEHOLDER, token );
  }


  /**
   * Performs models urls interpolation with the {@link fm.audiobox.core.utils.ModelUtil#ID_PLACEHOLDER}.
   *
   * @param url the url
   * @param id  the id
   *
   * @return the computed url interpolation
   */
  public static String interpolate(String url, long id) {
    return StringUtils.replace( url, ID_PLACEHOLDER, String.valueOf( id ) );
  }


  /**
   * Given a string representing an UTC date provided by AudioBox server,
   * this method returns a unix timestamp (always in UTC).
   *
   * @param simpleDateFormat the simple date format
   * @param audioboxDate     the string date provided by audiobox server.
   *
   * @return unix timestamp
   *
   * @throws ParseException the parse exception
   */
  public static long toUnixTime(SimpleDateFormat simpleDateFormat, String audioboxDate) throws ParseException {

    simpleDateFormat.applyPattern( AUDIOBOX_DATE_FORMAT );
    simpleDateFormat.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
    Date date = simpleDateFormat.parse( audioboxDate );
    return date.getTime();
  }


  /**
   * Given the user's playlists and the desired type this method will return the right playlist.
   * <br>
   * If the playlist is not found null is returned.
   *
   * @param playlists the user's playlists
   * @param type      the Playlists#PLAYLIST_* type to find
   *
   * @return the matched Playlist or null if not found
   */
  public static Playlist findPlaylistByType(List<Playlist> playlists, String type) {

    Preconditions.checkNotNull( playlists );
    Preconditions.checkNotNull( type );

    for ( Playlist p : playlists ) {
      if ( type.equals( p.getSystemName() ) ) {
        return p;
      }
    }

    return null;
  }


}
