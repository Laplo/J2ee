package fr.epsi.jeeProject.servlets;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import fr.epsi.jeeProject.beans.Utilisateur;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUtilisateurServlet extends BasicServletTestCaseAdapter {

    private static final Logger logger = LogManager.getLogger(TestUtilisateurServlet.class);

    @Before
    public void setUp() throws Exception {
        logger.info("Test Utilisateur Servlet");
        super.setUp();
        createServlet(UtilisateurServlet.class);
    }

    @Test
    public void testCannotDeleteAdminUser(){
        logger.info("Test Cannot Delete Admin User");
        setSessionAttribute("user_email", "contact@aquasys.fr");
        setSessionAttribute("user_isAdmin", true);
        addRequestParameter("delete", "contact@aquasys.fr");
        doGet();
        assertTrue((Boolean) getRequestAttribute("resultDelete"));
    }
}
