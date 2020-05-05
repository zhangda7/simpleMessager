package com.comm.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

public class TransData {

    private short preamble = 0XD1;

    /**
     * msg length exclude crc in bytes
     */
    private int msgLen;

    /**
     * only header length in bytes
     */
    private short headerLen;

    /**
     * version
     */
    private byte version = 1;



    /**
     * message seq id
     */
    private long seqId;

    /**
     * msg type:
     * 0:ACK
     * 1:TEXT
     * 2:IMAGE
     * 3:VIDEO
     * 4:
     */
    private byte msgType;

    /**
     * real data
     */
    private byte[] data;

    private int crc;

    public byte[] encode() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(bitSize());
        this.headerLen = 2 + 4 + 2 + 1 + 8 + 1;
        this.msgLen = this.headerLen + data.length;

        byteBuffer.putShort(this.preamble);
        byteBuffer.putInt(this.msgLen);
        byteBuffer.putShort(this.headerLen);
        byteBuffer.put(this.version);
        byteBuffer.putLong(this.seqId);
        byteBuffer.put(this.msgType);

        byteBuffer.put(this.data);
        byteBuffer.putInt(this.crc);

        return byteBuffer.array();
    }

    public void decode(byte[] bytes) {
        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        this.preamble = byteBuf.readShort();
        this.msgLen = byteBuf.readInt();
        this.headerLen = byteBuf.readShort();
        this.version = byteBuf.readByte();
        this.seqId = byteBuf.readLong();
        this.msgType = byteBuf.readByte();
        this.data = new byte[this.msgLen - this.headerLen];
        byteBuf.readBytes(this.data);
        this.crc = byteBuf.readInt();
    }

    public int bitSize() {
        return 2 + 4 + 2 + 1 + 8 + 1 + data.length + 4;
    }

    public short getPreamble() {
        return preamble;
    }

    public void setPreamble(short preamble) {
        this.preamble = preamble;
    }

    public int getMsgLen() {
        return msgLen;
    }

    public void setMsgLen(int msgLen) {
        this.msgLen = msgLen;
    }

    public short getHeaderLen() {
        return headerLen;
    }

    public void setHeaderLen(short headerLen) {
        this.headerLen = headerLen;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public long getSeqId() {
        return seqId;
    }

    public void setSeqId(long seqId) {
        this.seqId = seqId;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }
}
