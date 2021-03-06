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

package fm.audiobox.core.parsers;


import com.google.api.client.json.CustomizeJsonParser;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Preconditions;
import fm.audiobox.core.AudioBoxClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;


/**
 * A customizable JSON object parser.
 * <p>
 * The use of this class will allow custom collection {@link fm.audiobox.core.models.Model} to behave according
 * to the specified {@link com.google.api.client.json.CustomizeJsonParser} object passed as constructor argument.
 * </p>
 */
public class AudioBoxObjectParser extends JsonObjectParser {

  private CustomizeJsonParser customParser;


  /**
   * Instantiates a new Audio box object parser.
   *
   * @param audioBoxClient       the client
   * @param customParser the custom parser
   */
  public AudioBoxObjectParser(AudioBoxClient audioBoxClient, CustomizeJsonParser customParser) {
    super( ( new Builder( audioBoxClient.getConf().getJsonFactory() ) ) );
    this.customParser = customParser;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> T parseAndClose(InputStream in, Charset charset, Class<T> dataClass) throws IOException {
    return ( T ) parseAndClose( in, charset, ( Type ) dataClass );
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Object parseAndClose(InputStream in, Charset charset, Type dataType) throws IOException {
    JsonParser parser = getJsonFactory().createJsonParser( in, charset );
    initializeParser( parser );
    return parser.parse( dataType, true, customParser );
  }


  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> T parseAndClose(Reader reader, Class<T> dataClass) throws IOException {
    return ( T ) parseAndClose( reader, ( Type ) dataClass );
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Object parseAndClose(Reader reader, Type dataType) throws IOException {
    JsonParser parser = getJsonFactory().createJsonParser( reader );
    initializeParser( parser );
    return parser.parse( dataType, true, customParser );
  }


  /**
   * Initialize the parser to skip to wrapped keys (if any).
   *
   * @param parser JSON parser
   */
  private void initializeParser(JsonParser parser) throws IOException {
    if ( getWrapperKeys().isEmpty() ) {
      return;
    }
    boolean failed = true;
    try {
      String match = parser.skipToKey( getWrapperKeys() );
      Preconditions.checkArgument( match != null && parser.getCurrentToken() != JsonToken.END_OBJECT, "wrapper key(s) not found: %s", getWrapperKeys() );
      failed = false;
    } finally {
      if ( failed ) {
        parser.close();
      }
    }
  }

}
