package com.example.podbot;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class LDVidActivity extends AppCompatActivity {

    private TextView airplaneModeStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldvid);

        airplaneModeStatusText = findViewById(R.id.airplaneModeStatusText);
        updateAirplaneModeStatus();
    }

    private void updateAirplaneModeStatus() {
        boolean isAirplaneModeOn = Settings.Global.getInt(
                getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON,
                0
        ) != 0;

        String status = isAirplaneModeOn ? "Enabled" : "Disabled";
        airplaneModeStatusText.setText("Airplane mode needs to be enabled for app to work.\nCurrent status of Airplane mode: " + status);
    }

    public void goToBack(View view) {
        Intent intent = new Intent(LDVidActivity.this, HomePageActivity.class);
        startActivity(intent);
    }


    public void openUVCApp(View view) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.shenyaocn.android.usbcamera", "com.shenyaocn.android.usbcamera.MainActivity");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to open USB Camera app.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToDownloadPage(View view) {
        // Redirect the user to the Google play store for downloading the app
        String url = "https://play.google.com/store/apps/details?id=com.shenyaocn.android.usbcamera&hl=en_US";
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } catch (Exception e) {
            // Handle any unexpected errors
            Toast.makeText(this, "Unable to open the download page.", Toast.LENGTH_SHORT).show();
        }
    }

    public void openUserManual(View view) {
        Intent intent = new Intent(LDVidActivity.this, UserManualActivity.class);
        intent.putExtra("source", "LDVid");
        startActivity(intent);
    }
}