package com.example.podbot;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AuditLogger {
    private static final String FILE_NAME = "audit_log.csv";
    private static final String HEADER = "Timestamp,Action,User\n";

    // Method to create the log file with headers if it doesn't exist
    private static void createLogFileIfNeeded(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);

        // Check if the file exists; if not, create it with headers
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(HEADER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // General method to log an action (login or logout)
    public static void logAction(Context context, String action, String username) {
        createLogFileIfNeeded(context);

        // Get the current timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        String entry = timestamp + "," + action + "," + username + "\n";

        // Append the log entry to the file
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND)) {
            fos.write(entry.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read and format log entries as a list of strings
    public static List<String> readLogEntries(Context context) {
        List<String> logEntries = new ArrayList<>();
        File file = new File(context.getFilesDir(), FILE_NAME);

        // Check if the file exists
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                // Skip the header line if it exists
                br.readLine();

                while ((line = br.readLine()) != null) {
                    // Split each line by comma
                    String[] fields = line.split(",");

                    if (fields.length == 3) {
                        String timestamp = fields[0].trim();
                        String action = fields[1].trim();
                        String user = fields[2].trim();

                        // Format the entry for display
                        String formattedEntry = "Date: " + timestamp + "\n" +
                                "Action: " + action + "\n" +
                                "User: " + user;

                        logEntries.add(formattedEntry); // Add formatted entry to list
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logEntries;
    }

    public static void clearLogFile(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (file.exists()) {
            file.delete();
            createLogFileIfNeeded(context);  // Recreate the file with headers
        }
    }
}