package Utilities;

import org.apache.logging.log4j.LogManager;

public class Logger {

    static org.apache.logging.log4j.Logger log = LogManager.getLogger();

    public static void logMessage(String msg) {
        log.info(msg);
    }
}
