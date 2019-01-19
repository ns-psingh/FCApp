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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Set;

public class signin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    static String email;
    public void onBackPressed(){

    }
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signin);
        final ProgressBar loginprogress=(ProgressBar) findViewById(R.id.loginprocess);
        mAuth=FirebaseAuth.getInstance();
        loginprogress.setVisibility(View.INVISIBLE);
        final Button signinbtn=(Button) findViewById(R.id.signinbtn);
        final EditText emaiilid=(EditText) findViewById(R.id.emailid_login);
        final EditText password=(EditText) findViewById(R.id.password_login);
        TextView skipsignin=(TextView) findViewById(R.id.skipbtn);
        skipsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin.this,general_home.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emaiilid.getText().toString().equals("")||password.getText().toString().equals(""))
                {
                    Toast.makeText(signin.this, "Please Enter All Fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.getText().toString().equals("welcometofrequency")) {
                        loginprogress.setVisibility(View.VISIBLE);
                        signinbtn.setVisibility(View.INVISIBLE);
                        db.collection("unregistered_accounts")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            final DocumentSnapshot t;
                                            int flag = 0;

                                            String emailid2="";
                                            for (final DocumentSnapshot document : task.getResult()) {

                                                Map<String, Object> x = document.getData();
                                                Set<Map.Entry<String, Object>> st = x.entrySet();
                                                for (Map.Entry<String, Object> me : st) {
                                                    Log.d("e", me.getKey() + ":");
                                                    Log.d("e", me.getValue() + "");
                                                    if (me.getValue().equals(emaiilid.getText().toString()))
                                                    {
                                                        flag = 1;

                                                    }
                                                    emailid2=me.getValue()+"";
                                                }

                                            }
                                            if (flag == 1) {
                                                email=emaiilid.getText().toString();
                                                startActivity(new Intent(signin.this, Firsttimelogin.class));


                                            } else {
                                                loginprogress.setVisibility(View.INVISIBLE);
                                                signinbtn.setVisibility(View.VISIBLE);
                                                Toast.makeText(signin.this, "Sorry! Only Club Members can login to the app. If you're facing any issues, please write us at frequencyclub@gmail.com.", Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            Log.w("e", "Error getting documents.", task.getException());
                                        }
                                    }
                                });
                    }
                    else
                    {
                        loginprogress.setVisibility(View.VISIBLE);
                        signinbtn.setVisibility(View.INVISIBLE);
                        mAuth.signInWithEmailAndPassword(emaiilid.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),mainpage.class));
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                                else
                                {
                                    loginprogress.setVisibility(View.INVISIBLE);
                                    signinbtn.setVisibility(View.VISIBLE);
                                    Toast.makeText(signin.this, "Entered Credentials dont match!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }

            }
        });
    }
}
