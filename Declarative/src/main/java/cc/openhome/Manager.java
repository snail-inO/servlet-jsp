package cc.openhome;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manager")
@ServletSecurity(
	value=@HttpConstraint(rolesAllowed = {"admin", "manager"}),
	httpMethodConstraints = {
		@HttpMethodConstraint(value = "GET", rolesAllowed = {"admin", "manager"}),
		@HttpMethodConstraint(value = "POST", rolesAllowed = {"admin", "manager"}),
	}
)
public class Manager extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().println("只有 admin 与 manager 才看得到");
	}
}
