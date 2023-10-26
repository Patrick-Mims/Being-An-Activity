package edu.sfsu.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/*
 *   SQLiteHelper manages the database: create, access and maintenance
 *   1. Add the DB_NAME and DB_VERSION
 *   2. Create a "Contract Class"
 * */

public class SQLiteHelper extends SQLiteOpenHelper {
    /**
     *
     * @param context
     * Call the constructor of the SQLiteOpenHelper superclass, and passing it the database name and version.
     */

    public SQLiteHelper(@Nullable Context context) {
        super(context, Contract.ContractEntry.DB_NAME, null, Contract.ContractEntry.DB_VERSION);
        Log.i("LOG", "DB_NAME               => " + Contract.ContractEntry.DB_NAME);
        Log.i("LOG", "DB_VERSION            => " + Contract.ContractEntry.DB_VERSION);
        Log.i("LOG", "SQL_CREATE_ENTRIES    => " + Contract.ContractEntry.SQL_CREATE_ENTRIES);
        Log.i("LOG", "COLUMN_NAME_TITLE     => " + Contract.ContractEntry.COLUMN_NAME_TITLE);
        Log.i("LOG", "COLUMN_NAME_SUBTITLE  => " + Contract.ContractEntry.COLUMN_NAME_SUBTITLE);
        // Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
    }

    // onCreate(sqlLiteDatabase) gets called when the database first gets created on the device
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /**
         * execute the sql in the string on the database;
         */
        Log.v("LOG", "SQLiteHelper onCreate()");
        Log.v("LOG", "SQLiteHelper.class: Before SQL_CREATE_ENTRIES");
        sqLiteDatabase.execSQL(Contract.ContractEntry.SQL_CREATE_ENTRIES);
        insertRecord(sqLiteDatabase, "Training Day", "Denzel Washington");
        insertRecord(sqLiteDatabase, "Ghost Busters", "Dan Akroid");
        insertRecord(sqLiteDatabase, "Three Men and a Baby", "Ted Knight");
        insertRecord(sqLiteDatabase, "House", "Tim Jones");
        insertRecord(sqLiteDatabase, "Sister", "Jill Myers");
        Log.v("LOG", "SQLiteHelper.class: After SQL_CREATE_ENTRIES");
    }

    public void insertRecord(SQLiteDatabase db, String title, String subtitle) {
        ContentValues values = new ContentValues();

        values.put(Contract.ContractEntry.COLUMN_NAME_TITLE, title);
        values.put(Contract.ContractEntry.COLUMN_NAME_SUBTITLE, subtitle);

        // Insert the new row, returning the primary key value of the new row.
        db.insert("MOVIE", null, values);
    }

    // gets called when the database needs to be upgraded

    /**
     *
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(Contract.ContractEntry.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    /**
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}