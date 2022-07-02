package cc.openhome;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User
 */
@WebServlet("/user")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Optional<Cookie> userCookie = Optional.ofNullable(request.getCookies())
				.flatMap(this::userCookie);
		if (userCookie.isPresent()) {
			Cookie cookie = userCookie.get();
			request.setAttribute(cookie.getName(), cookie.getValue());
			request.getRequestDispatcher("user.view").forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Optional<Cookie> userCookie(Cookie[] cookies) {
		return Stream.of(cookies)
				.filter(cookie -> check(cookie))
				.findFirst();
	}
	
	private boolean check(Cookie cookie) {
		return "user".equals(cookie.getName()) && "caterpillar".equals(cookie.getValue());
	}

}
