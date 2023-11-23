package com.example.question3.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.net.URL;

@Service
public class FileSavingService {
    public static String fetchAndConvertToBase64(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } finally {
            connection.disconnect();
        }
    }

    public static void downloadFile(String fileUrl, String fileName, String destination) throws IOException {
        URL url = new URL(fileUrl);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // Handle redirects
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
            String redirectUrl = httpURLConnection.getHeaderField("Location");
            url = new URL(redirectUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }

        // Extract file extension from the Content-Disposition header
        String fileExtension = getFileExtension(httpURLConnection.getHeaderField("Content-Disposition"));

        // If the Content-Disposition header is not present or doesn't contain the filename, fallback to extracting from the URL
        if (fileExtension.isEmpty()) {
            fileExtension = getFileExtension(url.getPath());
        }

        // Append the file extension to the provided filename
        String finalFileName = fileName + "." + fileExtension;

        try (InputStream inputStream = httpURLConnection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(destination + File.separator + finalFileName)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    private static String getFileExtension(String header) {
        if (header != null && header.contains("filename=")) {
            String filename = header.substring(header.indexOf("filename=") + 9);
            return filename.substring(filename.lastIndexOf('.') + 1);
        }
        return "";
    }

    public void saveObjectToFile(Object objectToSave, String fileName, String fileLocation) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String jsonContent = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectToSave);

            File directory = new File(fileLocation);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(directory, fileName);

            org.apache.commons.io.FileUtils.writeStringToFile(file, jsonContent, "UTF-8");

            System.out.println("Object saved to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving object to file: " + e.getMessage());
        }
    }
}
