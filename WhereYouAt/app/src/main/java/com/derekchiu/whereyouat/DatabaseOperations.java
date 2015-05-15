package com.derekchiu.whereyouat;

import android.content.ContentValues;
import android.content.Context;
import com.derekchiu.whereyouat.TableData.TableInfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Derek Chiu on 3/26/2015.
 */
public class DatabaseOperations  extends SQLiteOpenHelper {

    public static final int database_version = 12;
    public String CREATE_QUERY =
            "CREATE TABLE " +
            TableInfo.TABLE_NAME +
            " (" +
            TableInfo.USER_NAME  +
            " TEXT, " +
            TableInfo.USER_PASS +
            " TEXT)";

    public DatabaseOperations(Context context) {
        super(context, TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(DatabaseOperations dop, String name, String pass) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableInfo.USER_NAME, name);
        cv.put(TableInfo.USER_PASS, pass);
        long k = SQ.insert(TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One row inserted");
    }

    public Cursor getInformation(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableInfo.USER_NAME, TableInfo.USER_PASS};
        Cursor CR = SQ.query(TableInfo.TABLE_NAME, columns, null, null,null,null,null);
        return CR;
    }
}
