package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Article")
public class ArticleServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AuthenticationServlet.class);

    public ArticleServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Article servlet doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if(session.getAttribute("user_email") == null) {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        try {
            Blog article = new ArticleDao().getArticle(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("article", article);
            this.getServletContext().getRequestDispatcher("/Article.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
