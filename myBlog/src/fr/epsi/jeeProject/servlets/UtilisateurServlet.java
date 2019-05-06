package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Utilisateur")
public class UtilisateurServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("user_email") == null) {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if (!(boolean)session.getAttribute("user_isAdmin")) {
            this.getServletContext().getRequestDispatcher("/NotAccess.jsp").forward(request, response);
            return;
        }

        if (request.getParameter("delete") != null) {
            try {
                new UtilisateurDao().deleteUtilisateur(new Utilisateur(request.getParameter("delete")));
                response.sendRedirect("/myEpsi/Admin");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
