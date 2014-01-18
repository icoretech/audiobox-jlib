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
 * Created by keytwo on 17/01/14.
 */
public class Preferences {

  @Key
  private String color;

  @Key
  private boolean repeat;

  @Key
  private boolean shuffle;

  @Key
  private boolean autoplay;

  @Key
  private boolean prebuffer;

  @Key
  private boolean js_demuxer;

  @Key
  private String top_bar_bg;

  @Key
  private String volume_level;

  @Key
  private boolean accept_emails;

  public String getColor() {
    return color;
  }

  public boolean isRepeatEnabled() {
    return repeat;
  }

  public boolean isShuffleEnabled() {
    return shuffle;
  }

  public boolean isAutoplayEnabled() {
    return autoplay;
  }

  public boolean isPrebufferEnabled() {
    return prebuffer;
  }

  public boolean isJsDemuxerEnabled() {
    return js_demuxer;
  }

  public String getTopBarBg() {
    return top_bar_bg;
  }

  public String getVolumeLevel() {
    return volume_level;
  }

  public boolean doesAcceptsEmails() {
    return accept_emails;
  }
}
