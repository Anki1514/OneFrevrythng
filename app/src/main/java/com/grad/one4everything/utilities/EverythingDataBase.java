package com.grad.one4everything.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Anki on 10/14/2015.
 */
public class EverythingDataBase extends SQLiteOpenHelper {
    Context context;
    public static final String LOCKED_PACKAGE_NAME = "packageName";

    public EverythingDataBase(Context context) {
        super(context, "everydatabase.db", null, 1);
    }

    public EverythingDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public EverythingDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVERY_APPTABLE;
        CREATE_EVERY_APPTABLE = "CREATE TABLE everydatabase ( appId INTEGER PRIMARY KEY, packageName TEXT)";
        db.execSQL(CREATE_EVERY_APPTABLE);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String CREATE_EVERY_APPTABLE;
        CREATE_EVERY_APPTABLE = "DROP TABLE IF EXISTS everydatabase";
        db.execSQL(CREATE_EVERY_APPTABLE);
        onCreate(db);
    }

    public void insertApp(String packageName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("packageName", packageName);
        db.insert("everydatabase", null, values);
        db.close();
    }

    public void deleteApp(String packageName) {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            database.delete("everydatabase", "packageName = ?", new String[]{packageName});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
    }

    public ArrayList<String> getAllApps() {
        ArrayList<String> listofApps = new ArrayList<String>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("select * from everydatabase", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            listofApps.add(res.getString(res.getColumnIndex(LOCKED_PACKAGE_NAME)));
            res.moveToNext();
        }
        return listofApps;
    }
}
