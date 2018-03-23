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
@EnableWebMvc//启用spring MVC
@ComponentScan("springMvcController")//这是spring mvc的上下文，要扫描控制器
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

	@Bean /*	视图解析器	 根据controller返回的视图名响应实际的视图*/
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver=
				new InternalResourceViewResolver();
		resolver.setPrefix("/view/");
		resolver.setSuffix(".jsp");
	
		resolver.setExposeContextBeansAsAttributes(true);
		
		return resolver;
	}
	@Override/* 	静态资源解析器handlerMappign	 */
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		// TODO 自动生成的方法存根
		super.configureDefaultServletHandling(configurer);
		configurer.enable();//处理静态资源如html等
	}
	
	
	
//	@Bean//内容协商解析器+设置内容协商管理器
//	public ViewResolver cnviewResover(ContentNegotiationManager cnm){
//		ContentNegotiatingViewResolver cnv=
//				new ContentNegotiatingViewResolver();
//		cnv.setContentNegotiationManager(cnm);
//		return cnv;
//	}
//	
//	@Override//协商资源配置----》内容写啥管理器
//	public void configureContentNegotiation(
//			ContentNegotiationConfigurer configurer) {
//		// TODO 自动生成的方法存根
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
