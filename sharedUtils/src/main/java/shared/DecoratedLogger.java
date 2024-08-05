package shared;

public class DecoratedLogger extends LoggerAdapter {

    public DecoratedLogger(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void info(String msg) {
        super.info(addMetadata(msg));
    }

    @Override
    public void error(String msg, Throwable t) {
        super.error(addMetadata(msg), t);
    }

    @Override
    public void debug(String msg) {
        super.debug(addMetadata(msg));
    }

    private String addMetadata(String message) {
        return String.format("[%s] [%s] %s", new java.util.Date(), Thread.currentThread().getName(), message);
    }
}
