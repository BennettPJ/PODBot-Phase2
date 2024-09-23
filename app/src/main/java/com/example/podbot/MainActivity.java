package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.content.SharedPreferences;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUserName = findViewById(R.id.editTextUserName);
    }

    public void goToHomePage(View view) {
        String userName = editTextUserName.getText().toString().trim(); // Get the user's name from the input field

        // Check if the input is not empty
        if (userName.isEmpty()) {
            Toast.makeText(this, "Please enter your name or employee ID", Toast.LENGTH_SHORT).show(); // Show a Toast message indicating that the input is required
        } else {

            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE); // Store the user's name in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USER_NAME", userName);
            editor.apply();  // Save the data

            // Move to the HomePageActivity
            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent);
        }
    }
}