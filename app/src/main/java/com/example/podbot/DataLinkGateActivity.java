package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DataLinkGateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_link_gate);
    }

    public void goToHome(View view) {
        // Go back to the home page
        Intent intent = new Intent(DataLinkGateActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    public void goToLDVid(View view) {
        // Go back to the home page
        Intent intent = new Intent(DataLinkGateActivity.this, LDVidActivity.class);
        startActivity(intent);
    }

    public void goToHDVid(View view) {
        // Go back to the home page
        Intent intent = new Intent(DataLinkGateActivity.this, DataLinkActivity.class);
        startActivity(intent);
    }
}