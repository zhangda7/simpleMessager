package com.comm.server;

import com.comm.CodecUtil;
import com.comm.model.TransData;
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

    private static final int CRC_LENGTH = 4;

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        logger.info("Readable bytes {}", byteBuf.readableBytes());
        if(byteBuf.readableBytes() < 16) {
            return;
        }
        byteBuf.markReaderIndex();
        short preamble = byteBuf.readShort();

        int msgLen = byteBuf.readInt();
        if(byteBuf.readableBytes() < msgLen + CRC_LENGTH) {
            byteBuf.resetReaderIndex();
            return;
        }
        logger.info("MsgLen:{}", msgLen);
        byte[] bytes = new byte[msgLen + CRC_LENGTH];
        byteBuf.resetReaderIndex();
        byteBuf.readBytes(bytes);
        TransData transData = new TransData();
        transData.decode(bytes);

        Object object = CodecUtil.decodeData(transData.getData());

        logger.info("Receive obj {}", gson.toJson(object));
//        list.add(object);
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
