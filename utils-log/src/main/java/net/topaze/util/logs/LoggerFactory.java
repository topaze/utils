package net.topaze.util.logs;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

public interface LoggerFactory {

    /**
     * @param categories
     * @param loggerName
     * @param loggerLevel
     * @return
     */
    Map<String, Logger> createLoggers(List<String> categories, String loggerName, String loggerLevel);
    
}
