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

package fm.audiobox.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic test case class.
 * <p/>
 * Created by keytwo on 16/01/14.
 */
public class AudioBoxTest {

  protected Logger logger = LoggerFactory.getLogger("ABT");

  private long time_start = 0;

  private String className;

  private boolean printTimingLog = false;

  @Rule
  public TestName name = new TestName();


  @Before
  public void setUp() {
    printTimingLog = true;
    className = this.getClass().getSimpleName();
    time_start = System.currentTimeMillis();
    logger.debug("*** [ " + className + "] Tests started: " + name.getMethodName());
  }


  @After
  public void tearDown() {
    long millis_taken = System.currentTimeMillis() - time_start;
    double seconds_taken = (double) millis_taken / 1000D;
    if (printTimingLog) {
      logger.debug("*** [ " + className + " ] Test completed in " + seconds_taken + " seconds");
    }
  }


}
