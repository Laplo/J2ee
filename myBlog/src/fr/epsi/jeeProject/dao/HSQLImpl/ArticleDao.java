package fr.epsi.jeeProject.dao.HSQLImpl;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.dao.interfaces.IArticleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao implements IArticleDao {

    private static final Logger logger = LogManager.getLogger(ArticleDao.class);

    @Override
    public void countArticles() throws ClassNotFoundException {
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try{
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003","SA","");
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM BLOG");
            ResultSet rs = ps.executeQuery();
            logger.debug("Requête DataBase : " + ps.toString());
            rs.next();
            logger.error("Nombre de posts présent en base : " + rs.getInt(1));
        } catch (SQLException e) {
            logger.error("Error while counting articles", e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error while closing connection");
            }
        }
    }

    @Override
    public List<Blog> getArticles() throws ClassNotFoundException {
        List<Blog> articles = new ArrayList<Blog>();
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003","SA","");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM BLOG");
            ResultSet rs = ps.executeQuery();
            logger.debug("Requête DataBase : " + ps.toString());
            while (rs.next()) {
                Blog article = createArticle(rs);
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

    @Override
    public Blog getArticle(int id) throws ClassNotFoundException {
        Blog article = null;

        Connection con = null;

        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003","SA","");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM BLOG  WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            logger.debug("Requête DataBase : " + ps.toString());
            if (rs.next()) {
                article = createArticle(rs);
            }
            rs.close();
            con.close();
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

        return article;
    }

    @Override
    public void createArticle(Blog blog) throws ClassNotFoundException {
        List<Blog> articles = new ArrayList<Blog>();

        Connection con = null;

        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("INSERT INTO BLOG (TITRE, DESCRIPTION, EMAIL, DATE_CREATION, DATE_MODIFICATION, STATUT)" +
                                                            " VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, blog.getTitre());
            ps.setString(2, blog.getDescription());
            ps.setString(3, blog.getCreateur().getEmail());
            ps.setDate(4, blog.getDateCreation());
            ps.setDate(5, blog.getDateModification());
            ps.setInt(6, blog.getStatut().getId());
            logger.debug("Requête DataBase : " + ps.toString());
            if (ps.executeUpdate() == 1) {
                logger.info("Blog " + blog.getTitre() + " correctement inseré dans la base.");
            } else {
                logger.error("Erreur pendant l'insertion du blog " + blog.getTitre() + ".");
            }
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
    }

    @Override
    public void deleteArticle (int id) throws ClassNotFoundException {

        Connection con = null;

        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("DELETE FROM BLOG WHERE ID = ?");
            ps.setInt(1, id);
            logger.debug("Requête DataBase : " + ps.toString());
            if (ps.executeUpdate() == 1) {
                logger.info("Blog " + id + " correctement supprimé");
            } else {
                logger.error("Erreur pendant la suppression du blog " + id + ".");
            }
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
    }

    @Override
    public Blog createArticle(ResultSet rs) throws SQLException, ClassNotFoundException {
        Blog article = new Blog();
        article.setId(rs.getInt("id"));
        article.setDescription(rs.getString("description"));
        article.setTitre(rs.getString("titre"));
        article.setCreateur(new UtilisateurDao().getUtilisateur(rs.getString("email")));
        article.setDateCreation(rs.getDate("date_creation"));
        article.setDateModification(rs.getDate("date_modification"));
        return article;
    }
}
