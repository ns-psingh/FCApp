package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Notificationspage extends AppCompatActivity {

    FirebaseFirestore db;
    ProgressBar notifs;
    TextView[] messages=new TextView[10];
    TextView[] senders=new TextView[10];

    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notificationspage);
        db=FirebaseFirestore.getInstance();
        notifs=(ProgressBar) findViewById(R.id.loadingnotifs);
        String[] message=new String [10];
        String[] sender=new String[10];
        messages[0]=(TextView) findViewById(R.id.message1);
        messages[1]=(TextView) findViewById(R.id.message2);
        messages[2]=(TextView) findViewById(R.id.message3);
        messages[3]=(TextView) findViewById(R.id.message4);
        messages[4]=(TextView) findViewById(R.id.message5);
        messages[5]=(TextView) findViewById(R.id.message6);
        messages[6]=(TextView) findViewById(R.id.message7);
        messages[7]=(TextView) findViewById(R.id.message8);
        messages[8]=(TextView) findViewById(R.id.message9);
        messages[9]=(TextView) findViewById(R.id.message10);

        senders[0]=(TextView) findViewById(R.id.sender1);
        senders[1]=(TextView) findViewById(R.id.sender2);
        senders[2]=(TextView) findViewById(R.id.sender3);
        senders[3]=(TextView) findViewById(R.id.sender4);
        senders[4]=(TextView) findViewById(R.id.sender5);
        senders[5]=(TextView) findViewById(R.id.sender6);
        senders[6]=(TextView) findViewById(R.id.sender7);
        senders[7]=(TextView) findViewById(R.id.sender8);
        senders[8]=(TextView) findViewById(R.id.sender9);
        senders[9]=(TextView) findViewById(R.id.sender10);
        TextView events=(TextView) findViewById(R.id.eventbtn);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Notificationspage.this,Projects.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //  Toast.makeText(Notificationspage.this, "This feature will be available in later updates.", Toast.LENGTH_SHORT).show();
            }
        });
        TextView userfullname=(TextView) findViewById(R.id.userfullname);
        userfullname.setText(Accountpage.name);
        TextView userpost=(TextView) findViewById(R.id.userpost);
        userpost.setText(Accountpage.post);
        TextView homebtn=(TextView) findViewById(R.id.hometext);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notificationspage.this,mainpage.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        TextView about=(TextView) findViewById(R.id.aboutbtn);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notificationspage.this,aboutpg.class));
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
        TextView accountbtn=(TextView) findViewById(R.id.accountbtn);
        accountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notificationspage.this,Accountpage.class));
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




        //  int countouside=0;
       // count=0;
        db.collection("notifications")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            count=0;
                            for(DocumentSnapshot document: task.getResult()) {
                                count++;
                            }
                            if(count<=10)
                            {
                                int i=0;
                                Log.d("e",count+"numbers");
                                for(final DocumentSnapshot document: task.getResult())
                                {
                                    Map<String,Object>x=document.getData();
                                    Set<Map.Entry<String,Object>> st=x.entrySet();
                                    for(Map.Entry<String,Object> me:st)
                                    {

                                        Log.d("e",me.getKey());
                                        Log.d("e",me.getValue()+"");
                                        if(me.getKey().equals("sender"))
                                            senders[count-i-1].setText("Sender: "+me.getValue()+"");
                                        if(me.getKey().equals("message"))
                                            messages[count-i-1].setText("Message: "+me.getValue()+"");
                                    }
                                    i++;
                                }
                            }
                            else
                            {
                                int base=count-10;
                                int i=0;
                                Log.d("e",count+"numbers");
                                for(final DocumentSnapshot document: task.getResult())
                                {
                                    Map<String,Object>x=document.getData();
                                    Set<Map.Entry<String,Object>> st=x.entrySet();
                                    for(Map.Entry<String,Object> me:st)
                                    {

                                        Log.d("e",me.getKey());
                                        Log.d("e",me.getValue()+"");
                                        if(i<base)
                                            continue;
                                        if(me.getKey().equals("sender"))
                                            senders[count-i-1].setText("Sender: "+me.getValue()+"");
                                        if(me.getKey().equals("message"))
                                            messages[count-i-1].setText("Message: "+me.getValue()+"");
                                    }
                                    i++;
                                }
                            }
                            notifs.setVisibility(View.INVISIBLE);
                        }
                    }
                });
        final EditText notificationtext=(EditText) findViewById(R.id.broadcast);
        Button notifybtn=(Button) findViewById(R.id.notifybtn);
        notifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notificationtext.getText().toString().equals(""))
                {
                    Toast.makeText(Notificationspage.this, "Please enter some content", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Map<String,Object> notif=new HashMap<>();
                    notif.put("message",notificationtext.getText().toString());
                    notif.put("sender",Accountpage.name+"("+Accountpage.post+")");
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(System.currentTimeMillis());
                    db.collection("notifications").document(timeStamp).set(notif);
                    Toast.makeText(Notificationspage.this, "Your Notification is now broadcasted. It will be shortly delivered to other members in the club.Thank You!", Toast.LENGTH_SHORT).show();
                    db.collection("notifications")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful())
                                    {
                                        notifs.setVisibility(View.VISIBLE);
                                        count=0;
                                        for(DocumentSnapshot document: task.getResult()) {
                                            count++;
                                        }
                                        if(count<=10)
                                        {
                                            int i=0;
                                            Log.d("e",count+"numbers");
                                            for(final DocumentSnapshot document: task.getResult())
                                            {
                                                Map<String,Object>x=document.getData();
                                                Set<Map.Entry<String,Object>> st=x.entrySet();
                                                for(Map.Entry<String,Object> me:st)
                                                {

                                                    Log.d("e",me.getKey());
                                                    Log.d("e",me.getValue()+"");
                                                    if(me.getKey().equals("sender"))
                                                        senders[count-i-1].setText("Sender: "+me.getValue()+"");
                                                    if(me.getKey().equals("message"))
                                                        messages[count-i-1].setText("Message: "+me.getValue()+"");
                                                }
                                                i++;
                                            }
                                        }
                                        else
                                        {
                                            int base=count-10;
                                            int i=0;
                                            Log.d("e",count+"numbers");
                                            for(final DocumentSnapshot document: task.getResult())
                                            {
                                                Map<String,Object>x=document.getData();
                                                Set<Map.Entry<String,Object>> st=x.entrySet();
                                                for(Map.Entry<String,Object> me:st)
                                                {

                                                    Log.d("e",me.getKey());
                                                    Log.d("e",me.getValue()+"");
                                                    if(i<base)
                                                        continue;
                                                    if(me.getKey().equals("sender"))
                                                        senders[count-i-1].setText("Sender: "+me.getValue()+"");
                                                    if(me.getKey().equals("message"))
                                                        messages[count-i-1].setText("Message: "+me.getValue()+"");
                                                }
                                                i++;
                                            }
                                        }
                                        notifs.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                }
            }
        });
    }
}
