 package batfai.samuentropy.brainboard7;

 import android.app.ProgressDialog;
 import android.content.Intent;
 import android.os.Bundle;
 import android.support.annotation.NonNull;
 import android.support.v7.app.AppCompatActivity;
 import android.text.TextUtils;
 import android.util.Log;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.google.android.gms.auth.api.Auth;
 import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
 import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
 import com.google.android.gms.auth.api.signin.GoogleSignInResult;
 import com.google.android.gms.common.ConnectionResult;
 import com.google.android.gms.common.SignInButton;
 import com.google.android.gms.common.api.GoogleApiClient;
 import com.google.android.gms.common.api.OptionalPendingResult;
 import com.google.android.gms.common.api.ResultCallback;

 public class LoginActivity extends AppCompatActivity implements
 GoogleApiClient.OnConnectionFailedListener, View.OnClickListener
 {
    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;
    private Button registerButton;

     private boolean signedIn = false;

     private static final String TAG = "LoginActivity";
     private static final int RC_SIGN_IN = 9001;

     private GoogleApiClient mGoogleApiClient;
     private TextView mStatusTextView;
     private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlogin);
        emailField=(EditText)(findViewById(R.id.emailText));
        passwordField=(EditText) findViewById((R.id.passwordText));

        loginButton = (Button)findViewById(R.id.loginButton);
        registerButton = (Button)findViewById(R.id.registerButton);

     /*   Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putBoolean("connectionCheck", signedIn);
        intent.putExtras(mBundle);
        startActivity(intent);
*/
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                signIn(emailField.getText().toString(), passwordField.getText().toString());
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                createAccount(emailField.getText().toString(), passwordField.getText().toString());
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        mStatusTextView = (TextView) findViewById(R.id.status);


    }

     @Override
     public void onStart()
     {
         super.onStart();

         OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
         if (opr.isDone())
         {
             // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
             // and the GoogleSignInResult will be available instantly.
             Log.d(TAG, "Got cached sign-in");
             GoogleSignInResult result = opr.get();
             handleSignInResult(result);
         }
         else
         {
             // If the user has not previously signed in on this device or the sign-in has expired,
             // this asynchronous branch will attempt to sign in the user silently.  Cross-device
             // single sign-on will occur in this branch.
             showProgressDialog();
             opr.setResultCallback(new ResultCallback<GoogleSignInResult>()
             {
                 @Override
                 public void onResult(@NonNull GoogleSignInResult googleSignInResult)
                 {
                     hideProgressDialog();
                     handleSignInResult(googleSignInResult);
                 }
             });
         }


     }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

     private void handleSignInResult(GoogleSignInResult result)
     {
         Log.d(TAG, "handleSignInResult:" + result.isSuccess());
         if (result.isSuccess())
         {
             signedIn = true;
             // Signed in successfully, show authenticated UI.
             GoogleSignInAccount acct = result.getSignInAccount();
             assert acct != null;
             mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
             updateUI(signedIn);

         }
         else
         {
             updateUI(signedIn);
         }
     }

    private void createAccount(String email, String password)
    {
        Log.d(TAG, "createAccount:" + email);
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();

        }

    }

    private void signIn(String email, String password)
    {
        Log.d(TAG, "signIn:" + email);

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
        }

        //if the email and password are not empty
        //displaying a progress dialog

    }

     private void signIn()
     {
         Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
         startActivityForResult(signInIntent, RC_SIGN_IN);
     }


     private void showProgressDialog()
     {
         if (mProgressDialog == null)
         {
             mProgressDialog = new ProgressDialog(this);
             mProgressDialog.setMessage(getString(R.string.loading));
             mProgressDialog.setIndeterminate(true);
         }

         mProgressDialog.show();
     }

     private void hideProgressDialog()
     {
         if (mProgressDialog != null && mProgressDialog.isShowing())
         {
             mProgressDialog.hide();
         }
     }

     private void updateUI(boolean signedIn)
     {
         if (signedIn)
         {
             findViewById(R.id.signedIn).setVisibility(View.VISIBLE);
             findViewById(R.id.signInAndSignUpButton).setVisibility(View.GONE);
             start();
         }
     }

     @Override
     public void onClick(View view)
     {
         switch (view.getId())
         {
             case R.id.sign_in_button:
             {
                 signIn();
                 break;
             }
         }
     }

     @Override
     public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
     {

     }

     public void start()
     {
         Intent intent = new Intent();
         intent.setClass(this, NeuronGameActivity.class);
         this.startActivity(intent);
     }
 }
