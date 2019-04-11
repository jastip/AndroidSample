package com.jastipapp.navigation.com.jastipapp.navigation.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbContext extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "sample_database";
    public static final String PERSON_TABLE_NAME = "person";
    public static final String PERSON_COLUMN_ID = "_id";
    public static final String PERSON_COLUMN_NAME = "name";
    public static final String PERSON_COLUMN_AGE = "age";
    public static final String PERSON_COLUMN_GENDER = "gender";

    public DbContext(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + PERSON_TABLE_NAME + " (" +
                PERSON_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PERSON_COLUMN_NAME + " TEXT, " +
                PERSON_COLUMN_AGE + " INT, " +
                PERSON_COLUMN_GENDER + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long saveToDB() {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContext.PERSON_COLUMN_NAME, "Test Hello DB");
        values.put(DbContext.PERSON_COLUMN_AGE, "20");
        values.put(DbContext.PERSON_COLUMN_GENDER, "Male");
        long newRowId = database.insert(DbContext.PERSON_TABLE_NAME, null, values);

        return newRowId;
    }

    public String readFromDB() {
        String name = "";
        String gender = "";
        String age = "";
        if(age.isEmpty())
            age = "0";

        SQLiteDatabase database = this.getReadableDatabase();

        String[] projection = {
                DbContext.PERSON_COLUMN_ID,
                DbContext.PERSON_COLUMN_NAME,
                DbContext.PERSON_COLUMN_AGE,
                DbContext.PERSON_COLUMN_GENDER
        };

        String selection =
                DbContext.PERSON_COLUMN_NAME + " like ? and " +
                        DbContext.PERSON_COLUMN_AGE + " > ? and " +
                        DbContext.PERSON_COLUMN_GENDER + " like ?";

        String[] selectionArgs = {"%" + name + "%", age, "%" + gender + "%"};

        Cursor cursor = database.query(
                DbContext.PERSON_TABLE_NAME,   // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        return cursor.getString(1);
    }
}
