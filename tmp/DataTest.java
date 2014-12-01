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
    + TABLE_NAME
    + "("
      + Columns.text + " String",;
      + Columns.question + " Question";
    + ")";
  }

  public static create(class_name object) {
  }

  public DataTest unmarshal(JSONObject object) throws JSONException {
    String text = object.getString("text");
    Question question = new Question().unmarshal(object.getJSONObject("question"));
    return new DataTest(text, question);
  }

  public JSONObject toJSONObject() throws JSONException {
    JSONObject object = new JSONObject();
    object.put("text", text);
    object.put("question", question.toJSONObject());
    return object;
  }
}
