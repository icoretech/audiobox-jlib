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

import com.google.api.client.util.Key;
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
   * Gets offline.
   *
   * @return the offline
   */
  public boolean getOffline() {
    return this.offline;
  }


  /**
   * Gets embeddable.
   *
   * @return the embeddable
   */
  public boolean getEmbeddable() {
    return this.embeddable;
  }


  /**
   * Gets visible.
   *
   * @return the visible
   */
  public boolean getVisible() {
    return this.visible;
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
