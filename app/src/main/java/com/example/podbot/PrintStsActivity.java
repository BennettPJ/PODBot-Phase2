package com.example.podbot;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PrintStsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_sts);

        // Retrieve values from the intent
        Intent intent = getIntent();
        String missionID = intent.getStringExtra("MissionID");
        String printer1Status = intent.getStringExtra("Printer-1");
        String printer2Status = intent.getStringExtra("Printer-2");
        String printer3Status = intent.getStringExtra("Printer-3");

        // Update the TextViews with the retrieved data
        TextView printerTextView = findViewById(R.id.printerStatusText);

        printerTextView.setText("Mission ID: " + (missionID != null ? missionID : "Unknown") + "\n\n" +
                                "Bambu P1S - 1: " + (printer1Status != null ? printer1Status : "No file assigned") + "\n\n" +
                                "Bambu P1S - 2: " + (printer2Status != null ? printer2Status : "No file assigned") + "\n\n" +
                                "Bambu P1S - 3: " + (printer3Status != null ? printer3Status : "No file assigned")
                                );
    }

    public void goToHome(View view) {
        // Go back to the home page
        Intent intent = new Intent(PrintStsActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    public void openBambuHandyApp(View view) {
        // Try to open the Bambu Handy app
        try {
            // Explicitly launch the main activity of the Bambu Handy app
            Intent intent = new Intent();
            intent.setClassName("bbl.intl.bambulab.com", "com.bambulab.bambulab.MainActivity");
            startActivity(intent);
        } catch (Exception e) {
            // Handle any errors
            Toast.makeText(this, "Unable to open Bambu Handy app.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToDownloadPage(View view) {
        // Redirect user to Bambu Handy download page
        try {
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=bbl.intl.bambulab.com"));
            startActivity(playStoreIntent);
        } catch (ActivityNotFoundException e) {
            // If Play Store app isn't available, open URL in browser
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=bbl.intl.bambulab.com"));
            startActivity(browserIntent);
        }
    }

    public void openUserManual(View view) {
        Intent intent = new Intent(PrintStsActivity.this, UserManualActivity.class);
        intent.putExtra("source", "PrintSts");
        startActivity(intent);
    }

    public void goToLAN(View view){
        Intent intent = new Intent(PrintStsActivity.this, LANPrintActivity.class);
        startActivity(intent);
    }
}