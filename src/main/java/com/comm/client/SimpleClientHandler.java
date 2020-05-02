package com.comm.client;

import com.comm.CodecUtil;
import com.comm.model.DataMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClientHandler extends MessageToByteEncoder<Object> {

    private static Logger logger = LoggerFactory.getLogger(SimpleClientHandler.class);

    protected void encode(ChannelHandlerContext channelHandlerContext, Object data, ByteBuf out) throws Exception {
        byte[] bytes = CodecUtil.encode(data);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
        logger.info("Encode {} bytes", bytes.length);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        logger.error("ERROR ", cause);
        ctx.fireExceptionCaught(cause);
    }
}
