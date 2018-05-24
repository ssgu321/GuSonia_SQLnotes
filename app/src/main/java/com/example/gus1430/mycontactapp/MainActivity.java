package com.example.gus1430.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        myDb = new DatabaseHelper(this);
        Log.d("myContactApp", "MainActivity: Instantiated myDb");
    }

    public void addData(View view){
        Log.d("myContactApp", "MainActivity: Add contact button pressed");

        boolean isInsertedName = myDb.insertData(editName.getText().toString(), null, null);
        boolean isInsertedAddress = myDb.insertData(editName.getText().toString(), null, null);
        boolean isInsertedNum = myDb.insertData(editName.getText().toString(), null, null);

        if(isInsertedName == true){
            Toast.makeText(MainActivity.this, "Success! Contact Inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Failed -- Contact Inserted", Toast.LENGTH_LONG).show();
        }

    }
}
