package week2.helperFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void logInfo(String message) {
        logToFile("File INFO: " + message);
    }

    @Override
    public void logWarning(String message) {
        logToFile("File WARNING: " + message);
    }

    @Override
    public void logError(String message) {
        logToFile("File ERROR: " + message);
    }

    private void logToFile(String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle or log the exception as needed
        }
    }
}
