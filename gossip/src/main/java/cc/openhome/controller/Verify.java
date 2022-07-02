package cc.openhome.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.UserService;

@WebServlet(
	urlPatterns =  {"/verify"},
	initParams = {@WebInitParam(name = "VERIFY_PATH", value = "/WEB_INF/jsp/verify.jsp")}
)
public class Verify extends HttpServlet {
	private String VERIFY_PATH;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		VERIFY_PATH = getInitParameter("VERIFY_PATH");
		userService = (UserService) getServletContext().getAttribute("userService");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var email = req.getParameter("email");
		var token = req.getParameter("token");
		
		req.setAttribute("acct", userService.verify(email, token));
		req.getRequestDispatcher(VERIFY_PATH).forward(req, resp);
	}
}
