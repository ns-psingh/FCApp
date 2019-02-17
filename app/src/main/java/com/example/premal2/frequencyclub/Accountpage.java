package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Accountpage extends AppCompatActivity {

    public static String name;
    public static String usn;
    public static  String mobile;
    public static String email;
    public static String dept;
    public static String post;
    private FirebaseAuth mAuth;
    public void onBackPressed(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_accountpage);
        if(Attendancepage.flag==1)
        {
            FirebaseFirestore db=FirebaseFirestore.getInstance();
            Log.d("e","entered in this region");
            db.collection("attendance")
                    .add(Attendancepage.user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("e", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("e", "Error adding document", e);
                        }
                    });
            Attendancepage.flag=0;
        }
        TextView info=(TextView) findViewById(R.id.info);
        TextView home=(TextView) findViewById(R.id.hometext);
        ImageView fb=(ImageView) findViewById(R.id.facebookbtn);
        ImageView linkedinbtn=findViewById(R.id.linkedinbtn);
        ImageView github=(ImageView) findViewById(R.id.githubbtn);
        ImageView browserbtn=findViewById(R.id.webbtn);
        ImageView mailbtn=(ImageView) findViewById(R.id.emailbtn);
        TextView userfull=(TextView) findViewById(R.id.userfullname);
        TextView userpost=(TextView) findViewById(R.id.userpost);

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
        userfull.setText(name);
        userpost.setText(post);
        Button attendancebtn=(Button) findViewById(R.id.attendancebtn);
        attendancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Accountpage.this,Attendancepage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //Toast.makeText(Accountpage.this, "This feature will be available in later updates.", Toast.LENGTH_SHORT).show();
            }
        });
        TextView events=(TextView) findViewById(R.id.eventbtn);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Accountpage.this,Projects.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                // Toast.makeText(Accountpage.this, "This feature will be available in later updates.", Toast.LENGTH_SHORT).show();
            }
        });
        TextView notificationbtn=(TextView) findViewById(R.id.notifcationbtn);
        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Accountpage.this,Notificationspage.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Accountpage.this,mainpage.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        TextView aboutbtn=(TextView) findViewById(R.id.aboutbtn);
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Accountpage.this,aboutpg.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        mAuth=FirebaseAuth.getInstance();
        String information="Name: "+name+"\nPost: "+post+"\nUSN: "+usn+"\nDepartment: "+dept+"\nEmail ID: "+email+"\nWhatsapp Number: "+mobile+"\n";
        info.setText(information);
        Button logout=(Button) findViewById(R.id.logoutbtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(Accountpage.this, "Signed out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Accountpage.this,signin.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
