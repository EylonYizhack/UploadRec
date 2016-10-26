package com.chatter.android.uploadrec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login extends Activity {
        private LoginButton loginButton;
        protected CallbackManager callbackManager;
        Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=context;

        FacebookSdk.sdkInitialize(getApplicationContext());
       // AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        updateWithToken(AccessToken.getCurrentAccessToken());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginFB();
            }
        });

    }

        private void updateWithToken(AccessToken currentAccessToken) {
            if (currentAccessToken != null) {
                Intent i = new Intent(Login.this, HomePage.class);
                startActivity(i);
            }
        }

        private void mLoginFB()
        {

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult)
                {
                    AccessToken accessToken = loginResult.getAccessToken();
                    Profile profile = Profile.getCurrentProfile();

                   /* if(fireBase contains this userId)
                        { Intent i = new Intent(Login.this, HomePage.class);
                            startActivity(i);} */

                    if (profile == null) {
                        Toast.makeText(Login.this, "profile is null", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        UsersMatconim umUser = new UsersMatconim(loginResult.getAccessToken().getUserId(),profile.getName(),"userImg",0);
                        umUser.saveUser();
                        Intent i = new Intent(Login.this, HomePage_old.class);
                        startActivity(i);
                    }

                }

                @Override
                public void onCancel()
                {
                   Toast.makeText(context,"canceled",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException e)
                {
                    Toast.makeText(context,"Unable to connect the facebook",Toast.LENGTH_SHORT).show();
                }
            });
        }
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            this.callbackManager.onActivityResult(requestCode, resultCode, data);

        }

        public void continueAsGuest(View view)
        {
            Intent i = new Intent(Login.this, HomePage_old.class);
            startActivity(i);
        }
}