package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONAnswer {
  
    private String text;
  
    private Boolean valid;
  
  public JSONAnswer unmarshal(JSONObject object) throws JSONException {
  
    
      String text = object.getString("text");
    
  
    
      Boolean valid = object.getBoolean("valid");
    
  
    return new JSONAnswer(text, valid);
  }

  public JSONObject toJSONObject() throws JSONException {
    JSONObject object = new JSONObject();
  
    object.put("text", text);
  
    object.put("valid", valid);
  
    return object;
  }

  public JSONAnswer(){
  }

  public JSONAnswer(String text, Boolean valid){
  
    this.text = text;
  
    this.valid = valid;
  
  }

  
  public String getText() {
    return text;
  }
  
  public Boolean getValid() {
    return valid;
  }
  
}
