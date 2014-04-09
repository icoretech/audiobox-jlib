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

package fm.audiobox.tests.unit.utils;

import fm.audiobox.core.utils.PlainTextContent;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * The type Plain text content tests.
 */
public class PlainTextContentTests extends AudioBoxTests {

  /**
   * Test length.
   *
   * @throws IOException the iO exception
   */
  @Test
  public void testLength() throws IOException {
    String testString = "test";
    PlainTextContent c = new PlainTextContent(testString);
    assertEquals( testString.length(), c.getLength() );
  }


  /**
   * Test type.
   */
  @Test
  public void testType() {
    PlainTextContent c = new PlainTextContent("AAA");
    assertEquals( "text/html", c.getType() );
  }


  @Test
  public void testWriteToOutput() throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    PlainTextContent c = new PlainTextContent("AAA");
    c.writeTo( os );
    assertEquals( "AAA", os.toString( Charset.forName( "UTF-8" ).toString() ) );
  }
}
