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
import fm.audiobox.core.AudioBoxClient;
import fm.audiobox.core.utils.ModelUtil;

import java.io.IOException;
import java.util.List;

/**
 * Used to get a list of all the playlist in the user's account.
 */
public class Playlists extends Model {

  private static final String PATH = "/api/v1/playlists.json";


  /**
   * The constant that identifies the "local" playlist.
   */
  public static final String PLAYLIST_LOCAL = "local";

  /**
   * The constant that identifies the "cloud" playlist.
   */
  public static final String PLAYLIST_CLOUD = "cloud";

  /**
   * The constant that identifies the "dropbox" playlist.
   */
  public static final String PLAYLIST_DROPBOX = "dropbox";

  /**
   * The constant that identifies the "onedrive" playlist.
   */
  public static final String PLAYLIST_ONERIVE = "skydrive";

  /**
   * The constant that identifies the "box" playlist.
   */
  public static final String PLAYLIST_BOX = "box";

  /**
   * The constant that identifies the "gdrive" playlist.
   */
  public static final String PLAYLIST_GDRIVE = "gdrive";

  /**
   * The constant that identifies the "youtube" playlist.
   */
  public static final String PLAYLIST_YOUTUBE = "youtube";

  /**
   * The constant that identifies the "soundcloud" playlist.
   */
  public static final String PLAYLIST_SOUNDCLOUD = "soundcloud";

  /**
   * The constant that identifies the "mega" playlist.
   */
  public static final String PLAYLIST_MEGA = "mega";

  /**
   * The constant that identifies the "offline" playlist.
   */
  public static final String PLAYLIST_OFFLINE = "offline";

  /**
   * The constant that identifies "custom" playlists.
   */
  public static final String PLAYLIST_CUSTOM = "custom";

  /**
   * The constant that identifies "smart" playlists.
   */
  public static final String PLAYLIST_SMART = "smart";

  @Key("playlists")
  private List<Playlist> playlists;


  /**
   * Gets the generic remote resource collection path.
   *
   * @return the path
   */
  public static String getPath() {
    return PATH;
  }


  /**
   * Gets playlists.
   *
   * @return the playlists collection.
   */
  public List<Playlist> getPlaylists() {
    return playlists;
  }


  /**
   * <p>
   * Gets the local playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the local playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getLocalPlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_LOCAL );
  }


  /**
   * <p>
   * Gets the cloud playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the cloud playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getCloudPlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_CLOUD );
  }


  /**
   * <p>
   * Gets the dropbox playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the dropbox playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getDropboxPlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_DROPBOX );
  }


  /**
   * <p>
   * Gets the skydrive playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the one drive playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getOneDrivePlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_ONERIVE );
  }


  /**
   * <p>
   * Gets the box playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the box playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getBoxPlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_BOX );
  }


  /**
   * <p>
   * Gets the gdrive playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the google drive playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getGdrivePlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_GDRIVE );
  }


  /**
   * <p>
   * Gets the youtube playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the youtube playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getYoutubePlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_YOUTUBE );
  }


  /**
   * <p>
   * Gets the soundcloud playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the soundcloud playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getSoundcloudPlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_SOUNDCLOUD );
  }


  /**
   * <p>
   * Gets the mega playlist
   * </p>
   * <p>.
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the mega playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getMegaPlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_MEGA );
  }


  /**
   * <p>
   * Gets the offline playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link fm.audiobox.core.utils.ModelUtil#findPlaylistByType(java.util.List, String)}
   * in combination with the Playlists#PLAYLIST_* constants to get the right playlist from the memory.
   * </p>
   *
   * @param audioBoxClient the {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   *
   * @return the offline playlist
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public static Playlist getOfflinePlaylist(AudioBoxClient audioBoxClient) throws IOException {
    return getPlaylistOfType( audioBoxClient, PLAYLIST_OFFLINE );
  }



  /* ================ */
  /*  Private methods */
  /* ================ */


  /**
   * Given the desired type this method will return the right playlist.
   * <br>
   * If the playlist is not found null is returned.
   *
   * @param audioBoxClient the user's {@link fm.audiobox.core.AudioBoxClient} to use to make the request
   * @param type   the Playlists#PLAYLIST_* type to if any problem occurs
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  private static Playlist getPlaylistOfType(AudioBoxClient audioBoxClient, String type) throws IOException {
    return ModelUtil.findPlaylistByType( audioBoxClient.getPlaylists(), type );
  }

}


