package dev.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A singleton factory class for creating logger instances. This class ensures that only
 * one instance of each logger is created per class, reducing redundant instances and
 * maintaining a central point for logger configuration.
 */
public class CustomLoggerFactory {

    private static CustomLoggerFactory instance;

    // Private constructor to prevent instantiation from outside this class.
    private CustomLoggerFactory() {}

    /**
     * This method ensures that the CustomLoggerFactory is a singleton and synchronized to
     * handle multithreaded access.
     *
     * @return The single instance of CustomLoggerFactory.
     */
    public static synchronized CustomLoggerFactory getInstance() {
        if (instance == null) {
            instance = new CustomLoggerFactory();
        }
        return instance;
    }

    /**
     * Provides a logger for a given class, utilizing the underlying SLF4J LoggerFactory.
     *
     * @param clazz The class for which the logger is to be created.
     * @return The logger associated with the specified class.
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
