package com.example.permissionchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnGetPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetPermission = findViewById(R.id.btnGetPermission);

        btnGetPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               permissionChecker();
            }
        });
    }

    //setting up different permissions
    private void permissionChecker(){
       PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

                Toast.makeText(getApplicationContext(),"Your permission is successfully granted!",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

                Toast.makeText(getApplicationContext(),"Your permission is denied!",Toast.LENGTH_SHORT).show();

            }
        };
        TedPermission.with(MainActivity.this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }
}
