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
 * General preferences of the user, mostly used in the Cloud Web Player.
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

  @Key
  private boolean hide_tooltips;


  /**
   * Gets the color chosen by the user.
   *
   * @return the color name (no rgba neither hex code)
   */
  public String getColor() {
    return color;
  }


  /**
   * Checks if CWP repetition is enabled by the user.
   *
   * @return the true if it is enabled, false otherwise.
   */
  public boolean isRepeatEnabled() {
    return repeat;
  }


  /**
   * Checks if CWP shuffle is enabled by the user.
   *
   * @return the true if it is enabled, false otherwise.
   */
  public boolean isShuffleEnabled() {
    return shuffle;
  }


  /**
   * Checks if CWP autoplay is enabled by the user.
   *
   * @return the true if it is enabled, false otherwise.
   */
  public boolean isAutoplayEnabled() {
    return autoplay;
  }


  /**
   * Checks if CWP prebuffer is enabled by the user.
   *
   * @return the true if it is enabled, false otherwise.
   */
  public boolean isPrebufferEnabled() {
    return prebuffer;
  }


  /**
   * Checks if CWP JS demuxer is enabled by the user.
   *
   * @return the true if it is enabled, false otherwise.
   */
  public boolean isJsDemuxerEnabled() {
    return js_demuxer;
  }


  /**
   * Gets CWP top bar background name.
   *
   * @return the CWP top bar background name.
   */
  public String getTopBarBg() {
    return top_bar_bg;
  }


  /**
   * Gets CWP volume level.
   *
   * @return the CWP volume level
   */
  public String getVolumeLevel() {
    return volume_level;
  }


  /**
   * Does accepts emails.
   *
   * @return true if the user accepts emails.
   */
  public boolean doesAcceptsEmails() {
    return accept_emails;
  }


  /**
   * Checks whether the CWP tooltips should be shown or not.
   *
   * @return true if the tooltip should be shown, false otherwise.
   */
  public boolean areTooltipsHidden() {
    return hide_tooltips;
  }
}
