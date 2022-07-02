package cc.openhome;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CompressionFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String encodings = req.getHeader("Accept-Encoding");
		if (encodings != null && encodings.contains("gzip")) {
			CompressionWrapper responseWrapper = new CompressionWrapper(res);
			responseWrapper.setHeader("Content-Encoiding", "gzip");
			
			chain.doFilter(req, responseWrapper);
			
			responseWrapper.finish();
		} else {
			chain.doFilter(req, res);
		}
	}
}
