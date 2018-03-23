package spittleMvc;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittWebAppInit 
		extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected String[] getServletMappings() {
		// TODO �Զ����ɵķ������
		return new String[]{"/"};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {//Spring �ڶ��������
		//����DispatcherServlet�����ģ����÷�Χ��С��ÿ��Dispatchservlet��һ��
		/*   viewResolver controller handlerMapping  	*/
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {//Spring ���ȼ������
		//���ø������ģ����÷�Χ�ϴ�ֻ��һ��
		/*	 serveries repositories	*/
		return new Class<?>[]{RootConfig.class};	

	}
	
	@Override//����upload��multipart��Դ������multipart��һЩ����������ʹ��MultipartFile�����ļ�һ��Ҫ�������
	protected void customizeRegistration(Dynamic registration) {
		// TODO �Զ����ɵķ������
		registration.setMultipartConfig(
					new MultipartConfigElement("e:/WebUpload"));
	}
	

}
