package com.hua;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class True
 */
@WebServlet("/result.html")
public class True extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public True() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		if ((req.getParameter("user") != null && req.getParameter("password") != null) &&
				(req.getParameter("user").equals("caterpillar") &&
				req.getParameter("password").equals("12345678")))
			out.print("<title>Login Succeeded</title>");
		else
			out.print("<title>Login Failed</title>");
		
		out.print("</head>");
		out.print("<body>");
		if ((req.getParameter("user") != null && req.getParameter("password") != null) &&
				(req.getParameter("user").equals("caterpillar") &&
				req.getParameter("password").equals("12345678")))
			out.print("<h1>Login Succeeded</h1>");
		else
			out.print("<h1>Login Failed</h1>");
		out.print("<p><a href=\"/practice_1/Login\">Return</a></p>");
		out.print("</body>");
		out.print("</html>");
	}
}
