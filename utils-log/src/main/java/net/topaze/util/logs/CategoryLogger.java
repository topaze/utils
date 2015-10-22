package net.topaze.util.logs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryLogger {

    // Chargé par spring par exemple
    private List<String> categories;
    // Chargé par spring par exemple
    private String loggerNamePattern;

    // construite à partir de la map Category/loggerName
    private Map<String, Logger> loggers;

    public CategoryLogger(List<String> categories, String loggerNamePattern) {
	this.categories = categories;
	this.loggerNamePattern = loggerNamePattern;
    }

    public void init() {
	loggers = new HashMap<>();
	categories.forEach(
		category->{
		    Logger logger = LoggerFactory.getLogger(String.format("%s%s",category,loggerNamePattern));
		    loggers.put(category, logger);
		}
		);
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

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	List<String> categories = Arrays.asList((new String[]{"CAT_1", "CAT_2", "CAT_3"}));
	List<String> levels = Arrays.asList((new String[]{"trace", "debug", "info", "warn", "error"}));
	String logPattern= "_MYLOG";
	CategoryLogger catLog = new CategoryLogger(categories, logPattern);
	catLog.init();

	Random r = new Random(System.nanoTime());

	for(int i=0;i<100; i++) {
	    int n = r.nextInt(categories.size());
	    String cat = categories.get(n);
	    int l = r.nextInt(levels.size());
	    String level = levels.get(l);	   
	    Method logMethod = catLog.getClass().getMethod(level, String.class, String.class);
	    logMethod.invoke(
		    catLog,
		    cat,
		    String.format("message(%d) v=%d",i, r.nextLong())
		    );

	}

    }

}
