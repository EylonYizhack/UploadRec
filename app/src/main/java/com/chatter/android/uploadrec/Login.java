package com.chatter.android.uploadrec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Login extends Activity
    {
        private TextView info,info1;
        private LoginButton loginButton;
        protected CallbackManager callbackManager;
        Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=context;

//facebook login
        FacebookSdk.sdkInitialize(getApplicationContext());
        info = (TextView)findViewById(R.id.info_FB);
        info1 = (TextView)findViewById(R.id.info_FB1);
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginFB();
            }
        });

//Google login code

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

//Faccebook login code
        private void mLoginFB()
        {

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult)
                {
                    String userName = Profile.getCurrentProfile().getFirstName().toString();
                    UsersMatconim umUser = new UsersMatconim(loginResult.getAccessToken().getUserId(),userName);
                    umUser.saveUser();
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                    //Intent iht = new Intent(Login.this,MainActivity.class);
                    //startActivity(new Intent(context,MainActivity.class));
                    Toast.makeText(Login.this, "Result:"+loginResult, Toast.LENGTH_LONG).show();
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