package com.play.util.jackson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sunitc on 1/25/17.
 */
public class JsonArrayVsJsonObject {

  public static void main(String[] args) {

    String inputOne = "{\"firstName\": \"Sunit\", \"lastName\": \"Chatterjee\", \"company\": \"Wikkerwork\", \"experience\": 10.5}";

    String inputTwo = "[{\"firstName\": \"Sunit\", \"lastName\": \"Chatterjee\", \"company\": \"Wikkerwork\", \"experience\": 10.5}," +
        "{\"firstName\": \"Avanti\", \"lastName\": \"Pande\", \"company\": \"Wikkerwork\", \"experience\": 11.5}," +
        "{\"firstName\": \"Surya\", \"lastName\": \"Thaminaina\", \"company\": \"Wikkerwork\", \"experience\": 3.5}]";

    System.out.println("\n\nInput ONE ... object...\n**************************");
    JSONArray jsonArray1 = getJsonArray(inputOne);;
    System.out.println(jsonArray1.toString());


    System.out.println("\n\nInput TWO ... array...\n**************************");
    JSONArray jsonArray2 = getJsonArray(inputTwo);;
    System.out.println(jsonArray2.toString());
  }

  private static JSONArray getJsonArray(String inputOne) {
    try{
      JSONObject jsonObject = new JSONObject(inputOne);
      return new JSONArray().put(jsonObject);

    } catch (Exception ex) {
      return new JSONArray(inputOne);
    }
  }

}
