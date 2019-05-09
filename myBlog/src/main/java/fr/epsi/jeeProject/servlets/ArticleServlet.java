package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import fr.epsi.jeeProject.dao.HSQLImpl.ReponseDao;
import fr.epsi.jeeProject.dao.HSQLImpl.StatutDao;
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
            if (article != null) {
                if (request.getParameter("titre") != null && request.getParameter("description") != null) {
                    article.setTitre(request.getParameter("titre"));
                    article.setDescription(request.getParameter("description"));
                    article.setDateModification(new Date(new java.util.Date().getTime()));
                    new ArticleDao().updateArticle(article);
                } else {
                    reponse.setBlog(article);
                    reponse.setPublication(new Date(new java.util.Date().getTime()));
                    reponse.setCommentaire(request.getParameter("comment"));
                    reponse.setBlogger(new UtilisateurDao().getUtilisateur(session.getAttribute("user_email").toString()));
                    new ReponseDao().createReponse(reponse);
                }
            } else {
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/myEpsi/Article?id="+ Objects.requireNonNull(article).getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doGet " + this.getClass().toString());
        HttpSession session = request.getSession();
        if (session.getAttribute("user_email") == null) {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("id") != null) {
            try {
                Blog article = new ArticleDao().getArticle(session.getAttribute("user_email").toString()
                        , Integer.parseInt(request.getParameter("id")));
                if (article != null) {
                    if (article.getCreateur().getEmail().equals(session.getAttribute("user_email").toString())) {
                        if (request.getParameter("publier") != null) {
                            article.setStatut(new StatutDao().getStatut(2));
                            article.setDateModification(new Date(new java.util.Date().getTime()));
                            new ArticleDao().updateArticle(article);
                            request.setAttribute("article", article);
                            response.sendRedirect("/myEpsi/Article?id=" + article.getId());
                        } else if (request.getParameter("annuler") != null) {
                            article.setStatut(new StatutDao().getStatut(4));
                            article.setDateModification(new Date(new java.util.Date().getTime()));
                            new ArticleDao().updateArticle(article);
                            request.setAttribute("article", article);
                            response.sendRedirect("/myEpsi/Article?id=" + article.getId());
                        } else if (request.getParameter("archiver") != null) {
                            article.setStatut(new StatutDao().getStatut(3));
                            article.setDateModification(new Date(new java.util.Date().getTime()));
                            new ArticleDao().updateArticle(article);
                            request.setAttribute("article", article);
                            response.sendRedirect("/myEpsi/Article?id=" + article.getId());
                        }
                        else {
                            redirectArticle(request, response, article);
                        }
                    } else {
                        article.setNbvues(article.getNbvues() + 1);
                        new ArticleDao().updateArticle(article);
                        redirectArticle(request, response, article);
                    }
                } else {
                    response.sendRedirect("/myEpsi/Blog");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void redirectArticle(HttpServletRequest request, HttpServletResponse response, Blog article) throws ClassNotFoundException, ServletException, IOException {
        request.setAttribute("article", article);
        List<Reponse> reponseList = new ReponseDao().getAllReponseByBlog(article);
        request.setAttribute("reponseList", reponseList);
        int countReponse = new ReponseDao().countReponseByBlog(article);
        request.setAttribute("nbComments", countReponse);
        this.getServletContext().getRequestDispatcher("/Article.jsp").forward(request, response);
    }
}
