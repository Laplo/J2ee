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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(TestServlet.class);

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> articleList = null;
        try {
            articleList = new ArticleDao().getArticles();
            articleList.forEach(article -> {
                System.out.println("authentication get article " + article.getTitre());
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("articles", articleList);
        this.getServletContext().getRequestDispatcher("/Blog.jsp").forward(request, response);
    }
}
