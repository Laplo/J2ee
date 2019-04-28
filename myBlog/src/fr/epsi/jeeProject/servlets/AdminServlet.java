package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user_email") == null) {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        } else if (!((boolean) session.getAttribute("user_isAdmin"))) {
            this.getServletContext().getRequestDispatcher("/NotAccess.jsp").forward(request, response);
            return;
        }

        try {
            List<Utilisateur> utilisateurs = new UtilisateurDao().getNonAdminUtilisateurs();
            List<Blog> articles = new ArticleDao().getArticles();

            request.setAttribute("articles", articles);
            request.setAttribute("utilisateurs", utilisateurs);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
    }
}
