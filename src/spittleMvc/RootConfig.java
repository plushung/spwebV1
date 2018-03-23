package spittleMvc;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import sqltest.sqlt;

@Configuration
@ComponentScan(basePackages={"configure"},//��root�µõ���config�������web���������У�ֱ��Ӧ�ùرա�
		excludeFilters={
				@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)
		})
public class RootConfig {
	
	
//	@Bean("sqltimp")
//	public sqlt getSqlt(){
//		return new sqlt();
//	}
	
}
