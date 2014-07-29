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

package fm.audiobox.core.models;


import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


/**
 * This class is the prototype of each model and defines model's common behaviors.
 */
public abstract class Model extends Observable {

  private final Map<String, Object> PROPS = new HashMap<>( 4 );


  /**
   * Sets a property to the model
   *
   * @param key   the key to store the value to.
   * @param value the value to set.
   */
  public final void setProp(String key, Object value) {
    PROPS.put( key, value );
  }


  /**
   * Gets the requested property or null.
   *
   * @param key the key where the value was previously stored.
   *
   * @return the requested object.
   */
  public final Object getProp(String key) {
    return PROPS.get( key );
  }


  @Override
  public void notifyObservers(Object arg) {
    this.setChanged();
    super.notifyObservers( arg );
  }


}
