package com.example.podbot;

import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jiangdg.uvc.USBMonitor;
import com.jiangdg.uvc.UVCCamera;
import com.jiangdg.uvc.UVCUtils;

public class LDVidActivity extends AppCompatActivity {

    private USBMonitor mUSBMonitor;
    private UVCCamera mUVCCamera;
    private SurfaceView mCameraSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldvid);

        // Initialize SurfaceView for camera feed
        mCameraSurface = findViewById(R.id.camera_view);

        // Initialize USB Monitor
        mUSBMonitor = new USBMonitor(this, new USBMonitor.OnDeviceConnectListener() {
            @Override
            public void onAttach(UsbDevice device) {
                Toast.makeText(LDVidActivity.this, "USB Device Attached", Toast.LENGTH_SHORT).show();
                mUSBMonitor.requestPermission(device);
            }

            @Override
            public void onDettach(UsbDevice device) {
                Toast.makeText(LDVidActivity.this, "USB Device Detached", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock, boolean createNew) {
                if (mUVCCamera != null) return; // Avoid multiple connections

                mUVCCamera = new UVCCamera();
                mUVCCamera.open(ctrlBlock);

                // Configure camera settings
                mUVCCamera.setPreviewSize(UVCCamera.DEFAULT_PREVIEW_WIDTH, UVCCamera.DEFAULT_PREVIEW_HEIGHT);

                // Set the camera display to the SurfaceView
                Surface surface = mCameraSurface.getHolder().getSurface();
                if (surface != null) {
                    mUVCCamera.setPreviewDisplay(surface);
                    mUVCCamera.startPreview();
                }
            }

            @Override
            public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
                if (mUVCCamera != null) {
                    mUVCCamera.close();
                    mUVCCamera = null;
                }
            }

            @Override
            public void onCancel(UsbDevice device) {
                Toast.makeText(LDVidActivity.this, "USB Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

        // Check USB permissions
        if (!UVCUtils.hasUSBPermission(this)) {
            UVCUtils.requestUSBPermission(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mUSBMonitor != null) {
            mUSBMonitor.register();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mUSBMonitor != null) {
            mUSBMonitor.unregister();
        }
        if (mUVCCamera != null) {
            mUVCCamera.stopPreview();
            mUVCCamera.close();
            mUVCCamera = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUSBMonitor != null) {
            mUSBMonitor.destroy();
        }
    }

    public void goToBack(View view) {
        Intent intent = new Intent(LDVidActivity.this, DataLinkGateActivity.class);
        startActivity(intent);
    }
}