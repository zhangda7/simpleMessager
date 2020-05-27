package com.comm.util;

import com.comm.model.DataMessage;
import com.google.gson.Gson;

public class CommonUtil {

    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String data, Class<?> clazz) {
        return (T) gson.fromJson(data, clazz);
    }

    public static DataMessage makeMessage(String type, Long seqId, Object data) {
        DataMessage dataMessage = new DataMessage();
        dataMessage.setType(type);
        dataMessage.setSeqId(seqId);
        dataMessage.setData(data);
        return dataMessage;
    }

}
