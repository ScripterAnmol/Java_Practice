package week2.helperFunction;

public class ConsoleLogger implements Logger{
    @Override
    public void logInfo(String message) {
        System.out.println("Console INFO: " + message);
    }

    @Override
    public void logWarning(String message) {
        System.out.println("Console WARNING: " + message);
    }

    @Override
    public void logError(String message) {
        System.out.println("Console ERROR: " + message);
    }
}
