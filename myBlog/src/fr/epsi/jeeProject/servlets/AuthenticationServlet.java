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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Authentication")
public class AuthenticationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(AuthenticationServlet.class);

    public AuthenticationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doPost " + this.getClass().toString());
        request.getParameterMap().keySet().forEach(System.out::println);

        Utilisateur user = new Utilisateur();
        user.setAdmin(false);
        user.setNom(request.getParameter("lastname") + ' ' + request.getParameter("firstname"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        try {
            if (user.getPassword().compareTo(request.getParameter("confirm-password")) != 0) {
                logger.error("les mots de passes ne correspondent pas");
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }

            List<Utilisateur> utilisateurs = new UtilisateurDao().getUtilisateurs();
            for (Utilisateur utilisateur : utilisateurs) {
                if (utilisateur.getEmail().compareTo(user.getEmail()) == 0) {
                    logger.error("Un utilisateur possède déjà cet email");
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }
            }

            new UtilisateurDao().createUtilisateur(user);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
