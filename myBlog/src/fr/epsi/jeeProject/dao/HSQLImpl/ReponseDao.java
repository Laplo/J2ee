package fr.epsi.jeeProject.dao.HSQLImpl;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.interfaces.IReponseDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReponseDao implements IReponseDao {
    private static final Logger logger = LogManager.getLogger(ReponseDao.class);

    @Override
    public Integer countReponseByBlog(Blog blog) throws ClassNotFoundException {
        Connection con = null;
        int count;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM BLOG_COMMENTAIRES WHERE BLOG_ID = ?");
            ps.setInt(1,blog.getId());
            logger.debug("Requête DataBase : " + ps);
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            rs.close();
            con.close();
        } catch (SQLException e) {
            count = -1;
            logger.error("Error while counting comments of a blog " + blog.getTitre(),e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error while closing connection");
            }
        }
        return count;
    }

    @Override
    public List<Reponse> getAllReponseByBlog(Blog blog) throws ClassNotFoundException {
        Connection con = null;
        List<Reponse> reponseList = new ArrayList<>();
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM BLOG_COMMENTAIRES WHERE BLOG_ID = ?");
            ps.setInt(1,blog.getId());
            logger.debug("Requête DataBase : " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reponse reponse = createReponse(rs);
                reponseList.add(reponse);
            }
        } catch (SQLException e){
            reponseList = null;
            logger.error("Error while getting comments of a blog " + blog.getTitre(), e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error while closing connection");
            }
        }
        return reponseList;
    }

    @Override
    public Reponse getReponse(Integer id) {
        return null;
    }

    @Override
    public List<Reponse> getReponses(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public void createReponse(Reponse reponse) throws ClassNotFoundException {
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("INSERT INTO BLOG_COMMENTAIRES (COMMENTAIRE, EMAIL, DATE_CREATION, BLOG_ID) " +
                    "VALUES (?,?,?,?)");
            ps.setString(1, reponse.getCommentaire());
            ps.setString(2, reponse.getBlogger().getEmail());
            ps.setDate(3, reponse.getPublication());
            ps.setInt(4, reponse.getBlog().getId());
            logger.debug("Requête DataBase : " + ps);
            if (ps.executeUpdate() == 1) {
                logger.info("Commentaire sur le blog" + reponse.getBlog().getId() + " correctement inseré dans la base.");
            } else {
                logger.error("Erreur pendant l'insertion du commentaire sur le blog " + reponse.getBlog().getId() + ".");
            }
            con.close();
        } catch (SQLException e) {
            logger.error("Error while creating comments of a blog " + reponse.getBlog().getId(),e);
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
    public Reponse createReponse(ResultSet rs) throws SQLException, ClassNotFoundException {
        Reponse myReponse = new Reponse();
        myReponse.setBlogger(new UtilisateurDao().getUtilisateur(rs.getString("email")));
        myReponse.setCommentaire(rs.getString("commentaire"));
        myReponse.setPublication(rs.getDate("date_creation"));
        myReponse.setBlog(new ArticleDao().getArticle(rs.getInt("blog_id")));
        return myReponse;
    }
}
