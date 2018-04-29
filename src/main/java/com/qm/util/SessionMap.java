package com.qm.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

/**
 * 存储session的工具类,
 * 采用单例模式
 * @author qiumin
 *
 */
public class SessionMap {
	private static final SessionMap SESSION_MAP = new SessionMap();
	
	private static final Map<String, HttpSession> sessionMap = new ConcurrentHashMap<String, HttpSession>();
	private SessionMap(){}
	
	public static SessionMap getInstances(){
		return SESSION_MAP;
	}
	
	public static void setSession(HttpSession session){
		String id = session.getId();
		if(sessionMap.containsKey(id)){
			return;
		}
		sessionMap.put(id, session);
	}
	
	public static HttpSession getSession(String sessionId){
		return sessionMap.get(sessionId);
	}
}
