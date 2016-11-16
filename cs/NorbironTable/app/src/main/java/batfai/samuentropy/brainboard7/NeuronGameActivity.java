/*
 * NeuronAnimActivity.java
 *
 * Norbiron Game
 * This is a case study for creating sprites for SamuEntropy/Brainboard.
 *
 * Copyright (C) 2016, Dr. Bátfai Norbert
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Ez a program szabad szoftver; terjeszthető illetve módosítható a
 * Free Software Foundation által kiadott GNU General Public License
 * dokumentumában leírtak; akár a licenc 3-as, akár (tetszőleges) későbbi
 * változata szerint.
 *
 * Ez a program abban a reményben kerül közreadásra, hogy hasznos lesz,
 * de minden egyéb GARANCIA NÉLKÜL, az ELADHATÓSÁGRA vagy VALAMELY CÉLRA
 * VALÓ ALKALMAZHATÓSÁGRA való származtatott garanciát is beleértve.
 * További részleteket a GNU General Public License tartalmaz.
 *
 * A felhasználónak a programmal együtt meg kell kapnia a GNU General
 * Public License egy példányát; ha mégsem kapta meg, akkor
 * tekintse meg a <http://www.gnu.org/licenses/> oldalon.
 *
 * Version history:
 *
 * 0.0.1, 2013.szept.29.
 */
package batfai.samuentropy.brainboard7;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import hu.gyulbor.norbirontable.webservice.DBHelperR;

/**
 * @author nbatfai
 */
public class NeuronGameActivity extends AppCompatActivity {

    private boolean isChecked = false;
    public static DBHelperR nodeDB;
    private String userID = "default";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        nodeDB = new DBHelperR(this);
        super.onCreate(savedInstanceState);

        this.userID = getIntent().getStringExtra("userID");

        setContentView(R.layout.neuron);
        Toolbar toolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_home_black_48dp);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem checkable = menu.findItem(R.id.action_delete);
        checkable.setChecked(isChecked);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_build:
                android.content.Intent i = new android.content.Intent(this, NodeActivity.class);
                startActivity(i);

                return true;

            case R.id.action_delete:

                if (!isChecked) {
                    item.setIcon(R.drawable.ic_delete_white_48dp);
                } else {
                    item.setIcon(R.drawable.ic_delete_black_48dp);
                }
                isChecked = !item.isChecked();
                item.setChecked(isChecked);
                Check.isChecked = this.isChecked;

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    static NeuronGameActivity neuronGameActivity = new NeuronGameActivity();

    public static boolean isChecked() {

        return neuronGameActivity.isChecked;
    }

    @Override
    protected void onStop() {
        super.onStop();

        nodeDB = new DBHelperR(this);

        //ha több, mint egy node van a kijelzőn, akkor mentünk az adatbázisba.
        if (NorbironSurfaceView.getNodeBoxes().size() != 0) {

            for (int i = 0; i < NorbironSurfaceView.getNodeBoxes().size(); i++) {

                //lementjük az egyes adatokat
                int type = NorbironSurfaceView.getNodeBoxes().get(i).getType();
                int x = NorbironSurfaceView.getNodeBoxes().get(i).getX();
                int y = NorbironSurfaceView.getNodeBoxes().get(i).getY();
                long nodeID = NorbironSurfaceView.getNodeBoxes().get(i).getId();

                Log.d("Created node with: ", type + " " + x + " " + y + " " + userID + " " + nodeID);

                int rowCount = NeuronGameActivity.nodeDB.countRows();
                boolean updateOnly = false;

                //az adatbázisban lévő nodeokat csak frissítjük, az újakat hozzáadjuk.
                if (rowCount > 0) {

                    //ebben a listában van minden id,
                    //ha a jelenleg vizsgált id benne van, akkor csak frissítjük.
                    List<Long> nodesByID = NeuronGameActivity.nodeDB.getNodeIDs();

                    for (long currentID : nodesByID) {
                        if (currentID == nodeID) {
                            updateOnly = true;
                        }
                    }
                }

                if (updateOnly) {
                    nodeDB.updateNode(type, x, y, userID, nodeID);
                    Log.d("node", "updated");
                } else {
                    nodeDB.insertNode(type, x, y, userID, nodeID);
                    Log.d("node", "inserted");
                }
            }
        }

    }
}

