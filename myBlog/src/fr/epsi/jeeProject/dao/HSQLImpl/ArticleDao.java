package fr.epsi.jeeProject.dao.HSQLImpl;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.interfaces.IArticleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao implements IArticleDao {

    private static final Logger logger = LogManager.getLogger(UtilisateurDao.class);

    @Override
    public List<Blog> getArticles() throws SQLException, ClassNotFoundException {
        List<Blog> articles = new ArrayList<Blog>();

        Connection con = null;

        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003","SA","");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM BLOG");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog article = new Blog();
                article.setDescription(rs.getString("description"));
                article.setTitre(rs.getString("titre"));
                article.setCreateur(new Utilisateur(rs.getString("email")));
                article.setDateCreation(rs.getDate("date_creation"));
                articles.add(article);
            }
            rs.close();
            con.close();
            articles.forEach(article -> System.out.println("articles dao get article : " + article.getTitre()));
        } catch (SQLException e) {
            logger.error("Error while getting articles ", e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error while closing connection");
            }
        }

        return articles;
    }
}
