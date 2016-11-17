package batfai.samuentropy.brainboard7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by transfet on 2016.11.17..
 */

public class SignupActivity extends AppCompatActivity
{

    private EditText rEmailField;
    private EditText rPasswordField;
    private EditText nickNameField;
    private EditText firstNameField;
    private EditText lastNameField;

    private Button signUpButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuplayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.signup_toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        signUpButton = (Button) findViewById(R.id.signUpButton);


        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                nickNameField = (EditText) findViewById(R.id.nickName);
                rEmailField = (EditText) findViewById(R.id.email);
                rPasswordField = (EditText) findViewById(R.id.password);
                firstNameField = (EditText) findViewById(R.id.firstName);
                lastNameField = (EditText) findViewById(R.id.lastName);

                signUpButton = (Button) findViewById(R.id.signUpButton);

                signUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        createAccount(rEmailField.getText().toString(),
                                rPasswordField.getText().toString(),
                                nickNameField.getText().toString(),
                                firstNameField.getText().toString(),
                                lastNameField.getText().toString()
                        );
                    }
                });
            }
        });
    }

    private void createAccount(String email, String password,String nickname, String firstname, String lastname)
    {

        if (TextUtils.isEmpty(nickname))
        {
            Toast.makeText(this, "Please choose a nickname", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(firstname))
        {
            Toast.makeText(this, "Please enter firstname", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(lastname))
        {
            Toast.makeText(this, "Please enter lastname", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            /*
            * REGISZTRÁCIÓ
            * DB-BE FELTÖLTENI AZ ARGUMENTUMOKAT
            * */


            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }




}
