package com.example.movielistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
TextView textView,textView2,textView3;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView=findViewById(R.id.imageView2);
        textView=findViewById(R.id.textView4);
        textView2=findViewById(R.id.textView5);
        textView3=findViewById(R.id.textView6);
        Bundle bundle=getIntent().getExtras();
        String t=bundle.getString("title");
        String p=bundle.getString("poster");
        String o=bundle.getString("overview");
        Double r=bundle.getDouble("rating");
        Glide.with(this).load(p).into(imageView);
        textView2.setText(r.toString());
        textView.setText(t);
        textView3.setText(o);

    }
}