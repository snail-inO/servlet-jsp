package com.hua;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login2
 */
@WebServlet("/login2")
public class Login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> users = new HashMap<String, String>() {{
		put("caterpillar", "123456");
		put("momor", "98765");
		put("hamimi", "13579");
	}};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		
		String page = "form2.html";
		if (users.containsKey(name) && users.get(name).equals(passwd)) {
			User user = new User(name).setBrowser(request.getHeader("User-Agent")).refreshTime();
			OnlineUsers.users.add(user);
			request.getSession().setAttribute("user", name);
			page = "welcome2.view";
		}
		response.sendRedirect(page);
	}

}
