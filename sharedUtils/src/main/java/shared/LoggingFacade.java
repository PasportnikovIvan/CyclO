package shared;

/**
 * Facade for logging functionalities, providing a simplified interface to the logging system.
 */
public class LoggingFacade {
    /**
     * Logs an info message for the specified class.
     *
     * @param clazz the class for which the logger is to be created
     * @param msg   the message to log
     */
    public static void logInfo(Class<?> clazz, String msg) {
        new DecoratedLogger(clazz).info(msg);
    }

    /**
     * Logs an error message for the specified class.
     *
     * @param clazz the class for which the logger is to be created
     * @param msg   the message to log
     * @param t     the throwable to log
     */
    public static void logError(Class<?> clazz, String msg, Throwable t) {
        new DecoratedLogger(clazz).error(msg, t);
    }

    /**
     * Logs a debug message for the specified class.
     *
     * @param clazz the class for which the logger is to be created
     * @param msg   the message to log
     */
    public static void logDebug(Class<?> clazz, String msg) {
        new DecoratedLogger(clazz).debug(msg);
    }
}
