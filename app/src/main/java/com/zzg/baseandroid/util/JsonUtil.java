package com.zzg.baseandroid.util;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by wwxwwq on 16/5/22.
 */
public class JsonUtil {
    public static Gson gson = new Gson();

    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T JsonStringToObject(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    public static JSONObject stringToJSONObject(String result) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            jsonObject = new JSONObject();
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONArray getJsonArrayFromJSONObject(JSONObject json, String name) {
        try {
            return json.getJSONArray(name);
        } catch (JSONException e) {
            return new JSONArray();
        }
    }

    public static JSONObject getJsonFromJSONArray(JSONArray array, int index) {
        try {
            return array.getJSONObject(index);
        } catch (JSONException e) {
            return new JSONObject();
        }
    }
    public static String getStringFromJSONObject(JSONObject json, String name, String defaultValue) {
        try {
            return json.getString(name);
        } catch (JSONException e) {
            return defaultValue;
        }
    }
}
