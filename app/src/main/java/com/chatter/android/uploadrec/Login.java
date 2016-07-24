package com.chatter.android.uploadrec;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Login extends Activity
    {
        private TextView info,info1;
        private LoginButton loginButton;
        protected CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//facebook login
        FacebookSdk.sdkInitialize(getApplicationContext());
        info = (TextView)findViewById(R.id.info_FB);
        info1 = (TextView)findViewById(R.id.info_FB1);
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
//Google login code

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

//Faccebook login code
        public void LoginFB(View v)
        {

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult)
                {
                   // Toast.makeText(getApplicationContext(), loginResult.getAccessToken().getUserId() , Toast.LENGTH_LONG).show();
                  info.setText(
                          "User ID: "   + loginResult.getAccessToken().getUserId());

                  info1.setText(
                               "Auth Token: " + loginResult.getAccessToken().getToken());
                }

                @Override
                public void onCancel()
                {
                    info.setText("Login attempt canceled.");
                }

                @Override
                public void onError(FacebookException e)
                {
                    info.setText("Login attempt failed.");
                }
            });
        }
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            this.callbackManager.onActivityResult(requestCode, resultCode, data);
        }


        //google code
        //hello
}