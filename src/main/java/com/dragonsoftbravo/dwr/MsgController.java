package com.dragonsoftbravo.dwr;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.dragonsoftbravo.pojo.Message;
import com.dragonsoftbravo.pojo.User;


@RemoteProxy
public class MsgController {
	
	
	
	public MsgController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@RemoteMethod
	public User getUser(){
		User u = new User();
		u.setAge(15);
		u.setHobby("打球");
		u.setId(1);
		u.setUsername("张三");
		u.setSex(1);
		u.setLogindate(new Date());
		return u;
	}
	
	@RemoteMethod
	public void sendMessage(final Message msg,HttpServletRequest request){
		if(msg == null || msg.getContent() == null || msg.getContent().equals("")){
			return;
		}
		final Integer toId = msg.getFid();
		final Integer fromId = msg.getUid();
		
		ScriptSessionFilter filter = new ScriptSessionFilter() {
			
			@Override
			public boolean match(ScriptSession session) {
				User u = (User) session.getAttribute("user");
				if(u == null){
					return false;
				}
				//System.out.println("uid="+u.getId());
				return u.getId() == toId;
			}
		};
		Runnable task = new Runnable() {
			ScriptBuffer buffer = new ScriptBuffer();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				buffer.appendCall("show", msg.getContent(),fromId,new Date());
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				for(ScriptSession s:sessions){
					s.addScript(buffer);
				}
			}
		};
		Browser.withAllSessionsFiltered(filter, task);
		/*WebContext webContext = WebContextFactory.get();
		//创建脚本
		ScriptBuffer buffer = new ScriptBuffer();
		buffer.appendScript("show(");  
		buffer.appendData(msg.getContent());  
		buffer.appendScript(");");  
		//buffer.appendCall("appendMessage", msg.getContent(),fromId,new Date());
		//获取访问该页面的所有session，即访问用户
		System.out.println("当前访问的路径是："+webContext.getCurrentPage());
		//Collection<ScriptSession> sessions = ServerContextFactory.get(request.getSession().getServletContext()).getScriptSessionsByPage(webContext.getCurrentPage());
		Collection<ScriptSession> sessionsPage = webContext.getScriptSessionsByPage(webContext.getCurrentPage());
		//Collection<ScriptSession> sessionsPage = webContext.getScriptSessionsByPage("/demo/page/home.html");
		System.out.println("总共访问的人数为："+sessionsPage.size());
		HttpSession session = null;
		Set<ScriptSession> sessions = new HashSet<ScriptSession>();
		for(ScriptSession s:sessionsPage){
			String sessionId = s.getHttpSessionId();
			session = SessionMap.getInstances().getSession(sessionId);
			if(session == null){
				continue;
			}
			User user = (User) session.getAttribute("user");
			User u = (User) s.getAttribute("user");
			if(user == null){
				continue;
			}
			System.out.println(user.getId()+":"+toId);
			if(user.getId() == toId){
				sessions.add(s);
				//s.addScript(buffer);
			}
		}
		Util util = new Util(sessionsPage);
		util.addScript(buffer);*/

	}
}
