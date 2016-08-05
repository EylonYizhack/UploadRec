package com.chatter.android.uploadrec;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chatter.android.uploadrec.HomePage;
import com.chatter.android.uploadrec.R;
import com.chatter.android.uploadrec.UsersMatconim;
import com.chatter.android.uploadrec.utilClasses.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login extends Activity {
    private LoginButton loginButton;
    protected CallbackManager callbackManager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;

        FacebookSdk.sdkInitialize(getApplicationContext());
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
            public void onSuccess(final LoginResult loginResult)
            {
             /*
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/{user-id}/permissions",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
            // handle the result
                            }
                        }
                ).executeAsync(); */


                new AsyncTask<Void,Void,Void>()
                {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();

                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        Intent i = new Intent(Login.this, HomePage.class);
                        startActivity(i);
                    }
                    @Override
                    protected Void doInBackground(Void... params)
                    {

                            UsersMatconim umUser = new UsersMatconim(loginResult.getAccessToken().getUserId(),"need th user name","userImg",0);
                            umUser.saveUser();


                        return null;
                    }

                }.execute();


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