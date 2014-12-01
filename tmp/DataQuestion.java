package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataQuestion {
  public final String TABLE_NAME "question";
  public final class Columns {
      public final String type = "type"
      public final String text = "text"
      public final String answers = "answers"
  }

  public final class OpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_COMMAND = "CREATE TABLE "
    + TABLE_NAME
    + "("
      + Columns.type + " String",;
      + Columns.text + " String",;
      + Columns.answers + " List<Answer>";
    + ")";
  }

  public static create(class_name object) {
  }

  public DataQuestion unmarshal(JSONObject object) throws JSONException {
    String type = object.getString("type");
    String text = object.getString("text");
    List<Answer> answers = new ArrayList<Answer>();
    for(int x = 0; x < object.getJSONArray("answers").length(); x++) {
    JSONObject answersObject = object.getJSONArray("answers")
      .getJSONObject(x);
      answers.add(new Answer().unmarshal(answersObject));
    }
    return new DataQuestion(type, text, answers);
  }

  public JSONObject toJSONObject() throws JSONException {
    JSONObject object = new JSONObject();
    object.put("type", type);
    object.put("text", text);
    JSONArray answersArray = new JSONArray();
    for (Answer answer : answers) {
      answersArray.put(answer.toJSONObject());
    }
    object.put("answers", answersArray);
    return object;
  }
}
