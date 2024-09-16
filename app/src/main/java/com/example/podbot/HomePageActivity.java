package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void goToPODBotConfig(View view) {
        Intent intent = new Intent(HomePageActivity.this, PODBotConfigActivity.class);
        startActivity(intent);
    }

    public void goToUserLogs(View view) {
        Intent intent = new Intent(HomePageActivity.this, UserLogsActivity.class);
        startActivity(intent);
    }
}