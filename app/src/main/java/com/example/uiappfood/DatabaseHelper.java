package com.example.uiappfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "user.db";
        private static final String TABLE_NAME = "user";
        private static final String COL_1 = "ID";
        private static final String COL_2 = "EMAIL";
        private static final String COL_3 = "PASSWORD";

        public DatabaseHelper(@Nullable Context context) {
                super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTERGER primary key, EMAIL TEXT, PASSWORD TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
        }

        public boolean insertData(String email, String password) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COL_2, email);
                contentValues.put(COL_3, password);
                long result = db.insert(TABLE_NAME, null, contentValues);
                return result != -1;
        }

        public boolean checkUse(String email, String password) {
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT *FROM "+ TABLE_NAME + " WHERE EMAIL = ? AND PASSWORD = ?", new String[]{
                        email, password
                });
                return cursor.getCount() > 0;
        }

        public String getEmail() {
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = null;
                String email = null;
                try {
                        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
                        if (cursor != null && cursor.moveToFirst()) {
                                email = cursor.getString(cursor.getColumnIndexOrThrow(COL_2));// Adjust "email" to the actual column name
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (cursor != null) {
                                cursor.close();
                        }
                }
                return email;
        }
}
