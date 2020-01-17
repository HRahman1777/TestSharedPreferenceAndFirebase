package com.example.testsharedpreferenceandfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    EditText editText1, editText2;
    Button buttonUpload, buttonShow, buttonRetrive;
    TextView textViewDetails;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText1 = findViewById(R.id.etTx1);
        editText2 = findViewById(R.id.etTx2);
        textViewDetails = findViewById(R.id.detailsTV);
        buttonUpload = findViewById(R.id.uploadBtn);
        buttonShow = findViewById(R.id.showBtn);
        buttonRetrive = findViewById(R.id.retriveBtn);

        //databaseReference = FirebaseDatabase.getInstance().getReference("ck");





        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveData();

                String satt = editText1.getText().toString().trim();
                String sunn = editText2.getText().toString().trim();

                UploadClass uploadClass = new UploadClass(satt,sunn);

                databaseReference = FirebaseDatabase.getInstance().getReference().child("ck").child("routine");

                databaseReference.setValue(uploadClass);
                Toast.makeText(Main2Activity.this, "DONE!!",Toast.LENGTH_LONG).show();

            }
        });


        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("ck").child("routine");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String satu = dataSnapshot.child("satt").getValue().toString();
                        String sund = dataSnapshot.child("sunn").getValue().toString();

                        SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("on",satu);
                        editor.putString("tw", sund);
                        editor.commit();

                        textViewDetails.setText(satu+"\n"+sund);
                        Toast.makeText(Main2Activity.this, "DONE!!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        buttonRetrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                String one = sharedPreferences.getString("on","dataNotFound");
                String two = sharedPreferences.getString("tw","dataNotFound");

                textViewDetails.setText(one+"\n"+two);
                Toast.makeText(Main2Activity.this, "DONE!!",Toast.LENGTH_LONG).show();
            }
        });

    }

    /*private void saveData() {
        String satt = editText1.getText().toString().trim();
        String sunn = editText2.getText().toString().trim();

        String key = databaseReference.push().getKey();

        UploadClass uploadClass = new UploadClass(satt,sunn);

        databaseReference.child(key).setValue(uploadClass);

    }

     */
}
