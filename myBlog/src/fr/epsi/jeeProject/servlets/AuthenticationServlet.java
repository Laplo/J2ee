package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Authentication")
public class AuthenticationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(TestServlet.class);

    public AuthenticationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterMap().keySet().forEach(System.out::println);

        Utilisateur user = new Utilisateur();
        user.setAdmin(false);
        user.setNom(request.getParameter("lastname") + ' ' + request.getParameter("firstname"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        try {
            new UtilisateurDao().createUtilisateur(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
