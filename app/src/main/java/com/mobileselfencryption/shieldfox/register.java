package com.mobileselfencryption.shieldfox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    EditText emailId , password , cpassword;
    Button signUp;
    TextView tvsignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.password1);
        signUp = findViewById(R.id.login);
        tvsignIn = findViewById(R.id.textView);
        signUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String cpwd = cpassword.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Please enter the password");
                    password.requestFocus();
                }
                else if(cpwd.isEmpty()){
                    cpassword.setError("Please confirm the password");
                    cpassword.requestFocus();
                }
                else if(!pwd.equals(cpwd)){
                    Toast.makeText(register.this,"Password mismatch",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(register.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email , pwd).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(register.this,"Sign Up Unsuccessful, Please try again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(register.this,MainActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(register.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}