package com.example.user.finansindiv;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 29.03.2018.
 */

public class ViewListConten  extends AppCompatActivity {
    private  static final String TAG = "ViewListConten";
    DatabeseHelp myDB;
    private ListView listView2;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vitrat);
        ListView listView2 = (ListView) findViewById(R.id.listView2);
        myDB = new DatabeseHelp(this);
        poplistView();
    }
    private void poplistView(){
        Cursor data = myDB.getData();
        ArrayList<String> theList = new ArrayList<>();

        while(data.moveToNext()){
            theList.add(data.getString(1));
        }
        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
        listView2.setAdapter(listAdapter);


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Cursor data = myDB.getItemID(name);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID> -1) {
                    Log.d(TAG, "onItemClick is"+itemID);
                    Intent editScreerIntent = new Intent(ViewListConten.this, EditDataActivity.class);
                    editScreerIntent.putExtra("id",itemID);
                    editScreerIntent.putExtra("name", name);
                    startActivity(editScreerIntent);
                } else{
                    Toast.makeText(ViewListConten.this, "База даних пуста", Toast.LENGTH_LONG).show();}

            }
        });
}}
