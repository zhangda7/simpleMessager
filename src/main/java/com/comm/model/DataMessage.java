package com.comm.model;

import java.io.Serializable;

public class DataMessage implements Serializable {

    public static final String TYPE_TEXT = "TEXT";

    public static final String TYPE_IMAGE = "IMAGE";

    /**
     * msg type, such as text, image, video...
     * refer to @see com.comm.model.MsgType
     */
    private String type;

    private Long seqId;

    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }


}
