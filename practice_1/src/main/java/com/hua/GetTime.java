package com.hua;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetTime
 */
@WebServlet("/GetTime")
public class GetTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTime() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		Date date = new Date(request.getDateHeader(getServletName()));
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Get Time</title>");
		out.print("</head>");
		out.print("<body>");
		out.println(LocalDateTime.now() + "<br>");
		out.println(request.getQueryString()+ "<br>");
		out.println(request.getRemoteAddr());
		out.print("</body>");
		out.print("</html>");
		
	}

}
