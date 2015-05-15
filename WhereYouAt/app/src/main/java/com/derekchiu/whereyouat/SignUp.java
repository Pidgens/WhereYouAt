package com.derekchiu.whereyouat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Derek Chiu on 3/23/2015.
 */
public class SignUp extends MainActivity {

    EditText USER_NAME, USER_PASS, USER_EMAIL, USER_CONFIRM;
    String user_name, user_pass, user_email, user_confirm;
    Button Join;
    Context ctx = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        USER_NAME = (EditText) findViewById(R.id.enterUserName);
        USER_PASS = (EditText) findViewById(R.id.enterPassword);
        USER_EMAIL = (EditText) findViewById(R.id.enterEmail);
        USER_CONFIRM = (EditText) findViewById(R.id.enterConfirm);

        Join = (Button) findViewById(R.id.toJoinButton);
        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = USER_NAME.getText().toString();
                user_pass = USER_PASS.getText().toString();
                user_confirm = USER_CONFIRM.getText().toString();
                user_email = USER_EMAIL.getText().toString();

                if(!(user_pass.equals(user_confirm))) {
                    Toast.makeText(getBaseContext(), "Passwords are not matching", Toast.LENGTH_LONG).show();
                    USER_NAME.setText("");
                    USER_PASS.setText("");
                    USER_CONFIRM.setText("");
                } else {
                    DatabaseOperations DB = new DatabaseOperations(ctx);
                    DB.putInformation(DB, user_name, user_pass);
                    Toast.makeText(getBaseContext(), "Registration successful", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

}