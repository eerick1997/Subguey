package com.example.erick.adooproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import Objects.Event;
import Objects.User;
import Preferences.SubgueyPreferences;
import UIElements.CustomNickAlert;
import UIElements.EventInfo;
import UIElements.EventsReports;

import static Preferences.Utilities.EMAIL;
import static Preferences.Utilities.IMG_PROFILE;
import static Preferences.Utilities.NAME_USER;
import static Preferences.Utilities.SIGNED;

public class Login extends AppCompatActivity implements OnConnectionFailedListener {

    private static final String TAG = "Login.java";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient googleSignInClient;
    //This is our own object, here we store information about this user
    private User user;
    //We gonna use preferences so we make an instance of this object
    SubgueyPreferences preferences = new SubgueyPreferences(Login.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Event event = new Event(1, "Erick", "12:00", new LatLng(-1.232423, 1.421311));
        new EventInfo(Login.this).showDialog(event);
        new EventsReports(Login.this).showDialog(event);
        //new EventsReports(Login.this).showDialog(new Event(0, "eerick1997", "12:43:59", new LatLng(-1.42324,1.13421213)));
        /*We create a GoogleSignInOptions to configure Google Sign-in to request users ID
        and get basic information using the DEFAULT_SIGN_IN parameter*/
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder
                (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();

        /*We create a GoogleSignInClient to set different options already
         * specified in the googleSignInOptions object*/
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        /*Making an instance of our button to sign in an user*/
        SignInButton signInButton = findViewById(R.id.btn_sign_in_button);
        //Setting size
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        /*Setting on click listener event*/
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Here we set the action when SignInButton is pressed*/
                Snackbar.make(v, "Sign-in button pressed", Snackbar.LENGTH_LONG).show();
                signIn();
            }
        });

        //DON'T ERASE THIS
        /*We create a Button to revoke access*/
        Button button = findViewById(R.id.BTN_revoke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Revoke access button pressed", Snackbar.LENGTH_LONG).show();
                revokeAccess();
            }
        });
        changeActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
        try {
            /*Here we check if an user is already signed in our app.
             * if someone is already signed the GoogleSignInAccount will be non-null*/
            GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
            if (googleSignInAccount != null) {
                Log.i(TAG, "onStart: An user has been found " + googleSignInAccount.getDisplayName());
                //getAccountData(googleSignInAccount);
            } else {
                Log.i(TAG, "onStart: I don't found a user");
                preferences.cleanPreferences();
            }
        } catch (Exception e) {
            Log.e(TAG, "onStart: ", e);
        }
    }

    /**
     * signIn method
     * Description: this method start a new Intent with startActivityForResult
     * Parameters: none
     * Returns: Anything
     **/
    private void signIn() {
        //Creating an Intent object
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * handleSignInResult method
     * Description: this method makes changes if the user signs-in successfully
     * Parameters: Task<GoogleSignInAccount>
     * Returns: anything
     **/
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            /*This object (GoogleSignInAccount) contains information about the
             * signed user*/
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            /**Signed in successfully we can update the UI**/
            if (account != null) {
                try {
                    String user_name = account.getDisplayName();
                    String user_given_name = account.getGivenName();
                    String user_family_name = account.getFamilyName();
                    String user_email = account.getEmail();
                    String user_id = account.getId();
                    Uri user_photo = account.getPhotoUrl();
                    String str_photo = "empty";
                    try {
                        str_photo = user_photo.toString();
                    } catch (Exception e) {
                        str_photo = "empty";
                    }
                    Log.i("VALOR: ", "" + user_photo);

                    //User datas
                    user = new User(user_name, user_email, user_given_name,
                            0.0f, 0.0f, str_photo,
                            1);

                    //We register to this user
                    CustomNickAlert nick_alert= new CustomNickAlert(Login.this);
                    nick_alert.showDialog(user);


                    Log.i(TAG, "handleSignInResult: " + user_name + " " + user_given_name + " "
                            + user_family_name + " " + user_email + " " + user_id + " " + user_photo);
                    //Getting account data and storing in preferences

                    //getAccountData(account);
                } catch (Exception e) {
                    Log.e(TAG, "handleSignInResult: ", e);
                }
            } else {
                Log.e(TAG, "handleSignInResult: we can't get the account");
            }
        } catch (ApiException e) {
            Log.e(TAG, "handleSignInResult: ", e);

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);*/
        if (requestCode == RC_SIGN_IN) {
            /*The task returned from this call is always completed, no need to attach a listener*/
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void revokeAccess() {
        googleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Access revoked", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onComplete: Cleaning preferences...");
                        preferences.cleanPreferences();
                    }
                });
    }

    @SuppressLint("ResourceType")
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), getString(R.id.no_connection), Toast.LENGTH_LONG).show();
    }

    /**
     * changeActivity method
     * Description: Verify if the user has already signed
     * Returns: Nothing
     **/
    private void changeActivity() {

        try {
            if (preferences.getIsSigned()) {
                Intent intent = new Intent(this, Main.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.e(TAG, "changeActivity: ", e);
            preferences.cleanPreferences();
        }
    }



    private void getAccountData(GoogleSignInAccount account) throws Exception {

        String user_name = account.getDisplayName();
        String user_given_name = account.getGivenName();
        String user_family_name = account.getFamilyName();
        String user_email = account.getEmail();
        String user_id = account.getId();
        Uri user_photo = account.getPhotoUrl();
        String str_photo = "empty";
        try {
            str_photo = user_photo.toString();
        } catch (Exception e) {
            str_photo = "empty";
        }

        //We save the information of the user in our preferences
        //To save the last state
        preferences.savePreference(SIGNED, true);
        //To save the name of this user
        preferences.savePreference(NAME_USER, user_name + " " + user_given_name);
        //To save the email
        preferences.savePreference(EMAIL, user_email);
        //To save img_profile
        preferences.savePreference(IMG_PROFILE, str_photo);
        Log.i(TAG, "handleSignInResult: " + user_name + " " + user_given_name + " "
                + user_family_name + " " + user_email + " " + user_id + " " + str_photo);
        //Now we go to the next activity
        changeActivity();
    }
}
