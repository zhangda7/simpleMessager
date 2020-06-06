package com.comm.service.dao;

import com.comm.model.DO.EventLog;
import com.comm.service.dao.EventLogExample;
import java.util.List;

import com.comm.service.dao.MyBatisBaseDao;
import org.apache.ibatis.annotations.Param;


/**
 * EventLogMapper继承基类
 */
public interface EventLogMapper extends MyBatisBaseDao<EventLog, Long, EventLogExample> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(EventLog record);

    int updateByExample(@Param("record") EventLog record, @Param("example") EventLogExample example);

    long countByExample(EventLogExample example);

    int updateByExampleSelective(@Param("record") EventLog record, @Param("example") EventLogExample example);

    int updateByPrimaryKeySelective(EventLog record);

    EventLog selectByPrimaryKey(Long id);

    List<EventLog> selectByExample(EventLogExample example);

    int updateByPrimaryKey(EventLog record);

    int deleteByExample(EventLogExample example);

    int insert(EventLog record);
}