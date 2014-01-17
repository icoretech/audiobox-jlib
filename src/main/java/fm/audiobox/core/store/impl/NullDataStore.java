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

package fm.audiobox.core.store.impl;

import fm.audiobox.core.store.interfaces.BaseDataStore;
import org.apache.commons.lang3.StringUtils;

/**
 * NullDataStore is exactly what the name suggests.
 * <p/>
 * It saves nothing and gets null when requested.
 * <p/>
 * It's the most basic {@link fm.audiobox.core.store.interfaces.BaseDataStore}
 * implementation used mainly for testing purpose
 * or when saving data is not important.
 */
public class NullDataStore implements BaseDataStore {


  @Override
  public boolean save(String key, Object value) {
    validateKey(key);
    return true;
  }


  @Override
  public boolean save(String key, String value) {
    validateKey(key);
    return true;
  }


  @Override
  public boolean save(String key, Long value) {
    validateKey(key);
    return true;
  }


  @Override
  public boolean save(String key, Double value) {
    validateKey(key);
    return true;
  }


  @Override
  public boolean save(String key, Boolean value) {
    validateKey(key);
    return true;
  }


  @Override
  public boolean remove(String key) {
    validateKey(key);
    return true;
  }


  @Override
  public Object get(String key) {
    validateKey(key);
    return null;
  }


  @Override
  public String getString(String key) {
    validateKey(key);
    return null;
  }


  @Override
  public Long getLong(String key) {
    validateKey(key);
    return null;
  }


  @Override
  public Double getDouble(String key) {
    validateKey(key);
    return null;
  }


  @Override
  public Boolean getBoolean(String key) {
    validateKey(key);
    return null;
  }


  /**
   * Checks if provided <code>key</code> is valid.
   *
   * @throws java.lang.IllegalArgumentException if <code>key</code> is not valid.
   */
  private void validateKey(String key) {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException(ARGUMENT_ERROR);
    }
  }
}
