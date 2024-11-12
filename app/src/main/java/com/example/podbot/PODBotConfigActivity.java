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
        radioGroupBotType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbAirBot) {
                    airBotSection.setVisibility(View.VISIBLE);
                    groundBotSection.setVisibility(View.GONE);
                } else if (checkedId == R.id.rbGroundBot) {
                    groundBotSection.setVisibility(View.VISIBLE);
                    airBotSection.setVisibility(View.GONE);
                }
            }
        });

        // Button to submit configuration
        Button submitButton = findViewById(R.id.btnOk);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitConfig(view);
            }
        });
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

        // Initialize intent to pass data to PrintConfirmActivity
        Intent intent = new Intent(this, PrintConfirmActivity.class);
        intent.putExtra("missionID", missionIDValue);
        intent.putExtra("botType", botType);

        // Collect answers based on the selected bot type
        if (botType.equals("AirBot")) {
            // AirBot-specific selections
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

        } else if (botType.equals("GroundBot")) {
            // GroundBot-specific selections
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

        startActivity(intent);
    }
}