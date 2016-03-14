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

package fm.audiobox.core.models.collections;


import fm.audiobox.core.models.Model;

import java.util.LinkedList;


/**
 * A {@link java.util.LinkedList} extension that triggers events on:
 * <ul>
 * <li>add an item {@link ModelEvent#ADD}</li>
 * <li>remove an item {@link ModelEvent#REMOVE}</li>
 * <li>clear the list {@link ModelEvent#CLEAR}</li>
 * </ul>
 *
 * @param <T> the type parameter
 */
public class EventedModelList<T> extends LinkedList<T> {

  private Model parent;


  /**
   * Instantiates a new Evented model list.
   * <p>
   * This default constructor is used by the parser when a {@link com.google.api.client.json.CustomizeJsonParser} is
   * not provided. In this last case no {@link ModelEvent} are triggered.
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
    trigger( ModelEvent.ADD, t );
    return super.add( t );
  }


  @Override
  public void add(int index, T element) {
    trigger( ModelEvent.ADD, element );
    super.add( index, element );
  }


  @Override
  public void addFirst(T t) {
    trigger( ModelEvent.ADD, t );
    super.addFirst( t );
  }


  @Override
  public void addLast(T t) {
    trigger( ModelEvent.ADD, t );
    super.addLast( t );
  }


  @Override
  @SuppressWarnings("unchecked")
  public boolean remove(Object o) {
    trigger( ModelEvent.REMOVE, ( T ) o );
    return super.remove( o );
  }


  @Override
  public T remove(int index) {
    return trigger( ModelEvent.REMOVE, super.remove( index ) );
  }


  @Override
  public T remove() {
    return trigger( ModelEvent.REMOVE, super.remove() );
  }


  @Override
  public T removeFirst() {
    return trigger( ModelEvent.REMOVE, super.removeFirst() );
  }


  @Override
  public T removeLast() {
    return trigger( ModelEvent.REMOVE, super.removeLast() );
  }


  @Override
  public void clear() {
    trigger( ModelEvent.CLEAR, null );
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
      new ModelEvent<>( what, item, this, this.parent ).trigger();
    }

    return item;
  }

}
