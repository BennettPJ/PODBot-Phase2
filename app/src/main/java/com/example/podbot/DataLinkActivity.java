package com.example.podbot;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DataLinkActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_link);

        // Initialize WebView
        webView = findViewById(R.id.dataView1);
        setupWebView();

        // Load URL (use a known HTTPS URL as a test)
        String url = "https://98.225.77.177:8080/video_feed"; // Replace with your URL or IP
        webView.loadUrl(url);
    }

    private void setupWebView() {
        WebSettings webSettings = webView.getSettings();

        // Enable JavaScript and DOM Storage
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        // Clear cache
        webView.clearCache(true);

        // Disable caching to force fresh load
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // Set WebViewClient to handle loading within WebView
        webView.setWebViewClient(new WebViewClient() {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors (for development only)
        }
        //});

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d("WebView", "Page loaded: " + url);
        }
    });
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseWebView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseWebView();
    }

    private void releaseWebView() {
        if (webView != null) {
            // Stop loading and clear resources
            webView.stopLoading();
            webView.clearHistory();
            webView.clearCache(true);
            webView.loadUrl("about:blank"); // Release any held page
            webView.freeMemory();
            webView = null;
        }
    }

    public void goToHome(View view) {
        // Go back to the home page
        Intent intent = new Intent(DataLinkActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}