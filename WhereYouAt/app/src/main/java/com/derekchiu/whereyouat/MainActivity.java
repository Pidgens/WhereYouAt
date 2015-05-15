package com.derekchiu.whereyouat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Fragment;
import android.widget.Toast;

/**
 * Created by Derek Chiu on 3/22/2015.
 */
public class MainActivity extends Activity {

    private Context ctx;
    private String username, userpass;
    private TextView userName;
    private Button loginButton;
    private Button signupButton;
    private Intent i;
    private EditText enterUser, enterPass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterPass = (EditText) findViewById(R.id.enterPass);
        enterUser = (EditText) findViewById(R.id.enterUser);
        loginButton = (Button) findViewById(R.id.LoginButton);
        signupButton = (Button) findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                username = enterUser.getText().toString();
                userpass = enterPass.getText().toString();
                DatabaseOperations dop = new DatabaseOperations(ctx);
                Cursor CR = dop.getInformation(dop);
                CR.moveToFirst();
                boolean login_status = false;
                String name = "";
                System.out.println(CR.getString(0));
                do {
                    if(username.equals(CR.getString(0)) && (userpass.equals(CR.getString(1)))) {
                        login_status = true;
                        name = CR.getString(0);
                    }
                } while(CR.moveToNext());
                if(login_status) {
                    Toast.makeText(getBaseContext(), "Login Successful----\n Welcome "+name, Toast.LENGTH_LONG).show();
                    //finish();
                    i = new Intent(MainActivity.this, StartScreen.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
        });
    }
}