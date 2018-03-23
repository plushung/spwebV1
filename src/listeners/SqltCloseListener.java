package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import sqltest.sqlt;

/**
 * Application Lifecycle Listener implementation class SqltCloseListener
 *
 */
@WebListener
public class SqltCloseListener implements ServletContextListener {
	
//	ApplicationContext context=new AnnotationConfigApplicationContext(Configure.class);
    private sqlt sq=null;
    public sqlt getSq() {
		return sq;
	}
    @Autowired
	public void setSq(sqlt sq) {
    	System.out.println("listener sqlt");
		this.sq = sq;
	}

	/**
     * Default constructor. 
     */
    public SqltCloseListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
//    	sq.allPage();
    	System.out.println("SqltListener.....closing");
//    	sq.closeall();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("SqltListener.....init");

    }

}
