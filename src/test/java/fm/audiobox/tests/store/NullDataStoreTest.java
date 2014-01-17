/*
 * ==
 * Copyright (c) 2009-2014 iCoreTech, Inc.
 * This file is part of the audiobox-jlib project.
 * ==
 *
 * @author keytwo
 * @copyright Copyright (c) 2009-2014 iCoreTech, Inc.
 * @license iCoreTech, Inc. Private License
 */

package fm.audiobox.tests.store;


import fm.audiobox.core.store.impl.NullDataStore;
import fm.audiobox.tests.AudioBoxTest;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class NullDataStoreTest extends AudioBoxTest {

  private NullDataStore nds = new NullDataStore();

  private static final String TEST_KEY = "test_key";



  /* ============================== */
  /* Saving exceptions expectations */
  /* ============================== */


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnObjectSavingIfNoKey() {
    nds.save(null, new Object());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnStringSavingIfNoKey() {
    nds.save(null, "");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnLongSavingIfNoKey() {
    nds.save(null, 1L);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnDoubleSavingIfNoKey() {
    nds.save(null, 1.0D);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnBooleanSavingIfNoKey() {
    nds.save(null, true);
  }


  /* =========================== */
  /* Saving success expectations */
  /* =========================== */


  @Test
  public void testSuccessOnObjectSaving() {
    assertTrue(nds.save(TEST_KEY, new Object()));
  }


  @Test
  public void testSuccessOnStringSaving() {
    assertTrue(nds.save(TEST_KEY, ""));
  }


  @Test
  public void testSuccessOnIntSaving() {
    assertTrue(nds.save(TEST_KEY, 1));
  }


  @Test
  public void testSuccessOnLongSaving() {
    assertTrue(nds.save(TEST_KEY, 1L));
  }


  @Test
  public void testSuccessOnDoubleSaving() {
    assertTrue(nds.save(TEST_KEY, 1D));
  }


  @Test
  public void testSuccessOnBooleanSaving() {
    assertTrue(nds.save(TEST_KEY, true));
  }


  /* ==================== */
  /* Removal expectations */
  /* ==================== */


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnKeyRemovalIfNoKey() {
    nds.remove(null);
  }


  @Test
  public void testSuccessOnRemoveKey() {
    assertTrue(nds.remove(TEST_KEY));
  }


  /* =============================== */
  /* Getters exceptions expectations */
  /* =============================== */


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnGettingObjectIfNoKey() {
    nds.get(null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnGettingStringIfNoKey() {
    nds.getString(null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnGettingLongIfNoKey() {
    nds.getLong(null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnGettingDoubleIfNoKey() {
    nds.getDouble(null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionOnGettingBooleanIfNoKey() {
    nds.getBoolean(null);
  }


  /* ============================ */
  /* Getters success expectations */
  /* ============================ */


  @Test
  public void testOnGettingObject() {
    assertNull("getting Object from NullDataStore should result in null", nds.get(TEST_KEY));
  }


  @Test
  public void testOnGettingString() {
    assertNull("getting String from NullDataStore should result in null", nds.getString(TEST_KEY));
  }


  @Test
  public void testOnGettingLong() {
    assertNull("getting Long from NullDataStore should result in null", nds.getLong(TEST_KEY));
  }


  @Test
  public void testOnGettingDouble() {
    assertNull("getting Double from NullDataStore should result in null", nds.getDouble(TEST_KEY));
  }


  @Test
  public void testSuccessOnGettingBoolean() {
    assertNull("getting Boolean from NullDataStore should result in null", nds.getBoolean(TEST_KEY));
  }
}
