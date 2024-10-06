package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrintConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_confirm);

        // Get the Intent that started this activity and extract the data
        Intent intent = getIntent();
        String missionID = intent.getStringExtra("missionID");
        String botType = intent.getStringExtra("botType");
        String timeOfDay = intent.getStringExtra("timeOfDay");

        // Find the TextView in the layout and set the text to display the passed data
        TextView missionIDTextView = findViewById(R.id.missionIdTextView);
        TextView botTypeTextView = findViewById(R.id.botTypeTextView);
        TextView timeOfDayTextView = findViewById(R.id.timeOfDayTextView);

        missionIDTextView.setText("Mission ID: " + missionID);
        botTypeTextView.setText("Bot Type: " + botType);
        timeOfDayTextView.setText("Time of Day: " + timeOfDay);
    }
}