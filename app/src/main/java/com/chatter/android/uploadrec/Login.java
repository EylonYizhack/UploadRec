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

public class Login extends Activity implements GoogleApiClient.OnConnectionFailedListener {
        private TextView info,info1;
        private LoginButton loginButton;
        protected CallbackManager callbackManager;
        Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=context;

        FacebookSdk.sdkInitialize(getApplicationContext());
        info = (TextView)findViewById(R.id.info_FB);
        info1 = (TextView)findViewById(R.id.info_FB1);
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
                        String userName = Profile.getCurrentProfile().getName().toString();
                        UsersMatconim umUser = new UsersMatconim(loginResult.getAccessToken().getUserId(), userName);
                        umUser.saveUser();
                        Intent i = new Intent(Login.this, HomePage.class);
                        startActivity(i);

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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}