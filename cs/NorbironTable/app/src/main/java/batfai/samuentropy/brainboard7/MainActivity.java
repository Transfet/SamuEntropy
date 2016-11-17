package batfai.samuentropy.brainboard7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String userID = "";
    private boolean signedIN = false;
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        setSupportActionBar(toolbar);

        final Button loginButton = (Button) findViewById(R.id.button_login);
        final Button startButton = (Button) findViewById(R.id.button_play);

        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startTable();
            }

        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startLogin();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        //  this.userID = getIntent().getStringExtra("userId");

    }

    //starts the game
    private void startTable() {
        Intent intent = new Intent();
        intent.setClass(this, NeuronGameActivity.class);
        intent.putExtra("signedIn", signedIN);
        intent.putExtra("uid", userID);
        this.startActivity(intent);
    }

    //navigates to the login interface
    private void startLogin() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        this.startActivity(intent);
    }


}