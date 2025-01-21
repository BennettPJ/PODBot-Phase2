package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PODBotConfigActivity extends AppCompatActivity {
    private String botConfigBinary = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podbot_config);

        // Reference to Mission ID input and bot type selection group
        EditText missionID = findViewById(R.id.missionIdInput);
        RadioGroup radioGroupBotType = findViewById(R.id.radioGroupBotType);

        // References to AirBot and GroundBot sections for visibility control
        final LinearLayout airBotSection = findViewById(R.id.airBotSection);
        final LinearLayout groundBotSection = findViewById(R.id.groundBotSection);

        // Set up listener for bot type selection
        radioGroupBotType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbAirBot) {
                airBotSection.setVisibility(View.VISIBLE);
                groundBotSection.setVisibility(View.GONE);
            } else if (checkedId == R.id.rbGroundBot) {
                groundBotSection.setVisibility(View.VISIBLE);
                airBotSection.setVisibility(View.GONE);
            }
        });

        // Button to submit configuration
        Button submitButton = findViewById(R.id.btnOk);
        submitButton.setOnClickListener(this::submitConfig);
    }

    public void goToConfirm(View view) {
        Intent intent = new Intent(PODBotConfigActivity.this, PrintConfirmActivity.class);
        startActivity(intent);
    }

    public void goToHomePage(View view) {
        Intent intent = new Intent(PODBotConfigActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    public void submitConfig(View view) {
        EditText missionID = findViewById(R.id.missionIdInput);
        String missionIDValue = missionID.getText().toString().trim();

        if (missionIDValue.isEmpty()) {
            Toast.makeText(this, "Please enter a Mission ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Capture selected bot type (AirBot or GroundBot)
        RadioGroup radioGroupBotType = findViewById(R.id.radioGroupBotType);
        int selectedBotId = radioGroupBotType.getCheckedRadioButtonId();

        if (selectedBotId == -1) {
            Toast.makeText(this, "Please select whether the bot is GroundBot or AirBot", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedBotType = findViewById(selectedBotId);
        String botType = selectedBotType.getText().toString();

        // Perform error checking for radio button selections
        if (botType.equals("AirBot") && !validateAirBotSelections()) {
            return; // Validation failed for AirBot
        } else if (botType.equals("GroundBot") && !validateGroundBotSelections()) {
            return; // Validation failed for GroundBot
        }

        // Initialize intent to pass data to PrintConfirmActivity
        Intent intent = new Intent(this, PrintConfirmActivity.class);
        intent.putExtra("missionID", missionIDValue);
        intent.putExtra("botType", botType);

        // Collect answers based on the selected bot type
        if (botType.equals("AirBot")) {
            collectAirBotData(intent);
        } else if (botType.equals("GroundBot")) {
            collectGroundBotData(intent);
        }

        startActivity(intent);
    }

    private boolean validateAirBotSelections() {
        // Validate each AirBot-specific radio group
        if (!isRadioGroupChecked(R.id.radioGroupLightConditionsAirBot)) {
            showError("Please select light conditions for AirBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupOperationTimeAirBot)) {
            showError("Please select operation time for AirBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupCommAirBot)) {
            showError("Please select communication requirements for AirBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupRangeAirBot)) {
            showError("Please select range for AirBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupVisualAirBot)) {
            showError("Please select visual requirements for AirBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupPayloadAirBot)) {
            showError("Please select payload capacity for AirBot.");
            return false;
        }
        return true;
    }

    private boolean validateGroundBotSelections() {
        // Validate each GroundBot-specific radio group
        if (!isRadioGroupChecked(R.id.radioGroupLightConditionsGroundBot)) {
            showError("Please select light conditions for GroundBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupOperationTimeGroundBot)) {
            showError("Please select operation time for GroundBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupCommGroundBot)) {
            showError("Please select communication requirements for GroundBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupRangeGroundBot)) {
            showError("Please select range for GroundBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupVisualGroundBot)) {
            showError("Please select visual requirements for GroundBot.");
            return false;
        }
        if (!isRadioGroupChecked(R.id.radioGroupPayloadGroundBot)) {
            showError("Please select payload capacity for GroundBot.");
            return false;
        }
        return true;
    }

    private boolean isRadioGroupChecked(int groupId) {
        RadioGroup group = findViewById(groupId);
        return group.getCheckedRadioButtonId() != -1;
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void collectAirBotData(Intent intent) {
        botConfigBinary = "00"; // The starting code for AirBot

        // Append binary for Light Conditions
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupLightConditionsAirBot);

        // Append binary for Operation Time
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupOperationTimeAirBot);

        // Append binary for Communication Requirements
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupCommAirBot);

        // Append binary for Range
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupRangeAirBot);

        // Append binary for Visual Communication
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupVisualAirBot);

        // Append binary for Payload
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupPayloadAirBot);

        //store botConfigBinary so its globally available
        saveBotConfigBinary(botConfigBinary);

        // Retrieve selected options as strings and pass them to the intent
        RadioButton rbAirBotLight = findViewById(((RadioGroup) findViewById(R.id.radioGroupLightConditionsAirBot)).getCheckedRadioButtonId());
        RadioButton rbAirBotOperationTime = findViewById(((RadioGroup) findViewById(R.id.radioGroupOperationTimeAirBot)).getCheckedRadioButtonId());
        RadioButton rbAirBotComm = findViewById(((RadioGroup) findViewById(R.id.radioGroupCommAirBot)).getCheckedRadioButtonId());
        RadioButton rbAirBotRange = findViewById(((RadioGroup) findViewById(R.id.radioGroupRangeAirBot)).getCheckedRadioButtonId());
        RadioButton rbAirBotVisual = findViewById(((RadioGroup) findViewById(R.id.radioGroupVisualAirBot)).getCheckedRadioButtonId());
        RadioButton rbAirBotPayload = findViewById(((RadioGroup) findViewById(R.id.radioGroupPayloadAirBot)).getCheckedRadioButtonId());

        intent.putExtra("airBotLight", rbAirBotLight.getText().toString());
        intent.putExtra("airBotOperationTime", rbAirBotOperationTime.getText().toString());
        intent.putExtra("airBotComm", rbAirBotComm.getText().toString());
        intent.putExtra("airBotRange", rbAirBotRange.getText().toString());
        intent.putExtra("airBotVisual", rbAirBotVisual.getText().toString());
        intent.putExtra("airBotPayload", rbAirBotPayload.getText().toString());
    }

    private void collectGroundBotData(Intent intent) {
        botConfigBinary = "01"; // The starting code for GroundBot

        // Append binary for Light Conditions
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupLightConditionsGroundBot);

        // Append binary for Operation Time
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupOperationTimeGroundBot);

        // Append binary for Communication Requirements
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupCommGroundBot);

        // Append binary for Range
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupRangeGroundBot);

        // Append binary for Visual Communication
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupVisualGroundBot);

        // Append binary for Payload
        botConfigBinary += getBinaryFromSelectedOption(R.id.radioGroupPayloadGroundBot);

        //store botConfigBinary so its globally available
        saveBotConfigBinary(botConfigBinary);

        // Retrieve selected options as strings and pass them to the intent
        RadioButton rbGroundBotLight = findViewById(((RadioGroup) findViewById(R.id.radioGroupLightConditionsGroundBot)).getCheckedRadioButtonId());
        RadioButton rbGroundBotOperationTime = findViewById(((RadioGroup) findViewById(R.id.radioGroupOperationTimeGroundBot)).getCheckedRadioButtonId());
        RadioButton rbGroundBotComm = findViewById(((RadioGroup) findViewById(R.id.radioGroupCommGroundBot)).getCheckedRadioButtonId());
        RadioButton rbGroundBotRange = findViewById(((RadioGroup) findViewById(R.id.radioGroupRangeGroundBot)).getCheckedRadioButtonId());
        RadioButton rbGroundBotVisual = findViewById(((RadioGroup) findViewById(R.id.radioGroupVisualGroundBot)).getCheckedRadioButtonId());
        RadioButton rbGroundBotPayload = findViewById(((RadioGroup) findViewById(R.id.radioGroupPayloadGroundBot)).getCheckedRadioButtonId());

        intent.putExtra("groundBotLight", rbGroundBotLight.getText().toString());
        intent.putExtra("groundBotOperationTime", rbGroundBotOperationTime.getText().toString());
        intent.putExtra("groundBotComm", rbGroundBotComm.getText().toString());
        intent.putExtra("groundBotRange", rbGroundBotRange.getText().toString());
        intent.putExtra("groundBotVisual", rbGroundBotVisual.getText().toString());
        intent.putExtra("groundBotPayload", rbGroundBotPayload.getText().toString());
    }

    private String getBinaryFromSelectedOption(int radioGroupId) {
        RadioGroup radioGroup = findViewById(radioGroupId);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        View selectedButton = findViewById(selectedId);

        // Loop through the RadioGroup children to find the index of the selected button
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            if (radioGroup.getChildAt(i) == selectedButton) {
                return String.format("%2s", Integer.toBinaryString(i)).replace(' ', '0'); // Convert index to 2-bit binary
            }
        }
        return "00"; // Default value if no button is selected
    }

    private void saveBotConfigBinary(String config) {
        getSharedPreferences("AppConfig", MODE_PRIVATE)
                .edit()
                .putString("botConfigBinary", config)
                .apply();
    }

}