package com.comm.server;

import com.comm.model.DO.EventLog;
import com.comm.model.DO.MessageText;
import com.comm.model.DataMessage;
import com.comm.model.MsgType;
import com.comm.model.msg.LoginInfo;
import com.comm.model.msg.TextData;
import com.comm.service.EventLogService;
import com.comm.service.MessageService;
import com.comm.util.ApplicationContextHolder;
import com.comm.util.CommonUtil;
import com.comm.util.MessageConstants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleServerHandler extends SimpleChannelInboundHandler<Object> {

    private static Logger logger = LoggerFactory.getLogger(SimpleServerHandler.class);

    private String curUid;

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(! (msg instanceof DataMessage)) {
            logger.warn("msg is not data message {}", msg);
            return;
        }

        DataMessage dataMessage = (DataMessage) msg;
        logger.info("Receive msg {}", CommonUtil.toJson(dataMessage));

        if(MsgType.TEXT.name().equals(dataMessage.getType())) {
            this.insertText((TextData) dataMessage.getData());
        } else if(MsgType.LOGIN.name().equals(dataMessage.getType())) {
            LoginInfo loginInfo = (LoginInfo) dataMessage.getData();
            this.curUid = loginInfo.getUid();
            logger.info("Cache uid {} for {}", this.curUid, ctx.channel());
            this.insertEvent(loginInfo.getUid(), MsgType.LOGIN.name());
        } else {
            logger.info("Ignore type {}", dataMessage.getType());
        }
    }

    private void insertEvent(String uid, String type) {
        EventLog eventLog = new EventLog();
        eventLog.setOccurTime(new Date());
        eventLog.setType(type);
        eventLog.setUid(uid);
        eventLog.setGmtCreate(new Date());
        eventLog.setGmtModified(new Date());
        eventLog.setIsDelete((byte) 0);
        ApplicationContextHolder.getBean(EventLogService.class).insert(eventLog);
    }

    private void insertText(TextData textData) {
        MessageText messageText = new MessageText();
        messageText.setUid(curUid);
        messageText.setContent(textData.getData());
        messageText.setDirection(MessageConstants.DIRECTION_CLIENT_TO_SERVER);
        messageText.setGmtCreate(new Date());
        messageText.setGmtModifed(new Date());
        messageText.setIsDelete((byte) 0);
        messageText.setMsgTime(new Date(textData.getMsgTs()));

        ApplicationContextHolder.getBean(MessageService.class).insert(messageText);
    }
}
