package com.mobileselfencryption.shieldfox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Document;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Notes extends AppCompatActivity {
    String xmlFile , FirebaseUser , string;
    Button encrypt;
    Document document , output;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db;
    String AES = "AES";
StorageReference mStorageRef;
Uri filepath;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        mStorageRef = FirebaseStorage.getInstance ().getReference ();
        xmlFile = "R.layout.activity_notes.xml";
        try {
            document = getDocument(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        encrypt = findViewById(R.id.btn_add);
        FirebaseUser = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid();
        DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    string = Objects.requireNonNull(documentSnapshot).getString("Notes Password");
                }
            }
            });

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    encrypt(document , string);
                } catch (Exception e) {
                    e.printStackTrace ();
                }

            }
        });


    }


    private void encrypt(Document doc , String Password) throws Exception {
       // Element rootElement = doc.getDocumentElement();
        SecretKeySpec key = generateKey(Password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE , key);
        cipher.doFinal ();

        FirebaseUser = mFirebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("UserInfo").document(FirebaseUser);
        Map<String, Object> notes = new HashMap<> ();
        notes.put("Notes", doc);
        documentReference.update(notes).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Notes.this, "Password saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Notes.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-246");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }
   

    private Document getDocument(String xmlFile) throws Exception {

        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = builder.newDocumentBuilder();
        return docBuilder.parse(xmlFile);

    }

}

