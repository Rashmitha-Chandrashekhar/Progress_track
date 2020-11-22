package com.example.progresstrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class add extends AppCompatActivity {
    EditText sName, sComment;
    Button sSave;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        sName = findViewById(R.id.s_Name);
        sComment = findViewById(R.id.s_Comment);
        sSave = findViewById(R.id.s_Save);

        //save data in firebase on button click
        sSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("addSub");
                //get all the values from users
                String name = sName.getEditableText().toString();
                String comment = sComment.getEditableText().toString();
                addsubfield addField = new addsubfield(name, comment);
                reference.child(name).setValue(addField);

            }
        });
    }
        public void read(){
             final String TAG="add.java";
            final String name = sName.getEditableText().toString().trim();
            final String comment = sComment.getEditableText().toString().trim();
            //firebase reference
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("addSub");
            Query checkUser = reference.orderByChild("name").equalTo(name);
            checkUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String name1 = dataSnapshot.child(name).child("name").getValue(String.class);
                        String comment1 = dataSnapshot.child(comment).child("comment").getValue(String.class);
                        Log.d(TAG,"name is" +name1);
                        Log.d(TAG,"comment is" +comment1);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w(TAG, "Failed to read value.", databaseError.toException());
                }
            });

        }
    }

