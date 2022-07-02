package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Questionnaire
 */
@WebServlet("/questionnaire")
public class Questionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Questionnaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("</head>");
        out.println("<body>");
        
        String page = request.getParameter("page");
        out.println("<form action='questionnaire' method='post'>");
        
        if ("page2".equals(page)) {
        	page2(request, out);
        } else if ("finish".equals(page)) {
        	page3(request, out);
        } else {
        	page1(out);
        }
        
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
	}
	
	private void page1(PrintWriter out) {
		out.println("問題一：<input type='text' name='p1q1'><br>");
        out.println("問題二：<input type='text' name='p1q2'><br>");
        out.println("<input type='submit' name='page' value='page2'>");
	}
	
	private void page2(HttpServletRequest request, PrintWriter out) {
		String p1q1 = request.getParameter("p1q1");
        String p1q2 = request.getParameter("p1q2");
        request.getSession().setAttribute("p1q1", p1q1);
        request.getSession().setAttribute("p1q2", p1q2);
        out.println("問題三：<input type='text' name='p2q1'><br>");
        out.println("<input type='submit' name='page' value='finish'>");
	}
	
	private void page3(HttpServletRequest request, PrintWriter out) {
		out.println(request.getSession().getAttribute("p1q1") + "<br>");
        out.println(request.getSession().getAttribute("p1q2") + "<br>");
        out.println(request.getParameter("p2q1") + "<br>");
	}

}
