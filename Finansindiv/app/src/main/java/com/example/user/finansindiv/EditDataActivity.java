package com.example.user.finansindiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 27.03.2018.
 */

public class EditDataActivity extends AppCompatActivity {
    private  static final String TAG = "EditDataActivity";
    DatabaseHelper myDB;
    Button delete;
    EditText editText, editText2;
    private String selectedName;
    private String selectedName2;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletedata);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        delete = (Button) findViewById(R.id.delete);
        myDB = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");
        selectedName2 = receivedIntent.getStringExtra("name2");
        editText.setText(selectedName);
        editText2.setText(selectedName2);
       /* delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editText.getText().toString();
                if(!item.equals("")) {
myDB.UpdataName(item,selectedID,selectedName);
                }else{
                }
            }
        });*/
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteName(selectedID,selectedName,selectedName2);
                editText.setText("");
                toastMessage("deleted from data");
            }});
        }

    private void toastMessage(String s) {
    }

}

