package com.comm.util;

import com.google.gson.Gson;

public class CommonUtil {

    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String data, Class<?> clazz) {
        return (T) gson.fromJson(data, clazz);
    }

}
