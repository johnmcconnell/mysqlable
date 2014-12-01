package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataQuiz {
  public final String TABLE_NAME "quiz";
  public final class Columns {
      public final String id = "id"
      public final String subject = "subject"
      public final String level = "level"
      public final String topic = "topic"
      public final String questions = "questions"
  }

  public final class OpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_COMMAND = "CREATE TABLE "
    + TABLE_NAME
    + "("
      + Columns.id + " Integer",;
      + Columns.subject + " String",;
      + Columns.level + " String",;
      + Columns.topic + " String",;
      + Columns.questions + " Question";
    + ")";
  }

  public static create(class_name object) {
  }

  public DataQuiz unmarshal(JSONObject object) throws JSONException {
    Integer id = object.getInteger("id");
    String subject = object.getString("subject");
    String level = object.getString("level");
    String topic = object.getString("topic");
    Question questions = new Question().unmarshal(object.getJSONObject("questions"));
    return new DataQuiz(id, subject, level, topic, questions);
  }

  public JSONObject toJSONObject() throws JSONException {
    JSONObject object = new JSONObject();
    object.put("id", id);
    object.put("subject", subject);
    object.put("level", level);
    object.put("topic", topic);
    object.put("questions", questions.toJSONObject());
    return object;
  }
}
