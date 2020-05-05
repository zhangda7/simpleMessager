package com.comm.model;

public enum MsgType {

    ACK((byte) 0),

    TEXT((byte) 1);

    private byte type;

    MsgType(byte type) {
        this.type = type;
    }


    public Byte getType() {
        return type;
    }
}
