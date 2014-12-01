package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataTest {
  public final String TABLE_NAME "test";
  public final class Columns {
      public final String text = "text"
      public final String question = "question"
  }

  public final class OpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_COMMAND = "CREATE TABLE "
    + TABLE_NAME + " "
    + "("
      + Columns.text + " String, ";
      + Columns.question + " Question";
    + ")";

    OpenHelper(Context context) {
      super(context, DatabaseSettings.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_TABLE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + UserProfile.Contract.TABLE_NAME);
      onCreate(db);
    }
  }

  public static final class DataSource {
    private OpenHelper dbHelper;

    public static DataTest find(Integer i) {
      SQLiteDatabase db = dbHelper.getWritableDatabase();
      Cursor cursor = db.query(TABLE_NAME, null, null,
          null, null, null, null, "1");

      DataTest object = null;
      if (cursor.moveToFirst()) {
        object = new DataTest(cursor);
      }

      db.close();
      cursor.close();
      return object;
    }

    public static void create(DataTest object) {
      SQLiteDatabase db = dbHelper.getWritableDatabase();
      ContentValues values = object.getContentValues();
      db.insert(UserProfile.Contract.TABLE_NAME, null, values);
      db.close();
    }

    public static void create(DataTest object) {
      SQLiteDatabase db = dbHelper.getWritableDatabase();
      ContentValues values = object.getContentValues();
      db.update(UserProfile.Contract.TABLE_NAME, null, values);
      db.close();
    }
  }
}
