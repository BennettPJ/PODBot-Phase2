package com.example.podbot;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
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

        // Retrieve values from shared pref
        SharedPreferences prefs = getSharedPreferences("PrinterFiles", MODE_PRIVATE);
        String missionID = prefs.getString("MissionID", "Unknown");
        String printer1 = prefs.getString("Printer1", "Error");
        String printer2 = prefs.getString("Printer2", "Error");
        String printer3 = prefs.getString("Printer3", "Error");

        // Update the TextViews with the retrieved data
        TextView printerTextView = findViewById(R.id.printerStatusText);

        printerTextView.setText("Mission ID: " + missionID + "\n\n" +
                                "Bambu P1S - 1: " + printer1 + "\n\n" +
                                "Bambu P1S - 2: " + printer2 + "\n\n" +
                                "Bambu P1S - 3: " + printer3
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