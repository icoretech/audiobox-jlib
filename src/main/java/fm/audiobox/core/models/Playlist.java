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

/**
 * Created by keytwo on 20/01/14.
 */
public class Playlist {
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


  public String getToken() {
    return this.token;
  }

  public String getName() {
    return this.name;
  }

  public String getSystemName() {
    return this.system_name;
  }

  public String getType() {
    return this.type;
  }

  public long getMediaFilesCount() {
    return this.media_files_count;
  }

  public long getPosition() {
    return this.position;
  }

  public boolean getOffline() {
    return this.offline;
  }

  public boolean getEmbeddable() {
    return this.embeddable;
  }

  public boolean getVisible() {
    return this.visible;
  }

  public boolean getLastAccessed() {
    return this.last_accessed;
  }

  public String getUpdatedAt() {
    return this.updated_at;
  }

  public boolean getSyncable() {
    return this.syncable;
  }

}
