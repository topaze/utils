package net.topaze.util.logs;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

/**
 * @author topaze
 *
 */
public class CategoryLogger {

    private List<String> categories;
    private String loggerName;
    private String loggerLevel = LoggerLevel.INFO.toString();
    private LoggerFactory loggerFactory;
    private Map<String, Logger> loggers;

    /**
     * 
     */
    public CategoryLogger(){	
    }

    public void init() {
	loggers = loggerFactory.createLoggers(categories, loggerName, loggerLevel);	
    }

    public void trace(String category, String message) {
	loggers.get(category).trace(message);
    }

    public void trace(String category, String format, Object... args) {
	loggers.get(category).trace(format, args);    
    }

    public void trace(String category, String message, Throwable t) {
	loggers.get(category).trace(message, t);
    }

    public void debug(String category, String message) {
	loggers.get(category).debug(message);
    }

    public void debug(String category, String format, Object... args) {
	loggers.get(category).debug(format, args);    
    }

    public void debug(String category, String message, Throwable t) {
	loggers.get(category).debug(message, t);
    }

    public void info(String category, String message) {
	loggers.get(category).info(message);
    }

    public void info(String category, String format, Object... args) {
	loggers.get(category).info(format, args);    
    }

    public void info(String category, String message, Throwable t) {
	loggers.get(category).info(message, t);
    }

    public void warn(String category, String message) {
	loggers.get(category).warn(message);
    }

    public void warn(String category, String format, Object... args) {
	loggers.get(category).warn(format, args);    
    }

    public void warn(String category, String message, Throwable t) {
	loggers.get(category).warn(message, t);
    }

    public void error(String category, String message) {
	loggers.get(category).error(message);
    }

    public void error(String category, String format, Object... args) {
	loggers.get(category).error(format, args);    
    }

    public void error(String category, String message, Throwable t) {
	loggers.get(category).error(message, t);    
    }

    public List<String> getCategories() {
	return categories;
    }

    public void setCategories(List<String> categories) {
	this.categories = categories;
    }

    public String getLoggerName() {
	return loggerName;
    }

    public void setLoggerName(String loggerName) {
	this.loggerName = loggerName;
    }

    public LoggerFactory getLoggerFactory() {
	return loggerFactory;
    }

    public void setLoggerFactory(LoggerFactory loggerFactory) {
	this.loggerFactory = loggerFactory;
    }

    public String getLoggerLevel() {
        return loggerLevel;
    }

    public void setLoggerLevel(String loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

}
