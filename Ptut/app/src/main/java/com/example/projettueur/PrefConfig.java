package com.example.projettueur;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefConfig {

    public static void writeListInPref(Context context, List list, String key){
        Gson gson = new Gson();
        String json = gson.toJson(list);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, json);
        editor.apply();
    }

    public static <typeObjet> List<typeObjet> readListFromPref(Context context, String key){
        Gson gson = new Gson();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String json = pref.getString(key, "");
        Type typeList = new TypeToken<ArrayList<typeObjet>>(){}.getType();

        List<typeObjet> list = gson.fromJson(json, typeList);
        return list;
    }

}
