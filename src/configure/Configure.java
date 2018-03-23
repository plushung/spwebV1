package configure;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.jspsmart.upload.SmartUpload;
import com.sun.glass.ui.Application;

import pageI.PageItem;
import spit.Spittle;
import sqltest.sqlt;

@Component
@Configuration
@ComponentScan({"sqltest","aop"})
@ImportResource("classpath:/applicationContext.xml")//加载xml配置
@PropertySource("classpath:/mov.properties")//属性源文件
public class Configure {
	@Bean
	@RequestScope//对与springMVC来讲的，控制器的Request
	public ArrayList<PageItem> getPageAList(){
		return new ArrayList<PageItem>();
	}
	@Bean   /* 属性占位符处理器 */
	public static PropertySourcesPlaceholderConfigurer ph(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	@Bean
	public BeanPostProcessor petpp(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	@Bean /* jdbctemplate */
	public JdbcOperations temp(DataSource ds){
		return new JdbcTemplate(ds);
	}
	
	@Bean
	public SmartUpload smart(){
		SmartUpload sm=new SmartUpload();
		try {
			sm.setDeniedFilesList("bat,exe,html");
		} catch (ServletException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return sm;
	}
	
	@Bean
	@Primary//默认用该bean注入到DataSource
	public DataSource Data(){ /*  数据源     */
		System.out.println("DataSource...");
		BasicDataSource bds=new BasicDataSource();
		
		bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		bds.setUrl("jdbc:sqlserver://localhost:49183;DatabaseName=UserManager");
		bds.setMaxTotal(5);
		bds.setInitialSize(3);
		bds.setRemoveAbandonedTimeout(10000);
		bds.setPassword("123456");
		bds.setUsername("plus");
		return bds;
	}

	
	@Bean("defaultuser")
	@SessionScope
	public Spittle getSpittle(sqlt sq){
		return sq.getSpit(sq.signIn("hung", "021501199"));
	}
//	@Bean("sqlt1")
//	public sqlt getsqlt(){
//		return new sqlt();
//	}
}
