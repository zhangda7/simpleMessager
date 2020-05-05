package com.comm.client;

import com.comm.CodecUtil;
import com.comm.model.DataMessage;
import com.comm.model.MsgType;
import com.comm.model.TransData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleClientHandler extends MessageToByteEncoder<Object> {

    private static Logger logger = LoggerFactory.getLogger(SimpleClientHandler.class);

    private AtomicLong seqLong = new AtomicLong();

    protected void encode(ChannelHandlerContext channelHandlerContext, Object data, ByteBuf out) throws Exception {
        if(!( data instanceof DataMessage)) {
            logger.warn("Not support {} to encode", data);
            return;
        }

        byte[] bytes = CodecUtil.encode(data);
        out.writeBytes(encodeDataMessage((DataMessage) data));
        logger.info("Encode {} bytes", bytes.length);
    }

    private byte[] encodeDataMessage(DataMessage dataMessage) throws IOException {
        byte[] bytes = CodecUtil.encode(dataMessage);
        Byte type = MsgType.valueOf(dataMessage.getType()).getType();

        if(type == null) {
            throw new IllegalArgumentException("Can not support type " + dataMessage.getType());
        }

        TransData transData = new TransData();
        transData.setMsgType(type);
        transData.setData(bytes);
        transData.setSeqId(seqLong.getAndIncrement());

        return transData.encode();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        logger.error("ERROR ", cause);
        ctx.fireExceptionCaught(cause);
    }
}
