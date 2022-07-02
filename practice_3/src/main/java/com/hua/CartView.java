package com.hua;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart.view")
public class CartView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		
		PrintWriter out = response.getWriter();
		var count = request.getAttribute("book counts");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Cart</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Cart</h1><br>");
		out.printf("<p>There are %s item(s) in your cart:<p>", count);
		out.println("<ul>");
		
		Map<String, String> books = (Map<String, String>) request.getAttribute("books");
		if (books != null) {
			books.forEach((bookNum, bookName) -> {
				out.printf("<li>%s: %s&nbsp&nbsp&nbsp<a href='cart?d_book=%s&count=%s'>"
						+ "Remove</a></li>", bookNum, bookName, bookNum, count);
			});
			
		}
		out.println("</ul>");
		out.println("<p><a href='shop'>Return to shop</a></p>");
		out.println("</body>");
		out.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
