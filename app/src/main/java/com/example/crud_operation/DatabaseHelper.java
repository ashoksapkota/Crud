package com.example.crud_operation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Student_db";
    public static final String TABLE_NAME = "Student_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "LASTNAME";
    public static final String COL4 = "MARKS";

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "" + " NAME TEXT, LASTNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public Object insertData (String NAME, String LASTNAME, String marks)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, NAME);
        contentValues.put(COL3, LASTNAME);
        contentValues.put(COL4, marks);
        Long success = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (success == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
}
    public Cursor getAllData(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cut = myDB.rawQuery("select * from " +TABLE_NAME, null);
        return cut;
    }

    public boolean Updatedata(String id, String name, String lastname, String marks){
        SQLiteDatabase update = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,lastname);
        contentValues.put(COL4,marks);
        update.update(TABLE_NAME,contentValues,"ID = ?", new String[] {id});

        return true;
    }
}
