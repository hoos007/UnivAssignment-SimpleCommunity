package com.wp.termproject;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext context = sce.getServletContext();
    	
    	DBConnectionInfo dbInfo = new DBConnectionInfo();
    	dbInfo.setJdbcDriverName(context.getInitParameter("oracle_jdbc_driver"));
    	dbInfo.setUrl(context.getInitParameter("db_url"));
    	dbInfo.setUserid(context.getInitParameter("db_userid"));
    	dbInfo.setPassword(context.getInitParameter("db_password"));
    	
    	String daouClassName = context.getInitParameter("dao_class1");
    	String daonClassName = context.getInitParameter("dao_class2");
    	try {
			Class clazz = Class.forName(daouClassName);
			userinfoDAO daou = (userinfoDAO) clazz.getConstructor(DBConnectionInfo.class).newInstance(dbInfo);
			context.setAttribute("daou", daou);
			clazz = Class.forName(daonClassName);
			noticeboardDAO daon = (noticeboardDAO) clazz.getConstructor(DBConnectionInfo.class).newInstance(dbInfo);
			context.setAttribute("daon", daon);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
