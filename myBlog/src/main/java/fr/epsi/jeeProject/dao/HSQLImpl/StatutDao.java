package fr.epsi.jeeProject.dao.HSQLImpl;

import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.interfaces.IStatutDao;
import fr.epsi.jeeProject.servlets.ArticleServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class StatutDao implements IStatutDao {

    private static final Logger logger = LogManager.getLogger(StatutDao.class);

    @Override
    public Statut getStatut(Integer id) throws ClassNotFoundException {
        Statut statut = new Statut();
        statut.setId(id);
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003","SA","");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM STATUT WHERE ID = ?");
            ps.setInt(1, id);
            logger.debug("Requête DataBase : " + ps);
            ResultSet rs = ps.executeQuery();
            rs.next();
            statut.setDescription(rs.getString("title"));
            rs.close();
            con.close();
        } catch (SQLException e) {
            statut = null;
            logger.error("Error while getting statut " + id, e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error while closing connection");
            }
        }
        return statut;
    }

    @Override
    public List<Statut> getAllStatut() {
        return null;
    }
}
