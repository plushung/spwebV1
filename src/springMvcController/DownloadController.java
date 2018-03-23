package springMvcController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.InputBuffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jspsmart.upload.SmartUpload;

import searchFile.SearchFile;
import sun.misc.Resource;

@Controller
@RequestMapping("/download/")
public class DownloadController {
	
	@Value("${moviepath}/")
	String moviepath=null;
	@RequestMapping("/{name}.{sufix}")
	public String dl(@PathVariable String name
			,@PathVariable String sufix
			,HttpServletResponse response
			) throws IOException {
		File f=SearchFile.getFile(moviepath, name+"."+sufix);
		System.out.println("cont:"+f.getName());
		response.setContentType("video/"+sufix);
		try(FileInputStream inf=new FileInputStream(f);
				BufferedInputStream bfr=new BufferedInputStream(inf);
				){
			
			byte[] b=new byte[1024*50];
			int i=0;

			do{
				i=bfr.read(b);
				response.getOutputStream().write(b,0,i);
				System.out.println(":"+i);
			}
			while(response.isCommitted()&&i!=-1);
		}catch (IOException e){
			System.out.println(e.getMessage());;
		}finally{
			
		}
		return "welcome";
	}

}
