package cc.openhome.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
	urlPatterns = {
		"/member", "/WEB-INF/jsp/member.jsp", "/new_message", "/del_message", "/logout"
	},
	initParams = {
			@WebInitParam(name = "LOGIN_PATH", value = "/WEB-INF/jsp/index.jsp")
	}
	
)
public class AccessFilter extends HttpFilter {
	private String LOGIN_PATH;
	
	public void init() throws ServletException {
		LOGIN_PATH = getInitParameter("LOGIN_PATH");
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if (req.getSession().getAttribute("login") == null) {
			res.sendRedirect(LOGIN_PATH);
		} else {
			chain.doFilter(req, res);
		}
	}
}
