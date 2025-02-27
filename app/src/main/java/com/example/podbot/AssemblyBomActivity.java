package com.example.podbot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssemblyBomActivity extends AppCompatActivity {

    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private ParcelFileDescriptor fileDescriptor;
    private ImageView pdfImageView; // ImageView to display PDF pages
    private int currentPageIndex = 0; // Start at the first page

    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix(); // Matrix for handling zoom and pan
    private float scaleFactor = 1.0f; // Initial zoom level
    private Bitmap currentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_bom);

        // Find ImageView for displaying PDF
        pdfImageView = findViewById(R.id.pdfImageView);

        // Initialize PDF renderer and display the first page
        try {
            openPdfRenderer();
            renderPageToBitmap(currentPageIndex); // Display the first page with high resolution
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize ScaleGestureDetector for pinch zoom
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        // Set touch listener on ImageView to detect gestures
        pdfImageView.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            return true;
        });

        setupNavigationButtons();
    }

    // Opens the PDF file using PdfRenderer
    private void openPdfRenderer() throws IOException {
        String config = getBotConfigBinary();
        String fileToOpen = "";
        //FIXME: might refactor to nest if statements in an if-else and split out the 2 left most bits for AB and GB
        if(config.trim().equals("0110010000000010")){
            //AirBot FR-2
            fileToOpen = "AirBot_FR2_0110010000000010.pdf";
        }
        else if(config.trim().equals("0110110000111111")){
            //AirBot TD-1
            fileToOpen = "AirBot_TD1_0110110000111111.pdf";
        }
        else if(config.trim().equals("0010010001111100")){
            //GroundBot BP-3 and TD-1
            fileToOpen = "GroundBot_BP3_TD1_0010010001111100.pdf";
        }
        else{
            //PODBot not configured
            fileToOpen = "Not_Configured_Display.pdf";
        }

        //File file = new File(getCacheDir(), fileToOpen);
        File file = new File(getCacheDir(), fileToOpen);
        try (InputStream asset = getAssets().open(fileToOpen);
             FileOutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = asset.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw e;
        }

        fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        pdfRenderer = new PdfRenderer(fileDescriptor);
    }

    // Renders a page from the PDF to a high-resolution bitmap
    private void renderPageToBitmap(int index) {
        if (pdfRenderer.getPageCount() <= index || index < 0) return;

        if (currentPage != null) {
            currentPage.close();
        }

        currentPage = pdfRenderer.openPage(index);

        // Set a higher resolution for the initial render
        int width = currentPage.getWidth() * 2; // Render at double the PDF page width
        int height = currentPage.getHeight() * 2; // Render at double the PDF page height

        currentBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        currentPage.render(currentBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

        pdfImageView.setImageBitmap(currentBitmap); // Display the initial scaled bitmap
        matrix.reset();
        pdfImageView.setImageMatrix(matrix);
    }

    // Clean up PdfRenderer resources
    @Override
    protected void onDestroy() {
        try {
            if (currentPage != null) currentPage.close();
            if (pdfRenderer != null) pdfRenderer.close();
            if (fileDescriptor != null) fileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    private void setupNavigationButtons() {
        Button prevButton = findViewById(R.id.prevPageButton);
        Button nextButton = findViewById(R.id.nextPageButton);

        prevButton.setOnClickListener(v -> {
            if (currentPageIndex > 0) {
                currentPageIndex--;
                renderPageToBitmap(currentPageIndex);
            }
        });

        nextButton.setOnClickListener(v -> {
            if (currentPageIndex < pdfRenderer.getPageCount() - 1) {
                currentPageIndex++;
                renderPageToBitmap(currentPageIndex);
            }
        });
    }

    // ScaleGestureDetector for pinch zoom functionality
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();

            // Limit the zoom factor between 0.5x and 3.0x
            scaleFactor = Math.max(0.5f, Math.min(scaleFactor, 3.0f));

            // Apply scaling to the matrix and set it to the ImageView
            matrix.setScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
            pdfImageView.setImageMatrix(matrix);
            return true;
        }
    }

    // Method to navigate back to the homepage
    public void goToHome(View view) {
        Intent intent = new Intent(AssemblyBomActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    private String getBotConfigBinary() {
        return getSharedPreferences("AppConfig", MODE_PRIVATE)
                .getString("botConfigBinary", "DEFAULT_BINARY"); // Default case
    }
}