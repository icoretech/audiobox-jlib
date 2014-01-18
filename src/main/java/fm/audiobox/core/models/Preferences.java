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


  /**
   * Gets color.
   *
   * @return the color
   */
  public String getColor() {
    return color;
  }


  /**
   * Is repeat enabled.
   *
   * @return the boolean
   */
  public boolean isRepeatEnabled() {
    return repeat;
  }


  /**
   * Is shuffle enabled.
   *
   * @return the boolean
   */
  public boolean isShuffleEnabled() {
    return shuffle;
  }


  /**
   * Is autoplay enabled.
   *
   * @return the boolean
   */
  public boolean isAutoplayEnabled() {
    return autoplay;
  }


  /**
   * Is prebuffer enabled.
   *
   * @return the boolean
   */
  public boolean isPrebufferEnabled() {
    return prebuffer;
  }


  /**
   * Is js demuxer enabled.
   *
   * @return the boolean
   */
  public boolean isJsDemuxerEnabled() {
    return js_demuxer;
  }


  /**
   * Gets top bar bg.
   *
   * @return the top bar bg
   */
  public String getTopBarBg() {
    return top_bar_bg;
  }


  /**
   * Gets volume level.
   *
   * @return the volume level
   */
  public String getVolumeLevel() {
    return volume_level;
  }


  /**
   * Does accepts emails.
   *
   * @return the boolean
   */
  public boolean doesAcceptsEmails() {
    return accept_emails;
  }
}
