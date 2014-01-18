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

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * Generic test case class.
 * <p/>
 * Created by keytwo on 16/01/14.
 */
public class AudioBoxTest {

  protected Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

  private long time_start = 0;

  private boolean printTimingLog = false;

  public Config fixtures = ConfigFactory.load("fixtures");;

  @Rule
  public TestName name = new TestName();


  @Before
  public void setUp() {
    // Remove existing handlers attached to j.u.l root logger
    SLF4JBridgeHandler.removeHandlersForRootLogger();

    // add SLF4JBridgeHandler to j.u.l's root logger
    SLF4JBridgeHandler.install();

    printTimingLog = true;
    time_start = System.currentTimeMillis();
    logger.debug("*** Tests started: " + name.getMethodName());
  }


  @After
  public void tearDown() {
    long millis_taken = System.currentTimeMillis() - time_start;
    double seconds_taken = (double) millis_taken / 1000D;
    if (printTimingLog) {
      logger.debug("*** Test completed in " + seconds_taken + " seconds");
    }
  }


}
