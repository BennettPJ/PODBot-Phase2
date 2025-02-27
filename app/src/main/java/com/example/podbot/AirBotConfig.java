package com.example.podbot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AirBotConfig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_bot_config);

    }

    public void goToQGC(View view) {
        try {
            Intent intent = new Intent();
            intent.setClassName("org.mavlink.qgroundcontrolbeta", "org.mavlink.qgroundcontrol.QGCActivity");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to open Q Ground Control app.", Toast.LENGTH_SHORT).show();
        }
    }


    public void goToUserManual(View view) {
        Intent intent = new Intent(AirBotConfig.this, UserManualActivity.class);
        intent.putExtra("source", "ABConfig");
        startActivity(intent);
    }


    public void goToDownloadPage(View view) {
        // Redirect the user to the Google play store for downloading the app
        String url = "https://docs.qgroundcontrol.com/master/en/qgc-user-guide/getting_started/download_and_install.html#android";
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } catch (Exception e) {
            // Handle any unexpected errors
            Toast.makeText(this, "Unable to open the download page.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToBack(View view) {
        Intent intent = new Intent(AirBotConfig.this, HomePageActivity.class);
        startActivity(intent);
    }
}