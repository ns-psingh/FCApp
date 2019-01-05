package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Workshop_Registration extends AppCompatActivity {

    public static String name;
    public static String phone;
    public static String email;
    public static String sem;
    public static String onlinenum;
    public static String transactionid;
    public static String paymentmethod="Select Payment Method";
    EditText ETname;
    EditText ETphone;
    EditText ETemail;
    EditText ETsem;
    public void onBackPressed(){
        startActivity(new Intent(Workshop_Registration.this,general_home.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_workshop__registration);
        ETname=(EditText) findViewById(R.id.name);
        ETphone=(EditText) findViewById(R.id.phonenum);
        ETemail=(EditText) findViewById(R.id.email);
        ETsem=(EditText) findViewById(R.id.sem);
        ETname.setText(name);
        ETphone.setText(phone);
        ETemail.setText(email);
        ETsem.setText(sem);
        Button continubtn=(Button) findViewById(R.id.continuebtn);
        continubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ETname.getText().toString().equals("")||ETphone.getText().toString().equals("")||ETemail.getText().toString().equals("")||ETsem.getText().toString().equals(""))
                {
                    Toast.makeText(Workshop_Registration.this, "Please fill all details in order to proceed.", Toast.LENGTH_SHORT).show();
                }
                else {

                    name=ETname.getText().toString();
                    phone=ETphone.getText().toString();
                    email=ETemail.getText().toString();
                    sem=ETsem.getText().toString();
                    startActivity(new Intent(Workshop_Registration.this, Workshop_Registration2.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }
        });
    }
}
