package com.example.podbot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        Intent PrinterStatusIntent = new Intent(PrintConfirmActivity.this, PrintStsActivity.class);

        String config = getBotConfigBinary();
        //Printer variables
        String Printer1 = "";
        String Printer2 = "";
        String Printer3 = "";
        String BotToPrint = "";
        //FIXME: might refactor to nest if statements in an if-else and split out the 2 left most bits for AB and GB
        if(config.trim().equals("0110010000000010")){
            //AirBot FR-2
            BotToPrint = "AirBot Mission-" + missionID;
            Printer1 = "AB_FR2_arms";
            Printer2 = "AB_FR_2_Bottom_Plate";
            Printer3 = "AB_FR_2_Top_Plate_Hooks_Landing_Gear";
        }
        else if(config.trim().equals("0110110000111111")){
            //AirBot TD-1
            BotToPrint = "AirBot Mission-" + missionID;
            Printer1 = "AB_TD1_arms";
            Printer2 = "AB_TD1_Bottom_Plate";
            Printer3 = "AB_TD1_Top_Plate_Hooks_Landing_Gear";
        }
        else if(config.trim().equals("0010010001111100")){
            //GroundBot BP-3 and TD-1
            BotToPrint = "GroundBot Mission-" + missionID;
            Printer1 = "GB_BP3_TD1_Base_Frame_Back_Wall";
            Printer2 = "GB_BP3_TD1_Front_Axle_Support_Top_Plate";
            Printer3 = "GB_BP3_TD1_Gears";
        }
        else{
            //PODBot not configured
            BotToPrint = "PODBot Other Mission-" + missionID;
            Printer1 = "FIXME: Generic file";
            Printer2 = "FIXME: Generic file";
            Printer3 = "FIXME: Generic file";
        }

        //Save the mission ID and printer files to shared preferences
        savePrinterFiles(missionID, Printer1, Printer2, Printer3);

        //Log the username and what they printed
        AuditLogger.logAction(this, "Printed " + BotToPrint, getUsername());

        // Navigate to the PrintStsActivity after deciding what to print
        startActivity(PrinterStatusIntent);
    }

    private String getBotConfigBinary() {
        return getSharedPreferences("AppConfig", MODE_PRIVATE)
                .getString("botConfigBinary", "DEFAULT_BINARY"); // Default case
    }

    private String getUsername() {
        // Retrieve the stored user's name from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("USER_NAME", "Guest");
    }

    private void savePrinterFiles(String missionID, String printer1, String printer2, String printer3) {
        getSharedPreferences("PrinterFiles", MODE_PRIVATE)
                .edit()
                .putString("MissionID", missionID)
                .putString("Printer1", printer1)
                .putString("Printer2", printer2)
                .putString("Printer3", printer3)
                .apply();
    }
}