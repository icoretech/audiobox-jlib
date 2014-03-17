/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 *
 * This file is part of the AudioBox-Jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.core.models;

import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import fm.audiobox.core.Client;
import fm.audiobox.core.exceptions.AudioBoxException;
import fm.audiobox.core.utils.ModelUtil;

import java.util.List;

/**
 * Used to get a list of all the playlist in the user's account.
 */
public class Playlists {

  private static final String PATH = "/api/v1/playlists.json";


  /**
   * The constant PLAYLIST_LOCAL.
   */
  public static final String PLAYLIST_LOCAL = "local";

  /**
   * The constant PLAYLIST_CLOUD.
   */
  public static final String PLAYLIST_CLOUD = "cloud";

  /**
   * The constant PLAYLIST_DROPBOX.
   */
  public static final String PLAYLIST_DROPBOX = "dropbox";

  /**
   * The constant PLAYLIST_ONERIVE.
   */
  public static final String PLAYLIST_ONERIVE = "skydrive";

  /**
   * The constant PLAYLIST_BOX.
   */
  public static final String PLAYLIST_BOX = "box";

  /**
   * The constant PLAYLIST_GDRIVE.
   */
  public static final String PLAYLIST_GDRIVE = "gdrive";

  /**
   * The constant PLAYLIST_YOUTUBE.
   */
  public static final String PLAYLIST_YOUTUBE = "youtube";

  /**
   * The constant PLAYLIST_SOUNDCLOUD.
   */
  public static final String PLAYLIST_SOUNDCLOUD = "soundcloud";

  /**
   * The constant PLAYLIST_UBUNTU.
   */
  public static final String PLAYLIST_UBUNTU = "ubuntu";

  /**
   * The constant PLAYLIST_OFFLINE.
   */
  public static final String PLAYLIST_OFFLINE = "offline";

  @Key( "playlists" )
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
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the local playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getLocalPlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_LOCAL );
  }


  /**
   * <p>
   * Gets the cloud playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the cloud playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getCloudPlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_CLOUD );
  }


  /**
   * <p>
   * Gets the dropbox playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the dropbox playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getDropboxPlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_DROPBOX );
  }


  /**
   * <p>
   * Gets the skydrive playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the one drive playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getOneDrivePlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_ONERIVE );
  }


  /**
   * <p>
   * Gets the box playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the box playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getBoxPlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_BOX );
  }


  /**
   * <p>
   * Gets the gdrive playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the google drive playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getGdrivePlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_GDRIVE );
  }


  /**
   * <p>
   * Gets the youtube playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the youtube playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getYoutubePlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_YOUTUBE );
  }


  /**
   * <p>
   * Gets the soundcloud playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the soundcloud playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getSoundcloudPlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_SOUNDCLOUD );
  }


  /**
   * <p>
   * Gets the ubuntu playlist
   * </p>
   * <p>.
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the ubuntu one music playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getUbuntuPlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_UBUNTU );
  }


  /**
   * <p>
   * Gets the offline playlist.
   * </p>
   * <p>
   * NOTE: this method will always make a request against AudioBox, use carefully.
   * If you want to avoid this behavior consider storing the playlist collection
   * within your application and use {@link } in combination with the PLAYLIST_*
   * to get the right playlist from the memory.
   * </p>
   *
   * @param client the {@link Client} to use to make the request
   *
   * @return the offline playlist
   *
   * @throws AudioBoxException if any problem occurs
   */
  public static Playlist getOfflinePlaylist(Client client) throws AudioBoxException {
    return getPlaylistOfType( client, PLAYLIST_OFFLINE );
  }



  /* ================ */
  /*  Private methods */
  /* ================ */


  /**
   * Given the desired type this method will return the right playlist.
   * <br/>
   * If the playlist is not found null is returned.
   *
   * @param client the user's {@link Client} to use to make the request
   * @param type   the Playlists#PLAYLIST_* type to if any problem occurs
   */
  private static Playlist getPlaylistOfType(Client client, String type) throws AudioBoxException {
    return ModelUtil.findPlaylistByType( client.getPlaylists(), type );
  }

}


