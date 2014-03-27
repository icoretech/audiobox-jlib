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
