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

package fm.audiobox.core.utils;

import com.google.api.client.http.HttpContent;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Little wrapper to make plain text multipart form content wrapping easier.
 */
public class PlainTextContent implements HttpContent {

  private String text;


  public PlainTextContent(String text) {
    this.text = Preconditions.checkNotNull( text );
  }


  @Override
  public long getLength() throws IOException {
    return text.length();
  }


  @Override
  public String getType() {
    return "text/html";
  }


  @Override
  public boolean retrySupported() {
    return false;
  }


  @Override
  public void writeTo(OutputStream out) throws IOException {
    out.write( text.getBytes() );
  }
}
