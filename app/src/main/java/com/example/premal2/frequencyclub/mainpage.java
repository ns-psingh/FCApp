package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Set;

public class mainpage extends AppCompatActivity {

    TextView heading1;
    TextView heading2;
    TextView heading3;
    TextView heading4;
    TextView heading5;
    TextView heading6;
    TextView heading7;
    TextView content1;
    TextView content2;
    TextView content3;
    TextView content4;
    TextView content5;
    TextView content6;
    TextView content7;
    TextView userfullname;
    TextView userpost;
    TextView accountbtn;
    public void onBackPressed(){
    }
    ProgressBar loading;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mainpage);
        if(MainActivity.flag==0) {
           // Toast.makeText(mainpage.this, "Signed in as " + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
        MainActivity.flag=1;
        }
        loading=(ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

         heading1=(TextView) findViewById(R.id.heading1);
        heading2=(TextView) findViewById(R.id.heading2);
        heading3=(TextView) findViewById(R.id.heading3);
        heading4=(TextView) findViewById(R.id.heading4);
        heading5=(TextView) findViewById(R.id.heading5);
        heading6=(TextView) findViewById(R.id.heading6);
        heading7=(TextView) findViewById(R.id.heading7);

        content1=(TextView) findViewById(R.id.content1);
        content2=(TextView) findViewById(R.id.content2);
        content3=(TextView) findViewById(R.id.content3);
        content4=(TextView) findViewById(R.id.content4);
        content5=(TextView) findViewById(R.id.content5);
        content6=(TextView) findViewById(R.id.content6);
        content7=(TextView) findViewById(R.id.content7);
        accountbtn=(TextView) findViewById(R.id.accountbtn);
        heading1.setText("");
        heading2.setText("");
        heading3.setText("");
        heading4.setText("");
        heading5.setText("");
        heading6.setText("");
        heading7.setText("");

        content1.setText("");
        content2.setText("");
        content3.setText("");
        content4.setText("");
        content5.setText("");
        content6.setText("");
        content7.setText("");
        userfullname=(TextView) findViewById(R.id.userfullname);
        userpost=(TextView) findViewById(R.id.userpost);
        userfullname.setVisibility(View.INVISIBLE);
        userpost.setVisibility(View.INVISIBLE);
       // userfullname.setText("");
       // userpost.setText("");
//        Log.d("e","email id jo chaiye"+mAuth.getCurrentUser().getEmail());
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                               if (task.isSuccessful()) {
                                                   for (final DocumentSnapshot document : task.getResult()) {
                                                       Map<String, Object> x = document.getData();
                                                       Set<Map.Entry<String, Object>> st = x.entrySet();
                                                       String name="";
                                                       String post="";
                                                       String dept="";
                                                       String usn="";
                                                       String mobile="";
                                                       int flag=0;
                                                       for (Map.Entry<String, Object> me : st) {
                                                            Log.d("e",me.getKey()+":"+me.getValue());
                                                            Log.d("e",mAuth.getCurrentUser().getEmail());
                                                             if((me.getKey().equals("email")) && (me.getValue().equals(mAuth.getCurrentUser().getEmail())))
                                                             {
                                                                 Log.d("e","success");
                                                                 flag=1;
                                                             }
                                                             if((me.getKey().equals("name")))
                                                             {
                                                                 Log.d("e","entered");
                                                                 name=me.getValue()+"";
                                                             }

                                                           if((me.getKey().equals("post")))
                                                           {
                                                               post=me.getValue()+"";
                                                           }
                                                           if((me.getKey().equals("dept")))
                                                           {
                                                               dept=me.getValue()+"";
                                                           }
                                                           if((me.getKey().equals("usn")))
                                                           {
                                                               usn=me.getValue()+"";
                                                           }
                                                           if((me.getKey().equals("mobile")))
                                                           {
                                                               mobile=me.getValue()+"";
                                                           }

                                                       }
                                                       if(flag==1)
                                                       {
                                                            Accountpage.name=name;
                                                            Accountpage.dept=dept;
                                                            Accountpage.usn=usn;
                                                            Accountpage.email=mAuth.getCurrentUser().getEmail();
                                                            Accountpage.mobile=mobile;
                                                            Accountpage.post=post;
                                                           userfullname.setText(name);
                                                           userpost.setText(post);
                                                           userfullname.setVisibility(View.VISIBLE);
                                                           userpost.setVisibility(View.VISIBLE);
                                                           Log.d("e","done");
                                                       }
                                                       Log.d("e",name);
                                                       Log.d("e",post);

                                                   }

                                               }
                                           }
                                       }
                );

        TextView aboutbtn=(TextView) findViewById(R.id.aboutbtn);
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainpage.this,aboutpg.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
        TextView notificationbtn=(TextView) findViewById(R.id.notifcationbtn);
        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainpage.this,Notificationspage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                String facebookPageUrl = "https://github.com/premalsingh/rvceconnect";
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
        accountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainpage.this,Accountpage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        db.collection("news_feed")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                               if (task.isSuccessful()) {
                                                   int count=0;
                                                   for (final DocumentSnapshot document : task.getResult()) {
                                                       Map<String, Object> x = document.getData();
                                                       Set<Map.Entry<String, Object>> st = x.entrySet();
                                                       String heading="";
                                                       String content="";
                                                       String num="";
                                                       for (Map.Entry<String, Object> me : st) {
                                                          // Log.d("e", me.getKey() + ":");
                                                         //  Log.d("e", me.getValue() + ":");
                                                           if(me.getKey().equals("num"))
                                                               num=(me.getValue()+"");
                                                           if(me.getKey().equals("heading"))
                                                               heading=(me.getValue()+"");
                                                           if(me.getKey().equals("content"))
                                                               content=(me.getValue()+"");
                                                       }
                                                       Log.d("e",num);
                                                       Log.d("e",heading);
                                                       Log.d("e",content);
                                                       count++;
                                                       switch (count)
                                                       {
                                                           case 1: heading1.setText(heading);
                                                                    content1.setText(content+"\n");
                                                                    break;

                                                           case 2: heading2.setText(heading);
                                                               content2.setText(content+"\n");
                                                               break;

                                                           case 3: heading3.setText(heading);
                                                               content3.setText(content+"\n");
                                                               break;

                                                           case 4: heading4.setText(heading);
                                                               content4.setText(content+"\n");
                                                               break;

                                                           case 5: heading5.setText(heading);
                                                               content5.setText(content+"\n");
                                                               break;

                                                           case 6: heading6.setText(heading);
                                                               content6.setText(content+"\n");
                                                               break;

                                                           case 7: heading7.setText(heading);
                                                               content7.setText(content+"\n");
                                                               break;
                                                       }
                                                   }
                                                   loading.setVisibility(View.INVISIBLE);
                                               }
                                           }
                                       }
                );

    }
}
