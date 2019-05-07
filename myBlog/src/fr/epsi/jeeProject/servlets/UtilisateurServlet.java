package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Utilisateur;
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

@WebServlet("/Utilisateur")
public class UtilisateurServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(UtilisateurServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Execution doPost " + this.getClass().toString());
        if (request.getParameter("isCreating").contains("true")){
            if (request.getParameter("name") != null
                    && request.getParameter("email") != null
                    && request.getParameter("password") != null) {
                try {
                    Utilisateur u = new Utilisateur(request.getParameter("delete"));
                    u.setAdmin(false);
                    u.setEmail(request.getParameter("email"));
                    u.setNom(request.getParameter("name"));
                    u.setPassword(request.getParameter("password"));
                    new UtilisateurDao().createUtilisateur(u);
                    response.sendRedirect("/myEpsi/Admin");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (request.getParameter("isCreating").contains("false")){
            if (request.getParameter("name") != null
                    && request.getParameter("email") != null
                    && request.getParameter("password") != null) {
                try {
                    Utilisateur u = new Utilisateur(request.getParameter("delete"));
                    u.setAdmin(false);
                    u.setEmail(request.getParameter("email"));
                    u.setNom(request.getParameter("name"));
                    u.setPassword(request.getParameter("password"));

                    List<Utilisateur> utilisateurs = new UtilisateurDao().getUtilisateurs();
                    for (Utilisateur utilisateur : utilisateurs) {
                        if (utilisateur.getEmail().compareTo(u.getEmail()) == 0
                        && utilisateur.getEmail().compareTo(request.getParameter("oldEmail")) != 0) {
                            logger.error("Un utilisateur possède déjà cet email : "+u.getEmail());
                            response.sendRedirect("/myEpsi/Admin");
                            return;
                        }
                    }
                    new UtilisateurDao().updateUtilisateur(u, request.getParameter("oldEmail"));
                    response.sendRedirect("/myEpsi/Admin");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doGet " + this.getClass().toString());
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
            logger.info("Suppression de l'utilsateur : " + request.getParameter("delete"));
            new UtilisateurDao().deleteUtilisateur(new Utilisateur(request.getParameter("delete")));
            response.sendRedirect("/myEpsi/Admin");
        }
    }
}
