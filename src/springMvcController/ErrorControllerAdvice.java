package springMvcController;

import java.io.FileNotFoundException;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice//处理所有控制器的控制器
public class ErrorControllerAdvice {
	
	@ExceptionHandler(FileNotFoundException.class)//处理异常方法
	public String exception(){
		System.out.println("fileNotFound");
		return "welcome";
	}
	@ExceptionHandler(NullPointerException.class)//处理异常方法
	public String Nullexception(){
		System.out.println("Null");

		return "welcome";
	}
	
}
