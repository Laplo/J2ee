package fr.epsi.jeeProject.servlets;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import fr.epsi.jeeProject.beans.Blog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArticleServlet extends BasicServletTestCaseAdapter {

    private static final Logger logger = LogManager.getLogger(TestArticleServlet.class);

    @Before
    public void setUp() throws Exception {
        logger.info("Test Article Servlet");
        super.setUp();
        createServlet(ArticleServlet.class);
    }

    @Test
    public void testCannotDeleteArticleWhenNoAuthor() {
        logger.info("Test Cannot Delete Article When No Author");
        setSessionAttribute("user_email", "t@t.fr");
        setSessionAttribute("user_isAdmin", false);
        addRequestParameter("id", "0");
        addRequestParameter("archiver", String.valueOf(true));
        doGet();
        Blog blog = (Blog) getRequestAttribute("article");
        assertNotEquals(3, java.util.Optional.ofNullable(blog.getStatut().getId()));
    }
}
