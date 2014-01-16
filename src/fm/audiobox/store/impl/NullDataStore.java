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

package fm.audiobox.store.impl;

import fm.audiobox.store.interfaces.BaseDataStore;
import org.apache.commons.lang3.StringUtils;

/**
 * NullDataStore is exactly what the name suggests.
 * It saves nothing and gets null when requested.
 * It's the most basic BaseDataStore Implementation used mainly for testing purpose
 * or where saving data is not important.
 */
public class NullDataStore implements BaseDataStore {


  @Override
  public boolean save(String key, Object value) throws IllegalArgumentException {
    validateKey(key);
    return true;
  }

  @Override
  public boolean save(String key, String value) throws IllegalArgumentException {
    validateKey(key);
    return true;
  }

  @Override
  public boolean save(String key, Long value) throws IllegalArgumentException {
    validateKey(key);
    return true;
  }

  @Override
  public boolean save(String key, Double value) throws IllegalArgumentException {
    validateKey(key);
    return true;
  }

  @Override
  public boolean save(String key, Boolean value) throws IllegalArgumentException {
    validateKey(key);
    return true;
  }

  @Override
  public Object get(String key) throws IllegalArgumentException {
    return null;
  }

  @Override
  public String getString(String key) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Long getLong(String key) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Double getDouble(String key) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Boolean getBoolean(String key) throws IllegalArgumentException {
    return null;
  }

  private void validateKey(String key) {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException(ARGUMENT_ERROR);
    }
  }
}
