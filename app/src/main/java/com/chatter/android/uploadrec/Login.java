package com.chatter.android.uploadrec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginFragment;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

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
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
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
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            public String Name;

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                try {
                                     Name = object.getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(Login.this, "Name : " + Name, Toast.LENGTH_LONG).show();
                                //___________________________________
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

                                        UsersMatconim umUser = new UsersMatconim(loginResult.getAccessToken().getUserId(),Name,"userImg",0);
                                        umUser.saveUser();


                                        return null;
                                    }

                                }.execute();
                                //___________________________________
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel()
            {
                Toast.makeText(context,"canceled",Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().logOut();
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