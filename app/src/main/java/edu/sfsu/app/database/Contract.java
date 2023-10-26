package edu.sfsu.app.database;

import android.provider.BaseColumns;

/**
 * It's helpful to create a companion class,
 * known as a "contract class", which explicitly
 * specifies the layout of your schema in a
 * systematic and self-documenting way.
 *
 * A contract class is a container for constant that define names for URIs, tables, and columns.
 * The contract class allows you to use the same constants across all the other classes in the same package.
 * This lets you change a column name in one place and have it propagate throughout your code.
 */

public class Contract {
    // To prevent someone from accidentally instantiating
    // the contract class, make the constructor private.
    private Contract() {}

    public static class ContractEntry implements BaseColumns {
        public static final int DB_VERSION = 1;
        public static final String DB_NAME = "hollywood";
        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_TITLE + " TEXT, " +
                COLUMN_NAME_SUBTITLE + " TEXT);";
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}