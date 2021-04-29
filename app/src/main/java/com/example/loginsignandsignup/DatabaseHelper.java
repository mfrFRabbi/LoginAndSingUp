package com.example.loginsignandsignup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userDetails.db";
    private static final String TABLE_NAME = "user_details";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private Context context;
    // query generate
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(250) NOT NULL, "
            +EMAIL+" TEXT NOT NULL, "+USERNAME+" TEXT NOT NULL, "+PASSWORD+" TEXT NOT NULL);";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public DatabaseHelper(@Nullable Context context,@Nullable int version) {
        super(context, DATABASE_NAME, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"onCreate is called",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"Exception "+e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(UserDetails userDetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,userDetails.getName());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(USERNAME,userDetails.getUsername());
        contentValues.put(PASSWORD,userDetails.getPassword());
        long rawId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rawId;
    }
    public Cursor findAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
}
