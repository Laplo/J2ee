package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
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
import java.util.List;
import java.util.Objects;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doPost " + this.getClass().toString());
        List<Utilisateur> utilisateurs = null;

        try {
            utilisateurs = new UtilisateurDao().getUtilisateurs();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();

        boolean isIn = false;
        for (Utilisateur utilisateur : Objects.requireNonNull(utilisateurs)) {
            if (request.getParameter("login-password").compareTo(utilisateur.getPassword()) == 0
                    && request.getParameter("login-email").compareTo(utilisateur.getEmail()) == 0) {
                isIn = true;
                session.setAttribute("user_isAdmin", utilisateur.getAdmin());
                break;
            }
        }

        if (!isIn) {
            logger.error(request.getParameter("login-email") + " tried to connect but wrong credentials");
            request.setAttribute("errorMessage", "wrong credentials");
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            session.setAttribute("user_email", request.getParameter("login-email"));
            List<Blog> articleList = null;

            try {
                articleList = new ArticleDao().getArticles();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("articles", articleList);
            response.sendRedirect("/myEpsi/Blog");
        }
    }
}
