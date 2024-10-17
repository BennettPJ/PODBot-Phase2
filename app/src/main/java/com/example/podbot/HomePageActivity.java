package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;  // For AppCompatActivity

import java.text.MessageFormat;


public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Retrieve the stored user's name from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("USER_NAME", "Guest");

        // Find the TextView by its ID
        TextView textViewUserName = findViewById(R.id.textViewUserName);

        // Set the user's name in the TextView
        textViewUserName.setText(MessageFormat.format("Welcome, {0}!", userName));
    }

    public void goToLogOut(View view) {
        // Move to the HomePageActivity
        Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
        startActivity(intent);
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

    public void goToDataLink(View view) {
        Intent intent = new Intent(HomePageActivity.this, DataLinkActivity.class);
        startActivity(intent);
    }

    public void goToAssemblyBOM(View view) {
        Intent intent = new Intent(HomePageActivity.this, AssemblyBomActivity.class);
        startActivity(intent);
    }

}