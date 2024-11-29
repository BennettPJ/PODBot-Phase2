package com.example.podbot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;  // For AppCompatActivity

import java.text.MessageFormat;


public class HomePageActivity extends AppCompatActivity {

    private String userName; // Store the username for use in logout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Retrieve the stored user's name from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userName = sharedPreferences.getString("USER_NAME", "Guest");

        // Find the TextView by its ID
        TextView textViewUserName = findViewById(R.id.textViewUserName);

        // Set the user's name in the TextView
        textViewUserName.setText(MessageFormat.format("Welcome, {0}!", userName));
    }

    public void goToLogOut(View view) {
        // Log the logout action using the AuditLogger
        AuditLogger.logAction(this, "logout", userName);

        // Clear the username from SharedPreferences if needed
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();

        // Move back to the MainActivity
        Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
        startActivity(intent);
        finish(); // Close HomePageActivity
    }

    public void goToPODBotConfig(View view) {
        Intent intent = new Intent(HomePageActivity.this, PODBotConfigActivity.class);
        startActivity(intent);
    }

    public void goToUserLogs(View view) {
        Intent intent = new Intent(HomePageActivity.this, UserLogsActivity.class);
        startActivity(intent);
    }

    public void goToPrintSts(View view) {
        Intent intent = new Intent(HomePageActivity.this, PrintStsActivity.class);
        startActivity(intent);
    }

    public void goToDataLinkGate(View view) {
        Intent intent = new Intent(HomePageActivity.this, DataLinkGateActivity.class);
        startActivity(intent);
    }

    public void goToAssemblyBOM(View view) {
        Intent intent = new Intent(HomePageActivity.this, AssemblyBomActivity.class);
        startActivity(intent);
    }

    public void goToUserManual(View view) {
        Intent intent = new Intent(HomePageActivity.this, UserManualActivity.class);
        intent.putExtra("source", "HomePage");
        startActivity(intent);
    }

}