public class CalculusSystemBaseException extends Exception {
    public CalculusSystemBaseException() {
    }

    public CalculusSystemBaseException(String message) {
        super(message);
    }

    public CalculusSystemBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculusSystemBaseException(Throwable cause) {
        super(cause);
    }

    public CalculusSystemBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
