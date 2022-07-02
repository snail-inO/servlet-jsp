package com.hua;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NumberPicture
 */
@WebServlet("/random_number")
public class NumberPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NumberPicture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		
		String number = new Random().ints(4, 0, 10)
									.mapToObj(String::valueOf)
									.collect(Collectors.joining());
		ImageIO.write(createImage(number), "JPG", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private BufferedImage createImage(String text) {
		BufferedImage image = new BufferedImage(50, 20, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.setFont(new Font("微软雅黑", Font.ITALIC, 13));
		g.drawString(text, 10, 15);
		
		return image;
	}
}
