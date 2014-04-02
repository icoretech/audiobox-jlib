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

package fm.audiobox.tests.mocks;

import com.google.api.client.http.HttpTransport;

/**
 * Created by keytwo on 28/03/14.
 */
public class MediaFilesMockHttp extends MockHttp {

  public static HttpTransport getMediaFileTransport(final String mediaFileToken) {
    return getTransport( "media_files/" + mediaFileToken + ".json" );
  }
}
