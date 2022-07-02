package cc.openhome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.UserService;

/**
 * Servlet implementation class Index
 */
@WebServlet(
	urlPatterns= {""},
	initParams={
		@WebInitParam(name = "INDEX_PATH", value = "/WEB-INF/jsp/index.jsp")
	}
)
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String INDEX_PATH;
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	INDEX_PATH = getInitParameter("INDEX_PATH");
    	userService = (UserService) getServletContext().getAttribute("userService");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var newest = userService.newestMessages(10);
		req.setAttribute("newest", newest);
		
		req.getRequestDispatcher(INDEX_PATH).forward(req, resp);
	}
}
