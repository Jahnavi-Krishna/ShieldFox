package com.mobileselfencryption.shieldfox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class file extends AppCompatActivity implements View.OnClickListener {

    public CardView Image , Video , Audio , Document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        Image = findViewById(R.id.image);
        Video = findViewById(R.id.video);
        Audio = findViewById(R.id.audio);
        Document = findViewById(R.id.document);

        Image.setOnClickListener(this);
        Video.setOnClickListener(this);
        Audio.setOnClickListener(this);
        Document.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.image:
                i = new Intent(file.this , image.class);
                startActivity(i);
                break;
            case R.id.video:
                i = new Intent(file.this , video.class);
                startActivity(i);
                break;
            case R.id.audio:
                i = new Intent(file.this , audio.class);
                startActivity(i);
                break;
            case R.id.document:
                i = new Intent(file.this , document.class);
                startActivity(i);
                break;
        }
    }
}