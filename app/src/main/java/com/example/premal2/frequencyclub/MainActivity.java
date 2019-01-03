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

public class MainActivity extends AppCompatActivity {

    private String version="1.0.0";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    public static int flag=0;
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
                startActivity(new Intent(MainActivity.this, signin.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
      //  Log.d("e",mAuth.getCurrentUser().getEmail());
        T1 t=new T1();
        t.start();
        //TimeUnit.SECONDS.sleep(1);



        // DocumentReference documentReference=FirebaseFirestore.getInstance().collection("version").document("ver");
    }
}
