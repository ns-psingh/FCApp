package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Attendancepage extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    final CheckBox names[]=new CheckBox[100];

    static Map<String,Object> user=new HashMap<>();
    static int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_attendancepage);
        db=FirebaseFirestore.getInstance();
        user.put("takenby",Accountpage.email);
        final ScrollView memberlist=(ScrollView) findViewById(R.id.memberlist);
        memberlist.setVisibility(View.INVISIBLE);
        final EditText passwd=(EditText) findViewById(R.id.attendancepwd);
        final Button submitbtn=(Button) findViewById(R.id.submit);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwd.getText().toString().equals("attendance123fc"))
                {
                    passwd.setVisibility(View.INVISIBLE);
                    submitbtn.setVisibility(View.INVISIBLE);
                    memberlist.setVisibility(View.VISIBLE);
                }
            }
        });
        final LinearLayout coreteamlist=(LinearLayout) findViewById(R.id.coreteamlist);
        final LinearLayout seniorengineerlist=(LinearLayout) findViewById(R.id.seniorengglist);
        final LinearLayout juniorengineerlist=(LinearLayout) findViewById(R.id.juniorenglist);
        final ArrayList<String> coreteam=new ArrayList<String>();
        final ArrayList<String> seniorengineer=new ArrayList<String>();
        final ArrayList<String> juniorengineer=new ArrayList<String>();
       // final int id=0;
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            Log.d("e","entered here");
                            int id=0;
                            {for(final DocumentSnapshot document: task.getResult())
                            {
                                String name="";
                                String post="";
                                Map<String,Object> x=document.getData();
                                Set<Map.Entry<String,Object>> st=x.entrySet();
                                for(Map.Entry<String,Object> me:st)
                                {
                                    Log.d("e","mg ka val"+me.getValue());
                                    if(me.getKey().equals("name"))
                                    {
                                        name=me.getValue().toString();
                                    }


                                    if(me.getKey().equals("post"))
                                    {
                                        post=me.getValue().toString();
                                    }
                                }
                                Log.d("e","name and post hai"+name+post);
                                if(post.equals("Core Team Member"))
                                {

                                    coreteam.add(name);
                                    Log.d("e","coreteam length is now"+coreteam.size());
                                }

                                if(post.equals("Senior Engineer"))
                                {
                                    seniorengineer.add(name);
                                }

                                if(post.equals("Junior Engineer"))
                                {
                                    juniorengineer.add(name);
                                }

                            }

                            }

                            final int length=coreteam.size()+seniorengineer.size()+juniorengineer.size();
                            Log.d("e","till here length is "+length);
                            int i;
                            for(i=0;i<coreteam.size();i++)
                            {
                                names[i]=new CheckBox(getApplicationContext());
                                names[i].setText(coreteam.get(i));
                                coreteamlist.addView(names[i]);
                            }
                            int j=0;
                            for(;i<coreteam.size()+seniorengineer.size();i++)
                            {

                                names[i]=new CheckBox(getApplicationContext());
                                names[i].setText(seniorengineer.get(j++));
                                seniorengineerlist.addView(names[i]);
                            }
                            j=0;
                            for(;i<coreteam.size()+seniorengineer.size()+juniorengineer.size();i++)
                            {
                                names[i]=new CheckBox(getApplicationContext());
                                names[i].setText(juniorengineer.get(j++));
                                juniorengineerlist.addView(names[i]);
                            }

                            Log.d("e","exited here");

                        }
                    }
                });

        Log.d("e","resume");
        Button x=findViewById(R.id.submitfinal);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len=coreteam.size()+seniorengineer.size()+juniorengineer.size();

                int u;
                for(u=0;u<len;u++)
                {
                    if(names[u].isChecked())
                    {
                        Log.d("e","name added"+names[u].getText());
                        user.put("Name"+u, names[u].getText());
                    }
                }
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(System.currentTimeMillis());
                user.put("Timestamp",timeStamp);
                Log.d("e","len ka value hai"+len+"timestamp hai"+timeStamp);
                flag=1;

                Toast.makeText(Attendancepage.this,"Submitted.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Attendancepage.this, Accountpage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                // FirebaseFirestore upu= FirebaseFirestore.getInstance();
/*
                db.collection("attendance")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("e", "Error adding document", e);
                            }
                        });

                        */



            }
        });

    }
}
