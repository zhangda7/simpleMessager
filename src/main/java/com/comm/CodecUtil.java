package com.comm;

import com.comm.model.DataMessage;
import com.comm.model.MsgType;
import com.comm.util.CommonUtil;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CodecUtil {

    private static Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    private static Map<Short, MsgType> typeMap = new ConcurrentHashMap<Short, MsgType>();

    static {
        init();
    }

    private static void init() {
        for (MsgType value : MsgType.values()) {
            typeMap.put(value.getType(), value);
        }
    }

    public static byte[] encode(Object data) throws IOException {
        return CommonUtil.toJson(data).getBytes();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = null;
//
//        try {
//            oos = new ObjectOutputStream(baos);
//            oos.writeObject(data);
//            return baos.toByteArray();
//        } catch (IOException e) {
//            logger.error("Failed to encode data " + data, e);
//            throw e;
//        } finally {
//            try {
//                if(oos != null) {
//                    oos.close();
//                }
//            } catch (IOException e) {
//                logger.error("Failed to close oss", e);
//            }
//        }
    }

    public static Object decodeData(short type, byte[] bytes) {
        if(! typeMap.containsKey(type)) {
            logger.warn("Do not recoginse type {}", type);
            return null;
        }
        Object obj = CommonUtil.fromJson(new String(bytes), typeMap.get(type).getClazz());
        return CommonUtil.makeMessage(typeMap.get(type).name(),
                0L, obj);
//        ObjectInputStream ois = null;
//        Object data = null;
//        try {
//            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
//            data = ois.readObject();
//        } catch (Exception e) {
//            logger.error("Error on decode ", e);
//        } finally {
//            if(ois != null) {
//                try {
//                    ois.close();
//                } catch (IOException e) {
//                    logger.error("ERROR on close ", e);
//                }
//            }
//        }
//        return data;
    }



}
