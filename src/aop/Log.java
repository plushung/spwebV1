package aop;

import org.aspectj.ajdt.internal.compiler.ast.Proceed;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Component
@Aspect		/*	定义为切面类	*/
public class Log {
	/*	定义切点	 */
	@Pointcut("execution(** springMvcController.*.*(..))")
	private void contro() {}
	@Pointcut("execution(** spittleMvc.*.getRootConfigClasses(..))")
	private void RootConfig() {}
	@Pointcut("execution(** spittleMvc.*.getServletConfigClasses(..))")
	private void webConfig() {}


	/*	环绕通知	*/
	@Around("contro()")
	public Object recoder(ProceedingJoinPoint pro) throws Throwable{
		Object o=null;
		System.out.println("start recoder....");
		o=pro.proceed();
		System.out.println("finished !");
		return o;
	}
	@Around("RootConfig()")
	public Object ListenRootConfig(ProceedingJoinPoint pro) throws Throwable{
		Object o=null;
		System.out.println("WebRootAppliacationContext.....");
		o=pro.proceed();
		System.out.println("done!");
		return o;
	}
	@Around("webConfig()")
	public Object ListenwebConfig(ProceedingJoinPoint pro) throws Throwable{
		Object o=null;
		System.out.println("SpringServletContext......");
		o=pro.proceed();
		System.out.println("done!");
		return o;
	}
	

}
