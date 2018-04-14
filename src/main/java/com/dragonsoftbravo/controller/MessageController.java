package com.dragonsoftbravo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dragonsoftbravo.pojo.Message;
import com.dragonsoftbravo.service.MessageService;
import com.dragonsoftbravo.util.ActionResult;

/**
 * 消息message的控制器
 * @author qiumin
 *
 */
@Controller
@RequestMapping("/user/message")
public class MessageController {
	
	
	@Autowired
	private MessageService meServiceImpl;
	
	/**
	 * 得到所有的消息
	 * @param uid
	 * @param fid
	 * @return
	 */
	@RequestMapping(value="/getAll",method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getAllMessage(@RequestParam("fromUser") Integer uid,
									  @RequestParam("toUser") Integer fid){
		List<Message> messages = meServiceImpl.getMessages(uid, fid,true);
		return ActionResult.Ok(messages);
	}
	
	
	/**
	 * 得到未读的消息
	 * @param uid
	 * @param fid
	 * @return
	 */
	@RequestMapping(value="/getNotRead",method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getNotRead(@RequestParam("fromUser") Integer uid,
									  @RequestParam("toUser") Integer fid){
		List<Message> message = meServiceImpl.getNoReadMessage(uid, fid);
		return ActionResult.Ok(message);
	}
	
	
	/**
	 * 保存消息
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public ActionResult sendMessage(Message message){
		meServiceImpl.saveMsg(message);
		return ActionResult.Ok(message);
	}
}
