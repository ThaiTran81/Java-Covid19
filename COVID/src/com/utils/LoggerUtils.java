package com.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {
    private static final Logger logger = LogManager.getLogger(LoggerUtils.class.getName());
    public static void main(String[] args) {

        logger.fatal("DEATH MESSAGE");
        logger.debug("Debug Message Logged !!!");
        logger.info("Info Message Logged !!!");
    }
}
