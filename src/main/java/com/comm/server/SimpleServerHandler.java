package com.comm.server;

import com.comm.model.DataMessage;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleServerHandler extends SimpleChannelInboundHandler<Object> {

    private static Logger logger = LoggerFactory.getLogger(SimpleServerHandler.class);

    private static Gson gson = new Gson();

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(! (msg instanceof DataMessage)) {
            logger.warn("msg is not data message {}", msg);
            return;
        }

        DataMessage dataMessage = (DataMessage) msg;
        logger.info("Receive msg {}", gson.toJson(dataMessage));
    }
}
