package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

        Button submitButton = findViewById(R.id.btnOk);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitConfig(view);
            }
        });
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

    public void submitConfig(View view) {
        // Get the Mission ID
        EditText missionID = findViewById(R.id.missionIdInput);
        String missionIDValue = missionID.getText().toString().trim();

        if (missionIDValue.isEmpty()) {
            Toast.makeText(this, "Please enter a Mission ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected Bot Type (Fly or Drive)
        RadioGroup radioGroupBotType = findViewById(R.id.radioGroupBotType);
        int selectedBotId = radioGroupBotType.getCheckedRadioButtonId();

        if (selectedBotId == -1) {
            Toast.makeText(this, "Please select whether the bot flies or drives", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedBotType = findViewById(selectedBotId);
        String botType = selectedBotType.getText().toString();

        // Get selected Time of Day (Day or Night)
        RadioGroup radioGroupTimeOfDay = findViewById(R.id.radioGroupTimeOfDay);
        int selectedTimeId = radioGroupTimeOfDay.getCheckedRadioButtonId();

        if (selectedTimeId == -1) {
            Toast.makeText(this, "Please select the time of day for the mission", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedTimeOfDay = findViewById(selectedTimeId);
        String timeOfDay = selectedTimeOfDay.getText().toString();

        // Prepare an Intent to pass the data to the next activity
        Intent intent = new Intent(this, PrintConfirmActivity.class);
        intent.putExtra("missionID", missionIDValue);
        intent.putExtra("botType", botType);
        intent.putExtra("timeOfDay", timeOfDay);
        //if()

        // Start the new activity
        startActivity(intent);
    }

}