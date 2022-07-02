package cc.openhome;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		
		String page;
		if ("caterpillar".equals(name) && "12345678".equals(passwd)) {
			processCookie(request, response);
			page = "user";
		} else {
			page = "login.html";
		}
		response.sendRedirect(page);
	}
	
	private static final int ONE_WEEK = 7 * 24 * 60 * 60;
	
	private void processCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("user", "caterpillar");
		if ("true".equals(request.getParameter("auto"))) {
			cookie.setMaxAge(ONE_WEEK);
		}
		response.addCookie(cookie);
	}

}
