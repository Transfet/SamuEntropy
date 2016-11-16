package batfai.samuentropy.brainboard7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    private long userID;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        setSupportActionBar(toolbar);

        final Button loginButton = (Button) findViewById(R.id.button_login);
        final Button startButton = (Button) findViewById(R.id.button_play);

        startButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startTable();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                startLogin();
            }

        });
    }

    private void startTable()
    {
        Intent intent = new Intent();
        intent.setClass(this, NeuronGameActivity.class);
        this.startActivity(intent);
    }

    private void startLogin()
    {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        //intent.putExtra("USER_ID", userID);
        this.startActivity(intent);

    }


}