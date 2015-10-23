package net.topaze.util.logs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CategoryLoggerTester {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	List<String> categories = Arrays.asList((new String[]{"CAT_1", "CAT_2", "CAT_3"}));
	List<String> levels = Arrays.asList((new String[]{
		LoggerLevel.TRACE.toString().toLowerCase(),
		LoggerLevel.DEBUG.toString().toLowerCase(),
		LoggerLevel.INFO.toString().toLowerCase(),
		LoggerLevel.WARN.toString().toLowerCase(),
		LoggerLevel.ERROR.toString().toLowerCase()
	}));
	String loggerName= "_MYLOG";
	LoggerFactory loggerFactory = new LogbackLoggerFactory();
	CategoryLogger catLog = new CategoryLogger();
	catLog.setCategories(categories);
	catLog.setLoggerName(loggerName);
	catLog.setLoggerLevel(LoggerLevel.TRACE.toString());
	catLog.setLoggerFactory(loggerFactory);
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
