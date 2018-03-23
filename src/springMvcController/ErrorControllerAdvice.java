package springMvcController;

import java.io.FileNotFoundException;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice//�������п������Ŀ�����
public class ErrorControllerAdvice {
	
	@ExceptionHandler(FileNotFoundException.class)//�����쳣����
	public String exception(){
		System.out.println("fileNotFound");
		return "welcome";
	}
	@ExceptionHandler(NullPointerException.class)//�����쳣����
	public String Nullexception(){
		System.out.println("Null");

		return "welcome";
	}
	
}
