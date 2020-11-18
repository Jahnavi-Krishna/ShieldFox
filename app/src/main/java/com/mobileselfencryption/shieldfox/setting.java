package com.mobileselfencryption.shieldfox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class setting extends AppCompatActivity {

    SwitchCompat Switch1, Switch2, Switch3, Switch4;
    FirebaseFirestore db;
    String FirebaseUser;
    FirebaseAuth mFirebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Switch1 = findViewById(R.id.switch1);
        Switch2 = (SwitchCompat) findViewById(R.id.switch2);
        Switch3 = (SwitchCompat) findViewById(R.id.switch3);
        Switch4 = (SwitchCompat) findViewById(R.id.switch4);

        SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
        Switch1.setChecked(sharedPreferences.getBoolean("value",true));
        Switch2.setChecked(sharedPreferences.getBoolean("value",true));
        Switch3.setChecked(sharedPreferences.getBoolean("value",true));
        Switch4.setChecked(sharedPreferences.getBoolean("value",true));

        mFirebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();

        Switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Switch1.setChecked(false);
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Switch1.setChecked(true);


                    db.collection("UserInfo").document(FirebaseUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(Objects.requireNonNull(task.getResult()).exists()){
                                 dialog1();
                            }
                            else{
                                newd1();
                            }
                        }
                    });

                }
            }
        });

        Switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Switch2.setChecked(false);
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Switch2.setChecked(true);

                    db.collection("UserInfo").document(FirebaseUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(Objects.requireNonNull(task.getResult()).exists()){
                                dialog2();
                            }
                            else{
                                newd2();
                            }
                        }
                    });

                }
            }
        });

        Switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Switch3.setChecked(false);
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Switch3.setChecked(true);

                    db.collection("UserInfo").document(FirebaseUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(Objects.requireNonNull(task.getResult()).exists()){
                                dialog3();
                            }
                            else{
                                newd3();
                            }
                        }
                    });
                }
            }
        });

        Switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Switch4.setChecked(false);
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Switch4.setChecked(true);

                    db.collection("UserInfo").document(FirebaseUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(Objects.requireNonNull(task.getResult()).exists()){
                                dialog4();
                            }
                            else{
                                newd4();
                            }
                        }
                    });
                }
            }
        });

    }

    private void dialog4() {
        final Dialog di1;
        Button enter;
        final EditText new_pwd , con_pwd , old_pwd;




        di1 = new Dialog(setting.this);
        di1.setContentView(R.layout.activity_dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            di1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        di1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        di1.setCancelable(false);
        di1.getWindow().getAttributes().windowAnimations = R.style.animation;


        enter = di1.findViewById(R.id.enter1);
        old_pwd = di1.findViewById(R.id.pwd_1);
        new_pwd = di1.findViewById(R.id.pwd_2);
        con_pwd = di1.findViewById(R.id.pwd_3);


        di1.show();

        enter.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String opwd = old_pwd.getText().toString();
                String pwd = new_pwd.getText().toString();
                String cpwd = con_pwd.getText().toString();
                if (pwd.isEmpty()) {
                    new_pwd.setError("Please enter the password");
                    new_pwd.requestFocus();
                }
                else if(opwd.isEmpty()){
                    old_pwd.setError("Please enter the password");
                    old_pwd.requestFocus();
                }
                else if (cpwd.isEmpty()) {
                    con_pwd.setError("Please confirm the password");
                    con_pwd.requestFocus();
                } else if (!pwd.equals(cpwd)) {
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                }
                else if(opwd.equals(pwd)){
                    Toast.makeText(setting.this, "The new password you entered is the same as the old password. Enter a different password", Toast.LENGTH_SHORT).show();
                }
                else {
                    di1.dismiss();
                    FirebaseUser = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    Map<String, Object> Conpwd = new HashMap<>();
                    Conpwd.put("Contact Password", pwd);
                    documentReference.update(Conpwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Password saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(setting.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });
    }

    private void dialog3() {
        final Dialog di1;
        Button enter;
        final EditText new_pwd , con_pwd , old_pwd;




        di1 = new Dialog(setting.this);
        di1.setContentView(R.layout.activity_dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            di1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        di1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        di1.setCancelable(false);
        di1.getWindow().getAttributes().windowAnimations = R.style.animation;


        enter = di1.findViewById(R.id.enter1);
        old_pwd = di1.findViewById(R.id.pwd_1);
        new_pwd = di1.findViewById(R.id.pwd_2);
        con_pwd = di1.findViewById(R.id.pwd_3);


        di1.show();

        enter.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String opwd = old_pwd.getText().toString();
                String pwd = new_pwd.getText().toString();
                String cpwd = con_pwd.getText().toString();
                if (pwd.isEmpty()) {
                    new_pwd.setError("Please enter the password");
                    new_pwd.requestFocus();
                }
                else if(opwd.isEmpty()){
                    old_pwd.setError("Please enter the password");
                    old_pwd.requestFocus();
                }
                else if (cpwd.isEmpty()) {
                    con_pwd.setError("Please confirm the password");
                    con_pwd.requestFocus();
                } else if (!pwd.equals(cpwd)) {
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                }
                else if(opwd.equals(pwd)){
                    Toast.makeText(setting.this, "The new password you entered is the same as the old password. Enter a different password", Toast.LENGTH_SHORT).show();
                }
                else {
                    di1.dismiss();
                    FirebaseUser = mFirebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    Map<String, Object> Conpwd = new HashMap<>();
                    Conpwd.put("Contact Password", pwd);
                    documentReference.update(Conpwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Password saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(setting.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });
    }

    private void dialog2() {
        final Dialog di1;
        Button enter;
        final EditText new_pwd , con_pwd , old_pwd;




        di1 = new Dialog(setting.this);
        di1.setContentView(R.layout.activity_dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            di1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        di1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        di1.setCancelable(false);
        di1.getWindow().getAttributes().windowAnimations = R.style.animation;


        enter = di1.findViewById(R.id.enter1);
        old_pwd = di1.findViewById(R.id.pwd_1);
        new_pwd = di1.findViewById(R.id.pwd_2);
        con_pwd = di1.findViewById(R.id.pwd_3);


        di1.show();

        enter.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String opwd = old_pwd.getText().toString();
                String pwd = new_pwd.getText().toString();
                String cpwd = con_pwd.getText().toString();
                if (pwd.isEmpty()) {
                    new_pwd.setError("Please enter the password");
                    new_pwd.requestFocus();
                }
                else if(opwd.isEmpty()){
                    old_pwd.setError("Please enter the password");
                    old_pwd.requestFocus();
                }
                else if (cpwd.isEmpty()) {
                    con_pwd.setError("Please confirm the password");
                    con_pwd.requestFocus();
                } else if (!pwd.equals(cpwd)) {
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                }
                else if(opwd.equals(pwd)){
                    Toast.makeText(setting.this, "The new password you entered is the same as the old password. Enter a different password", Toast.LENGTH_SHORT).show();
                }
                else {
                    di1.dismiss();
                    FirebaseUser = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    Map<String, Object> Conpwd = new HashMap<>();
                    Conpwd.put("Contact Password", pwd);
                    documentReference.update(Conpwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Password saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(setting.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });
    }

    private void dialog1() {
        final Dialog di1;
        Button enter;
        final EditText new_pwd , con_pwd , old_pwd;




        di1 = new Dialog(setting.this);
        di1.setContentView(R.layout.activity_dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            di1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        di1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        di1.setCancelable(false);
        di1.getWindow().getAttributes().windowAnimations = R.style.animation;


        enter = di1.findViewById(R.id.enter1);
        old_pwd = di1.findViewById(R.id.pwd_1);
        new_pwd = di1.findViewById(R.id.pwd_2);
        con_pwd = di1.findViewById(R.id.pwd_3);


        di1.show();

        enter.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String opwd = old_pwd.getText().toString();
                String pwd = new_pwd.getText().toString();
                String cpwd = con_pwd.getText().toString();
                if (pwd.isEmpty()) {
                    new_pwd.setError("Please enter the password");
                    new_pwd.requestFocus();
                }
                else if(opwd.isEmpty()){
                    old_pwd.setError("Please enter the password");
                    old_pwd.requestFocus();
                }
                else if (cpwd.isEmpty()) {
                    con_pwd.setError("Please confirm the password");
                    con_pwd.requestFocus();
                } else if (!pwd.equals(cpwd)) {
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                }
                else if(opwd.equals(pwd)){
                    Toast.makeText(setting.this, "The new password you entered is the same as the old password. Enter a different password", Toast.LENGTH_SHORT).show();
                }
                else {
                    di1.dismiss();
                    FirebaseUser = mFirebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    Map<String, Object> Conpwd = new HashMap<>();
                    Conpwd.put("Contact Password", pwd);
                    documentReference.update(Conpwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Password saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(setting.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });

    }

    private void newd4() {
        final Dialog di1;
        Button enter1;
        final EditText new_pwd , con_pwd;

        di1 = new Dialog(setting.this);
        di1.setContentView(R.layout.activity_newd1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            di1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        di1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        di1.setCancelable(false);
        di1.getWindow().getAttributes().windowAnimations = R.style.animation;

        mFirebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        enter1 = di1.findViewById(R.id.enter);
        new_pwd = di1.findViewById(R.id.pwd_new);
        con_pwd = di1.findViewById(R.id.pwd_con);


        di1.show();

        enter1.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String pwd = new_pwd.getText().toString();
                String cpwd = con_pwd.getText().toString();
                if (pwd.isEmpty()) {
                    new_pwd.setError("Please enter the password");
                    new_pwd.requestFocus();
                } else if (cpwd.isEmpty()) {
                    con_pwd.setError("Please confirm the password");
                    con_pwd.requestFocus();
                } else if (!pwd.equals(cpwd)) {
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    di1.dismiss();
                    FirebaseUser = mFirebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    Map<String, Object> Filepwd = new HashMap<>();
                    Filepwd.put("File Password", pwd);
                    documentReference.update(Filepwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Password saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(setting.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });

    }

    private void newd3() {
        final Dialog di1;
        Button enter1;
        final EditText new_pwd , con_pwd;



        di1 = new Dialog(setting.this);
        di1.setContentView(R.layout.activity_newd1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            di1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        di1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        di1.setCancelable(false);
        di1.getWindow().getAttributes().windowAnimations = R.style.animation;

        mFirebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        enter1 = di1.findViewById(R.id.enter);
        new_pwd = di1.findViewById(R.id.pwd_new);
        con_pwd = di1.findViewById(R.id.pwd_con);


        di1.show();

        enter1.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String pwd = new_pwd.getText().toString();
                String cpwd = con_pwd.getText().toString();
                if (pwd.isEmpty()) {
                    new_pwd.setError("Please enter the password");
                    new_pwd.requestFocus();
                } else if (cpwd.isEmpty()) {
                    con_pwd.setError("Please confirm the password");
                    con_pwd.requestFocus();
                } else if (!pwd.equals(cpwd)) {
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    di1.dismiss();
                    FirebaseUser = mFirebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    Map<String, Object> Notepwd = new HashMap<>();
                    Notepwd.put("Note Password", pwd);
                    documentReference.update(Notepwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Password saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(setting.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });
    }

    private void newd2() {
        final Dialog di1;
        Button enter1;
        final EditText new_pwd , con_pwd;


        di1 = new Dialog(setting.this);
        di1.setContentView(R.layout.activity_newd1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            di1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        di1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        di1.setCancelable(false);
        di1.getWindow().getAttributes().windowAnimations = R.style.animation;

        mFirebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        enter1 = di1.findViewById(R.id.enter);
        new_pwd = di1.findViewById(R.id.pwd_new);
        con_pwd = di1.findViewById(R.id.pwd_con);


        di1.show();

        enter1.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String pwd = new_pwd.getText().toString();
                String cpwd = con_pwd.getText().toString();
                if (pwd.isEmpty()) {
                    new_pwd.setError("Please enter the password");
                    new_pwd.requestFocus();
                } else if (cpwd.isEmpty()) {
                    con_pwd.setError("Please confirm the password");
                    con_pwd.requestFocus();
                } else if (!pwd.equals(cpwd)) {
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    di1.dismiss();
                    FirebaseUser = mFirebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    Map<String, Object> Mespwd = new HashMap<>();
                    Mespwd.put("Message Password", pwd);
                    documentReference.update(Mespwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Password saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(setting.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });


    }

    private void newd1() {
         final Dialog di1;
        Button enter1;
        final EditText new_pwd , con_pwd;


        di1 = new Dialog(setting.this);
        di1.setContentView(R.layout.activity_newd1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            di1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        di1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        di1.setCancelable(false);
        di1.getWindow().getAttributes().windowAnimations = R.style.animation;

        mFirebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        enter1 = di1.findViewById(R.id.enter);
        new_pwd = di1.findViewById(R.id.pwd_new);
        con_pwd = di1.findViewById(R.id.pwd_con);


        di1.show();

        enter1.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                String pwd = new_pwd.getText().toString();
                String cpwd = con_pwd.getText().toString();
                if (pwd.isEmpty()) {
                    new_pwd.setError("Please enter the password");
                    new_pwd.requestFocus();
                } else if (cpwd.isEmpty()) {
                    con_pwd.setError("Please confirm the password");
                    con_pwd.requestFocus();
                } else if (!pwd.equals(cpwd)) {
                    Toast.makeText(setting.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    di1.dismiss();
                    FirebaseUser = mFirebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
                    Map<String, Object> Conpwd = new HashMap<>();
                    Conpwd.put("Contact Password", pwd);
                    documentReference.update(Conpwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Password saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(setting.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });


    }


}

