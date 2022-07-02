package cc.openhome;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(
	name = "Login",
	urlPatterns = {"/login"},
	initParams = {
		@WebInitParam(name = "SUCCESS", value = "success.view"),
		@WebInitParam(name = "ERROR", value = "error.view")
	}
)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String SUCCESS_PATH;
	private String ERROR_PATH;
	
	@Override
	public void init() throws ServletException {
		SUCCESS_PATH = getInitParameter("SUCCESS");
		ERROR_PATH = getInitParameter("ERROR");
	}
       
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		String path = login(name, passwd) ? SUCCESS_PATH :ERROR_PATH;
		response.sendRedirect(path);
	}
	
	private boolean login(String name, String passwd) {
		return "caterpillar".equals(name) && "12345678".equals(passwd);
	}

}
