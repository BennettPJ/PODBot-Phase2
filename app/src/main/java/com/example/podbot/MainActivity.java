package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components and any other setup here
    }

    // Method for the login button
    public void goToHomePage(View view) {
        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}