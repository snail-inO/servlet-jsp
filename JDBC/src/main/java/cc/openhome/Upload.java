package cc.openhome;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/upload")
public class Upload extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Pattern fileNameRegex = Pattern.compile("filename=\"(.*)\"");
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Part part = req.getPart("file");
		String filename = getSubmittedFileName(part);
		byte[] bytes = getBytes(part);
		File file = new File();
		file.setFilename(filename);
		file.setBytes(bytes);
		file.setSavedTime(Instant.now().toEpochMilli());
		
		FileService service = (FileService) getServletContext().getAttribute("fileService");
		service.save(file);
		
		resp.sendRedirect("file.jsp");
	}
	
	private String getSubmittedFileName(Part part) {
		String header = part.getHeader("Content-Disposition");
		Matcher matcher = fileNameRegex.matcher(header);
		matcher.find();
		
		String filename = matcher.group(1);
		if (filename.contains("\\")) {
			return filename.substring(filename.lastIndexOf("\\") + 1);
		}
		
		return filename;
	}
	
	private byte[] getBytes(Part part) throws IOException {
		try (InputStream in = part.getInputStream();
				ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			return out.toByteArray();
		}
	}
}
