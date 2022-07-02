package com.hua;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String count = request.getParameter("count");
		String deleteBookNum = request.getParameter("d_book");
		
		if (count == null)
			count = "0";
		Optional<Cookie[]> cookies = Optional.ofNullable(request.getCookies());
		
		Map<String, String> books;
		if (cookies.isPresent()) {
			books = Stream.of(cookies.get()).filter(this::getBooks).collect(Collectors
					.toMap(bookNum -> bookNum.getName(),
							bookName -> URLDecoder.decode(bookName.getValue()), (b1, b2) -> b1,
							() -> new TreeMap<String, String>(this::sortBooks)));
			if (deleteBookNum != null) {
				if (books.remove(deleteBookNum) != null) {
					Integer i = Integer.parseUnsignedInt(count) - 1;
					count = i.toString();
					Cookie reduceCount = new Cookie("book_count", count);
					Cookie deleteCookie = new Cookie(deleteBookNum, "");
					reduceCount.setMaxAge(7 * 24 * 60 * 60);
					deleteCookie.setMaxAge(0);
					response.addCookie(deleteCookie);
					response.addCookie(reduceCount);
				}
			}
			request.setAttribute("books", books);
		}

		request.setAttribute("book counts", count);
		request.getRequestDispatcher("cart.view").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean getBooks(Cookie cookie) {
		return cookie.getName().matches("book{1}\\d+");
	}
	
	private int sortBooks(Object b1, Object b2) {
		Integer i1 = Integer.parseUnsignedInt(((String)b1).replaceAll("book", ""));
		Integer i2 = Integer.parseUnsignedInt(((String)b2).replaceAll("book", ""));
		
		return i1 - i2;
	}
	
}
