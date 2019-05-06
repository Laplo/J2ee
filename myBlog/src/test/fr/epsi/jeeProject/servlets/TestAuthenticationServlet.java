package test.fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.servlets.AuthenticationServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;

import static org.junit.Assert.assertEquals;

public class TestAuthenticationServlet {

    private static final Logger logger = LogManager.getLogger(TestAuthenticationServlet.class);
    private AuthenticationServlet servlet;
//    private MockHttpServletRequest request;
//    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        servlet = new AuthenticationServlet();
//        request = ;
//        response = ;
    }
    @Test
    public void uniqueEmail() throws ServletException {
        logger.debug("Test Authentication Servlet");
//        request.addParameter("lastname","Test");
//        request.addParameter("firstname","Mock");
//        request.addParameter("email","contact@aquasys.java.fr");
//        request.addParameter("password","sa");
//        request.addParameter("confirm-password","sa");
//        servlet.doPost(resquest, response);
//        assertEquals("Un utilisateur possède déjà cet email", response.getError(0));
    }
}
