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

  public static create(class_name object) {
  }

}
