package com.kimsoft.kims.easier.boot.tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Kimi
 * @date 2020/3/2
 */
public class JsonTools {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);

    }
}
