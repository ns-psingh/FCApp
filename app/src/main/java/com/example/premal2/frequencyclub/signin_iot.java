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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Set;

public class signin_iot extends AppCompatActivity {

    EditText emailtext;
    EditText passwordtext;
    Button signinbtn;
    ProgressBar loginprogress;
    FirebaseAuth mAuth;
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(),general_home.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_iot);
        getSupportActionBar().hide();
        mAuth=FirebaseAuth.getInstance();
        emailtext=(EditText) findViewById(R.id.emailtext);
        passwordtext=(EditText) findViewById(R.id.passwordtext);
        loginprogress=(ProgressBar) findViewById(R.id.loginprocess);
        loginprogress.setVisibility(View.INVISIBLE);
        signinbtn=(Button) findViewById(R.id.signinbtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emailtext.getText().toString().equals("")||passwordtext.getText().toString().equals(""))
                {
                    Toast.makeText(signin_iot.this, "Please Enter All Fields!", Toast.LENGTH_SHORT).show();
                }
                else {

                        loginprogress.setVisibility(View.VISIBLE);
                        signinbtn.setVisibility(View.INVISIBLE);
                        mAuth.signInWithEmailAndPassword(emailtext.getText().toString(),passwordtext.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),Workspace_access.class));
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                                else
                                {
                                    loginprogress.setVisibility(View.INVISIBLE);
                                    signinbtn.setVisibility(View.VISIBLE);
                                    Toast.makeText(signin_iot.this, "Entered Credentials dont match!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                }

            }
        });
    }
}
