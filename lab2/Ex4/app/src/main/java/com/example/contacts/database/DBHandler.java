package com.example.contacts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.contacts.data.saveData;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context) {
        super(context, DBParameters.DB_NAME, null, DBParameters.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable="CREATE TABLE " + DBParameters.DB_TABLE + "(" + DBParameters.KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + DBParameters.KEY_NAME + " VARCHAR," + DBParameters.KEY_NUMBER +
            " VARCHAR, " + DBParameters.KEY_DESCRIPTION +" VARCHAR)";

        String createTable1="CREATE TABLE " + DBParameters.DB_TABLE1 + "(" + DBParameters.KEY_ID +
                " INTEGER," + DBParameters.KEY_NAME + " VARCHAR," + DBParameters.KEY_NUMBER +
                " VARCHAR, " + DBParameters.KEY_DESCRIPTION +" VARCHAR)";
        sqLiteDatabase.execSQL(createTable);
        sqLiteDatabase.execSQL(createTable1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertContact(saveData save){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBParameters.KEY_NAME,save.getName());
        contentValues.put(DBParameters.KEY_NUMBER,save.getNumber());
        contentValues.put(DBParameters.KEY_DESCRIPTION,save.getDescription());
        sqLiteDatabase.insert(DBParameters.DB_TABLE,null,contentValues);
        sqLiteDatabase.close();

    }
    public void moveToTrash(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="INSERT INTO " + DBParameters.DB_TABLE1 + " SELECT * FROM " + DBParameters.DB_TABLE + " WHERE " + DBParameters.KEY_ID + "= "+id;
        db.execSQL(query);
        db.delete(DBParameters.DB_TABLE, DBParameters.KEY_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void moveFromTrash(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="INSERT INTO " + DBParameters.DB_TABLE + " SELECT * FROM " + DBParameters.DB_TABLE1 + " WHERE " + DBParameters.KEY_ID + "= "+id;
        db.execSQL(query);
        db.delete(DBParameters.DB_TABLE1, DBParameters.KEY_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public List<saveData> readTrash(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String select="SELECT * FROM "+ DBParameters.DB_TABLE1;
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        List<saveData> list=new ArrayList<>();
        while (cursor.moveToNext()){
            saveData save=new saveData();
            save.setId(cursor.getString(0));
            save.setName(cursor.getString(1));
            save.setNumber(cursor.getString(2));
            save.setDescription(cursor.getString(3));
            list.add(save);
        }
        return list;
    }
    public List<saveData> readContact(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String select="SELECT * FROM "+ DBParameters.DB_TABLE;
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        List<saveData> list=new ArrayList<>();
        while (cursor.moveToNext()){
            saveData save=new saveData();
            save.setId(cursor.getString(0));
            save.setName(cursor.getString(1));
            save.setNumber(cursor.getString(2));
            save.setDescription(cursor.getString(3));
            list.add(save);
        }
        return list;
    }
    public void update(String id,String name, String number, String description) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(DBParameters.KEY_NAME, name);
        values.put(DBParameters.KEY_NUMBER, number);
        values.put(DBParameters.KEY_DESCRIPTION, description);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with id of our number which is stored in original id variable.
        db.update(DBParameters.DB_TABLE, values, "id=?", new String[]{id});
        db.close();
    }
    public void deleteALLContact(){

    }
    public void deleteSpecificContactTrash(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBParameters.DB_TABLE1, DBParameters.KEY_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
