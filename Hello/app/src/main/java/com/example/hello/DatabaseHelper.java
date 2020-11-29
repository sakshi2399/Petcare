package com.example.hello;



import android.database.DatabaseErrorHandler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;





public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "pets.db";
    private static final String TABLE_NAME = "pets_data3";          ///change table name here
    private static final String COL1 = "ID";
    private static final String COL2 = "PETNAME";
    private static final String COL3 = "PETSPECIES";            ////add new columns here
    private static final String COL4 = "PETBREEDS";
    private static final String COL5 = "PETBIRTHDATE";
    private static final String COL6 = "PETGENDER";
    private static final String COL7 = "PETIMAGE";



    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " PETNAME TEXT, PETSPECIES TEXT, PETBREEDS TEXT, PETBIRTHDATE TEXT, PETGENDER TEXT, PETIMAGE BLOB )";
                                                                                                    //add columns here
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(String pName,String pSpecies, String pBreeds, String pBirthDate, String pGender,  byte[] image) {           //add columns here
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, pName);
        contentValues.put(COL3, pSpecies);         //add columns here
        contentValues.put(COL4, pBreeds);                                //add columns here
        contentValues.put(COL5, pBirthDate);
        contentValues.put(COL6, pGender);
        contentValues.put(COL7, image);

        //Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */

    //query for 1 week repeats


    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    /////////////////////////////////////////made changes till here//////////////////


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */





    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();  /// String newSpecies, String newBreeds,  String oldSpecies, String oldBreeds
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";                 ///add columns here   "', "  + COL3 + " = '" + newSpecies +"', " + COL4 + "='" + newBreeds +
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}

///end





/**
 * Created by Mitch on 2016-05-13.
 */






