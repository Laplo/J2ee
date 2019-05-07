package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import fr.epsi.jeeProject.dao.HSQLImpl.ReponseDao;
import fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@WebServlet("/Article")
public class ArticleServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ArticleServlet.class);

    public ArticleServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doPost " + this.getClass().toString());
        HttpSession session = request.getSession();
        if (session.getAttribute("user_email") == null) {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        Reponse reponse = new Reponse();
        Blog article = null;
        try {
            article = new ArticleDao().getArticle((String) session.getAttribute("user_email")
                                                    , Integer.parseInt(request.getParameter("blogId")));
            reponse.setBlog(article);
            reponse.setPublication(new Date(new java.util.Date().getTime()));
            reponse.setCommentaire(request.getParameter("comment"));
            reponse.setBlogger(new UtilisateurDao().getUtilisateur(session.getAttribute("user_email").toString()));
            new ReponseDao().createReponse(reponse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/myEpsi/Article?id="+ Objects.requireNonNull(article).getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doGet " + this.getClass().toString());
        HttpSession session = request.getSession();
        if(session.getAttribute("user_email") == null) {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("id") == null) {
            if (request.getParameter("delete") != null) {
                if (!(boolean) session.getAttribute("user_isAdmin")) {
                    this.getServletContext().getRequestDispatcher("/NotAccess.jsp").forward(request, response);
                    return;
                }
                try {
                    new ArticleDao().deleteArticle(Integer.parseInt(request.getParameter("delete")));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("/myEpsi/Admin");
            }
        } else {
            try {
                Blog article = new ArticleDao().getArticle((String) session.getAttribute("user_email")
                                                            , Integer.parseInt(request.getParameter("id")));
                if (article != null) {
                    article.setNbvues(article.getNbvues() + 1);
                    article.setDateModification(new Date(new java.util.Date().getTime()));
                    new ArticleDao().updateArticle(article);
                    request.setAttribute("article", article);
                    List<Reponse> reponseList = new ReponseDao().getAllReponseByBlog(article);
                    request.setAttribute("reponseList", reponseList);
                    int countReponse = new ReponseDao().countReponseByBlog(article);
                    request.setAttribute("nbComments", countReponse);
                    this.getServletContext().getRequestDispatcher("/Article.jsp").forward(request, response);
                } else {
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
