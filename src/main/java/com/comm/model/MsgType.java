package com.comm.model;

import com.comm.model.msg.HeartBeat;
import com.comm.model.msg.LoginInfo;
import com.comm.model.msg.TextData;

public enum MsgType {

    ACK((short) 1, HeartBeat.class),

    LOGIN((short) 2, LoginInfo.class),

    TEXT((short) 3, TextData.class);

    private short type;

    private Class<?> clazz;

    MsgType(short type, Class<?> clazz) {
        this.type = type;
        this.clazz = clazz;
    }


    public short getType() {
        return type;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
