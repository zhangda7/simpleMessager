//package com.comm.service;
//
//import com.comm.model.MessageText;
//import com.comm.model.MessageTextExample;
//import java.util.List;
//import org.apache.ibatis.annotations.Param;
//
//public interface MessageTextDao {
//    long countByExample(MessageTextExample example);
//
//    int deleteByExample(MessageTextExample example);
//
//    int deleteByPrimaryKey(Long id);
//
//    int insert(MessageText record);
//
//    int insertSelective(MessageText record);
//
//    List<MessageText> selectByExample(MessageTextExample example);
//
//    MessageText selectByPrimaryKey(Long id);
//
//    int updateByExampleSelective(@Param("record") MessageText record, @Param("example") MessageTextExample example);
//
//    int updateByExample(@Param("record") MessageText record, @Param("example") MessageTextExample example);
//
//    int updateByPrimaryKeySelective(MessageText record);
//
//    int updateByPrimaryKey(MessageText record);
//}