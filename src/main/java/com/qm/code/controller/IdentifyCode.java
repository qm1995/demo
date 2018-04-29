package com.qm.code.controller;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qm.util.ActionResult;


/**
 * 验证码
 * @author qiumin
 *
 */
@Controller
public class IdentifyCode {
	
	private static final int DEFAULT_WIDTH = 100;
	private static final int DEFAULT_HEIGHT = 34;
	
	private static final String CODESTR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@RequestMapping("/login/getCodeImage")
	@ResponseBody
	public void getCode(HttpServletRequest request,HttpServletResponse response){
		//设置响应类型，为图片类型
		response.setContentType("image/jpeg;charset=utf-8");
		//作用是将生成的验证码存入session中
		HttpSession session = request.getSession();
		
		int width = DEFAULT_WIDTH;
		int height = DEFAULT_HEIGHT;
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		
		//创建画板
		Graphics graphics = bufferedImage.getGraphics();
		
		graphics.drawRect(0, 0, width, height);
		graphics.setColor(Color.YELLOW);
		graphics.fillRect(0, 0, width-2, height-2);
		
		Random random = new Random();
		
		for(int i=1; i<=120; i++)
		{
			graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
		}
		graphics.setFont(new Font("宋体",Font.BOLD,30));
		
		
		String code = drawRandomStr((Graphics2D)graphics);
		session.setAttribute("code", code.toString());
		
		// 发头控制浏览器不要缓存  
        response.setDateHeader("expries", -1);  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragma", "no-cache");  
		
		try {
			ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String drawRandomStr(Graphics2D g){
		StringBuilder code = new StringBuilder();
		int size = CODESTR.length();
		int x = 5;
		for(int i = 0; i < 4; i++){
			// 生成0-255的随机数  
            int R = new Random().nextInt(256);  
            int G = new Random().nextInt(256);  
            int B = new Random().nextInt(256);  
            // 设置每个随机数的颜色  
            g.setColor(new Color(R, G, B));
			int index = new Random().nextInt(size);
			code.append(CODESTR.charAt(index));
			
			// 设置字体旋转角度  
            int degree = new Random().nextInt() % 30;  
			
			// 正向角度  
			g.rotate(degree * Math.PI / 180, x, 20);  
			g.drawString(CODESTR.charAt(index)+"", x, 25);
            // 反向角度  
			g.rotate(-degree * Math.PI / 180, x, 20);  
            x += 20;  
		}
		return code.toString();
	}
	
	@RequestMapping("/login/getCodeNum")
	@ResponseBody
	public String getCodeNum(HttpServletRequest request){
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		
		return code;
	}
}
