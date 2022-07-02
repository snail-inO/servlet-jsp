package cc.openhome;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Photo
 */
@MultipartConfig
@WebServlet("/photo")
public class Photo extends HttpServlet {
	private final Pattern fileNameRegex = Pattern.compile("filename=\"(.*)\"");
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part photo = request.getPart("photo");
		String filename = getSubmittedFileName(photo);
		write(photo, filename);
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
	
	private void write(Part photo, String filename) throws IOException, FileNotFoundException {
		try (InputStream in = photo.getInputStream();
				OutputStream out = new FileOutputStream(String.format("G:/eclipse-workspace/%s", filename))) {
			out.write(in.readAllBytes());
		}
			
	}
}
