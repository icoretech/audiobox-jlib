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

package fm.audiobox.tests.support;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import fm.audiobox.core.store.CredentialDataStore;

import java.io.File;
import java.io.IOException;

public class FileCredentialStore implements CredentialDataStore {

  DataStoreFactory dsf;
  DataStore<StoredCredential> db;


  public FileCredentialStore(File file) throws IOException {
    dsf = new FileDataStoreFactory( file );
    db = StoredCredential.getDefaultDataStore( dsf );
  }


  @Override
  public void saveCredentials(String label, Credential credential) throws IOException {
    db.set( label, new StoredCredential( credential ));
  }


  @Override
  public StoredCredential getCredentials(String label) throws IOException {
    return db.get( label );
  }

  public DataStore<StoredCredential> getDB() {
    return this.db;
  }
}
