package java;

import java.fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import java.fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao;
import java.fr.epsi.jeeProject.dao.interfaces.IArticleDao;
import java.fr.epsi.jeeProject.dao.interfaces.IUtilisateurDao;
import java.fr.epsi.jeeProject.jmx.Premier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.lang.management.ManagementFactory;

@WebListener()
public class StartupListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger(StartupListener.class);

    // Public constructor is required by servlet spec
    public StartupListener() {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name;

        try {
            name = new ObjectName("java.fr.epsi.jmx:type=PremierMBean");
            Premier mbean = new Premier();

            mbs.registerMBean(mbean, name);
        } catch (MalformedObjectNameException | NullPointerException | InstanceAlreadyExistsException | NotCompliantMBeanException | MBeanRegistrationException e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        logger.debug("Lancement de l'application");
        IArticleDao articleDao = new ArticleDao();
        IUtilisateurDao utilisateurDao = new UtilisateurDao();
        try {
            articleDao.countArticles();
            utilisateurDao.countUtilisateurs();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        logger.debug("Fermeture de l'application");
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        logger.debug("Ouverture de la session");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        logger.debug("Fermeture de la session");
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
