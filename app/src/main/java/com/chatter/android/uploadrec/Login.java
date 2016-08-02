package com.chatter.android.uploadrec;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.Arrays;

public class Login extends Activity
    {
        private TextView info,info1;
        private LoginButton loginButton;
        protected CallbackManager callbackManager;
        Context context;

        //facebook from the internet
        private static final String TAG = "SignInActivity";
        private static final int RC_SIGN_IN = 9001;

        private GoogleApiClient mGoogleApiClient;
        private TextView mStatusTextView;
        private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;


//facebook login
        FacebookSdk.sdkInitialize(getApplicationContext());
        info = (TextView) findViewById(R.id.info_FB);
        info1 = (TextView) findViewById(R.id.info_FB1);
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        callbackManager = CallbackManager.Factory.create();


//Google login code

        mStatusTextView = (TextView) findViewById(R.id.status);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                    Intent i = new Intent(Login.this ,MainActivity.class);
                    startActivity(i);
                        String myResult =loginResult.getAccessToken().getUserId().toString();
                        Toast.makeText(Login.this, myResult, Toast.LENGTH_SHORT).show();
                        //  info.setText(myResult);

                        // Toast.makeText(getApplicationContext(), loginResult.getAccessToken().getUserId() , Toast.LENGTH_LONG).show();
//                  info.setText(
//                          "User ID: "   + loginResult.getAccessToken().getUserId().toString());
//
//                  info1.setText(
//                               "Auth Token: " + loginResult.getAccessToken().getToken().toString());
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
        });

    }




 public void singInGoogle(View v)
 {

 }

        public void LoginFB(View v)
        {

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult)
                {
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
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
            Intent i = new Intent(Login.this,MainActivity.class);
            startActivity(i);
        }


        //google code







    }



