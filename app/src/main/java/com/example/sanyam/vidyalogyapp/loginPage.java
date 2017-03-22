package com.example.sanyam.vidyalogyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanyam.vidyalogyapp.Database.LoginDataBaseAdapter;

public class loginPage extends AppCompatActivity {

    EditText usernameTxt, pwdTxt;
    Button loginBtn;

    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        loginBtn = (Button) findViewById(R.id.login);
        usernameTxt = (EditText) findViewById(R.id.mobNumber);
        pwdTxt = (EditText) findViewById(R.id.passwrd);

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
                    Intent quickRevision = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(quickRevision);

                        //dialog.dismiss();

                }
                else
                {
                    Toast.makeText(loginPage.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}
