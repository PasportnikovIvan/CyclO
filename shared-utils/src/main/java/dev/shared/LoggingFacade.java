package dev.shared;

public class LoggingFacade {

    public static void logInfo(Class<?> clazz, String msg) {
        new DecoratedLogger(clazz).info(msg);
    }

    public static void logError(Class<?> clazz, String msg, Throwable t) {
        new DecoratedLogger(clazz).error(msg, t);
    }

    public static void logDebug(Class<?> clazz, String msg) {
        new DecoratedLogger(clazz).debug(msg);
    }
}
