package shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class for creating logger instances. This class provides static methods to get logger instances,
 * thus avoiding the need for object creation. It utilizes the underlying SLF4J LoggerFactory.
 */
public final class CustomLoggerFactory {

    // Private constructor to prevent instantiation of the utility class.
    private CustomLoggerFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Provides a logger for a given class.
     *
     * @param clazz The class for which the logger is to be created.
     * @return The logger associated with the specified class.
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
