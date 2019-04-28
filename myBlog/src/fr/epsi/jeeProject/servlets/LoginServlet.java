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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterMap().keySet().forEach(System.out::println);
        List<Utilisateur> utilisateurs = null;

        try {
            utilisateurs = new UtilisateurDao().getUtilisateurs();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();

        boolean isIn = false;
        for (int i = 0; i < utilisateurs.size(); i++) {
            if (request.getParameter("login-password").compareTo(utilisateurs.get(i).getPassword()) == 0
                && request.getParameter("login-email").compareTo(utilisateurs.get(i).getEmail()) == 0) {
                isIn = true;
                session.setAttribute("user_isAdmin", utilisateurs.get(i).getAdmin());
                break;
            }
        }

        if (isIn == false) {
            logger.error(request.getParameter("login-email") + " tried to connect but wrong credentials");
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            session.setAttribute("user_email", request.getParameter("login-email"));
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
            request.getSession().setAttribute("articles", articleList);
            response.sendRedirect("/myEpsi/Blog");
        }
    }
}
