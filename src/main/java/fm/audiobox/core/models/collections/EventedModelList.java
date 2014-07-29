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

import java.util.LinkedList;


/**
 * A {@link java.util.LinkedList} extension that triggers events on:
 * - add an item {@link Event#ADD}
 * - remove an item {@link Event#REMOVE}
 * - clear the list {@link Event#CLEAR}
 *
 * @param <T> the type parameter
 */
public class EventedModelList<T> extends LinkedList<T> {

  private Model parent;


  /**
   * Instantiates a new Evented model list.
   * <p>
   * This default constructor is used by the parser when a {@link com.google.api.client.json.CustomizeJsonParser} is
   * not provided. In this last case no {@link fm.audiobox.core.models.collections.EventedModelList.Event} are triggered.
   * </p>
   */
  public EventedModelList() {
  }


  /**
   * Instantiates a new Evented model list.
   *
   * @param parent the parent
   */
  public EventedModelList(Model parent) {
    this();
    this.parent = parent;
  }


  @Override
  public boolean add(T t) {
    trigger( Event.ADD, t );
    return super.add( t );
  }


  @Override
  public void add(int index, T element) {
    trigger( Event.ADD, element );
    super.add( index, element );
  }


  @Override
  public void addFirst(T t) {
    trigger( Event.ADD, t );
    super.addFirst( t );
  }


  @Override
  public void addLast(T t) {
    trigger( Event.ADD, t );
    super.addLast( t );
  }


  @Override
  @SuppressWarnings("unchecked")
  public boolean remove(Object o) {
    trigger( Event.REMOVE, ( T ) o );
    return super.remove( o );
  }


  @Override
  public T remove(int index) {
    return trigger( Event.REMOVE, super.remove( index ) );
  }


  @Override
  public T remove() {
    return trigger( Event.REMOVE, super.remove() );
  }


  @Override
  public T removeFirst() {
    return trigger( Event.REMOVE, super.removeFirst() );
  }


  @Override
  public T removeLast() {
    return trigger( Event.REMOVE, super.removeLast() );
  }


  @Override
  public void clear() {
    trigger( Event.CLEAR, null );
    super.clear();
  }



  /* =============== */
  /* Private methods */
  /* =============== */


  /**
   * Creates a new event and notifies the observers.
   *
   * @param what the occurred event
   * @param item the object that triggered the event
   */
  private T trigger(int what, T item) {
    if ( this.parent != null ) {
      new Event( what, item, this ).trigger();
    }

    return item;
  }


  /**
   * This is the wrapper object sent to the observers.
   * <p>
   * It contains information about what happened, the item that triggered the event
   * and the item the event impacted (i.e. the list).
   * </p>
   */
  public class Event {

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
     * The type of the event (see {@link Event#ADD}, {@link Event#REMOVE} and {@link Event#CLEAR}).
     */
    public int what;

    /**
     * The item that triggered the event.
     */
    public T source;

    /**
     * The list where the event is originated.
     */
    public EventedModelList target;


    /**
     * Instantiates a new Event.
     *
     * @param what   the what
     * @param source the target
     * @param target the target
     */
    public Event(int what, T source, EventedModelList target) {
      this.what = what;
      this.source = source;
      this.target = target;
    }


    /**
     * Notifies parents observers
     */
    private void trigger() {
      parent.notifyObservers( this );
    }
  }

}
