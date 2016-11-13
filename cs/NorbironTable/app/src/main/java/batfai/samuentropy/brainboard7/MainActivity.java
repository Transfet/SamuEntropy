package batfai.samuentropy.brainboard7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button loginButton = (Button) findViewById(R.id.button);
        final Button startButton = (Button) findViewById(R.id.button2);

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

    public void startTable()
    {
        Intent intent = new Intent();
        intent.setClass(this, NeuronGameActivity.class); //TODO
        this.startActivity(intent);
    }

    public void startLogin()
    {
        Intent intent = new Intent();
        intent.setClass(this, FacebookLoginActivity.class); //TODO
        this.startActivity(intent);

    }


}
