package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button enableCamera;
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableCamera = (Button) findViewById(R.id.enableCamera);
        enableCamera.setOnClickListener(btnEnableCamera);
    }

    Button.OnClickListener btnEnableCamera = new Button.OnClickListener() {

        @Override
        public void onClick(View view) {
            if(hasCameraPermission()) {
                startCamera();
            } else {
                requestPermission();
            }
        }
    };

    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, CAMERA_PERMISSION, CAMERA_REQUEST_CODE);
    }

    private void startCamera() {
        Intent it = new Intent(this, CameraActivity.class);
        startActivity(it);
    }
}