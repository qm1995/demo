package com.qm.dwr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;

import com.qm.pojo.User;

public class DWRScriptSessionListener implements ScriptSessionListener{
	
	private Map<String,ScriptSession> SCRIPTSESSIONMAP = new HashMap<String,ScriptSession>();
	
	public void sessionCreated(ScriptSessionEvent event) {  
        WebContext webcontext = WebContextFactory.get();  
        HttpSession httpsession = webcontext.getSession();  
        ScriptSession scriptsession = event.getSession();
        scriptsession.setAttribute("user", httpsession.getAttribute("user"));
        SCRIPTSESSIONMAP.put(httpsession.getId(), scriptsession);  
        System.out.println("scriptsession is create!");  
    }  
  
    public void sessionDestroyed(ScriptSessionEvent arg0) {  
        WebContext webcontext = WebContextFactory.get();  
        HttpSession httpsession = webcontext.getSession();  
        SCRIPTSESSIONMAP.remove(httpsession.getId());  
        System.out.println("scriptsession is destoryed!!");  
          
    }  
    public Collection<ScriptSession> getAllSessions(){  
        return SCRIPTSESSIONMAP.values();  
    }  
    public ScriptSession getOneScriptSession(String str){  
         Collection<ScriptSession> sessions = getAllSessions();  
         ScriptSession session = null;  
         for(ScriptSession sess:sessions){
        	 User user = (User) sess.getAttribute("user");
        	 Integer id = user==null?0:user.getId();
        	 System.out.println("user_id="+id);
             if(str.equals(id+"")){  
                 session = sess;  
                 break;  
             }  
         }  
        return session;  
    }  
}
