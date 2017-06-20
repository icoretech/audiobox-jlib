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

package fm.audiobox.core.models;

import com.google.api.client.util.Key;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * General preferences of the user, mostly used in the Cloud Web Player.
 */
public class Preferences extends Model {

  public static final String PATH = "/api/v1/preferences.json";

  /**
   * Contains the color mapping: key is the name, value is the RGB value.
   */
  public static final Map<String, String> COLORS = new HashMap<>( 8 );


  static {
    COLORS.put( "techcrunch-green", "#3cc535" );
    COLORS.put( "shadows-grey", "#36393D" );
    COLORS.put( "flock-blue", "#4096EE" );
    COLORS.put( "audiobox-fm-blue", "#6DA5CB" );
    COLORS.put( "flickr-pink", "#FF0084" );
    COLORS.put( "last-fm-crimson", "#D01F3C" );
    COLORS.put( "mozilla-red", "#FF1A00" );
    COLORS.put( "rss-orange", "#FF7400" );
  }


  @Key
  protected String color;

  @Key
  protected boolean repeat;

  @Key
  protected boolean shuffle;

  @Key
  protected boolean autoplay;

  @Key
  protected boolean prebuffer;

  @Key
  protected boolean js_demuxer;

  @Key
  protected String top_bar_bg;

  @Key
  protected int volume_level;

  @Key
  protected boolean accept_emails;

  @Key
  protected boolean hide_tooltips;


  /**
   * Gets the color chosen by the user.
   *
   * @return the color name (no RGB neither hex code)
   */
  public String getColor() {
    return color;
  }


  /**
   * Gets the RGB value of the color chosen by the user.
   *
   * @return the RGB color value
   */
  public String getColorValue() {
    return COLORS.get( getColor() );
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
  public int getVolumeLevel() {
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


  /**
   * Sets color (must be one of the {@link fm.audiobox.core.models.Preferences#COLORS} keys).
   *
   * @param colorName the color name to set
   *
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void setColor(String colorName) throws IllegalArgumentException {
    if ( !COLORS.containsKey( colorName ) ) {
      throw new IllegalArgumentException( "Color must be one of " + StringUtils.join( COLORS.keySet(), ", " ) );
    }
    this.color = colorName;
  }


  /**
   * Enable or disable repeat feature on CWP.
   *
   * @param repeat true to enable false to disable
   */
  public void setRepeat(boolean repeat) {
    this.repeat = repeat;
  }


  /**
   * Enable or disable shuffle feature on CWP.
   *
   * @param shuffle true to enable false to disable
   */
  public void setShuffle(boolean shuffle) {
    this.shuffle = shuffle;
  }


  /**
   * Enable or disable autoplay feature on CWP.
   *
   * @param autoplay true to enable false to disable
   */
  public void setAutoplay(boolean autoplay) {
    this.autoplay = autoplay;
  }


  /**
   * Enable or disable prebuffer feature on CWP.
   *
   * @param prebuffer true to enable false to disable
   */
  public void setPrebuffer(boolean prebuffer) {
    this.prebuffer = prebuffer;
  }


  /**
   * Enable or disable CWP experimental apple losseless support.
   *
   * @param js_demuxer true to enable false to disable
   */
  public void setJsDemuxer(boolean js_demuxer) {
    this.js_demuxer = js_demuxer;
  }


  /**
   * Sets top bar bg.
   *
   * @param top_bar_bg the top _ bar _ bg
   */
  public void setTopBarBg(String top_bar_bg) {
    this.top_bar_bg = top_bar_bg;
  }


  /**
   * Sets CWP volume level.
   *
   * @param volume_level a range from 0 (mute) to 100 (max) in form of a String
   */
  public void setVolumeLevel(int volume_level) {
    this.volume_level = volume_level;
  }


  /**
   * Sets accept emails.
   *
   * @param accept_emails true to accept email false otherwise
   */
  public void setAcceptEmails(boolean accept_emails) {
    this.accept_emails = accept_emails;
  }


  /**
   * Sets CWP hide tooltips preference.
   *
   * @param hide_tooltips true to show tooltips false to hide
   */
  public void setHideTooltips(boolean hide_tooltips) {
    this.hide_tooltips = hide_tooltips;
  }

}
