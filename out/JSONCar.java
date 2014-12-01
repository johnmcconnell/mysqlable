package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONCar {
  
    private String color;
  
    private CarCompany manufacturer;
  
    private List<Wheel> wheels;
  
  public JSONCar unmarshal(JSONObject object) throws JSONException {
  
    
      String color = object.getString("color");
    
  
    
      CarCompany manufacturer = new CarCompany().unmarshal(object.getJSONObject(manufacturer));
    
  
    
       List<Wheel> wheels = new ArrayList<Wheel>();
       for(int x = 0; x < object.getJSONArray("wheels").length(); x++) {
       JSONObject wheelsObject = object.getJSONArray("wheels")
         .getJSONObject(x);
         wheels.add(new Wheel().unmarshal(wheelsObject));
       }
    
  
    return new JSONCar(color, manufacturer, wheels);
  }

  public JSONObject toJSONObject() throws JSONException {
    JSONObject object = new JSONObject();
  
    object.put("color", color);
  
    object.put("manufacturer", manufacturer.toJSONObject());
  
    JSONArray wheelsArray = new JSONArray();
    for (Wheel wheel : wheels) {
      wheelsArray.put(wheel.toJSONObject());
    }
    object.put("wheels", wheelsArray);
  
    return object;
  }

  public JSONCar(){
  }

  public JSONCar(String color, CarCompany manufacturer, List<Wheel> wheels){
  
    this.color = color;
  
    this.manufacturer = manufacturer;
  
    this.wheels = wheels;
  
  }

  
  public String getColor() {
    return color;
  }
  
  public CarCompany getManufacturer() {
    return manufacturer;
  }
  
  public List<Wheel> getWheels() {
    return wheels;
  }
  
}
