package shared;
/**
 * Decorates a logger to add additional context information such as timestamps and thread details.
 */
public class DecoratedLogger extends LoggerAdapter {
    /**
     * Constructs a DecoratedLogger for the specified class.
     *
     * @param clazz the class for which the logger is to be created
     */
    public DecoratedLogger(Class<?> clazz) {
        super(clazz);
    }

    /**
     * Logs an info message with additional context.
     *
     * @param msg the message to log
     */
    @Override
    public void info(String msg) {
        super.info(addMetadata(msg));
    }

    /**
     * Logs an error message with additional context.
     *
     * @param msg the message to log
     * @param t   the throwable to log
     */
    @Override
    public void error(String msg, Throwable t) {
        super.error(addMetadata(msg), t);
    }

    /**
     * Logs a debug message with additional context.
     *
     * @param msg the message to log
     */
    @Override
    public void debug(String msg) {
        super.debug(addMetadata(msg));
    }

    /**
     * Adds metadata to the log message.
     *
     * @param message the original log message
     * @return the log message with additional metadata
     */
    private String addMetadata(String message) {
        return String.format("[%s] [%s] %s", new java.util.Date(), Thread.currentThread().getName(), message);
    }
}
