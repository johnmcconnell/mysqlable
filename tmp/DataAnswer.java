package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataAnswer {
  public final String TABLE_NAME "answer";
  public final class Columns {
      public final String text = "text"
      public final String valid = "valid"
  }

  public final class OpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_COMMAND = "CREATE TABLE "
    + TABLE_NAME
    + "("
      + Columns.text + " String",;
      + Columns.valid + " Boolean";
    + ")";
  }

  public static create(class_name object) {
  }

  public DataAnswer unmarshal(JSONObject object) throws JSONException {
    String text = object.getString("text");
    Boolean valid = object.getBoolean("valid");
    return new DataAnswer(text, valid);
  }

  public JSONObject toJSONObject() throws JSONException {
    JSONObject object = new JSONObject();
    object.put("text", text);
    object.put("valid", valid);
    return object;
  }
}
