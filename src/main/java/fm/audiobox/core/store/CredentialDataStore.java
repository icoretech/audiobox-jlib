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

package fm.audiobox.core.store;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import java.io.IOException;

/**
 * This interface is intended to be used for storing and getting back user's credentials.
 */
public interface CredentialDataStore {

  /**
   * The implementation of this method should persists given {@link com.google.api.client.auth.oauth2.Credential}
   * with the corresponding label.
   *
   * @param label      a string that identifies the given credentials
   * @param credential the credential to store
   * @throws IOException if any
   */
  public void saveCredentials(String label, Credential credential) throws IOException;


  /**
   * The implementation of this method must return the {@link com.google.api.client.auth.oauth2.StoredCredential}
   * referenced by the label parameter.
   *
   * @param label the identifiers of the previous saved credentials.
   * @throws IOException if any
   * @return must return a {@link com.google.api.client.auth.oauth2.StoredCredential}
   */
  public StoredCredential getCredentials(String label) throws IOException;


  /**
   * The implementation of this method must return the {@link com.google.api.client.util.store.DataStore}
   * used to save the credentials
   *
   * @return must return a valid {@link com.google.api.client.util.store.DataStore} instance.
   */
  public DataStore<StoredCredential> getDB();
}
