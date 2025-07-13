package it.unisa.control;
import java.io.File;
import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(name = "/FileUploadServlet", urlPatterns = { "/fileupload" }, initParams = {
		@WebInitParam(name = "file-upload", value = "tmpDir") })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
														// temporarily stored on disk
		maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
		maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class FileUploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR = "";

	public void init() {
		// Get the file location where it would be stored
		SAVE_DIR = getServletConfig().getInitParameter("file-upload");
	}

	public FileUploadServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = request.getServletContext().getRealPath("") + SAVE_DIR;
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String message = "upload =\n";
		if (request.getParts() != null) {
			for (Part part : request.getParts()) {
				String fileName = part.getSubmittedFileName();
				if (fileName != null && !fileName.equals("")) {
					part.write(savePath + File.separator + fileName);
					System.out.println(savePath + File.separator + fileName);
					message = message + " " + fileName + "\n";
				} else {
					request.setAttribute("error", "Errore: Bisogna selezionare almeno un file");
				}
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}

