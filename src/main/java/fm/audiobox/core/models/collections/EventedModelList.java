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
 * - add an item
 * - remove an item
 * - clear the list
 *
 * @param <T> the type parameter
 */
public class EventedModelList<T> extends LinkedList<T> {

  private Model parent;


  /**
   * Instantiates a new Evented model list.
   *
   * @param parent the parent
   */
  public EventedModelList(Model parent) {
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


  private T trigger(int what, T item) {
    if ( this.parent != null ) {
      new Event( what, item, this ).trigger();
    }

    return item;
  }


  /**
   * The type Event.
   */
  public class Event {

    /**
     * The constant ADD.
     */
    public static final int ADD = 1;

    /**
     * The constant REMOVE.
     */
    public static final int REMOVE = -1;

    /**
     * The constant CLEAR.
     */
    public static final int CLEAR = 0;

    /**
     * The What.
     */
    public int what;

    /**
     * The Source.
     */
    public T source;

    /**
     * The Target.
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


    private void trigger() {
      parent.notifyObservers( this );
    }
  }

}
