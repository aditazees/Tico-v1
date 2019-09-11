package com.aditazees.ticov1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    public EditText emailId, passwd, name;
    Button btnSignUp;
    TextView signIn;
    String nametyped, emailtyped, currencytypeentered;
    FirebaseAuth firebaseAuth;

    ProgressBar pbar;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();

        String[] arraySpinner = new String[] {"INR", "USD", "EUR", "JPY"};

        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        currencytypeentered = s.getSelectedItem().toString();

        emailId = findViewById(R.id.ETemail);
        passwd = findViewById(R.id.ETpassword);
        name = findViewById(R.id.name);
        //emailId = findViewById(R.id.ETemail);
        btnSignUp = findViewById(R.id.btnSignUp);
        signIn = findViewById(R.id.TVSignIn);
        //usrname=findViewById(R.id.username);
        pbar=(ProgressBar)findViewById(R.id.progressBar2);
        pbar.setVisibility(View.INVISIBLE);




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailID = emailId.getText().toString();
                final String paswd = passwd.getText().toString();
                //String usrnme=usrname.getText().toString();
                nametyped=name.getText().toString().trim();
                emailtyped=emailId.getText().toString();
                //usernametyped=usrname.getText().toString();


                if (emailID.isEmpty()) {
                    emailId.setError("Provide your Email first!");
                    emailId.requestFocus();
                } else if (paswd.isEmpty()) {
                    passwd.setError("Set your password");
                    passwd.requestFocus();
                } else if (emailID.isEmpty() && paswd.isEmpty()) {
                    Toast.makeText(Signup.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailID.isEmpty() && paswd.isEmpty())) {

                    pbar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(Signup.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                pbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Signup.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                User user=new User(nametyped,emailtyped,currencytypeentered);
                                FirebaseDatabase.getInstance().getReference("User Info")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            pbar.setVisibility(View.INVISIBLE);
                                            Intent intent = new Intent(Signup.this, Login.class);
                                            startActivity(intent);

                                        }else{
                                            pbar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Signup.this,"Registration Error",Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });

                            }
                        }
                    });
                } else {
                    pbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Signup.this, "Registration Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(Signup.this, Login.class);
                startActivity(I);
            }
        });
    }
}