package com.example.loginsignandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper databaseHelper;
    private Button signUpNow, loginButton;
    private EditText userNameEditText,passwordEditText;
    HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        // database;
        databaseHelper = new DatabaseHelper(this,1);
        SQLiteDatabase liteDatabase = databaseHelper.getWritableDatabase();

        //findId
        userNameEditText = findViewById(R.id.userNameEditTextId);
        passwordEditText = findViewById(R.id.passwordEditTextId);
        loginButton = findViewById(R.id.loginButtonId);
        signUpNow = findViewById(R.id.signUpNowButtonId);

        writeDataIntoHasMap();

        // add Listener
        loginButton.setOnClickListener(this);
        signUpNow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        final Boolean b = (!userName.equals("") && !password.equals(""));
        // signUp now
        if(v.getId() == R.id.signUpNowButtonId){
            Intent intent = new Intent(this,SignUpActivity.class);
            startActivity(intent);
        }
        // login
        if(v.getId() == R.id.loginButtonId){
            if(b){
                if(hashMap.containsKey(userName)){
                    if(password.equals(hashMap.get(userName))){
                        Log.d("Login: ","Success!");
                    }
                }
            }

        }
    }
    private void writeDataIntoHasMap(){
        Cursor cursor = databaseHelper.findAllData();
        hashMap = new HashMap<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                String username = cursor.getString(3);
                String password = cursor.getString(4);
                hashMap.put(username,password);
            }

        }
    }
}