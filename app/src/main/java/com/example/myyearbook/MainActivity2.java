package com.example.myyearbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity2 extends AppCompatActivity {
private Button button2;         // Joe's page
private Button button3;         // Share button
private ImageView image;        // Image that is shared

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        button2 = (Button) findViewById(R.id.button2);            // go to Joe's page
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        button3 = (Button) findViewById(R.id.button4);           //share the image
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareButton();
            }
        });

    }

    public void openActivity() {      //code to go to main page (joe's page)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void shareButton() {           //Code to allow image sharing
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        File im = new File (getExternalCacheDir() + "/" + getResources().getString(R.string.app_name) + ".png");
        Intent shareint;

        try {
            FileOutputStream outputStream= new FileOutputStream(im);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);

            outputStream.flush();
            outputStream.close();
            shareint = new Intent(Intent.ACTION_SEND);
            shareint.setType("image/*");
            shareint.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(im));  //permission to use image
            shareint.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        }catch (Exception e) {
            throw new RuntimeException (e);
        }

        startActivity(Intent.createChooser(shareint,"share image"));     //open the sharable options for android



    }
    }
