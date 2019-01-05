package com.example.premal2.frequencyclub;

import android.content.Intent;
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

    public static MainActivity mainActivity;
    public static Boolean isVisible = false;
    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported by Google Play Services.");
                ToastNotify("This device is not supported by Google Play Services.");
                finish();
            }
            return false;
        }
        return true;
    }
    public void registerWithNotificationHubs()
    {
        if (checkPlayServices()) {
            Log.d("e","donn");
            // Start IntentService to register this application with FCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
            Log.d("e","donn3");
        }
    }

    private String version="1.0.0";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    public static int flag=0;
    class T2 extends Thread
    {
        public void run()
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            mainActivity = MainActivity.this;
            Log.d("e","eee1");
          //  NotificationsManager.handleNotifications(mainActivity, NotificationSettings.SenderId, MyHandler.class);
            Log.d("e","eee2");
            registerWithNotificationHubs();
        }
    }
    class T1 extends Thread
    {
        public void run()
        {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                                    Log.d("e","iternation");
                                    Log.d("e",me.getKey());
                                    Log.d("e",me.getValue()+"");
                                    String vers=me.getValue()+"";
                                    if(!vers.equals(version))
                                    {
                                        Toast.makeText(MainActivity.this, "Please Download Latest Update! We've released a newer version!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }}

                        }
                    });
            if(mAuth.getCurrentUser()!=null)
            {
                Log.d("e","entered here");
                startActivity(new Intent(MainActivity.this,mainpage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            else {
                startActivity(new Intent(MainActivity.this, general_home.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        isVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isVisible = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisible = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isVisible = false;
    }

    public void ToastNotify(final String notificationMessage) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("e","worksout");
                Toast.makeText(MainActivity.this, notificationMessage, Toast.LENGTH_LONG).show();
                TextView helloText = (TextView) findViewById(R.id.text_hello);
                helloText.setText(notificationMessage);
            }
        });
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
        T2 t2=new T2();
        t2.start();
        Log.d("e","donw");

        Log.d("e","donn4");



        mAuth=FirebaseAuth.getInstance();
        Log.d("e","donn5");
      //  Log.d("e",mAuth.getCurrentUser().getEmail());
        T1 t=new T1();
        t.start();
        //TimeUnit.SECONDS.sleep(1);



        // DocumentReference documentReference=FirebaseFirestore.getInstance().collection("version").document("ver");
    }
}
