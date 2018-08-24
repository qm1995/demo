package com.qm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qm.mapper.MessageMapper;
import com.qm.pojo.Message;
import com.qm.pojo.MessageExample;
import com.qm.pojo.MessageExample.Criteria;

/**
 * 操作用户消息的service
 * @author qiumin
 *
 */
@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageMapper mapper;
	
	/* (non-Javadoc)
	 * @see com.qm.service.MessageService#saveMsg(com.qm.pojo.Message)
	 */
	@Override
	public void saveMsg(Message message){
		message.setSendtime(new Date());
		message.setIsread(0);
		mapper.insert(message);
		return;
	}
	
	/* (non-Javadoc)
	 * @see com.qm.service.MessageService#getMessages(java.lang.Integer, java.lang.Integer, boolean)
	 */
	@Override
	public List<Message> getMessages(Integer fromId,Integer toId,boolean isRead){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(fromId);
		ids.add(toId);
		MessageExample example = new MessageExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUidIn(ids);
		createCriteria.andFidIn(ids);
		if(!isRead){
			createCriteria.andIsreadEqualTo(0);
		}
		List<Message> messageList = mapper.selectByExample(example);
		return messageList;
	}
	
	/* (non-Javadoc)
	 * @see com.qm.service.MessageService#getNoReadMessage(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Message> getNoReadMessage(Integer fromId,Integer toId){
		
		List<Message> messageList = getMessages(fromId, toId, false);
		return messageList;
	}
}
