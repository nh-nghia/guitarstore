package com.nhnghia.guitarstore.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUpdateUtil {
	private static final String UPLOAD_DIR = "resource/images";

	@SuppressWarnings("resource")
	public static String saveImage(HttpServletRequest request) {
		String result = "";
		try {
			String applicationPath = request.getServletContext().getRealPath("");
			Part filePart = request.getPart("image");
			
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		    String filePath = applicationPath + "/" + UPLOAD_DIR + "/" + fileName; 
		    // String filePath = applicationPath + File.separator + UPLOAD_DIR + File.separator + fileName;
	        InputStream inputStream = null;
	        OutputStream outputStream = null;
        
	        File outputFilePath = new  File(filePath);
	        inputStream = filePart.getInputStream();
	        outputStream = new FileOutputStream(outputFilePath);
	        int read = 0;
	        final byte[] bytes =  new  byte[1024];
	        while((read = inputStream.read(bytes)) != -1){
	            outputStream.write(bytes, 0, read);
	        }
	        
	        result = UPLOAD_DIR + "/" + fileName; 
	        // result = UPLOAD_DIR + File.separator + fileName;
        }catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
