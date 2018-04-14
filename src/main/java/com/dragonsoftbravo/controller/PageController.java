package com.dragonsoftbravo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author DBC
 *
 */

@Controller
public class PageController {
	
	@RequestMapping("/page/index")
	public String turnToPage(HttpServletRequest request) {
		Object attribute = request.getSession().getAttribute("user");
		if(attribute!= null){
			return "home";
		}
		return "index";
	}
	
	@RequestMapping("/user/home")
	public String turnToPage() {
		return "home";
	}
}
