package com.example.db_class2;

// Libraries
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// Super class
public class SQLiteHelper extends SQLiteOpenHelper {
    // Declare variables
    public static final String DATABASE_NAME = "students_marks";
    public static final String TABLE_NAME = "marks_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "MARKS";
    // Constructor
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase database = this.getWritableDatabase();
    }
    // OnCreate method
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MARKS INTEGER)");

    }
    // OnUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }
    public boolean insertData(String name, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, marks);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result !=-1;
    }
    public Cursor viewAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return  res;
    }
    public Integer deleteMarks(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME,"ID = ?", new String[]{id});
    }
    public boolean updateMarks(String id, String name, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, marks);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;

    }
}
