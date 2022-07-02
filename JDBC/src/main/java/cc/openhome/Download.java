package cc.openhome;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class Download extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FileService fileService = (FileService) getServletContext().getAttribute("fileService");
		String id = req.getParameter("id");
		
		File file = new File();
		file.setId(Long.parseLong(id));
		file = fileService.getFile(file);
		
		String filename = fileName(req, file);
		
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
		
		OutputStream out = resp.getOutputStream();
		out.write(file.getBytes());
	}
	
	private String fileName(HttpServletRequest req, File file) throws UnsupportedEncodingException {
		String agent = req.getHeader("User-Agent");
		if (agent.contains("rv:")) {
			return URLEncoder.encode(file.getFilename(), "UTF-8");
		}
		return new String(file.getFilename().getBytes("UTF-8"), "ISO-8859-1");
	}
}
