package com.hua;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopView
 */
@WebServlet("/shop.view")
public class ShopView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String page = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset='UTF-8'>"
				+ "<title>Shop</title>"
				+ "</head>"
				+ "<body>"
				+ "<p><a href='cart?count=%s'>You have %s books in your cart</a></p><br><br>"
				+ "<p><a href='shop?book=The Maid'>Buy 'The Maid'</a></p>"
				+ "<p><a href='shop?book=Invisible'>Buy 'Invisible'</a></p>"
				+ "<p><a href='shop?book=It Ends with Us'>Buy 'It Ends with Us'</a></p>"
				+ "</body>"
				+ "</html>";
		String count = (String) request.getAttribute("count");
		out.printf(page, count, count);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
