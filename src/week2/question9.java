package week2;

import week2.helperFunction.Logger;
import week2.helperFunction.ConsoleLogger;
import week2.helperFunction.DatabaseLogger;
import week2.helperFunction.FileLogger;

public class question9{
    public static void main(String[] args) {
        Logger consoleLogger = new ConsoleLogger();
        consoleLogger.logInfo("This is an informational message.");
        consoleLogger.logWarning("This is a warning message.");
        consoleLogger.logError("This is an error message.");

        Logger fileLogger = new FileLogger("F:/errLogs/error.txt");
        fileLogger.logInfo("Log to file: Informational message");
        fileLogger.logWarning("Log to file: Warning message");
        fileLogger.logError("Log to file: Error message");

        Logger databaseLogger = new DatabaseLogger();
        databaseLogger.logInfo("Log to database: Informational message");
        databaseLogger.logWarning("Log to database: Warning message");
        databaseLogger.logError("Log to database: Error message");
    }
}
