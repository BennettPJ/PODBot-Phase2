package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        // Common Information
        TextView missionIDTextView = findViewById(R.id.missionIdTextView);
        TextView botTypeTextView = findViewById(R.id.botTypeTextView);
        missionIDTextView.setText("Mission ID: " + missionID);
        botTypeTextView.setText("Bot Type: " + botType);

        // Check if botType is "AirBot" or "GroundBot" and display relevant information
        if ("AirBot".equals(botType)) {
            // AirBot-specific information
            TextView airBotLightTextView = findViewById(R.id.airBotLightTextView);
            TextView airBotOperationTimeTextView = findViewById(R.id.airBotOperationTimeTextView);
            TextView airBotCommTextView = findViewById(R.id.airBotCommTextView);
            TextView airBotRangeTextView = findViewById(R.id.airBotRangeTextView);
            TextView airBotVisualTextView = findViewById(R.id.airBotVisualTextView);
            TextView airBotPayloadTextView = findViewById(R.id.airBotPayloadTextView);

            // Set AirBot fields visible
            airBotLightTextView.setVisibility(View.VISIBLE);
            airBotOperationTimeTextView.setVisibility(View.VISIBLE);
            airBotCommTextView.setVisibility(View.VISIBLE);
            airBotRangeTextView.setVisibility(View.VISIBLE);
            airBotVisualTextView.setVisibility(View.VISIBLE);
            airBotPayloadTextView.setVisibility(View.VISIBLE);

            // Set values for AirBot-specific fields
            airBotLightTextView.setText("Light Conditions: " + intent.getStringExtra("airBotLight"));
            airBotOperationTimeTextView.setText("Operation Time: " + intent.getStringExtra("airBotOperationTime"));
            airBotCommTextView.setText("2-Way Communication: " + intent.getStringExtra("airBotComm"));
            airBotRangeTextView.setText("Communication Range: " + intent.getStringExtra("airBotRange"));
            airBotVisualTextView.setText("Visual Communication: " + intent.getStringExtra("airBotVisual"));
            airBotPayloadTextView.setText("Payload Capacity: " + intent.getStringExtra("airBotPayload"));

        } else if ("GroundBot".equals(botType)) {
            // GroundBot-specific information
            TextView groundBotLightTextView = findViewById(R.id.groundBotLightTextView);
            TextView groundBotOperationTimeTextView = findViewById(R.id.groundBotOperationTimeTextView);
            TextView groundBotCommTextView = findViewById(R.id.groundBotCommTextView);
            TextView groundBotRangeTextView = findViewById(R.id.groundBotRangeTextView);
            TextView groundBotVisualTextView = findViewById(R.id.groundBotVisualTextView);
            TextView groundBotPayloadTextView = findViewById(R.id.groundBotPayloadTextView);

            // Set GroundBot fields visible
            groundBotLightTextView.setVisibility(View.VISIBLE);
            groundBotOperationTimeTextView.setVisibility(View.VISIBLE);
            groundBotCommTextView.setVisibility(View.VISIBLE);
            groundBotRangeTextView.setVisibility(View.VISIBLE);
            groundBotVisualTextView.setVisibility(View.VISIBLE);
            groundBotPayloadTextView.setVisibility(View.VISIBLE);

            // Set values for GroundBot-specific fields
            groundBotLightTextView.setText("Light Conditions: " + intent.getStringExtra("groundBotLight"));
            groundBotOperationTimeTextView.setText("Operation Time: " + intent.getStringExtra("groundBotOperationTime"));
            groundBotCommTextView.setText("2-Way Communication: " + intent.getStringExtra("groundBotComm"));
            groundBotRangeTextView.setText("Communication Range: " + intent.getStringExtra("groundBotRange"));
            groundBotVisualTextView.setText("Visual Communication: " + intent.getStringExtra("groundBotVisual"));
            groundBotPayloadTextView.setText("Payload Capacity: " + intent.getStringExtra("groundBotPayload"));
        }
    }

    public void backToConfig(View view) {
        Intent intent = new Intent(PrintConfirmActivity.this, PODBotConfigActivity.class);
        startActivity(intent);
    }

    public void goToPrint(View view) {
        Intent intent = new Intent(PrintConfirmActivity.this, PrintStsActivity.class);
        startActivity(intent);
    }
}