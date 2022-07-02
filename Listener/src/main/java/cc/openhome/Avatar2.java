package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Avatar2
 */

public class Avatar2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final Path AVATAR_DIR;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Avatar2(Path AVATAR_DIR) {
        this.AVATAR_DIR = AVATAR_DIR;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        
        String path = String.format("/%s", AVATAR_DIR.getFileName());
        getServletContext().getResourcePaths(path)
        	.forEach(avatar -> {
        		out.printf("<img src='%s'>%n", avatar.replaceFirst("/", ""));
        	});
        
        out.println("</body>");
        out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
