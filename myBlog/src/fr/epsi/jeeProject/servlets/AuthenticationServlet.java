package fr.epsi.jeeProject.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(TestServlet.class);

    public AuthenticationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        request.getParameterMap().keySet().forEach(System.out::println);

        logger.info("oui");
        logger.info(nom);
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
