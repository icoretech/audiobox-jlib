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

package fm.audiobox.tests.unit.exceptions;


import com.fasterxml.jackson.core.JsonParseException;
import com.google.api.client.http.HttpResponse;
import fm.audiobox.core.exceptions.RemoteMessageException;
import fm.audiobox.core.models.MediaFile;
import fm.audiobox.tests.unit.base.AudioBoxTests;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class RemoteMessageExceptionsTests extends AudioBoxTests {

  @Test
  public void testRemoteException() throws IOException {
    HttpResponse rsp = c.doGET( "/api/v1/malformed.json" );
    try {
      rsp.parseAs( MediaFile.class );
    } catch ( JsonParseException e ) {
      RemoteMessageException ex = new RemoteMessageException( rsp );
      assertEquals( "fm.audiobox.core.exceptions.RemoteMessageException: Client got a remote error (200) but no message was given.\n", ex.getMessage() );
    }
  }


  @Test
  public void testNullErrors() throws IOException {
    RemoteMessageException rme = new RemoteMessageException( null, HttpStatus.SC_INTERNAL_SERVER_ERROR );
    logger.info(rme.getMessage());
    assertEquals( "fm.audiobox.core.exceptions.RemoteMessageException: Client got a remote error (500) but no message was given.\n", rme.getMessage() );
  }

  @Test
  public void testNullResponse() throws IOException {
    RemoteMessageException rme = new RemoteMessageException( null );
    logger.info(rme.getMessage());
    assertEquals( "fm.audiobox.core.exceptions.RemoteMessageException: Client got a remote error (-1) but no message was given.\n", rme.getMessage() );
  }
}
