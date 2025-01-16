package com.example.podbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LANPrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanprint);
    }

    public void goToPrintSts(View view){
        Intent intent = new Intent(LANPrintActivity.this, PrintStsActivity.class);
        startActivity(intent);
    }

    public void goToUserManual(View view) {
        Intent intent = new Intent(LANPrintActivity.this, UserManualActivity.class);
        intent.putExtra("source", "LANPage");
        startActivity(intent);
    }
}