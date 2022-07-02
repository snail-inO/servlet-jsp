package com.hua;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifyView
 */
@WebServlet("/verify.view")
public class VerifyView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyView() {
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
					+ "<title>Verification</title>"
					+ "</heand>"
					+ "<body>"
					+ "%s"
					+ "<img src='generate_code' alt='verification code'/><br>"
					+ "<form action='verify_code' method='get'>"
					+ "<label for='code'>Please enter the verification code: </label>"
					+ "<input type='text' id='code' name='code'>"
					+ "<input type='submit' value='Submit'>"
					+ "</form>"
					+ "</body>"
					+ "</html>";
		Boolean res = (Boolean) request.getAttribute("correct");
		String result;
		if (res == null)
			result = "";
		else if (res)
			result = "Code correct<br>";
		else
			result = "Code incorrect<br>";
		out.printf(page, result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
