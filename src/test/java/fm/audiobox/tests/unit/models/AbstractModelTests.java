/*
 * Copyright 2009-2014 iCoreTech, Inc.
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

package fm.audiobox.tests.unit.models;


import fm.audiobox.core.models.Playlist;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertSame;


/**
 * Test common models behaviors.
 */
public class AbstractModelTests extends AudioBoxTests {

  @Test
  public void testStoreAndGetPropertyOnModel() throws IOException {
    List<Playlist> pls = c.getPlaylists();
    Playlist p = pls.get(0);

    Object a = new Object();
    Object b = new Object();
    Object c = new Object();
    Object d = new Object();
    Object e = new Object();
    Object f = new Object();

    p.setProp( "a", a );
    p.setProp( "b", b );
    p.setProp( "c", c );
    p.setProp( "d", d );
    p.setProp( "e", e );
    p.setProp( "f", f );

    assertSame(a, p.getProp( "a" ));
    assertSame(b, p.getProp( "b" ));
    assertSame(c, p.getProp( "c" ));
    assertSame(d, p.getProp( "d" ));
    assertSame(e, p.getProp( "e" ));
    assertSame(f, p.getProp( "f" ));
  }
}
