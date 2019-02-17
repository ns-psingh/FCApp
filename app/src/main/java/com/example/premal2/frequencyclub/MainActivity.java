package com.example.premal2.frequencyclub;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.microsoft.windowsazure.notifications.NotificationsManager;
import com.onesignal.OneSignal;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String version="0.9.0";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    public static int flag=0;

        public static int recieved=-1;
        public static String enabled="";
        public static String event_name="";
        public static String fee="";
        public static int limit=0;
        public static int count=0;
        public static String thankyou="";
        public int complete1=0;
        public int complete2=0;
        public static String limitthere="";

    private boolean isOnline() //Function to check whether Internet is enabled
    {
        Log.d("e","entered 2");
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo=cm.getActiveNetworkInfo();
        Log.d("e","entered 3");
        if(netInfo!=null && netInfo.isConnectedOrConnecting())
        { Log.d("e","entered 4");
            return true;}
        else
        { Log.d("e","entered 5");
           // Toast.makeText(MainActivity.this, "Communication Issues in connecting to our servers! Please check internet connection and restart the app!", Toast.LENGTH_SHORT).show();

            return false;
        }
    }
    class T1 extends Thread
    {
        public void run()
        {

            /*
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            recieved=-1;
            enabled="";
            event_name="";
            fee="";
            limit=0;
            count=0;
            thankyou="";
            complete1=0;
            complete2=0;
            limitthere="";


            final int count3=0;

            db.collection("workshop_count")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful())
                            {
                                Log.d("e","start hua");

                                for(final DocumentSnapshot document: task.getResult())
                                {
                                    Map<String,Object> x=document.getData();
                                    Set<Map.Entry<String,Object>> st=x.entrySet();
                                    for(Map.Entry<String,Object> me:st)
                                    {
                                        if(me.getKey().equals("count"))
                                            count=Integer.parseInt(me.getValue()+"");
                                    }
                                }
                                Log.d("e","end hua"+count);
                                complete1=1;
                            }
                        }
                    });
            db.collection("workshop_configurations")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful())
                            {
                                Log.d("e","aya yahan pe");
                                for(final DocumentSnapshot document: task.getResult())
                            {
                                Map<String,Object>x=document.getData();
                                Set<Map.Entry<String,Object>> st=x.entrySet();
                                for(Map.Entry<String,Object> me:st)
                                {
                                    Log.d("e",me.getKey()+"-"+me.getValue());
                                    if(me.getKey().equals("enabled"))
                                        enabled=me.getValue()+"";
                                    if(me.getKey().equals("event name"))
                                    { Log.d("e","hehehe"+me.getValue());
                                        event_name=me.getValue()+"";}
                                    if(me.getKey().equals("fee"))
                                        fee=me.getValue()+"";
                                    if(me.getKey().equals("limit"))
                                        limit=Integer.parseInt(me.getValue()+"");
                                    if(me.getKey().equals("thankyoumessage"))
                                        thankyou=me.getValue()+"";
                                    if(me.getKey().equals("limitcheck"))
                                        limitthere=me.getValue()+"";
                                }
                                recieved=1;
                            }}

                        }
                    });
            db.collection("version")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful())
                            {for(final DocumentSnapshot document: task.getResult())
                            {
                                Map<String,Object>x=document.getData();
                                Set<Map.Entry<String,Object>> st=x.entrySet();
                                for(Map.Entry<String,Object> me:st)
                                {
                                    String vers=me.getValue()+"";
                                    if(!vers.equals(version))
                                    {
                                        Toast.makeText(MainActivity.this, "Please Download Latest Update! We've released a newer version!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }}

                        }
                    });
            Log.d("e","wait started");
            while(complete1==0 || recieved==0)
            Log.d("e","wait stopped");
            if(mAuth.getCurrentUser()!=null)
            {

                startActivity(new Intent(MainActivity.this,mainpage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            else {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(MainActivity.this, general_home.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        if(isOnline())
        { T1 t=new T1();
        t.start();}
        else
        {
             Toast.makeText(MainActivity.this, "Communication Issues in connecting to our servers! Please check internet connection and restart the app!", Toast.LENGTH_SHORT).show();

        }
    }
}
