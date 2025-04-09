package com.example.podbot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LANPrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanprint);
    }

    public void goToPrintSts(View view) {
        Intent intent = new Intent(LANPrintActivity.this, PrintStsActivity.class);
        startActivity(intent);
    }

    public void goToUserManual(View view) {
        Intent intent = new Intent(LANPrintActivity.this, UserManualActivity.class);
        intent.putExtra("source", "LANPage");
        startActivity(intent);
    }

    public void sendFileToPrinter1(View view) {
        EditText ipField = findViewById(R.id.P1IP);
        EditText passField = findViewById(R.id.P1Pass);
        EditText RasPiIP = findViewById(R.id.RPIP);

        String printerIp = ipField.getText().toString().trim();
        String accessCode = passField.getText().toString().trim();
        String raspberryPiIP = RasPiIP.getText().toString().trim();

        disableButtons(); // Grey out all buttons

        if (printerIp.isEmpty() || accessCode.isEmpty() || raspberryPiIP.isEmpty()) {
            Toast.makeText(this, "Enter all required fields!", Toast.LENGTH_SHORT).show();
            enableButtons(); //Activate all buttons again
            return;
        }

        // Retrieve values from shared pref
        SharedPreferences prefs = getSharedPreferences("PrinterFiles", MODE_PRIVATE);
        String printer1File = prefs.getString("Printer1", "Error");

        new Thread(() -> {
            boolean printSuccess = sendPrintRequestToPi(raspberryPiIP, printerIp, accessCode, printer1File);
            runOnUiThread(() -> {
                if (printSuccess) {
                    Toast.makeText(this, "File uploaded to printer!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File upload failed.", Toast.LENGTH_SHORT).show();
                }
                enableButtons(); //Activate all buttons again
            });
        }).start();
    }


    public void sendFileToPrinter2(View view) {
        EditText ipField = findViewById(R.id.P2IP);
        EditText passField = findViewById(R.id.P2Pass);
        EditText RasPiIP = findViewById(R.id.RPIP);

        String printerIp = ipField.getText().toString().trim();
        String accessCode = passField.getText().toString().trim();
        String raspberryPiIP = RasPiIP.getText().toString().trim();

        disableButtons(); // Grey out all buttons

        if (printerIp.isEmpty() || accessCode.isEmpty() || raspberryPiIP.isEmpty()) {
            Toast.makeText(this, "Enter all required fields!", Toast.LENGTH_SHORT).show();
            enableButtons(); //Activate all buttons again
            return;
        }

        // Retrieve values from shared pref
        SharedPreferences prefs = getSharedPreferences("PrinterFiles", MODE_PRIVATE);
        String printer2File = prefs.getString("Printer2", "Error");

        new Thread(() -> {
            boolean printSuccess = sendPrintRequestToPi(raspberryPiIP, printerIp, accessCode, printer2File);
            runOnUiThread(() -> {
                if (printSuccess) {
                    Toast.makeText(this, "File uploaded to printer!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File upload failed.", Toast.LENGTH_SHORT).show();
                }
                enableButtons(); //Activate all buttons again
            });
        }).start();
    }


    public void sendFileToPrinter3(View view) {
        EditText ipField = findViewById(R.id.P3IP);
        EditText passField = findViewById(R.id.P3Pass);
        EditText RasPiIP = findViewById(R.id.RPIP);

        String printerIp = ipField.getText().toString().trim();
        String accessCode = passField.getText().toString().trim();
        String raspberryPiIP = RasPiIP.getText().toString().trim();

        disableButtons(); // Grey out all buttons

        if (printerIp.isEmpty() || accessCode.isEmpty() || raspberryPiIP.isEmpty()) {
            Toast.makeText(this, "Enter all required fields!", Toast.LENGTH_SHORT).show();
            enableButtons(); //Activate all buttons again
            return;
        }

        // Retrieve values from shared pref
        SharedPreferences prefs = getSharedPreferences("PrinterFiles", MODE_PRIVATE);
        String printer3File = prefs.getString("Printer3", "Error");

        new Thread(() -> {
            boolean printSuccess = sendPrintRequestToPi(raspberryPiIP, printerIp, accessCode, printer3File);
            runOnUiThread(() -> {
                if (printSuccess) {
                    Toast.makeText(this, "File uploaded to printer!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File upload failed.", Toast.LENGTH_SHORT).show();
                }
                enableButtons(); //Activate all buttons again
            });
        }).start();
    }




    private boolean sendPrintRequestToPi(String raspberryPiIP, String printerIp, String accessCode, String fileName) {
        try {
            String printUrl = "http://" + raspberryPiIP + ":5000/print";
            Log.d("FTPS", "Sending request to: " + printUrl);

            HttpURLConnection conn = (HttpURLConnection) new URL(printUrl).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonRequest = "{"
                    + "\"printer_ip\": \"" + printerIp + "\","
                    + "\"access_code\": \"" + accessCode + "\","
                    + "\"file_name\": \"" + fileName + "\""
                    + "}";

            Log.d("FTPS", "JSON Request: " + jsonRequest);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonRequest.getBytes("utf-8"));
            }

            int responseCode = conn.getResponseCode();
            Log.d("FTPS", "Response Code: " + responseCode);

            if (responseCode != HttpURLConnection.HTTP_OK) {
                Log.e("FTPS", "Error: Server responded with code " + responseCode);
            }

            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            Log.e("FTPS", "Request failed: " + e.getMessage());
            return false;
        }
    }


    private void disableButtons(){
        Button button1 = findViewById(R.id.SendPrint1);
        button1.setEnabled(false);
        Button button2 = findViewById(R.id.SendPrint2);
        button2.setEnabled(false);
        Button button3 = findViewById(R.id.SendPrint3);
        button3.setEnabled(false);
    }

    private void enableButtons(){
        Button button1 = findViewById(R.id.SendPrint1);
        button1.setEnabled(true);
        Button button2 = findViewById(R.id.SendPrint2);
        button2.setEnabled(true);
        Button button3 = findViewById(R.id.SendPrint3);
        button3.setEnabled(true);
    }
}