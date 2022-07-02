package cc.openhome;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class TimieIt extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		long begin = current();
		
		chain.doFilter(req, res);
		
		getServletContext().log(String.format("Request process in %d milliseconds",
				current() - begin));
		
	}
	
	private long current() {
		return System.currentTimeMillis();
	}
}
