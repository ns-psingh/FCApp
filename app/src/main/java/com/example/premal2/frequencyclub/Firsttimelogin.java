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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Firsttimelogin extends AppCompatActivity {

    String post;
    private FirebaseAuth mAuth;
    ProgressBar ftl;

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    public void onBackPressed(){
        Toast.makeText(Firsttimelogin.this, "Please Complete this Procedure.", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_firsttimelogin);
        mAuth=FirebaseAuth.getInstance();
         ftl=(ProgressBar) findViewById(R.id.processing_ftl);
        ftl.setVisibility(View.INVISIBLE);
        TextView welcome=(TextView) findViewById(R.id.welcomemessage);
        welcome.setText("Welcome "+signin.email+" to Frequency Club!\nYou are signing into this application for the first time. Please fill all fields.");
        TextView email_id=(TextView) findViewById(R.id.email_id);
        email_id.setText("Your Email ID: "+signin.email);
        final EditText password1=(EditText) findViewById(R.id.password1);
        final EditText password2=(EditText) findViewById(R.id.password2);
        final EditText name=(EditText) findViewById(R.id.fullname);
        final EditText usn=(EditText) findViewById(R.id.usn);
        final EditText dept=(EditText) findViewById(R.id.dept);
        final EditText num=(EditText) findViewById(R.id.mobileno);
        Button continuebtn =(Button) findViewById(R.id.continuebtn);

        RadioGroup radioGroup=(RadioGroup) findViewById(R.id.post);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedid) {
                RadioButton rb=(RadioButton) radioGroup.findViewById(checkedid);
                if(null!=rb && checkedid>-1)
                {
                    post=rb.getText()+"";
                   // Toast.makeText(Firsttimelogin.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("e","entered here");
                if(password1.getText().toString().equals("")||password2.getText().toString().equals("")||name.getText().toString().equals("")||post.equals("")||usn.getText().toString().equals("")||dept.getText().toString().equals("")||num.getText().toString().equals(""))
                {
                    Log.d("e","problem");
                    Toast.makeText(Firsttimelogin.this, "Please Enter All Fields to Proceed!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password1.getText().toString().equals(password2.getText().toString())) {

                        if(password1.getText().toString().length()<8)
                        {
                            Toast.makeText(Firsttimelogin.this, "Password is too small!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            ftl.setVisibility(View.VISIBLE);
                            Map<String, Object> user = new HashMap<>();
                            user.put("email", signin.email);
                            user.put("name", name.getText().toString());
                            user.put("post", post);
                            user.put("usn", usn.getText().toString());
                            user.put("dept", dept.getText().toString());
                            user.put("mobile", num.getText().toString());
                            Log.d("e", "works");
                            db.collection("users")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d("e", "DocumentSnapshot added with ID: " + documentReference.getId());
                                            //  ftl.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Firsttimelogin.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                                            Log.d("e", "Email id: " + signin.email + " Password: " + password1.getText().toString());
                                            mAuth.createUserWithEmailAndPassword(signin.email, password1.getText().toString());

                                             mAuth.signInWithEmailAndPassword(signin.email,password1.getText().toString());
                                            startActivity(new Intent(Firsttimelogin.this, mainpage.class));
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("e", "Error adding document", e);
                                        }
                                    });
                        }

                    }
                    else
                    {
                        Toast.makeText(Firsttimelogin.this, "Make sure both passwords are same!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /*mAuth.createUserWithEmailAndPassword("premals98@gmail.com","password")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });*/
    }
}
