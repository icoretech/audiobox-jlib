/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 *
 * This file is part of the AudioBox-Jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.store.interfaces;

/**
 * Implementation of this interface should provide a general way
 * to save, get back and remove data that may be useful to persist.
 * <p/>
 * Created by keytwo on 16/01/14.
 */
public interface BaseDataStore {

  /**
   * Default 'argument error' message
   */
  public static final String ARGUMENT_ERROR = "'key' parameter is mandatory.";

  /**
   * This method:
   * <ul>
   * <li>saves (in memory or persisted somewhere) the given {@link java.lang.Object}</li>
   * <li>returns true if the saving succeed, false otherwise</li>
   * <li>throws an {@link java.lang.IllegalArgumentException} if <code>key</code> is not provided</li>
   * </ul>
   *
   * @param key   the key under which save the data.
   * @param value the {@link java.lang.Object} to save.
   *
   * @return true if save succeed
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public boolean save(String key, Object value);


  /**
   * This method:
   * <ul>
   * <li>saves (in memory or persisted somewhere) the given {@link java.lang.String}</li>
   * <li>returns true if the saving succeed, false otherwise</li>
   * <li>throws an {@link java.lang.IllegalArgumentException} if <code>key</code> is not provided</li>
   * </ul>
   *
   * @param key   the key under which save the data.
   * @param value the {@link java.lang.String} to save.
   *
   * @return true if save succeed
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public boolean save(String key, String value);


  /**
   * This method:
   * <ul>
   * <li>save (in memory or persisted somewhere) the given {@link java.lang.Long}</li>
   * <li>return true if the saving succeed, false otherwise</li>
   * <li>throw an {@link java.lang.IllegalArgumentException} if <code>key</code> is not provided</li>
   * </ul>
   *
   * @param key   the key under which save the data.
   * @param value the {@link java.lang.Long} to save.
   *
   * @return true if save succeed
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public boolean save(String key, Long value);


  /**
   * This method:
   * <ul>
   * <li>saves (in memory or persisted somewhere) the given {@link java.lang.Double}</li>
   * <li>returns true if the saving succeed, false otherwise</li>
   * <li>throws an {@link java.lang.IllegalArgumentException} if <code>key</code> is not provided</li>
   * </ul>
   *
   * @param key   the key under which save the data.
   * @param value the {@link java.lang.Double} to save.
   *
   * @return true if save succeed
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public boolean save(String key, Double value);


  /**
   * This method:
   * <ul>
   * <li>saves (in memory or persisted somewhere) the given {@link java.lang.Boolean}</li>
   * <li>returns true if the saving succeed, false otherwise</li>
   * <li>throws an {@link java.lang.IllegalArgumentException} if <code>key</code> is not provided</li>
   * </ul>
   *
   * @param key   the key under which save the data.
   * @param value the {@link java.lang.Boolean} to save.
   *
   * @return true if save succeed
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public boolean save(String key, Boolean value);


  /**
   * This method:
   * <ul>
   * <li>removes (from memory or persisted location) the object identified by <code>key</code></li>
   * <li>returns true if deletion succeeds</li>
   * <li>throw an {@link java.lang.IllegalArgumentException} if <code>key</code> is not provided</li>
   * </ul>
   *
   * @param key the key of the value to be removed;
   *
   * @return true if object have been successfully removed from data store, false otherwise.
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public boolean remove(String key);


  /**
   * This method:
   * <ul>
   * <li>return the {@link java.lang.Object} specified by the <code>key</code> or null.</li>
   * <li>throw an {@link java.lang.IllegalArgumentException} if the <code>key</code> is not provided.</li>
   * </ul>
   *
   * @param key the key of the value to get.
   *
   * @return the {@link java.lang.Object} specified by the <code>key</code> or null if not found.
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public Object get(String key);


  /**
   * This method:
   * <ul>
   * <li>return the {@link java.lang.String} specified by the <code>key</code> or null.</li>
   * <li>throw an {@link java.lang.IllegalArgumentException} if the <code>key</code> is not provided.</li>
   * </ul>
   *
   * @param key the key of the value to get.
   *
   * @return the {@link java.lang.String} specified by the <code>key</code> or null if not found.
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public String getString(String key);


  /**
   * This method:
   * <ul>
   * <li>return the {@link java.lang.Long} specified by the <code>key</code> or null.</li>
   * <li>throw an {@link java.lang.IllegalArgumentException} if the <code>key</code> is not provided.</li>
   * </ul>
   *
   * @param key the key of the value to get.
   *
   * @return the {@link java.lang.Long} specified by the <code>key</code> or null if not found.
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public Long getLong(String key);


  /**
   * This method:
   * <ul>
   * <li>return the {@link java.lang.Double} specified by the <code>key</code> or null.</li>
   * <li>throw an {@link java.lang.IllegalArgumentException} if the <code>key</code> is not provided.</li>
   * </ul>
   *
   * @param key the key of the value to get.
   *
   * @return the {@link java.lang.Double} specified by the <code>key</code> or null if not found.
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public Double getDouble(String key);


  /**
   * This method:
   * <ul>
   * <li>return the {@link java.lang.Boolean} specified by the <code>key</code> or null.</li>
   * <li>throw an {@link java.lang.IllegalArgumentException} if the <code>key</code> is not provided.</li>
   * </ul>
   *
   * @param key the key of the value to get.
   *
   * @return the {@link java.lang.Boolean} specified by the <code>key</code> or null if not found.
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not provided.
   */
  public Boolean getBoolean(String key);


}
