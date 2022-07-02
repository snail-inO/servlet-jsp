package com.hua;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrintPicture
 */
@WebServlet("/print_picture")
public class PrintPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintPicture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String msg = request.getParameter("name");
		response.setContentType("image/jpeg");
		
		if (msg != null) {
			ImageIO.write(createImage(msg), "JPG", response.getOutputStream());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private BufferedImage createImage(String text) throws IOException{
		BufferedImage image = loadImage("G:/eclipse-workspace/practice_2/src/main/webapp/WEB-INF/Sherlock.jpg");
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.setFont(new Font("微软雅黑", Font.BOLD, 100));
		g.drawString(text, 300, 200);
		
		return image;
	}
	
	private BufferedImage loadImage(String path) throws IOException{
		BufferedImage read = null;
		
		try {
			read = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw e;
		}
		
		return read;
	}
}
