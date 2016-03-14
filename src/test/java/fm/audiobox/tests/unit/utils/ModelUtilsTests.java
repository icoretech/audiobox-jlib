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

package fm.audiobox.tests.unit.utils;

import fm.audiobox.core.models.Playlist;
import fm.audiobox.core.models.Playlists;
import fm.audiobox.core.utils.ModelUtil;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * The type Model utils tests.
 */
public class ModelUtilsTests extends AudioBoxTests {

  /**
   * Test new instance.
   */
  @Test
  public void testNewInstance() {
    assertNotNull( new ModelUtil() ); // coverage purpose
  }


  /**
   * Test playlist null find.
   */
  @Test
  public void testPlaylistNullFind() {
    Playlist p = new Playlist( "name" );
    List<Playlist> pls = new ArrayList<>();
    pls.add( p );

    assertNull( ModelUtil.findPlaylistByType( pls, Playlists.PLAYLIST_BOX ) );
  }
}
