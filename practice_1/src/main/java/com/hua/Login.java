package com.hua;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
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
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Login</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<form action=\"result.html\" target=\"_parent\" method=\"post\">");
		out.print("<label for=\"user\">User:</label><br>");
		out.print("<input type=\"text\" id=\"user\" name=\"user\"><br>");
		out.print("<label for=\"password\">Password:</label><br>");
		out.print("<input type=\"text\" id=\"password\" name=\"password\"><br><br>");
		out.print("<input type=\"submit\" value=\"submit\">");
		out.print("</form><br><br>");
		out.print("</body>");
		out.print("</html>");
	}
}
