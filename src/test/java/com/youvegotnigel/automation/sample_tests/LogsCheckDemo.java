package com.youvegotnigel.automation.sample_tests;
/**
 * @author: youvegotnigel
 * This Class is created to check the log4j2 functionality, it is an independent class.
 * The log4j2.xml file is located under the resources folder, this should be added to the project build path.
 * Log level order : TRACE > DEBUG > INFO > WARN > ERROR > FATAL > OFF, use the appropriate level in the log4j2.xml file.
 * Check the log file to see if the log message are getting added into the file.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LogsCheckDemo {

    final Logger log = LogManager.getLogger(LogsCheckDemo.class.getName());

    @Test
    public void TEST_Logging() {

        log.trace("Trace Logged message.");
        log.debug("Debug Logged message.");
        log.info("Info Logged message.");
        log.warn("Warning Logged message.");
        log.error("Error Logged message.");
        log.fatal("Fatal Logged message.");
        System.out.println("Execution completed.");
    }
}
