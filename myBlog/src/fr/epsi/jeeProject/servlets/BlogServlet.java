package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/Blog")
public class BlogServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(BlogServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doPost " + this.getClass().toString());
        Blog blog = new Blog();
        blog.setTitre(request.getParameter("titre"));
        blog.setDescription(request.getParameter("description"));
        blog.setStatut(new Statut(1));
        Date date = new java.sql.Date(new java.util.Date().getTime());
        blog.setDateCreation(date);
        blog.setDateModification(date);
        blog.setNbvues(0);
        List<Blog> articles = null;
        try {
            blog.setCreateur(new UtilisateurDao().getUtilisateur((String) request.getSession().getAttribute("user_email")));
            new ArticleDao().createArticle(blog);
            articles = new ArticleDao().getArticles();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher("/Blog.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doGet " + this.getClass().toString());
        try {
            request.setAttribute("articles", new ArticleDao().getArticles());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/Blog.jsp").forward(request, response);
    }
}
