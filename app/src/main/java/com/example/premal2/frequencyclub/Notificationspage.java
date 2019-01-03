package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                                            senders[count-i-1].setText("Message: "+me.getValue()+"");
                                        if(me.getKey().equals("message"))
                                            messages[count-i-1].setText("Sender: "+me.getValue()+"");
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
                                            senders[count-i-1].setText("Message: "+me.getValue()+"");
                                        if(me.getKey().equals("message"))
                                            messages[count-i-1].setText("Sender: "+me.getValue()+"");
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
                    Toast.makeText(Notificationspage.this, "Your Notification is now broadcasted. Thank You!", Toast.LENGTH_SHORT).show();
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
