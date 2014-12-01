package com.bareknucklecoding.plato.json_models;

import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataCar {
  public final String TABLE_NAME "Car";
  public final class Columns {
      public final String color = "color"
      public final String manufacturer = "manufacturer"
      public final String wheels = "wheels"
  }

  public final class OpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_COMMAND = "CREATE TABLE "
    + TABLE_NAME
    + "("
      + Columns.color + " String",;
      + Columns.manufacturer + " CarCompany",;
      + Columns.wheels + " List<Wheel>";
    + ")";
  }

  public static create(class_name object) {
  }

  public DataCar unmarshal(JSONObject object) throws JSONException {
    String color = object.getString("color");
    CarCompany manufacturer = new CarCompany().unmarshal(object.getJSONObject("manufacturer"));
    List<Wheel> wheels = new ArrayList<Wheel>();
    for(int x = 0; x < object.getJSONArray("wheels").length(); x++) {
    JSONObject wheelsObject = object.getJSONArray("wheels")
      .getJSONObject(x);
      wheels.add(new Wheel().unmarshal(wheelsObject));
    }
    return new DataCar(color, manufacturer, wheels);
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
}
