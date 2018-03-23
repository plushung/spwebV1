package springMvcController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.RemoteCall;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.connector.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import spit.Spittle;

@Controller
@RequestMapping("/upload/")
public class UploadController {
	@Autowired
	SmartUpload sm=null;
	@Value("${uploadpath}/")
	String uploadpath=null;
	
	@RequestMapping("/fileupload")
	public String upload(
			@RequestPart("file")MultipartFile data) throws IOException, ServletException, SmartUploadException {
		String ss[]=data.getOriginalFilename().split("\\\\");
		System.out.println(ss.length);
		File f=new File(uploadpath+ss[ss.length-1]);
		data.transferTo(f);
//		sm.initialize(sc.getServletConfig(), req, res);
//		sm.upload();
//		sm.save("/uploads");
		
		System.out.println("okok"+data.getName());
		return "welcome";
	}
	

}
