package com.hua;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopController
 */
@WebServlet("/shop")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String count = "0";
		Optional<Cookie> cookie = Optional.ofNullable(request.getCookies())
				.flatMap(this::userCookie);
		if (cookie.isPresent()) {
			count = cookie.get().getValue();
		}
		
		String book = request.getParameter("book");
		if (book != null) {
			Integer i = Integer.parseUnsignedInt(count) + 1;
			count = i.toString();
			Cookie bookCookie = new Cookie("book" + count, URLEncoder.encode(book));
			response.addCookie(bookCookie);
		}
		
		Cookie c = new Cookie("book_count", count);
		c.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(c);
		request.setAttribute("count", count);
		request.getRequestDispatcher("shop.view").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private Optional<Cookie> userCookie(Cookie[] cookies) {
		return Stream.of(cookies).filter(cookie -> getCount(cookie)).findFirst();
	}
	
	private boolean getCount(Cookie cookie) {
		return "book_count".equals(cookie.getName());
	}
}
