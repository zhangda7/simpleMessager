package com.comm;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class CodecUtil {

    private static Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    public static byte[] encode(Object data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            return baos.toByteArray();
        } catch (IOException e) {
            logger.error("Failed to encode data " + data, e);
            throw e;
        } finally {
            try {
                if(oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                logger.error("Failed to close oss", e);
            }
        }
    }

    public static Object decodeData(byte[] bytes) {
        ObjectInputStream ois = null;
        Object data = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            data = ois.readObject();
        } catch (Exception e) {
            logger.error("Error on decode ", e);
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    logger.error("ERROR on close ", e);
                }
            }
        }
        return data;
    }



}
