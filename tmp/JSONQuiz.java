package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONQuiz {
    private Integer id;
    private String subject;
    private String level;
    private String topic;
    private Question questions;

  public JSONQuiz unmarshal(JSONObject object) throws JSONException {
    Integer id = object.getInteger("id");
    String subject = object.getString("subject");
    String level = object.getString("level");
    String topic = object.getString("topic");
    Question questions = new Question().unmarshal(object.getJSONObject("questions"));
    return new JSONQuiz(id, subject, level, topic, questions);
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

  public JSONQuiz(){
  }

  public JSONQuiz(Integer id, String subject, String level, String topic, Question questions) {
      this.id = id;
      this.subject = subject;
      this.level = level;
      this.topic = topic;
      this.questions = questions;
  }

  public Integer getId() {
    return this.id;
  }
  public String getSubject() {
    return this.subject;
  }
  public String getLevel() {
    return this.level;
  }
  public String getTopic() {
    return this.topic;
  }
  public Question getQuestions() {
    return this.questions;
  }
}
