package spittleMvc;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittWebAppInit 
		extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected String[] getServletMappings() {
		// TODO 自动生成的方法存根
		return new String[]{"/"};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {//Spring 第二加载这个
		//配置DispatcherServlet上下文，作用范围较小，每个Dispatchservlet有一个
		/*   viewResolver controller handlerMapping  	*/
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {//Spring 首先加载这个
		//配置根上下文，作用范围较大，只有一个
		/*	 serveries repositories	*/
		return new Class<?>[]{RootConfig.class};	

	}
	
	@Override//处理upload的multipart资源，设置multipart的一些限制条件，使用MultipartFile接受文件一定要配置这个
	protected void customizeRegistration(Dynamic registration) {
		// TODO 自动生成的方法存根
		registration.setMultipartConfig(
					new MultipartConfigElement("e:/WebUpload"));
	}
	

}
