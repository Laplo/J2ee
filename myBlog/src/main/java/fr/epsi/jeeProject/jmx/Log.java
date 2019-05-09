package fr.epsi.jeeProject.jmx;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class Log implements LogMBean {
    private static final Logger logger = LogManager.getLogger(Log.class);
    private LoggerConfig loggerConfig;
    private LoggerContext ctx;

    public Log() {
        ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
    }

    @Override
    public String afficherNiveauLogs() {
        return logger.getLevel().toString();
    }

    @Override
    public String debug() {
        String s = "Niveau des logs passés de " + logger.getLevel().toString();

        loggerConfig.setLevel(Level.DEBUG);
        ctx.updateLoggers();

        s += " à " + logger.getLevel().toString();
        return s;
    }

    @Override
    public String info() {
        String s = "Niveau des logs passés de " + logger.getLevel().toString();

        loggerConfig.setLevel(Level.INFO);
        ctx.updateLoggers();

        s += " à " + logger.getLevel().toString();
        return s;
    }

    @Override
    public String error() {
        String s = "Niveau des logs passés de " + logger.getLevel().toString();

        loggerConfig.setLevel(Level.ERROR);
        ctx.updateLoggers();

        s += " à " + logger.getLevel().toString();
        return s;
    }
}
