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
            TextView airBotNavTimeTextView = findViewById(R.id.airBotNavTimeTextView);
            TextView airBotCommTextView = findViewById(R.id.airBotCommTextView);
            TextView airBotCamTextView = findViewById(R.id.airBotCamTextView);
            TextView airBotETimeTextView = findViewById(R.id.airBotETimeTextView);
            TextView airBotERangeTextView = findViewById(R.id.airBotERangeTextView);
            TextView airBotPayloadTextView = findViewById(R.id.airBotPayloadTextView);

            // Set AirBot fields visible
            airBotLightTextView.setVisibility(View.VISIBLE);
            airBotNavTimeTextView.setVisibility(View.VISIBLE);
            airBotCommTextView.setVisibility(View.VISIBLE);
            airBotCamTextView.setVisibility(View.VISIBLE);
            airBotETimeTextView.setVisibility(View.VISIBLE);
            airBotERangeTextView.setVisibility(View.VISIBLE);
            airBotPayloadTextView.setVisibility(View.VISIBLE);

            // Set values for AirBot-specific fields
            airBotLightTextView.setText("Light Conditions: " + intent.getStringExtra("airBotLight"));
            airBotNavTimeTextView.setText("Navigation Time: " + intent.getStringExtra("airBotNavTime"));
            airBotCommTextView.setText("2-Way Audio Communication: " + intent.getStringExtra("airBotComm"));
            airBotCamTextView.setText("Camera Requirement: " + intent.getStringExtra("airBotCam"));
            airBotETimeTextView.setText("Effector Time: " + intent.getStringExtra("airBotETime"));
            airBotERangeTextView.setText("Effector Range: " + intent.getStringExtra("airBotERange"));
            airBotPayloadTextView.setText("Payload Capacity: " + intent.getStringExtra("airBotPayload"));

        } else if ("GroundBot".equals(botType)) {
            // GroundBot-specific information
            TextView groundBotLightTextView = findViewById(R.id.groundBotLightTextView);
            TextView groundBotNavTimeTextView = findViewById(R.id.groundBotNavigationTimeTextView);
            TextView groundBotCommTextView = findViewById(R.id.groundBotCommTextView);
            TextView groundBotCamTextView = findViewById(R.id.groundBotCamTextView);
            TextView groundBotETimeTextView = findViewById(R.id.groundBotETimeTextView);
            TextView groundBotERangeTextView = findViewById(R.id.groundBotERangeTextView);
            TextView groundBotPayloadTextView = findViewById(R.id.groundBotPayloadTextView);

            // Set GroundBot fields visible
            groundBotLightTextView.setVisibility(View.VISIBLE);
            groundBotNavTimeTextView.setVisibility(View.VISIBLE);
            groundBotCommTextView.setVisibility(View.VISIBLE);
            groundBotCamTextView.setVisibility(View.VISIBLE);
            groundBotETimeTextView.setVisibility(View.VISIBLE);
            groundBotERangeTextView.setVisibility(View.VISIBLE);
            groundBotPayloadTextView.setVisibility(View.VISIBLE);

            // Set values for GroundBot-specific fields
            groundBotLightTextView.setText("Light Conditions: " + intent.getStringExtra("groundBotLight"));
            groundBotNavTimeTextView.setText("Navigation Time: " + intent.getStringExtra("groundBotNavTime"));
            groundBotCommTextView.setText("2-Way Audio Communication: " + intent.getStringExtra("groundBotComm"));
            groundBotCamTextView.setText("Camera Requirement: " + intent.getStringExtra("groundBotCam"));
            groundBotETimeTextView.setText("Effector Time: " + intent.getStringExtra("groundBotETime"));
            groundBotERangeTextView.setText("Effector Range: " + intent.getStringExtra("groundBotERange"));
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
            String NavTime = intent.getStringExtra("airBotNavTime");
            String communication = intent.getStringExtra("airBotComm");
            String camera = intent.getStringExtra("airBotCam");
            String eTime = intent.getStringExtra("airBotETime");
            String eRange = intent.getStringExtra("airBotERange");
            String payload = intent.getStringExtra("airBotPayload");

//            //FIXME: Remove the following lines -- Just for Demo purpose
//            PrinterStatusIntent.putExtra("MissionID", missionID);
//            PrinterStatusIntent.putExtra("Printer-1", "AB_Arms");
//            PrinterStatusIntent.putExtra("Printer-2", "AB-Top_plate");
//            PrinterStatusIntent.putExtra("Printer-3", "AB-Bottom_plate");

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