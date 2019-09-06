package com.example.newproject.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newproject.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageFileActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivImage;
    private Button btnWrite,btnRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_file);

        ivImage = findViewById(R.id.im_image);
        btnWrite = findViewById(R.id.im_write);
        btnRead = findViewById(R.id.im_read);

        btnWrite.setOnClickListener(this);
        btnRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String filename = "demo1.png";
        switch (view.getId()){
            case R.id.im_write:
                saveToSD(filename);
                break;
            case R.id.im_read:
                readToSD("image3.jpg");
                break;
        }
    }


    //这个很重要，上来就要申请权限
    private void  saveToSD(String filename){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                return;
            }
        }

        String path = Environment.getExternalStoragePublicDirectory("").getPath()
                + File.separator
                +Environment.DIRECTORY_PICTURES;
        File file = new File(path,filename);
        try {
            if (file.createNewFile()){
                BitmapDrawable drawable = (BitmapDrawable) ivImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                FileOutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readToSD(String filename){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},2);
                return;
            }
        }

        String path = Environment.getExternalStoragePublicDirectory("").getPath()
                +File.separator
                +Environment.DIRECTORY_PICTURES;
        File file = new File(path,filename);

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ivImage.setImageBitmap(BitmapFactory.decodeStream(inputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //权限申请被拒绝
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0 || grantResults[0]!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "权限申请被拒绝", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode){
            case 1:
                saveToSD("demo1.png");
                break;
            case 2:
                readToSD("image3.jpg");

        }
    }
}
