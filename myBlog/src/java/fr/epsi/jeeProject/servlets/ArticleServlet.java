package java.fr.epsi.jeeProject.servlets;

import java.fr.epsi.jeeProject.beans.Blog;
import java.fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Article")
public class ArticleServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ArticleServlet.class);

    public ArticleServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doPost " + this.getClass().toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Execution doGet " + this.getClass().toString());
        HttpSession session = request.getSession();
        if(session.getAttribute("user_email") == null) {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("id") == null) {
            if (request.getParameter("delete") != null) {
                if (!((boolean) session.getAttribute("user_isAdmin"))) {
                    this.getServletContext().getRequestDispatcher("/NotAccess.jsp").forward(request, response);
                    return;
                }
                try {
                    new ArticleDao().deleteArticle(Integer.parseInt(request.getParameter("delete")));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("/myEpsi/Admin");
            }
        } else {
            try {
                Blog article = new ArticleDao().getArticle(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("article", article);
                this.getServletContext().getRequestDispatcher("/Article.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}