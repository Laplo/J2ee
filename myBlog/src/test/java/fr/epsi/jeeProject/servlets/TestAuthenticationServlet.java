package fr.epsi.jeeProject.servlets;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestAuthenticationServlet extends BasicServletTestCaseAdapter {

    private static final Logger logger = LogManager.getLogger(TestAuthenticationServlet.class);

    @Before
    public void setUp() throws Exception {
        logger.info("Test Authentication Servlet");
        super.setUp();
         createServlet(AuthenticationServlet.class);
    }

    @Test
    public void testUniqueEmail() {
        logger.info("Test Unique Email");
        addRequestParameter("lastname","Test");
        addRequestParameter("firstname","Mock");
        addRequestParameter("email","contact@aquasys.fr");
        addRequestParameter("password","sa");
        addRequestParameter("confirm-password","sa");
        doPost();
        assertNotNull(getRequestAttribute("errorMessageSignUpEM"));
    }
}
