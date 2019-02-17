package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class theaboutgeneral extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_theaboutgeneral);
        ImageView map=(ImageView) findViewById(R.id.maplink);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Frequency+Club+RVCE/@12.9236525,77.5012081,15z/data=!4m5!3m4!1s0x0:0x6630a4d79ed10282!8m2!3d12.9236525!4d77.5012081"));
                startActivity(intent);
            }
        });
    }
}
