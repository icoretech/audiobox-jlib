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

package fm.audiobox.core.models.collections;

import fm.audiobox.core.models.Model;

/**
 * This is the wrapper object sent to the observers.
 * <p>
 * It contains information about what happened, the item that triggered the event
 * and the item the event impacted (i.e. the list).
 * </p>
 */
public class ModelEvent<E> {

  /**
   * An item is added to the collection.
   */
  public static final int ADD = 1;

  /**
   * An item is removed from the collection.
   */
  public static final int REMOVE = -1;

  /**
   * The collection is cleared.
   */
  public static final int CLEAR = 0;

  /**
   * The type of the event (see {@link ModelEvent#ADD}, {@link ModelEvent#REMOVE} and {@link ModelEvent#CLEAR}).
   */
  public int what;

  /**
   * The item that triggered the event.
   */
  public E source;

  /**
   * The object where the event is originated.
   */
  public EventedModelList target;


  private Model parent;


  /**
   * Instantiates a new Event.
   *
   * @param what      the type of the event (see {@link ModelEvent#ADD}, {@link ModelEvent#REMOVE} and {@link ModelEvent#CLEAR}).
   * @param source    the item that triggered the event.
   * @param target    the object where the event is originated (can be null).
   * @param notifiers the model to notify the event to.
   */
  public ModelEvent(int what, E source, EventedModelList target, Model notifiers) {
    this.what = what;
    this.source = source;
    this.target = target;
    this.parent = notifiers;
  }


  /**
   * Notifies parents observers
   */
  public void trigger() {
    parent.notifyObservers( this );
  }
}
