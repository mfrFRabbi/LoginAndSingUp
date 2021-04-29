package com.example.loginsignandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signUpButton;
    private EditText nameEditText,emailEditText, userNameEditText, passEditText;

    private UserDetails userDetails;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        databaseHelper = new DatabaseHelper(this,1);

        nameEditText = findViewById(R.id.nameEditTextId);
        emailEditText = findViewById(R.id.emailEditTextId);
        userNameEditText = findViewById(R.id.userNameSingUpEditTextId);
        passEditText = findViewById(R.id.passwordSingUpEditTextId);
        signUpButton = findViewById(R.id.signUpButtonId);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username = userNameEditText.getText().toString();
        String password = passEditText.getText().toString();

        final Boolean b = (!name.equals("") && !email.equals("") && !username.equals("") && !password.equals(""));


        if(v.getId() == R.id.signUpButtonId){
            if(b){
                userDetails = new UserDetails(name,email,username,password);
                long rawId = databaseHelper.insertData(userDetails);
                if(rawId>0){
                    Toast.makeText(this,"Successfully inserted",Toast.LENGTH_LONG).show();
                    clear();
                }else {
                    Toast.makeText(this,"unsuccessful",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this,"Please put data",Toast.LENGTH_LONG).show();
            }
        }
    }
    private void clear(){
       nameEditText.setText("");
       emailEditText.setText("");
       userNameEditText.setText("");
       passEditText.setText("");
    }
}