package com.jfx.love.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Controller
public class AuthImage extends HttpServlet{

	private Logger logger = LoggerFactory.getLogger(AuthImage.class);

	private static final long serialVersionUID = 1L;
	Font mFont = new Font("Courier",Font.ITALIC, 16);
	/**
	 *生成随机颜色
	 * @param j 
	 * @param i 
	 *
	 */
	public Color getRendColor(int i, int j) {
		Random random = new Random();
		int r = random.nextInt(150)+105;
		int g = random.nextInt(150)+105;
		int b = random.nextInt(150)+105;
		return new Color(r,g,b);
	}
	@GetMapping("/check")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setHeader("Pragma", "No-cache");
	resp.setHeader("Cache-Control", "no-cache");
	resp.setDateHeader("Expires", 0);
	resp.setContentType("image/jpeg");
		
		BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRendColor(200, 250));
		g.fillRect(1, 1, 79, 19);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, 79, 19);
		g.setFont(mFont);
		g.setColor(getRendColor(160, 200));
		for (int i = 0; i < 4; i++) {
			int x = random.nextInt(79);
			int y = random.nextInt(19);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			g.drawLine(x, y, x1, y1);
		}
		for (int i = 0; i < 3; i++) {
			int x = random.nextInt(79);
			int y = random.nextInt(19);
			int x1 = random.nextInt(12) + 1;
			int y1 = random.nextInt(6) + 1;
			g.drawLine(x, y, -x1, -y1);
		}
		String sRand="";
		for (int i = 0; i < 4; i++) {
			String tmp = getRandomChar();
			sRand+=tmp;
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(tmp, 15*i+10, 15);
		}
		
		HttpSession session = req.getSession(true);
		session.setAttribute("randomImageStr", sRand.toLowerCase());
//		System.err.println("图片验证码："+sRand.toLowerCase());
		logger.info("图片验证码："+sRand.toLowerCase());
		g.dispose();
		ImageIO.write(image, "JPEG", resp.getOutputStream());
	}

	public static String getRandomChar() {
		Random random = new Random();
		int rand = (int) Math.round(Math.random()*2);
		long itmp=0;
		//ctmp初始值是空格
		char ctmp='\u0000';
		switch (rand) {
		case 1:
			itmp = random.nextInt(25)+65;
			ctmp = (char)itmp;
			return String.valueOf(ctmp);
		case 2:
			itmp = random.nextInt(25)+97;
			ctmp = (char)itmp;
			return String.valueOf(ctmp);
		default:
			int int1 = random.nextInt(9);
			return String.valueOf(int1);
		}
	}

}
