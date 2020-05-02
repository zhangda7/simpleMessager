package com.comm.model;

public class TransData {

    private int version;

    private long seqId;

    private int bodyLen;


    private byte[] data;

    public int getBodyLen() {
        return bodyLen;
    }

    public void setBodyLen(int bodyLen) {
        this.bodyLen = bodyLen;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
