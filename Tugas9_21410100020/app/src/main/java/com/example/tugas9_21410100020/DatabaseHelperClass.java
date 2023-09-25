package com.example.tugas9_21410100020;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "contacts_database";
    //Database Table name
    private static final String TABLE_NAME = "contacts";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String noHP = "noHP";
    private SQLiteDatabase sqLiteDatabase;


    //membuat table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
        " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+noHP+" TEXT NOT NULL);";
    //konstruktor
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add contacts Data
    public void addcontacts(contactsModelClass contactsModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME, contactsModelClass.getName());
        contentValues.put(DatabaseHelperClass.noHP, contactsModelClass.getnoHP());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<contactsModelClass> getcontactsList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<contactsModelClass> storecontacts = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String noHP = cursor.getString(2);
                storecontacts.add(new contactsModelClass(id,name,noHP));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storecontacts;
    }

    public void updatecontacts(contactsModelClass contactsModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME,contactsModelClass.getName());
        contentValues.put(DatabaseHelperClass.noHP,contactsModelClass.getnoHP());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(contactsModelClass.getId())});
    }

    public void deletecontacts(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}
