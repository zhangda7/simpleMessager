package com.comm.model.msg;

/**
 * Only represents text data
 */
public class TextData {

    private long msgTs;

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getMsgTs() {
        return msgTs;
    }

    public void setMsgTs(long msgTs) {
        this.msgTs = msgTs;
    }
}
