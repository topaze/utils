package net.topaze.util.logs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

public class LogbackLoggerFactory implements LoggerFactory {

    @Override
    public Map<String, Logger> createLoggers(List<String> categories, String loggerName, String loggerLevel) {
	Map<String, Logger> loggers = new HashMap<>();

	categories
	.forEach(
		categorie -> {
		    String catLogName = String.format("%s%s", categorie, loggerName);
		    Logger logger = createLogger(catLogName, loggerLevel);
		    loggers.put(categorie, logger);		    
		}
		);

	return loggers;
    }

    private Logger createLogger(String loggerName, String loggerLevel) {
	LoggerContext lc = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
	PatternLayoutEncoder ple = new PatternLayoutEncoder();

	ple.setPattern("%date %level [%thread] %logger{10} [%file:%line] %msg%n");
	ple.setContext(lc);
	ple.start();
	FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
	fileAppender.setFile(String.format("logs/%s%s", loggerName, ".log"));
	fileAppender.setEncoder(ple);
	fileAppender.setContext(lc);
	fileAppender.start();

	Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(loggerName);
	((ch.qos.logback.classic.Logger)logger).addAppender(fileAppender);	
	((ch.qos.logback.classic.Logger)logger).setLevel(Level.valueOf(loggerLevel));
	((ch.qos.logback.classic.Logger)logger).setAdditive(false);

	return logger;
    }

}
