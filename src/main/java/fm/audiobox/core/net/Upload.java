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

package fm.audiobox.core.net;


import com.google.api.client.http.*;
import fm.audiobox.core.AudioBoxClient;
import fm.audiobox.core.config.Configuration;
import fm.audiobox.core.models.MediaFile;
import fm.audiobox.core.models.MediaFileWrapper;
import fm.audiobox.core.utils.MD5Checksum;
import fm.audiobox.core.utils.MediaContent;
import fm.audiobox.core.utils.PlainTextContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;


/**
 * Uploads media files to AudioBox Cloud.
 */
public class Upload {

  private enum State {ready, started, completed}

  private static final Logger logger = LoggerFactory.getLogger( Upload.class );

  private static final String UPLOAD_PATH = "/api/v1/upload";

  private static final String MAGIC_UPLOAD_HEADER = "x-uploading-md5";

  private File file;

  private NetworkProgressListener listener;

  private AudioBoxClient audioBoxClient;

  private State state = State.ready;


  /**
   * Instantiates a new Upload.
   *
   * @param audioBoxClient   the client
   * @param file     the file to upload on AudioBox
   * @param listener the listener
   */
  public Upload(AudioBoxClient audioBoxClient, File file, NetworkProgressListener listener) {
    this.file = file;
    this.listener = listener;
    this.audioBoxClient = audioBoxClient;
  }


  /**
   * Gets listener.
   *
   * @return the listener
   */
  public NetworkProgressListener getListener() {
    return listener;
  }


  /**
   * Sets listener.
   *
   * @param listener the listener
   */
  public void setListener(NetworkProgressListener listener) {
    this.listener = listener;
  }


  /**
   * Starts to upload media files to AudioBox Cloud.
   * <p/>
   * If the subscription is not valid {@link fm.audiobox.core.exceptions.ForbiddenException} is thrown.
   * If a media file already exists on AudioBox Cloud {@link fm.audiobox.core.exceptions.FileAlreadyUploaded} is thrown.
   * <p/>
   * Other errors may include {@link fm.audiobox.core.exceptions.ValidationException},
   * {@link fm.audiobox.core.exceptions.SystemOverloadedException} or {@link fm.audiobox.core.exceptions.RemoteMessageException}
   * with additional information in the exception body.
   * <p/>
   * The application should ensure to accept those errors and retry accordingly after few minutes.
   * <p/>
   * On successful upload the server returns a new {@link MediaFile} with additional information,
   * including the token assigned to the newly uploaded media file.
   * <p/>
   * Files uploaded through this method will go directly into the CloudPlaylist.
   * </p>
   *
   * @return a {@link fm.audiobox.core.models.MediaFile} containing additional information
   *
   * @throws fm.audiobox.core.exceptions.AudioBoxException if any of the remote error exception is detected.
   * @throws java.lang.IllegalStateException               if the operation is already running or it's completed
   * @throws java.io.IOException                           if any connection problem occurs.
   * @see fm.audiobox.core.exceptions.AudioBoxException
   */
  public MediaFile start() throws IOException, IllegalStateException {

    if ( this.state != State.ready ) {
      throw new IllegalStateException( "Upload is already started or completed." );
    }

    this.state = State.started;

    try {
      MediaContent fileContent = new MediaContent( URLConnection.guessContentTypeFromName( file.getAbsolutePath() ), file );
      if ( getListener() != null ) {
        fileContent.setUploadProgressListener( getListener() );
      }

      HttpHeaders headers = null;
      try {
        String md5 = MD5Checksum.checkSum( new FileInputStream( file ) );
        headers = new HttpHeaders();
        headers.set(MAGIC_UPLOAD_HEADER, md5 );
      } catch ( NoSuchAlgorithmException e ) {
        logger.warn( "Unable to perform magic upload due to lack of MD5 algorithm. Proceeding with whole upload process." );
      }

      HttpContent pathContent = new PlainTextContent( file.getAbsolutePath() );

      MultipartFormDataContent multipart = new MultipartFormDataContent();
      multipart.addPart( new MultipartContent.Part( pathContent ), "remotePath", null );
      multipart.addPart( new MultipartContent.Part( fileContent ), "files[]", file.getName() );

      HttpResponse rsp = audioBoxClient.doRequestToChannel( HttpMethods.POST, UPLOAD_PATH, multipart, null, Configuration.Channels.upload, headers );
      return rsp.isSuccessStatusCode() ? rsp.parseAs( MediaFileWrapper.class ).getMediaFile() : null;

    } finally {
      this.state = State.completed;
    }

  }


}
