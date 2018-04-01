package com.example.user.finansindiv;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by user on 29.03.2018.
 */

public class DatabeseHelp extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "myli_dat";
    public static final String COL1="ID";
    public static final String COL2="ITEM1";
    public static final String COL3="ITEM2";

    public DatabeseHelp(Context context) {
        super(context,DATABASE_NAME,null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE"+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ "ITEM1 TEXT," + "ITEM2 TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
    }
    public boolean addData(String item1, String item2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        contentValues.put(COL3, item2);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getItemID(String name){
        SQLiteDatabase db= this.getWritableDatabase();
        String query = "SELECT"+COL1+"FROM"+ TABLE_NAME+"WHERE"+COL2+"='"+name+"'"+COL3+"='"+name+"'";
        Cursor data =  db.rawQuery(query,null);
        return data;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT"+ COL1+"FROM"+TABLE_NAME;
        Cursor data= db.rawQuery(query,null);
        return  data;
    }
    public void UpdataName(String newName, int id, String oldName,String newName2,String oldName2){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE"+TABLE_NAME+"SET"+ COL2+"='"+newName+"'"+COL3+"'"+newName2+"' WHERE"+COL1+"='"+id+"'"+"AND"+COL2+"='"+oldName+"'"+COL3+"='"+oldName2+"'";
        Log.d(TAG,"updata query"+query);
        Log.d(TAG,"updata newName"+newName);
        db.execSQL(query);
    }
    public void deleteName( int id, String name, String name2){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM"+TABLE_NAME+"' WHERE"+COL1+"='"+id+"'"+"AND"+COL2+"='"+name+"'"+COL3+"='"+name2+"'";
        Log.d(TAG,"delete query"+query);
        Log.d(TAG,"delete name"+name+"from database");
        db.execSQL(query);
    }
}
