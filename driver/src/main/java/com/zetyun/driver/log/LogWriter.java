package com.zetyun.driver.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogWriter {
    public static void debug(Class<?> clazz, String message){
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.debug(message);
    }

    public static void info(Class<?> clazz, String message){
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.info(message);
    }

    public static void error(Class<?> clazz, String message){
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.error(message);
    }

    public static void trace(Class<?> clazz, String message){
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.trace(message);
    }

    public static void warn(Class<?> clazz, String message){
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.warn(message);
    }
}
