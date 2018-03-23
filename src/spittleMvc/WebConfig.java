package spittleMvc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cglib.core.internal.CustomizerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import pageI.PageItem;
import sqltest.sqlt;

@Configuration
@EnableWebMvc//����spring MVC
@ComponentScan("springMvcController")//����spring mvc�������ģ�Ҫɨ�������
public class WebConfig 
		extends WebMvcConfigurerAdapter{

/*	@Bean
	public DataSource Data(){
		System.out.println("DataSource...");
		BasicDataSource bds=new BasicDataSource();
		
		bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		bds.setUrl("jdbc:sqlserver://localhost:49183;DatabaseName=UserManager");
		bds.setMaxTotal(15);
		bds.setInitialSize(3);
		bds.setPassword("123456");
		bds.setUsername("plus");
		return bds;
	}*/
	
/*	@Bean
	public sqlt sqsq(){
		return new sqlt();
	}*/

	@Bean /*	��ͼ������	 ����controller���ص���ͼ����Ӧʵ�ʵ���ͼ*/
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver=
				new InternalResourceViewResolver();
		resolver.setPrefix("/view/");
		resolver.setSuffix(".jsp");
	
		resolver.setExposeContextBeansAsAttributes(true);
		
		return resolver;
	}
	@Override/* 	��̬��Դ������handlerMappign	 */
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		// TODO �Զ����ɵķ������
		super.configureDefaultServletHandling(configurer);
		configurer.enable();//����̬��Դ��html��
	}
	
	
	
//	@Bean//����Э�̽�����+��������Э�̹�����
//	public ViewResolver cnviewResover(ContentNegotiationManager cnm){
//		ContentNegotiatingViewResolver cnv=
//				new ContentNegotiatingViewResolver();
//		cnv.setContentNegotiationManager(cnm);
//		return cnv;
//	}
//	
//	@Override//Э����Դ����----������дɶ������
//	public void configureContentNegotiation(
//			ContentNegotiationConfigurer configurer) {
//		// TODO �Զ����ɵķ������
//		super.configureContentNegotiation(configurer);
//		configurer.defaultContentType(MediaType.APPLICATION_JSON);
//	}
//	
//	@Bean
//	public ViewResolver beanresover(){
//		return new BeanNameViewResolver();
//	}
//	
//	@Bean
//	public View Spittle(){
//		return new MappingJackson2JsonView();
//	}
	
}
