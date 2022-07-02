package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletMapping;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/mapping/*")
public class Mapping extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpServletMapping mapping = request.getHttpServletMapping();
		
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Mapping Servlet</title>");
        out.print("</head>");
        out.print("<body>");
        out.printf("%s<br>", mapping.getMappingMatch());
        out.printf("%s<br>", mapping.getMatchValue());
        out.printf(mapping.getPattern());
        out.print("</body>");
        out.print("</html>");
	}
}
