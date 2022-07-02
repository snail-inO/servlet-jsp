package cc.openhome.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.UserService;

@WebServlet(
	urlPatterns = {"/reset_password"},
	initParams = {
		@WebInitParam(name = "RESET_PW_PATH", value = "/WEB-INF/jsp/reset_password.jsp"),
		@WebInitParam(name = "SUCCESS_PATH", value = "/WEB-INF/jsp/reset_success.jsp")
	}
)
public class ResetPassword extends HttpServlet {
	private String RESET_PW_PATH;
	private String SUCCESS_PATH;
	private String LOGIN_PATH;
	
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		RESET_PW_PATH = getInitParameter("RESET_PW_PATH");
		SUCCESS_PATH = getInitParameter("SUCCESS_PATH");
		LOGIN_PATH = getServletContext().getContextPath();
		userService = (UserService) getServletContext().getAttribute("userService");
	}
	
	private final Pattern passwdRegex = Pattern.compile("^\\w{8,16}$");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var name = req.getParameter("name");
		var email = req.getParameter("email");
		var token = req.getParameter("token");
		
		var optionalAcct = userService.accountByNameEmail(name, email);
		
		if (optionalAcct.isPresent()) {
			var acct = optionalAcct.get();
			if (acct.getEncrypt().equals(token)) {
				req.setAttribute("acct", acct);
				req.getSession().setAttribute("token", token);
				req.getRequestDispatcher(RESET_PW_PATH).forward(req, resp);
				return;
			}
		}
		
		resp.sendRedirect(LOGIN_PATH);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var token = req.getParameter("token");
		var storedToken = (String) req.getSession().getAttribute("token");
		if (storedToken == null || !storedToken.equals(token)) {
			resp.sendRedirect(LOGIN_PATH);
			return;
		}
		
		var name = req.getParameter("name");
		var email = req.getParameter("email");
		var password = req.getParameter("password");
		var password2 = req.getParameter("password2");
		
		if (!validatePassword(password, password2)) {
			var optionalAcct = userService.accountByNameEmail(name, email);
			req.setAttribute("errors", Arrays.asList("请确认密码符合格式并再度确认密码"));
			req.setAttribute("acct", optionalAcct.get());
			req.getRequestDispatcher(RESET_PW_PATH).forward(req, resp);
		} else {
			userService.resetPassword(name, password);
			req.getRequestDispatcher(SUCCESS_PATH).forward(req, resp);
		}
	}
	
	private boolean validatePassword(String password, String password2) {
		return password != null && passwdRegex.matcher(password).find() && password.equals(password2);
	}
}
