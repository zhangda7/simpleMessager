package com.comm.service;

import com.comm.model.DO.MessageText;
import com.comm.service.dao.MessageTextDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息相关的service
 * 包括消息插入，读取，删除
 */
@Service
public class MessageService {

    @Autowired
    private MessageTextDao messageTextDao;

    public int insert(MessageText record) {
        return this.messageTextDao.insert(record);
    }

}
