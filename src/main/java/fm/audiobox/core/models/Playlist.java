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

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.util.Key;
import fm.audiobox.core.Client;
import fm.audiobox.core.utils.ModelUtil;

/**
 * Created by keytwo on 20/01/14.
 */
public class Playlist {

  private static final String PATH = "/api/v1/playlists/" + ModelUtil.TOKEN_PLACEHOLDER + ".json";

  @Key
  private String token;

  @Key
  private String name;

  @Key
  private String system_name;

  @Key
  private String type;

  @Key
  private long media_files_count;

  @Key
  private long position;

  @Key
  private boolean offline;

  @Key
  private boolean embeddable;

  @Key
  private boolean visible;

  @Key
  private boolean last_accessed;

  @Key
  private String updated_at;

  @Key
  private boolean syncable;


  /**
   * Instantiates a new Playlist.
   */
  public Playlist() {
  }


  /**
   * Instantiates a new Playlist.
   *
   * @param name the name of the playlist
   */
  public Playlist(String name) {
    this.name = name;
  }


  /**
   * Remotely saves the playlist.
   *
   * @param client the client
   *
   * @return the boolean
   */
  public boolean save(Client client) {
    HttpResponse rsp = client.doPUT(ModelUtil.interpolate(getPath(), getToken()), new JsonHttpContent(client.getConf().getJsonFactory(), this));
    return rsp.getStatusCode() == HttpStatusCodes.STATUS_CODE_NO_CONTENT;
  }


  /**
   * Remotely destroys the playlist.
   *
   * @param client the client
   *
   * @return the boolean
   */
  public boolean delete(Client client) {
    HttpResponse rsp = client.doDELETE(ModelUtil.interpolate(getPath(), getToken()));
    return rsp.getStatusCode() == HttpStatusCodes.STATUS_CODE_NO_CONTENT;
  }


  /**
   * Gets path.
   *
   * @return the path
   */
  public static String getPath() {
    return PATH;
  }


  /**
   * Gets token.
   *
   * @return the token
   */
  public String getToken() {
    return this.token;
  }


  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }


  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * Gets system name.
   *
   * @return the system name
   */
  public String getSystemName() {
    return this.system_name;
  }


  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return this.type;
  }


  /**
   * Gets media files count.
   *
   * @return the media files count
   */
  public long getMediaFilesCount() {
    return this.media_files_count;
  }


  /**
   * Gets position.
   *
   * @return the position
   */
  public long getPosition() {
    return this.position;
  }


  /**
   * Sets position.
   *
   * @param position the position
   */
  public void setPosition(long position) {
    this.position = position;
  }


  /**
   * Gets offline.
   *
   * @return the offline
   */
  @Deprecated
  public boolean isOffline() {
    return this.offline;
  }


  /**
   * Gets embeddable.
   *
   * @return the embeddable
   */
  public boolean isEmbeddable() {
    return this.embeddable;
  }


  /**
   * Sets embeddable.
   *
   * @param embeddable the embeddable
   */
  public void setEmbeddable(boolean embeddable) {
    this.embeddable = embeddable;
  }


  /**
   * Gets visible.
   *
   * @return the visible
   */
  public boolean isVisible() {
    return this.visible;
  }


  /**
   * Sets visible.
   *
   * @param visible the visible
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
  }


  /**
   * Gets last accessed.
   *
   * @return the last accessed
   */
  public boolean getLastAccessed() {
    return this.last_accessed;
  }


  /**
   * Gets updated at.
   *
   * @return the updated at
   */
  public String getUpdatedAt() {
    return this.updated_at;
  }


  /**
   * Gets syncable.
   *
   * @return the syncable
   */
  public boolean getSyncable() {
    return this.syncable;
  }


  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Playlist)) {
      return false;
    }

    Playlist o = (Playlist) other;

    boolean eq = false;
    if (this.token != null) {
      eq = this.token.equals(o.getToken());
    }

    if (this.updated_at != null) {
      eq = eq && this.updated_at.equals(o.getUpdatedAt());
    }

    return eq;

  }


  @Override
  public int hashCode() {
    int hashCode = 0;

    if (this.token != null)
      hashCode = hashCode * 37 + this.token.hashCode();
    if (this.updated_at != null)
      hashCode = hashCode * 37 + this.updated_at.hashCode();

    return hashCode;
  }


}
