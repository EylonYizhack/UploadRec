package com.chatter.android.uploadrec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;

public class Login extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "";
    Context context;
    private SignInButton googleBtn;
    private TextView info, info1;
    private LoginButton loginButton;
    protected CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private final int RC_SIGN_IN = 0;
    private String mFullName;
    private String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleBtn = (SignInButton) findViewById(R.id.sign_in_button);
//facebook login
        FacebookSdk.sdkInitialize(getApplicationContext());
        info = (TextView) findViewById(R.id.info_FB);
        info1 = (TextView) findViewById(R.id.info_FB1);
        setContentView(R.layout.activity_login);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
//Google login code

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ignGoogle();
        }
    }

    );

}

    public void ignGoogle() {
        Toast.makeText(Login.this, "GOOGLE SIGN IN", Toast.LENGTH_SHORT).show();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    ///////////////


    //////////////////////
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            //acct.getDisplayName().toString();
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    /////////////////////
    private void updateUI(boolean b) {
        if (b != true) {
            Toast.makeText(context, "failed to log in the account try again", Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent(context, MainActivity.class);
            startActivity(i);
        }
    }


    //Faccebook login code
    public void LoginFB(View v) {

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Toast.makeText(getApplicationContext(), loginResult.getAccessToken().getUserId() , Toast.LENGTH_LONG).show();
                info.setText(
                        "User ID: " + loginResult.getAccessToken().getUserId());

                info1.setText(
                        "Auth Token: " + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //this.callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        // Get account information
        //assert acct != null;
        //mFullName = acct.getDisplayName();
        // mEmail = acct.getEmail();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}