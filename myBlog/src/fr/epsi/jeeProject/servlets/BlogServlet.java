package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Blog")
public class BlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Blog blog = new Blog();
        blog.setTitre(request.getParameter("titre"));
        blog.setDescription(request.getParameter("description"));
        blog.setStatut(new Statut(1));
        Date date = new java.sql.Date(new java.util.Date().getTime());
        blog.setDateCreation(date);
        blog.setDateModification(date);
        List<Blog> articles = (List<Blog>)request.getSession().getAttribute("articles");
        try {
            blog.setCreateur(new UtilisateurDao().getUtilisateur((String) request.getSession().getAttribute("user_email")));
            new ArticleDao().createArticle(blog);
            articles.add(0, blog);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher("/Blog.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get Blog");
        request.setAttribute("articles", request.getSession().getAttribute("articles"));
        this.getServletContext().getRequestDispatcher("/Blog.jsp").forward(request, response);
    }
}
