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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GenerateCode
 */
@WebServlet("/generate_code")
public class GenerateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		
		String code = new Random().ints(4, 0, 10)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining());
		
		if (request.getSession(false) != null) {
			request.changeSessionId();
		}
		request.getSession().setAttribute("number", code);
		ImageIO.write(createImage(code), "JPG", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private BufferedImage createImage(String code) {
		BufferedImage image = new BufferedImage(50, 20, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("微软雅黑", Font.ITALIC, 12));
		g.drawString(code, 10, 15);
		
		return image;
	}
}
