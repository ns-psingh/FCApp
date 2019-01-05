package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicketConfirmation extends AppCompatActivity {

    public void onBackPressed(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ticket_confirmation);
        TextView name=(TextView) findViewById(R.id.nametext);
        name.setText(Workshop_Registration.name);
        TextView emailid=(TextView) findViewById(R.id.emailtext);
        emailid.setText(Workshop_Registration.email);
        TextView phone=(TextView) findViewById(R.id.phonetext);
        phone.setText(Workshop_Registration.phone);
        TextView sem=(TextView) findViewById(R.id.semtext);
        sem.setText(Workshop_Registration.sem);
        TextView method=(TextView) findViewById(R.id.paytmdisplay);
        method.setText(Workshop_Registration.paymentmethod+" Number");
        TextView paynum=(TextView) findViewById(R.id.paytmtext);
        paynum.setText(Workshop_Registration.onlinenum);
        TextView transactionid=(TextView) findViewById(R.id.tidtext);
        transactionid.setText(Workshop_Registration.transactionid);
        Button mainbtn=(Button) findViewById(R.id.mainbtn);
        mainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TicketConfirmation.this,MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
