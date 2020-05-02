package com.comm.server;

import com.comm.CodecUtil;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SimpleServerHandler extends ByteToMessageDecoder {

    private static Logger logger = LoggerFactory.getLogger(SimpleServerHandler.class);

    private static Gson gson = new Gson();

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        logger.info("Readable bytes {}", byteBuf.readableBytes());
        if(byteBuf.readableBytes() < 5) {
            return;
        }
        byteBuf.markReaderIndex();
        int bodyLen = byteBuf.readInt();
        if(byteBuf.readableBytes() < bodyLen) {
            byteBuf.resetReaderIndex();
            return;
        }
        logger.info("Bodylen:{}", bodyLen);
        byte[] bytes = new byte[bodyLen];
        byteBuf.readBytes(bytes);
        Object object = CodecUtil.decodeData(bytes);

        logger.info("Receive obj {}", gson.toJson(object));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.printf("active:" + ctx.channel().toString());
    }

}
