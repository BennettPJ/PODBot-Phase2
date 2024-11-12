package com.example.podbot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class UserLogsActivity extends AppCompatActivity {

    private static final String ADMIN_PASSWORD = "PODBot_Rocks";  // FIXME: This will need to change

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logs);

        // Retrieve the log entries from AuditLogger
        List<String> logEntries = AuditLogger.readLogEntries(this);

        // Set up the ListView with the log entries
        ListView logListView = findViewById(R.id.logListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, logEntries);
        logListView.setAdapter(adapter);
    }

    // Method to prompt for password before clearing logs
    public void promptClearLogs(View view) {
        // Inflate the password input layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View passwordPromptView = inflater.inflate(R.layout.dialog_password_prompt, null);
        final EditText passwordInput = passwordPromptView.findViewById(R.id.passwordInput);

        // Build the AlertDialog for password prompt
        new AlertDialog.Builder(this)
                .setTitle("Enter Password")
                .setView(passwordPromptView)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String enteredPassword = passwordInput.getText().toString();
                        if (enteredPassword.equals(ADMIN_PASSWORD)) {
                            // Password is correct, clear the logs
                            AuditLogger.clearLogFile(UserLogsActivity.this);
                            refreshLogDisplay();
                            Toast.makeText(UserLogsActivity.this, "Logs cleared successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Password is incorrect
                            Toast.makeText(UserLogsActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // Method to refresh the log display after clearing logs
    private void refreshLogDisplay() {
        List<String> logEntries = AuditLogger.readLogEntries(this);
        ListView logListView = findViewById(R.id.logListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, logEntries);
        logListView.setAdapter(adapter);
    }

    public void goToHome(View view) {
        // Navigate back to the Home page
        Intent intent = new Intent(UserLogsActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}