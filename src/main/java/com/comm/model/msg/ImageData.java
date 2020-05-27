package com.comm.model.msg;

public class ImageData {

    private String imageFormat;

    private byte[] binData;

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public byte[] getBinData() {
        return binData;
    }

    public void setBinData(byte[] binData) {
        this.binData = binData;
    }
}
