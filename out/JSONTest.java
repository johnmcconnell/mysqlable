package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONTest {
  
    private String text;
  
    private Question question;
  
  public JSONTest unmarshal(JSONObject object) throws JSONException {
  
    
      String text = object.getString("text");
    
  
    
      Question question = new Question().unmarshal(object.getJSONObject(question));
    
  
    return new JSONTest(text, question);
  }

  public JSONObject toJSONObject() throws JSONException {
    JSONObject object = new JSONObject();
  
    object.put("text", text);
  
    object.put("question", question.toJSONObject());
  
    return object;
  }

  public JSONTest(){
  }

  public JSONTest(String text, Question question){
  
    this.text = text;
  
    this.question = question;
  
  }

  
  public String getText() {
    return text;
  }
  
  public Question getQuestion() {
    return question;
  }
  
}
