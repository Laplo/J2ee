package test.fr.epsi.jeeProject.servlets;


import java.fr.epsi.jeeProject.servlets.UtilisateurServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestUtilisateurServlet {

    private static final Logger logger = LogManager.getLogger(TestUtilisateurServlet.class);
    private UtilisateurServlet servlet;
//    private MockHttpServletRequest request;
//    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        servlet = new UtilisateurServlet();
//        request = ;
//        response = ;
    }

    @Test
    public void cannotDeleteAdminUser(){

    }
}
