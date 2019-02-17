package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Set;

public class Projects extends AppCompatActivity {

    static String project;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_projects);
        final TextView assigntext=(TextView) findViewById(R.id.assigntext);
        final TextView projectdesc=(TextView) findViewById(R.id.projectdescription);
        db=FirebaseFirestore.getInstance();
        TextView aboutbtn=(TextView) findViewById(R.id.aboutbtn);
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Projects.this,aboutpg.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
        TextView notificationbtn=(TextView) findViewById(R.id.notifcationbtn);
        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Projects.this,Notificationspage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        TextView accountbtn=(TextView) findViewById(R.id.accountbtn);
        accountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Projects.this,Accountpage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        TextView homebtn=(TextView) findViewById(R.id.hometext);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Projects.this,mainpage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        ImageView fb=(ImageView) findViewById(R.id.facebookbtn);
        ImageView linkedinbtn=findViewById(R.id.linkedinbtn);
        ImageView github=(ImageView) findViewById(R.id.githubbtn);
        ImageView browserbtn=findViewById(R.id.webbtn);
        ImageView mailbtn=(ImageView) findViewById(R.id.emailbtn);
        ImageView instabtn=(ImageView) findViewById(R.id.instabtn);
        instabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebookPageUrl = "https://instagram.com/frequencyclub_rv?utm_source=ig_profile_share&igshid=1fbgxx82k06c0";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookPageUrl));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
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

        ImageView whatsapp=findViewById(R.id.whatsappbtn);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent sendIntent = new Intent("android.intent.action.MAIN");
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Download the Frequency Club App From frequencyclub.azurewebsite.net");
                    sendIntent.putExtra("jid", "8982625174" + "@s.whatsapp.net"); //phone number without "+" prefix
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                } catch(Exception e) {
                    Log.d("e","error");
                }

            }
        });

        if(!project.equals(""));
        {
            db.collection("projects").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        String tempproject = "", tempemail = "";
                        int flag = 0;
                        {
                            for (final DocumentSnapshot document : task.getResult()) {
                                Map<String, Object> x = document.getData();
                                Set<Map.Entry<String, Object>> st = x.entrySet();
                                for (Map.Entry<String, Object> me : st) {
                                    if (me.getKey().equals("desc"))
                                        tempproject = me.getValue()+"";
                                    if (me.getKey().equals("project"))
                                        tempemail = me.getValue().toString();
                                }
                                if (tempemail.equals(project)) {
                                    projectdesc.setText(Html.fromHtml(tempproject));
                                    assigntext.setText("");
                                    Log.d("e", Projects.project + tempproject);
                                }
                            }
                        }
                    }
                }
            });
        }


    }
}
