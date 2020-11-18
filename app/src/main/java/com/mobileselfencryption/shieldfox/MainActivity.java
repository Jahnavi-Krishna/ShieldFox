package com.mobileselfencryption.shieldfox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseFirestore db;
    String FirebaseUser;


    Dialog d1;
    Button e1;
    EditText pwd1, pwd2, pwd3, pwd4;

    public CardView contacts, messages, files, notes, settings, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



        contacts = findViewById(R.id.c1);
        messages = findViewById(R.id.c2);
        notes = findViewById(R.id.c3);
        files = findViewById(R.id.c4);
        settings = findViewById(R.id.c5);
        logout = findViewById(R.id.c6);


        contacts.setOnClickListener(this);
        messages.setOnClickListener(this);
        notes.setOnClickListener(this);
        files.setOnClickListener(this);
        settings.setOnClickListener(this);
        logout.setOnClickListener(this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            Toast.makeText(MainActivity.this, "User Already Logged In", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.c1:
                 dia1();
                break;
             case R.id.c2:
                dia2();
                break;
            case R.id.c3:
               dia3();
                break;
            case R.id.c4:
               dia4();
                break;

            case R.id.c5:
                Intent i = new Intent(MainActivity.this, setting.class);
                startActivity(i);
                break;

            case R.id.c6:
                FirebaseAuth.getInstance().signOut();
                Intent out = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(out);
                break;

        }


    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void dia4() {
        d1 = new Dialog(MainActivity.this);
        d1.setContentView(R.layout.custom_dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        d1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d1.setCancelable(false);
        d1.getWindow().getAttributes().windowAnimations = R.style.animation;

        e1 = d1.findViewById(R.id.p1);
        pwd4 = d1.findViewById(R.id.pwd_1);

        d1.show();

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pwd = pwd4.getText().toString();
                if (pwd.isEmpty()) {
                    pwd4.setError("Please enter the password");
                    pwd4.requestFocus();
                } else {
                    FirebaseUser = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentSnapshot = task.getResult();
                                String string = Objects.requireNonNull(documentSnapshot).getString("File Password");
                                if (pwd.equals(string)){
                                    d1.dismiss();
                                    Intent i = new Intent(MainActivity.this , file.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                d1.dismiss();
                                Toast.makeText(MainActivity.this, "Set Password In Setting", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void dia3() {
        d1 = new Dialog(MainActivity.this);
        d1.setContentView(R.layout.custom_dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        d1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d1.setCancelable(false);
        d1.getWindow().getAttributes().windowAnimations = R.style.animation;

        e1 = d1.findViewById(R.id.p1);
        pwd3 = d1.findViewById(R.id.pwd_1);

        d1.show();

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pwd = pwd3.getText().toString();
                if (pwd.isEmpty()) {
                    pwd3.setError("Please enter the password");
                    pwd3.requestFocus();
                } else {
                    FirebaseUser = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentSnapshot = task.getResult();
                                String string = Objects.requireNonNull(documentSnapshot).getString("Note Password");
                                if (pwd.equals(string)){
                                    d1.dismiss();
                                    Intent i = new Intent(MainActivity.this , note.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                d1.dismiss();
                                Toast.makeText(MainActivity.this, "Set Password In Setting", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void dia2() {
        d1 = new Dialog(MainActivity.this);
        d1.setContentView(R.layout.custom_dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        d1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d1.setCancelable(false);
        d1.getWindow().getAttributes().windowAnimations = R.style.animation;

        e1 = d1.findViewById(R.id.p1);
        pwd2 = d1.findViewById(R.id.pwd_1);

        d1.show();

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pwd = pwd2.getText().toString();
                if (pwd.isEmpty()) {
                    pwd2.setError("Please enter the password");
                    pwd2.requestFocus();
                } else {
                    FirebaseUser = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentSnapshot = task.getResult();
                                String string = Objects.requireNonNull(documentSnapshot).getString("Message Password");
                                if (pwd.equals(string)){
                                    d1.dismiss();
                                    Intent i = new Intent(MainActivity.this , mes.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                d1.dismiss();
                                Toast.makeText(MainActivity.this, "Set Password In Setting", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void dia1() {

        d1 = new Dialog(MainActivity.this);
        d1.setContentView(R.layout.custom_dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          d1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        d1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d1.setCancelable(false);
        d1.getWindow().getAttributes().windowAnimations = R.style.animation;

        e1 = d1.findViewById(R.id.p1);
        pwd1 = d1.findViewById(R.id.pwd_1);

        d1.show();

        e1.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
                final String pwd = pwd1.getText().toString();
                if (pwd.isEmpty()) {
                    pwd1.setError("Please enter the password");
                    pwd1.requestFocus();
                } else {
                    FirebaseUser = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                               DocumentSnapshot documentSnapshot = task.getResult();
                                String string = Objects.requireNonNull(documentSnapshot).getString("Contact Password");
                                if (pwd.equals(string)){
                                    d1.dismiss();
                                    Intent i = new Intent(MainActivity.this , cont.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                d1.dismiss();
                                Toast.makeText(MainActivity.this, "Set Password In Setting", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


}





