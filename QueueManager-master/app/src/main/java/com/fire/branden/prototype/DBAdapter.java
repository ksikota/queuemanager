// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package com.fire.branden.prototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {


    /////////////////////////////////////////////////////////////////////
    //	Constants & Data
    /////////////////////////////////////////////////////////////////////
    // For logging:
    private static final String TAG = "DBAdapter";

    // DB Fields
    public static final String KEY_ROWID = "_id";
    public static final int COL_ROWID = 0;
    /*
     * CHANGE 1:
     */
    // TODO: Setup your fields here:
    public static final String KEY_NAME = "name";
    public static final String KEY_CELL = "cell";

    public static final String KEY_SERVICE_A = "serviceA";
    public static final String KEY_SERVICE_B = "serviceB";
    public static final String KEY_SERVICE_C = "serviceC";
    public static final String KEY_SERVICE_D = "serviceD";
    public static final String KEY_SERVICE_E = "serviceE";
    public static final String KEY_SERVICE_F = "serviceF";

    public static final String KEY_SALE_1 = "sale1";
    public static final String KEY_SALE_2 = "sale2";
    public static final String KEY_SALE_3 = "sale3";
    public static final String KEY_SALE_4 = "sale4";
    public static final String KEY_SALE_5 = "sale5";
    public static final String KEY_SALE_6 = "sale6";
    public static final String KEY_SALE_7 = "sale7";
    public static final String KEY_SALE_8 = "sale8";
    public static final String KEY_SALE_9 = "sale9";
    public static final String KEY_SALE_10 = "sale10";
    public static final String KEY_SALE_11 = "sale11";
    public static final String KEY_SALE_12 = "sale12";
    public static final String KEY_SALE_13 = "sale13";
    public static final String KEY_SALE_14 = "sale14";
    public static final String KEY_SALE_15 = "sale15";
    public static final String KEY_SALE_16 = "sale16";
    public static final String KEY_SALE_17 = "sale17";
    public static final String KEY_SALE_18 = "sale18";

    public static final String KEY_DESC = "description";
    public static final String KEY_TIME = "time";
    public static final String KEY_TIME_STR = "timeString";
    public static final String KEY_CONSULTANT = "consultant";

    // TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
    public static final int COL_NAME = 1;
    public static final int COL_CELL = 2;

    public static final int COL_SERVICE_A = 3;
    public static final int COL_SERVICE_B = 4;
    public static final int COL_SERVICE_C = 5;
    public static final int COL_SERVICE_D = 6;
    public static final int COL_SERVICE_E = 7;
    public static final int COL_SERVICE_F = 8;

    public static final int COL_SALE_1 = 9;
    public static final int COL_SALE_2 = 10;
    public static final int COL_SALE_3 = 11;
    public static final int COL_SALE_4 = 12;
    public static final int COL_SALE_5 = 13;
    public static final int COL_SALE_6 = 14;
    public static final int COL_SALE_7 = 15;
    public static final int COL_SALE_8 = 16;
    public static final int COL_SALE_9 = 17;
    public static final int COL_SALE_10 = 18;
    public static final int COL_SALE_11 = 19;
    public static final int COL_SALE_12 = 20;
    public static final int COL_SALE_13 = 21;
    public static final int COL_SALE_14 = 22;
    public static final int COL_SALE_15 = 23;
    public static final int COL_SALE_16 = 24;
    public static final int COL_SALE_17 = 25;
    public static final int COL_SALE_18 = 26;

    public static final int COL_DESC = 27;
    public static final int COL_TIME = 28;
    public static final int COL_TIME_STR = 29;
    public static final int COL_CONSULTANT = 30;

    public static final String[] ALL_KEYS = new String[]{
            KEY_ROWID,

            KEY_NAME,
            KEY_CELL,

            KEY_SERVICE_A,
            KEY_SERVICE_B,
            KEY_SERVICE_C,
            KEY_SERVICE_D,
            KEY_SERVICE_E,
            KEY_SERVICE_F,

            KEY_SALE_1,
            KEY_SALE_2,
            KEY_SALE_3,
            KEY_SALE_4,
            KEY_SALE_5,
            KEY_SALE_6,
            KEY_SALE_7,
            KEY_SALE_8,
            KEY_SALE_9,
            KEY_SALE_10,
            KEY_SALE_11,
            KEY_SALE_12,
            KEY_SALE_13,
            KEY_SALE_14,
            KEY_SALE_15,
            KEY_SALE_16,
            KEY_SALE_17,
            KEY_SALE_18,

            KEY_DESC,
            KEY_TIME,
            KEY_TIME_STR,
            KEY_CONSULTANT};

    // DB info: it's name, and the table we are using (just one).
    public static final String DATABASE_NAME = "queryDb";
    public static final String DATABASE_TABLE = "queryTable";
    // Track DB version if a new version of your app changes the format.
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_SQL =
            "create table " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " integer primary key autoincrement, "

			/*
             * CHANGE 2:
			 */
                    // TODO: Place your fields here!
                    // + KEY_{...} + " {type} not null"
                    //	- Key is the column name you created above.
                    //	- {type} is one of: text, integer, real, blob
                    //		(http://www.sqlite.org/datatype3.html)
                    //  - "not null" means it is a required field (must be given a value).
                    // NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
                    + KEY_NAME + " text not null, " +
                    KEY_CELL + " text not null, " +

                    KEY_SERVICE_A + " integer not null, " +
                    KEY_SERVICE_B + " integer not null, " +
                    KEY_SERVICE_C + " integer not null, " +
                    KEY_SERVICE_D + " integer not null, " +
                    KEY_SERVICE_E + " integer not null, " +
                    KEY_SERVICE_F + " integer not null, " +

                    KEY_SALE_1 + " integer not null, " +
                    KEY_SALE_2 + " integer not null, " +
                    KEY_SALE_3 + " integer not null, " +
                    KEY_SALE_4 + " integer not null, " +
                    KEY_SALE_5 + " integer not null, " +
                    KEY_SALE_6 + " integer not null, " +
                    KEY_SALE_7 + " integer not null, " +
                    KEY_SALE_8 + " integer not null, " +
                    KEY_SALE_9 + " integer not null, " +
                    KEY_SALE_10 + " integer not null, " +
                    KEY_SALE_11 + " integer not null, " +
                    KEY_SALE_12 + " integer not null, " +
                    KEY_SALE_13 + " integer not null, " +
                    KEY_SALE_14 + " integer not null, " +
                    KEY_SALE_15 + " integer not null, " +
                    KEY_SALE_16 + " integer not null, " +
                    KEY_SALE_17 + " integer not null, " +
                    KEY_SALE_18 + " integer not null," +

                    KEY_DESC + " text not null," +
                    KEY_TIME + " integer not null, " +
                    KEY_TIME_STR + " text not null, " +
                    KEY_CONSULTANT + " integer not null);" ;

    // Context of application who uses us.
    private final Context context;

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    /////////////////////////////////////////////////////////////////////
    //	Public methods:
    /////////////////////////////////////////////////////////////////////

    public DBAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public DBAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    public long getSize(){
        return DatabaseUtils.queryNumEntries(db,DATABASE_TABLE);
    }
    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    // Add a new set of values to the database.
    public long insertRow(String name, String cell, int[] check, String desc, int time, String strTime, int consultant) {

        /*
         * CHANGE 3:
		 */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_CELL, cell);

        initialValues.put(KEY_SERVICE_A, check[0]);
        initialValues.put(KEY_SERVICE_B, check[1]);
        initialValues.put(KEY_SERVICE_C, check[2]);
        initialValues.put(KEY_SERVICE_D, check[3]);
        initialValues.put(KEY_SERVICE_E, check[4]);
        initialValues.put(KEY_SERVICE_F, check[5]);

        initialValues.put(KEY_SALE_1, check[6]);
        initialValues.put(KEY_SALE_2, check[7]);
        initialValues.put(KEY_SALE_3, check[8]);
        initialValues.put(KEY_SALE_4, check[9]);
        initialValues.put(KEY_SALE_5, check[10]);
        initialValues.put(KEY_SALE_6, check[11]);
        initialValues.put(KEY_SALE_7, check[12]);
        initialValues.put(KEY_SALE_8, check[13]);
        initialValues.put(KEY_SALE_9, check[14]);
        initialValues.put(KEY_SALE_10, check[15]);
        initialValues.put(KEY_SALE_11, check[16]);
        initialValues.put(KEY_SALE_12, check[17]);
        initialValues.put(KEY_SALE_13, check[18]);
        initialValues.put(KEY_SALE_14, check[19]);
        initialValues.put(KEY_SALE_15, check[20]);
        initialValues.put(KEY_SALE_16, check[21]);
        initialValues.put(KEY_SALE_17, check[22]);
        initialValues.put(KEY_SALE_18, check[23]);

        initialValues.put(KEY_DESC, desc);
        initialValues.put(KEY_TIME, time);
        initialValues.put(KEY_TIME_STR, strTime);
        initialValues.put(KEY_CONSULTANT, consultant);

        // Insert it into the database.
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {

        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATABASE_TABLE, where, null) != 0;
    }

    public void deleteAll() {

        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    public Cursor getAllRows() {
        String where = null;
        Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, KEY_TIME+ " ASC", null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null,  null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void updateTime(int t,String str, long n){
        db.execSQL("UPDATE " + DATABASE_TABLE +" SET "+KEY_TIME+"="+t+", "+KEY_TIME_STR+"= '"+str+"' WHERE "+ KEY_ROWID+"="+n);
    }

    // Change an existing row to be equal to new data.
    public boolean updateRow(long rowId, String name, String cell, int[] check, String desc, int time, String strTime, int consultant) {
        String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_NAME, name);
        newValues.put(KEY_CELL, cell);

        newValues.put(KEY_SERVICE_A, check[0]);
        newValues.put(KEY_SERVICE_B, check[1]);
        newValues.put(KEY_SERVICE_C, check[2]);
        newValues.put(KEY_SERVICE_D, check[3]);
        newValues.put(KEY_SERVICE_E, check[4]);
        newValues.put(KEY_SERVICE_F, check[5]);

        newValues.put(KEY_SALE_1, check[6]);
        newValues.put(KEY_SALE_2, check[7]);
        newValues.put(KEY_SALE_3, check[8]);
        newValues.put(KEY_SALE_4, check[9]);
        newValues.put(KEY_SALE_5, check[10]);
        newValues.put(KEY_SALE_6, check[11]);
        newValues.put(KEY_SALE_7, check[12]);
        newValues.put(KEY_SALE_8, check[13]);
        newValues.put(KEY_SALE_9, check[14]);
        newValues.put(KEY_SALE_10, check[15]);
        newValues.put(KEY_SALE_11, check[16]);
        newValues.put(KEY_SALE_12, check[17]);
        newValues.put(KEY_SALE_13, check[18]);
        newValues.put(KEY_SALE_14, check[19]);
        newValues.put(KEY_SALE_15, check[20]);
        newValues.put(KEY_SALE_16, check[21]);
        newValues.put(KEY_SALE_17, check[22]);
        newValues.put(KEY_SALE_18, check[23]);

        newValues.put(KEY_DESC, desc);
        newValues.put(KEY_TIME, time);
        newValues.put(KEY_TIME_STR, strTime);
        newValues.put(KEY_CONSULTANT, consultant);
        // Insert it into the database.
        return db.update(DATABASE_TABLE, newValues, where, null) != 0;
    }


    /////////////////////////////////////////////////////////////////////
    //	Private Helper Classes:
    /////////////////////////////////////////////////////////////////////

    /**
     * Private class which handles database creation and upgrading.
     * Used to handle low-level database access.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }
}
