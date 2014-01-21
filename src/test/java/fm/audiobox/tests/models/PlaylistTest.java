/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 * This file is part of the audiobox-jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.tests.models;

import fm.audiobox.core.exceptions.ValidationException;
import fm.audiobox.core.models.Playlist;
import fm.audiobox.tests.ClientTest;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by keytwo on 21/01/14.
 */
public class PlaylistTest extends ClientTest {


  /**
   * Test playlists.
   *
   * @throws java.io.IOException the iO exception
   */
  @Test
  @Ignore
  public void testPlaylists() throws IOException {
    List<Playlist> list = c.getPlaylists();
    assertNotNull(list);
    assertFalse(list.isEmpty());
  }


  /**
   * Test playlist should be null if token is invalid.
   */
  @Test
  @Ignore
  public void testPlaylistShouldBeNullIfTokenIsInvalid() {
    assertNull(c.getPlaylist("asd"));
  }


  /**
   * Test playlist should be null if token is invalid.
   */
  @Test
  @Ignore
  public void testPlaylistShouldNotBeNullIfTokenIsValid() {
    List<Playlist> list = c.getPlaylists();
    Playlist p1 = list.get(0);
    assertNotNull(p1);

    Playlist p2 = c.getPlaylist(p1.getToken());
    assertEquals(p2, p1);
  }


  @Test
  public void testPlaylistCreation() {
    try {
      Playlist p = c.createNewPlaylist("My test playlist");
      assertFalse(p.isVisible());
      p.setVisible(true);
      assertTrue(p.isVisible());

      assertTrue(p.save(c));

      Playlist p2 = c.getPlaylist(p.getToken());
      assertTrue(p2.isVisible());

    } catch (ValidationException e) {
      List<Playlist> list = c.getPlaylists();
      for (Playlist p : list) {
        if ("My test playlist".equals(p.getName())) {
          p.delete(c);
          break;
        }
      }
      testPlaylistCreation();

    }


  }
}
