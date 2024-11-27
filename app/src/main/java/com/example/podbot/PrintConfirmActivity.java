package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrintConfirmActivity extends AppCompatActivity {

    private String missionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_confirm);


        // Get the Intent that started this activity and extract the data
        Intent intent = getIntent();
        missionID = intent.getStringExtra("missionID");
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
        // Get the intent and the data provided by the user
        Intent intent = getIntent();
        String botType = intent.getStringExtra("botType");

        Intent PrinterStatusIntent = new Intent(PrintConfirmActivity.this, PrintStsActivity.class);

        if ("AirBot".equals(botType)) {
            // Retrieve AirBot-specific data
            String lightConditions = intent.getStringExtra("airBotLight");
            String operationTime = intent.getStringExtra("airBotOperationTime");
            String communication = intent.getStringExtra("airBotComm");
            String range = intent.getStringExtra("airBotRange");
            String visualCommunication = intent.getStringExtra("airBotVisual");
            String payload = intent.getStringExtra("airBotPayload");

            //FIXME: Remove the following lines -- Just for Demo purpose
            PrinterStatusIntent.putExtra("MissionID", missionID);
            PrinterStatusIntent.putExtra("Printer-1", "AB_Arms");
            PrinterStatusIntent.putExtra("Printer-2", "AB-Top_plate");
            PrinterStatusIntent.putExtra("Printer-3", "AB-Bottom_plate");

            //FIXME: Add in this truth table checking
//            if("Daytime".equals(lightConditions) && "15 minutes".equals(operationTime) &&
//                    "Yes".equals(communication) && "250 meters".equals(range) &&
//                    "30 minutes".equals(visualCommunication) && "0 pounds".equals(payload)
//            ){
//                //FIXME: BOT CONFIG 1
//            }
//            else if("Nighttime".equals(lightConditions) && "15 minutes".equals(operationTime) &&
//                    "Yes".equals(communication) && "250 meters".equals(range) &&
//                    "30 minutes".equals(visualCommunication) && "0 pounds".equals(payload)
//            ){
//                //FIXME: BOT CONFIG 2
//            }
//            else if("Both".equals(lightConditions) && "15 minutes".equals(operationTime) &&
//                    "Yes".equals(communication) && "250 meters".equals(range) &&
//                    "30 minutes".equals(visualCommunication) && "0 pounds".equals(payload)
//            ){
//                //FIXME: BOT CONFIG 3
//            }


        }
        else if ("GroundBot".equals(botType)) {
            // Retrieve GroundBot-specific data
            String lightConditions = intent.getStringExtra("groundBotLight");
            String operationTime = intent.getStringExtra("groundBotOperationTime");
            String communication = intent.getStringExtra("groundBotComm");
            String range = intent.getStringExtra("groundBotRange");
            String visualCommunication = intent.getStringExtra("groundBotVisual");
            String payload = intent.getStringExtra("groundBotPayload");

            //FIXME: Remove the following lines -- Just for Demo purpose
            PrinterStatusIntent.putExtra("MissionID", missionID);
            PrinterStatusIntent.putExtra("Printer-1", "GB-Front_axle");
            PrinterStatusIntent.putExtra("Printer-2", "GB-Top_plate");
            PrinterStatusIntent.putExtra("Printer-3", "GB-Bottom_plate");

            //FIXME: Add in this truth table checking
//            if("Daytime".equals(lightConditions) && "15 minutes".equals(operationTime) &&
//                    "Yes".equals(communication) && "250 meters".equals(range) &&
//                    "30 minutes".equals(visualCommunication) && "0 pounds".equals(payload)
//            ){
//                //FIXME: BOT CONFIG 1
//            }
//            else if("Nighttime".equals(lightConditions) && "15 minutes".equals(operationTime) &&
//                    "Yes".equals(communication) && "250 meters".equals(range) &&
//                    "30 minutes".equals(visualCommunication) && "0 pounds".equals(payload)
//            ){
//                //FIXME: BOT CONFIG 2
//            }
//            else if("Both".equals(lightConditions) && "15 minutes".equals(operationTime) &&
//                    "Yes".equals(communication) && "250 meters".equals(range) &&
//                    "30 minutes".equals(visualCommunication) && "0 pounds".equals(payload)
//            ){
//                //FIXME: BOT CONFIG 3
//            }
        }

        // Navigate to the PrintStsActivity after deciding what to print
        startActivity(PrinterStatusIntent);
    }
}