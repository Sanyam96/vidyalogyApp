package com.example.sanyam.vidyalogyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanyam.vidyalogyapp.Database.LoginDataBaseAdapter;
import com.example.sanyam.vidyalogyapp.Session.SessionManagement;

public class loginPage extends AppCompatActivity {

    EditText usernameTxt, pwdTxt;
    Button loginBtn;

    LoginDataBaseAdapter loginDataBaseAdapter;
    // Session Manager Class
    SessionManagement session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Session Manager
        session = new SessionManagement(getApplicationContext());

        loginBtn = (Button) findViewById(R.id.login);
        usernameTxt = (EditText) findViewById(R.id.mobNumber);
        pwdTxt = (EditText) findViewById(R.id.passwrd);

        //session.checkLogin();

//        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
//        if(session.isLoggedIn()){
//            Intent quickRevision = new Intent(getApplicationContext(),MainActivity.class);
//            startActivity(quickRevision);
//            finish();
//        }


        final String usernameString = usernameTxt.getText().toString();
        final String passwordString = pwdTxt.getText().toString();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fetch the Password form database for respective user name
//                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if("1234567890".equals(usernameTxt.getText().toString()) && "123456".equals(pwdTxt.getText().toString()) ) {
                    Toast.makeText(loginPage.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();

                    // Creating user login session
                    // For testing i am stroing name, email as follow
                    // Use user real data
                    session.createLoginSession(usernameTxt.getText().toString(), pwdTxt.getText().toString());

                    Intent quickRevision = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(quickRevision);
                    finish();

                        //dialog.dismiss();

                }
                else
                {
                    Toast.makeText(loginPage.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    int k = 0;

    boolean doubleBackToExitPressedOnce = false;
//
//    public void onBackPressed()
//    {
//        Log.e("My Tags", "onBackPressed");
//        k++;
//        if(k == 1)
//        {
//            Toast.makeText(loginPage.this, "Please press again to exit activity.", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            finish();
//
//
//        }
//    }

    @Override
    public void onBackPressed() {
        if( k >= 2 ){
            finish();
            System.exit(0);
        }
        if (doubleBackToExitPressedOnce) {
            k = 1;
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                k++;
                doubleBackToExitPressedOnce=false;
                k = 2;
                finish();
                System.exit(0);


            }
        }, 2000);
//        finish();
//        System.exit(0);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();

    }
}


