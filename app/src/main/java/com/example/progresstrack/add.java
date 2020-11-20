package com.example.progresstrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add extends AppCompatActivity {
    EditText sName,sComment;
    Button sSave;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        sName=findViewById(R.id.s_Name);
        sComment=findViewById(R.id.s_Comment);
        sSave=findViewById(R.id.s_Save);

        //save data in firebase on button click
        sSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("addSub");
                //get all the values from users
                String name = sName.getEditableText().toString();
                String comment = sComment.getEditableText().toString();
                addsubfield addField = new addsubfield(name,comment);
                reference.child(name).setValue(addField);

            }
        });
    }

}