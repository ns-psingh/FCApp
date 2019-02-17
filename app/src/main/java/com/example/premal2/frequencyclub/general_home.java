package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class general_home extends AppCompatActivity {

    public static int seatsleft;
    Button iotbtn;
    public void onBackPressed(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_general_home);
        ImageView img=(ImageView) findViewById(R.id.logo);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        img.startAnimation(animFadeIn);
        Workshop_Registration.name="";
        Workshop_Registration.email="";
        Workshop_Registration.phone="";
        Workshop_Registration.onlinenum="";
        Workshop_Registration.transactionid="";
        Workshop_Registration.sem="";
        Log.d("e","count ka value hai"+MainActivity.count);
        Log.d("e","limit ka value hai"+MainActivity.limit);
        Log.d("e","works1");
        seatsleft=MainActivity.limit-MainActivity.count;
        if(seatsleft<=0)
            seatsleft=0;
        Button workshopbtn=(Button) findViewById(R.id.workshopbtn);
        if(MainActivity.enabled.equals("yes") && MainActivity.limitthere.equals("yes")) {
            if (seatsleft > 1)
                workshopbtn.setText("Register for Workshop (" + seatsleft + " seats left!)");
            if (seatsleft == 1)
                workshopbtn.setText("Register for Workshop (1 seat left!)");
            if (seatsleft == 0)
            {workshopbtn.setText("Register for Workshop (0 seats left!)");
                workshopbtn.setTextColor(Color.RED);}
            Log.d("e","works2");

        //signinbtn.startAnimation(animFadeIn);


        }
        else
            workshopbtn.setText("Register for Workshop");
        final Button signinbtn=(Button) findViewById(R.id.signinbtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("e","works3");
                startActivity(new Intent(general_home.this, signin.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        Button aboutusbtn=(Button) findViewById(R.id.aboutusbtn);
        //aboutusbtn.startAnimation(animFadeIn);
        aboutusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(general_home.this, theaboutgeneral.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        iotbtn=(Button) findViewById(R.id.iotbtn);
        iotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(general_home.this,"Coming soon in next major update.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(general_home.this,signin_iot.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        //workshopbtn.startAnimation(animFadeIn);
        workshopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("e","happens");
                if(MainActivity.recieved==-1)
                {
                    Log.d("e","happens2");
                    Toast.makeText(general_home.this, "Communication Issues in connecting to our servers! Please check internet connection and restart the app!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.d("e","happens2"+MainActivity.enabled);
                    if(MainActivity.enabled.equals("no"))
                        Toast.makeText(general_home.this, "We are not accepting registrations right now.", Toast.LENGTH_SHORT).show();
                    else if(MainActivity.count>=MainActivity.limit && MainActivity.limitthere.equals("yes"))
                        Toast.makeText(general_home.this, "Sorry, we are not accepting any more registrations at this moment.Kindly check again later.", Toast.LENGTH_SHORT).show();
                    else
                    {
                        startActivity(new Intent(general_home.this, Workshop_Registration.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            }
        });
        ImageView fb=(ImageView) findViewById(R.id.facebookbtn);
        ImageView linkedinbtn=findViewById(R.id.linkedinbtn);
        ImageView github=(ImageView) findViewById(R.id.githubbtn);
        ImageView browserbtn=findViewById(R.id.webbtn);
        ImageView mailbtn=(ImageView) findViewById(R.id.emailbtn);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebookPageUrl = "https://www.facebook.com/rvcefrequency";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookPageUrl));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        linkedinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebookPageUrl = "https://www.linkedin.com/company/frequency-club-rvce/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookPageUrl));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebookPageUrl = "https://github.com/Frequency-Club-RVCE";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookPageUrl));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        browserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebookPageUrl = "http://frequencyclub.azurewebsites.net/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookPageUrl));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        mailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: frequencyclub@rvce.edu.in"));
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }
}
