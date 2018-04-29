package com.qm.service;

import java.util.List;

import com.qm.pojo.Message;

public interface MessageService {

	/**
	 * 将消息入库
	 * @param message
	 */
	void saveMsg(Message message);

	/**
	 * 根据发送方和接受方返回信息列表 
	 * @param fromId
	 * @param toId
	 * @param isRead 消息是否读取,若为true，则全部查询
	 * @return
	 */
	List<Message> getMessages(Integer fromId, Integer toId, boolean isRead);

	/**
	 * 得到未读的消息
	 * @param fromId
	 * @param toId
	 * @return
	 */
	List<Message> getNoReadMessage(Integer fromId, Integer toId);

}