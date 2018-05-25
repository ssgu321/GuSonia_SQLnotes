package com.example.gus1430.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
        editAddress = findViewById(R.id.editText_address);
        editNum = findViewById(R.id.editText_num);
        myDb = new DatabaseHelper(this);
        Log.d("myContactApp", "MainActivity: Instantiated myDb");
    }

    public void addData(View view){
        Log.d("myContactApp", "MainActivity: Add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editNum.getText().toString());

        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Success! Contact Inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Failed -- Contact Not Inserted", Toast.LENGTH_LONG).show();
        }

    }

    public void viewData(View view){
        Cursor res = myDb.getAllData();

        Log.d("myContactApp", "MainActivity: viewData: received cursor");

        if(res.getCount() == 0){
            showMessage("Error", "No data found in the database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            //append the res col 0, 1, 2, 3 -- see the StringBuffer and Cursor API
            //Delimit each of the "appends" with a line feed "\n"
            for(int i = 0; i < 4; i++){
                buffer.append(res.getString(i));
                buffer.append("\n");
            }
            buffer.append("\n");

        }

        Log.d("myContactApp", "MainActivity:StringBuffer Created");

        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        Log.d("myContactApp", "MainActivity: Assembling alert dialogue");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


}
