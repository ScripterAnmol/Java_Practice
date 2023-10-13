package week2.helperFunction;

public class DatabaseLogger implements Logger{
    @Override
    public void logInfo(String message) {
        // Implement logging to a database for informational messages
        System.out.println("Database INFO: " + message);
    }

    @Override
    public void logWarning(String message) {
        // Implement logging to a database for warning messages
        System.out.println("Database WARNING: " + message);
    }

    @Override
    public void logError(String message) {
        // Implement logging to a database for error messages
        System.out.println("Database ERROR: " + message);
    }
}
