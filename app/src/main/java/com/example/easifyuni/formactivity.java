package com.example.easifyuni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class formactivity extends AppCompatActivity implements View.OnClickListener {
    //private static final String TAG = "formactivity";
    //private static final String event_name="name";
    //private static final String event_desc="desc";
    //private static final String event_Tag="Tag";
    //private static final String event_Dept="dept";
    //private static final String event_FacCo="FacCo";
    //private static final String event_Stuco="Stuco";

    private TextInputEditText editTextname;
    private TextInputEditText editTextdesc;
    private TextInputEditText editTextTagline;
    private TextInputEditText editTextdept;
    private TextInputEditText editTextFacCo;
    private TextInputEditText editTextStuco;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);

        db = FirebaseFirestore.getInstance();

        editTextname = findViewById(R.id.event_name);
        editTextdesc = findViewById(R.id.event_desc);
        editTextTagline = findViewById(R.id.event_Tag);
        editTextdept = findViewById(R.id.event_Dept);
        editTextFacCo = findViewById(R.id.event_FacCo);
        editTextStuco = findViewById(R.id.event_Stuco);

        findViewById(R.id.button6).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name = editTextname.getText().toString().trim();
        String desc = editTextdesc.getText().toString().trim();
        String tag = editTextTagline.getText().toString().trim();
        String dept = editTextdept.getText().toString().trim();
        String FacCo = editTextFacCo.getText().toString().trim();
        String Stuco = editTextStuco.getText().toString().trim();

        CollectionReference dbReg = db.collection("Reg_event");
        Register Reg = new Register(
                name,
                desc,
                tag,
                dept,
                FacCo,
                Stuco
        );
        dbReg.add(Reg)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(formactivity.this, "Product Added", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(formactivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        Intent i1 = new Intent(this, form2activity.class);
        startActivity(i1);
    }
    }


