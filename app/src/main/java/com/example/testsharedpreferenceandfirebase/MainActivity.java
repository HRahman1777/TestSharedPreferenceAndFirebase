package com.example.testsharedpreferenceandfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnLoad, next;
    TextView textView8, textView10, textView12;
    EditText editText8, editText10, editText12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btnSave =  findViewById(R.id.saveBtn);
       btnLoad = findViewById(R.id.loadBtn);
       textView8 = findViewById(R.id.TxtVw8);
       textView10 = findViewById(R.id.TxtVw10);
       textView12 = findViewById(R.id.TxtVw12);
       editText8 = findViewById(R.id.editTxt8);
       editText10 = findViewById(R.id.editTxt10);
       editText12 = findViewById(R.id.editTxt12);

       next = findViewById(R.id.intentBtn);

       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String one = editText8.getText().toString();
               String two = editText10.getText().toString();
               String three = editText12.getText().toString();

               SharedPreferences sharedPreferences = getSharedPreferences("routine", Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPreferences.edit();

               editor.putString("on",one);
               editor.putString("tw", two);
               editor.putString("th", three);
               editor.commit();
           }
       });

       btnLoad.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               SharedPreferences sharedPreferences = getSharedPreferences("routine", Context.MODE_PRIVATE);
               String one = sharedPreferences.getString("on","dataNotFound");
               String two = sharedPreferences.getString("tw","dataNotFound");
               String three = sharedPreferences.getString("th","dataNotFound");

               textView8.setText(one);
               textView10.setText(two);
               textView12.setText(three);

           }
       });

       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent((MainActivity.this) , (Main2Activity.class));
               startActivity(intent);
           }
       });

    }
}
