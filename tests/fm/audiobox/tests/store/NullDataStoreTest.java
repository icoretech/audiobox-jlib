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


import fm.audiobox.store.impl.NullDataStore;
import fm.audiobox.tests.AudioBoxTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NullDataStoreTest extends AudioBoxTest {

  private NullDataStore nds;

  private static final String TEST_KEY = "test_key";


  @Before
  public void setUp() {
    super.setUp();
    nds = new NullDataStore();
  }



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
    assertNull(nds.get(TEST_KEY));
  }


  @Test
  public void testOnGettingString() {
    assertNull(nds.getString(TEST_KEY));
  }


  @Test
  public void testOnGettingLong() {
    assertNull(nds.getLong(TEST_KEY));
  }


  @Test
  public void testOnGettingDouble() {
    assertNull(nds.getDouble(TEST_KEY));
  }


  @Test
  public void testSuccessOnGettingBoolean() {
    assertNull(nds.getBoolean(TEST_KEY));
  }
}
