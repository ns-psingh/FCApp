package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Workspace_access extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button logoutbtn;
    TextView signintext;
    public void onBackPressed(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_workspace_access);
        mAuth=FirebaseAuth.getInstance();
        signintext=(TextView) findViewById(R.id.signintext);
        signintext.setText("You are signed in as "+mAuth.getCurrentUser().getEmail());
        logoutbtn=(Button) findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Workspace_access.this,signin_iot.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
