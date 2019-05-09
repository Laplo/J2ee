package fr.epsi.jeeProject.servlets;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import fr.epsi.jeeProject.beans.Blog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestBlogServlet extends BasicServletTestCaseAdapter {

    private static final Logger logger = LogManager.getLogger(TestBlogServlet.class);

    @Before
    public void setUp() throws Exception {
        logger.info("Test Blog Servlet");
        super.setUp();
        createServlet(BlogServlet.class);
    }

    @Test
    public void testCannotSeeWrongPostOfOtherUsers(){
        logger.info("Test Cannot See Wrong Post Of Other Users");
        String email = "t@t.fr";
        setSessionAttribute("user_email", email);
        setSessionAttribute("user_isAdmin", false);
        doGet();
        List<Blog> blogList = (List<Blog>) getRequestAttribute("articles");
        boolean error = false;
        for (Blog blog : blogList) {
            if (!blog.getCreateur().getEmail().equals(email) && blog.getStatut().getId() != 2) {
                error = true;
            }
        }
        assertFalse(error);
    }
}
