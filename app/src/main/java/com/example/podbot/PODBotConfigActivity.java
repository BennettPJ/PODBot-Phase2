package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PODBotConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podbot_config);
    }

    public void goToConfirm(View view){
        // Move to the PrintConfirmActivity
        Intent intent = new Intent(PODBotConfigActivity.this, PrintConfirmActivity.class);
        startActivity(intent);
    }

    public void goToHomePage(View view){
        // Move to the HomePageActivity
        Intent intent = new Intent(PODBotConfigActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}