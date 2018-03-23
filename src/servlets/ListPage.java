package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pageI.PageItem;
import sqltest.sqlt;

/**
 * Servlet implementation class ListPage
 */
@WebServlet(urlPatterns="/ListPage",displayName="helolol")
public class ListPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   @Autowired
       private sqlt sq=null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPage() {
        super();
        System.out.println("initpage");
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		SpringBeanAutowiringSupport
		.processInjectionBasedOnServletContext(
				this, config.getServletContext());
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("application/video");

	    ArrayList<PageItem> plis=new ArrayList<PageItem>();
	    System.out.println("loging....");
		plis.addAll(sq.allPage());
		request.setAttribute("pagelist", plis);

		request.getRequestDispatcher("/view/welcome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
