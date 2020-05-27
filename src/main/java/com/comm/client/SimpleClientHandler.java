package com.comm.client;

import com.comm.model.DataMessage;
import com.comm.util.CommonUtil;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClientHandler extends SimpleChannelInboundHandler<Object> {

    private static Logger logger = LoggerFactory.getLogger(SimpleClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(! (msg instanceof DataMessage)) {
            logger.warn("msg is not data message {}", msg);
            return;
        }

        DataMessage dataMessage = (DataMessage) msg;
        logger.info("Receive msg {}", CommonUtil.toJson(dataMessage));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("ERROR ", cause);
        ctx.fireExceptionCaught(cause);
    }
}
