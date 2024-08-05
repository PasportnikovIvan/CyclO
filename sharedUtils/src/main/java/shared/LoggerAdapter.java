package shared;

import org.slf4j.Logger;

public class LoggerAdapter {

    private final Logger logger;

    public LoggerAdapter(Class<?> clazz) {
        this.logger = CustomLoggerFactory.getLogger(clazz);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    public void debug(String msg) {
        logger.debug(msg);
    }
}
