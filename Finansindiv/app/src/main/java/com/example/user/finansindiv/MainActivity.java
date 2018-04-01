package com.example.user.finansindiv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    DatabaseHelper myDB;
    Button dohit;
    EditText editText, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        dohit = (Button) findViewById(R.id.dohit);
        myDB = new DatabaseHelper(this);



        dohit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String newEntry1 = editText2.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry,newEntry1);
                    editText.setText("");
                    Intent intent = new Intent (MainActivity.this,ViewListContent.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "text", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
                public void AddData(String newEntry,String newEntry1){
                    boolean insertData= myDB.addData(newEntry, newEntry1);
                    if(insertData==true){
                        Toast.makeText(MainActivity.this,"OK", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this,"wrong", Toast.LENGTH_LONG).show();
                    }
    }
}
