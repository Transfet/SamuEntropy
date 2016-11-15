package hu.gyulbor.norbirontable.webservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import batfai.samuentropy.brainboard7.NeuronBox;
import batfai.samuentropy.brainboard7.NeuronGameActivity;
import batfai.samuentropy.brainboard7.Nodes;
import batfai.samuentropy.brainboard7.NorbironSurfaceView;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final String DATABASE_NAME = "NodeBackup.db";
    public static final String NODES_TABLE_NAME = "nodes";
    public static final String NODES_COLUMN_TYPE = "type";
    public static final String NODES_COLUMN_POSITION_X = "x";
    public static final String NODES_COLUMN_POSITION_Y = "y";
    public static final String NODES_COLUMN_USER = "user";
    public static final String NODES_COLUMN_NODE = "node";

    private int maxColumnIndex = 4;
    private Nodes nodes;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table nodes" +
                        "(type integer, x integer, y integer, user long, node long)"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS nodes");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertNode(int type, int x, int y, long user, long node) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", type);
        contentValues.put("x", x);
        contentValues.put("y", y);
        contentValues.put("user", user);
        contentValues.put("node", node);
        db.insert("nodes", null, contentValues);
        db.close();
        return true;
    }

    public boolean updateNode(int type, int x, int y, long user, long node) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", type);
        contentValues.put("x", x);
        contentValues.put("y", y);
        contentValues.put("user", user);
        contentValues.put("node", node);
        db.update("nodes", contentValues, "node = ? ", new String[] {Long.toString(node)});
        db.close();
        return true;
    }

    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS nodes");
        db.close();
    }

    public Integer deleteNode(long node) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("nodes",
                "node = ? ",
                new String[]{Long.toString(node)});

    }

    public int getType(long uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select type from nodes where node = '" + uuid +"'", null);

        int type = cursor.getInt(0);

        cursor.close();
        db.close();

        return type;
    }

    public int getX (long uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select x from nodes where node = '" + uuid +"'", null);
        Log.d("uuid: ", uuid+"");

        int x = cursor.getInt(0);

        cursor.close();
        db.close();

        return x;
    }

    public int getY (long uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select y from nodes where node = '" + uuid +"'", null);

        int y = cursor.getInt(0);

        cursor.close();
        db.close();

        return y;
    }

    public long getUser (long uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select user from nodes where node = '" + uuid + "'", null);

        long userID = cursor.getLong(0);

        cursor.close();
        db.close();

        return userID;
    }

    public ArrayList<Long> getNodes () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select node from nodes", null);

        ArrayList<Long> nodeIDs = new ArrayList<>();

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            nodeIDs.add(cursor.getLong(0));
            cursor.moveToNext();
        }

        return nodeIDs;
    }

    public int countRows() {
        String countQuery = "SELECT  * FROM " + NODES_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        db.close();
        return cnt;
    }


}