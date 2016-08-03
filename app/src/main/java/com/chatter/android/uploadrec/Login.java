package com.chatter.android.uploadrec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class Login extends Activity {
        private LoginButton loginButton;
        protected CallbackManager callbackManager;
        Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=context;

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        //updateWithToken(AccessToken.getCurrentAccessToken());

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
                        Intent i = new Intent(Login.this, HomePage.class);
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
            Intent i = new Intent(Login.this, HomePage.class);
            startActivity(i);
        }
}