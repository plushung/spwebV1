package servlets;

import java.applet.AppletContext;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import configure.Configure;
import sqltest.sqlt;

/**
 * Servlet implementation class ListUser
 */
//@Component
@WebServlet(urlPatterns={"/ListUser"})
public class ListUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	ApplicationContext context;//new AnnotationConfigApplicationContext(Configure.class);
	@Autowired
    private sqlt sq;
    /**
     * @see HttpServlet#HttpServlet()
     */

    public ListUser() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		sq=new sqlt();
		request.setAttribute("userlist", sq.getSpittle());
		request.getRequestDispatcher("/view/welcome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO 自动生成的方法存根
		super.init(config);
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		SpringBeanAutowiringSupport
				.processInjectionBasedOnServletContext(
						this, config.getServletContext());
		//使此servlet支持Autowried功能
		}

}
