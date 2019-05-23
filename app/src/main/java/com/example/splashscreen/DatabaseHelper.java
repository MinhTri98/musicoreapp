package com.example.splashscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(name text primary key, email text, pass text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String name, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("pass",pass);
        long ins = db.insert("user",null,contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public boolean chekname_email(String name, String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where name=? and email=?",new String[]{name, email});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public boolean chekname_pass(String name, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where name=? and pass=?",new String[]{name, pass});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
