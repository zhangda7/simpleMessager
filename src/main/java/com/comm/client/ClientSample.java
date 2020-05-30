package com.comm.client;

import com.comm.model.DataMessage;
import com.comm.model.MsgType;
import com.comm.model.msg.LoginInfo;
import com.comm.model.msg.TextData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ClientSample {

    private static Logger logger = LoggerFactory.getLogger(ClientSample.class);

    private static void sendLogin(SimpleClient simpleClient) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUid(UUID.randomUUID().toString());

        DataMessage dataMessage = new DataMessage();
        dataMessage.setType(MsgType.LOGIN.name());
        dataMessage.setSeqId(1L);
        dataMessage.setData(loginInfo);
        simpleClient.sendMsg(dataMessage);
    }

    private static void mockSendMsg(SimpleClient simpleClient) throws InterruptedException {
        logger.info("Begin send data");


        for (int i = 0; i < 2; i++) {
            DataMessage dataMessage = new DataMessage();
            dataMessage.setType("TEXT");
            dataMessage.setSeqId((long) i);
            TextData textData = new TextData();
            textData.setData("data" + i);
            dataMessage.setData(textData);
            logger.info("Send data {}", i);
            simpleClient.sendMsg(dataMessage);
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleClient simpleClient = new SimpleClient("localhost", 8081);
        simpleClient.start();

        sendLogin(simpleClient);
        mockSendMsg(simpleClient);
    }

}
