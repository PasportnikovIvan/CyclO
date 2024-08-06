package shared;

import org.slf4j.Logger;
/**
 * Adapter for SLF4J Logger providing a simplified logging interface.
 */
public class LoggerAdapter {

    private final Logger logger;

    /**
     * Constructs a LoggerAdapter for the specified class.
     *
     * @param clazz the class for which the logger is to be created
     */
    public LoggerAdapter(Class<?> clazz) {
        this.logger = CustomLoggerFactory.getLogger(clazz);
    }

    /**
     * Logs an info message.
     *
     * @param msg the message to log
     */
    public void info(String msg) {
        logger.info(msg);
    }

    /**
     * Logs an error message.
     *
     * @param msg the message to log
     * @param t   the throwable to log
     */
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    /**
     * Logs a debug message.
     *
     * @param msg the message to log
     */
    public void debug(String msg) {
        logger.debug(msg);
    }
}
