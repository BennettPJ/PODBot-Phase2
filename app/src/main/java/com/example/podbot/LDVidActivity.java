package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class LDVidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldvid);
    }

    public void goToBack(View view) {
        Intent intent = new Intent(LDVidActivity.this, DataLinkGateActivity.class);
        startActivity(intent);
    }
}