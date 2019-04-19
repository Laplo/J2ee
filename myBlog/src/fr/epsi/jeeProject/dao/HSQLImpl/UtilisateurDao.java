package fr.epsi.jeeProject.dao.HSQLImpl;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.interfaces.IUtilisateurDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UtilisateurDao implements IUtilisateurDao {

    private static final Logger logger = LogManager.getLogger(UtilisateurDao.class);

    @Override
    public Utilisateur getUtilisateur(String email) {

        Utilisateur myUser = null;
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","d41d8cd98f00b204e9800998ecf8427e");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                myUser = new Utilisateur();
                myUser.setEmail(rs.getString(1));
                myUser.setNom(rs.getString(2));
                myUser.setPassword(rs.getString(4));
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            myUser = null;
            logger.error("Error while getting user " + email,e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error while closing connection");
            }
        }

        return myUser;
    }

    @Override
    public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "d41d8cd98f00b204e9800998ecf8427e");
            Date date = new java.sql.Date(new java.util.Date().getTime());
            PreparedStatement ps = con.prepareStatement("INSERT INTO USERS (EMAIL, NOM, DATE_CREATION, PASSWORD, IS_ADMIN) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1,utilisateur.getEmail());
            ps.setString(2,utilisateur.getNom());
            ps.setDate(3,date);
            ps.setString(4,utilisateur.getPassword());
            ps.setBoolean(5,utilisateur.getAdmin());
            if (ps.executeUpdate() == 1) {
                logger.info("Utilisateur " + utilisateur.getEmail() + " correctement inseré dans la base.");
            } else {
                logger.error("Erreur pendant l'insertion de l'utilisateur " + utilisateur.getEmail() + ".");
            }
        } catch (SQLException e) {
            logger.error("Error while getting user " + utilisateur.getEmail(),e);
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
    public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "d41d8cd98f00b204e9800998ecf8427e");
            PreparedStatement ps = con.prepareStatement("UPDATE USERS SET NOM = ?, PASSWORD = ?, IS_ADMIN = ? WHERE EMAIL = ?");
            ps.setString(1,utilisateur.getNom());
            ps.setString(2,utilisateur.getPassword());
            ps.setBoolean(3,utilisateur.getAdmin());
            ps.setString(4,utilisateur.getEmail());
            if (ps.executeUpdate() == 1) {
                logger.info("Utilisateur " + utilisateur.getEmail() + " correctement mis à jour dans la base.");
            } else {
                logger.error("Erreur pendant la mise à jour de l'utilisateur " + utilisateur.getEmail() + ".");
            }
        } catch (SQLException e) {
            logger.error("Error while getting user " + utilisateur.getEmail(),e);
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
    public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "d41d8cd98f00b204e9800998ecf8427e");
            PreparedStatement ps = con.prepareStatement("DELETE FROM USERS WHERE EMAIL = ?");
            ps.setString(1,utilisateur.getEmail());
            if (ps.executeUpdate() == 1) {
                logger.info("Utilisateur " + utilisateur.getEmail() + " correctement mis à jour dans la base.");
            } else {
                logger.error("Erreur pendant la mise à jour de l'utilisateur " + utilisateur.getEmail() + ".");
            }
        } catch (SQLException e) {
            logger.error("Error while getting user " + utilisateur.getEmail(), e);
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
}