package com.example.premal2.frequencyclub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Workshop_Registration2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public int counttt;
    public int flag;
    EditText paymentnum;
    EditText transactionid;
    Spinner paymentmethod;
    CheckBox sel;
    FirebaseFirestore db;
    TextView eventname;
    TextView eventfee;
    int checked=0;
    public void onBackPressed(){
        Workshop_Registration.onlinenum=paymentnum.getText().toString();
        Workshop_Registration.transactionid=transactionid.getText().toString();
        Workshop_Registration.paymentmethod=paymentmethod.getSelectedItem().toString();
        startActivity(new Intent(Workshop_Registration2.this,Workshop_Registration.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
      //  String item = parent.getItemAtPosition(position).toString();
      //  Workshop_Registration.paymentmethod=item;
        // Showing selected spinner item
     //   Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_workshop__registration2);
        db=FirebaseFirestore.getInstance();
        eventname=(TextView) findViewById(R.id.eventname);
        eventfee=(TextView) findViewById(R.id.eventfee);
        eventname.setText(MainActivity.event_name);
        eventfee.setText(MainActivity.fee);
        paymentnum=(EditText) findViewById(R.id.paymentnum);
        transactionid=(EditText) findViewById(R.id.transactionid);
        paymentnum.setText(Workshop_Registration.onlinenum);
        transactionid.setText(Workshop_Registration.transactionid);
        paymentmethod=(Spinner) findViewById(R.id.payment);
        sel=(CheckBox) findViewById(R.id.confirmationbtn);
        List<String> options=new ArrayList<String>();
        options.add("Select Payment Method");
        options.add("Paytm");
        options.add("Google Pay");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        paymentmethod.setAdapter(dataAdapter);
        switch (Workshop_Registration.paymentmethod)
        {
            case "Select Payment Method": paymentmethod.setSelection(0);
            break;
            case "Paytm": paymentmethod.setSelection(1);
                break;
            case "Google Pay": paymentmethod.setSelection(2);
                break;
        }


        final Button submitbtn=(Button) findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while(checked==1);
                if(paymentnum.getText().toString().equals("")||transactionid.getText().toString().equals("")||paymentmethod.getSelectedItem().toString().equals("Select Payment Method"))
                {
                    Toast.makeText(Workshop_Registration2.this, "Please fill all details in order to submit.", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    if(MainActivity.count>MainActivity.limit && MainActivity.limitthere.equals("yes"))
                    {
                        flag=1;
                        Toast.makeText(Workshop_Registration2.this, "Sorry, seats are filled!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Workshop_Registration2.this,MainActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                    if(flag==0) {
                        if (!sel.isChecked()) {
                            Toast.makeText(Workshop_Registration2.this, "Please make sure all details are correct, and check the checkbox.", Toast.LENGTH_SHORT).show();

                        } else {
                            db.collection("workshop_count").document("count")
                                    .delete();
                            Map<String,Object> countdoc=new HashMap<>();
                            countdoc.put("count",MainActivity.count+1);
                            db.collection("workshop_count").document("count").set(countdoc);
                            submitbtn.setVisibility(View.INVISIBLE);
                            Workshop_Registration.onlinenum = paymentnum.getText().toString();
                            Workshop_Registration.paymentmethod = paymentmethod.getSelectedItem().toString();
                            Workshop_Registration.transactionid = transactionid.getText().toString();
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name", Workshop_Registration.name);
                            user.put("Email-ID", Workshop_Registration.email);
                            user.put("Phone Number", Workshop_Registration.phone);
                            user.put("Semester", Workshop_Registration.sem);
                            user.put("Payment Method ", Workshop_Registration.paymentmethod);
                            user.put("Payment Number", Workshop_Registration.onlinenum);
                            user.put("Transaction ID", Workshop_Registration.transactionid);
                            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(System.currentTimeMillis());
                            db.collection("workshop_registrations").document(timeStamp).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(Workshop_Registration2.this, TicketConfirmation.class));
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Workshop_Registration2.this, "Unable to register.", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            ;

                        }
                    }

                }

            }
        });
        //Its advised that you screenshot this.
        //Registration Number: 3
        //"Thank you for registering at NVidia VR Workshop. Your registration has been submitted successfully to our database. We will be sending confirmation to your email address shortly. Kindly note that, since seats are limited, and in case your registration is not within top 60, you will not be able to participate in the event, and your registration fee will be refunded to your respective number."
    }
}
