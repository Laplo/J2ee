package fr.epsi.jeeProject.dao.HSQLImpl;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.interfaces.IUtilisateurDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDao implements IUtilisateurDao {

    private static final Logger logger = LogManager.getLogger(UtilisateurDao.class);

    @Override
    public void countUtilisateurs() throws ClassNotFoundException {
        Connection con;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM USERS");
            logger.debug("Requête DataBase : " + ps);
            ResultSet rs = ps.executeQuery();
            rs.next();
            logger.error("Nombre d'utilisateurs présent en base : " + rs.getInt(1));
        } catch (SQLException e) {
            logger.error("Error while counting users ", e);
        }
    }

    @Override
    public List<Utilisateur> getUtilisateurs() throws ClassNotFoundException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS");
            logger.debug("Requête DataBase : " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur utilisateur = createUtilisateur(rs);
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            logger.error("Error while getting users ", e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error while closing connection");
            }
        }
        return utilisateurs;
    }

    @Override
    public List<Utilisateur> getNonAdminUtilisateurs() throws ClassNotFoundException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE IS_ADMIN = false");
            logger.debug("Requête DataBase : " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur utilisateur = createUtilisateur(rs);
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            logger.error("Error while getting users ", e);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                logger.warn("Error while closing connection");
            }
        }
        return utilisateurs;
    }

    @Override
    public Utilisateur getUtilisateur(String email) throws ClassNotFoundException {
        Utilisateur myUser = null;
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003","SA","");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE email = ?");
            ps.setString(1, email);
            logger.debug("Requête DataBase : " + ps);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                myUser = createUtilisateur(rs);
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
    public void createUtilisateur(Utilisateur utilisateur) throws ClassNotFoundException {
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            Date date = new java.sql.Date(new java.util.Date().getTime());
            PreparedStatement ps = con.prepareStatement("INSERT INTO USERS (EMAIL, NOM, DATE_CREATION, PASSWORD, IS_ADMIN) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1,utilisateur.getEmail());
            ps.setString(2,utilisateur.getNom());
            ps.setDate(3,date);
            ps.setString(4,utilisateur.getPassword());
            ps.setBoolean(5,utilisateur.getAdmin());
            logger.debug("Requête DataBase : " + ps);
            if (ps.executeUpdate() == 1) {
                logger.info("Utilisateur " + utilisateur.getEmail() + " correctement inseré dans la base.");
            } else {
                logger.error("Erreur pendant l'insertion de l'utilisateur " + utilisateur.getEmail() + ".");
            }
            con.close();
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
    public void updateUtilisateur(Utilisateur utilisateur, String oldEmail) throws ClassNotFoundException {
        if (oldEmail == null) {
            oldEmail = utilisateur.getEmail();
        }
        Connection con = null;
        Class.forName("org.hsqldb.jdbcDriver");
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("UPDATE USERS SET NOM = ?, PASSWORD = ?, IS_ADMIN = ?, EMAIL = ? WHERE EMAIL = ?");
            ps.setString(1,utilisateur.getNom());
            ps.setString(2,utilisateur.getPassword());
            ps.setBoolean(3,utilisateur.getAdmin());
            ps.setString(4,utilisateur.getEmail());
            ps.setString(5,oldEmail);
            logger.debug("Requête DataBase : " + ps);
            if (ps.executeUpdate() == 1) {
                logger.info("Utilisateur " + oldEmail + " correctement mis à jour dans la base.");
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
    public void deleteUtilisateur(Utilisateur utilisateur) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9003", "SA", "");
            PreparedStatement ps = con.prepareStatement("DELETE FROM USERS WHERE EMAIL = ?");
            ps.setString(1,utilisateur.getEmail());
            logger.debug("Requête DataBase : " + ps);
            if (ps.executeUpdate() == 1) {
                logger.info("Utilisateur " + utilisateur.getEmail() + " correctement supprimé.");
            } else {
                logger.error("Erreur pendant la suppression de l'utilisateur " + utilisateur.getEmail() + ".");
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

    @Override
    public Utilisateur createUtilisateur(ResultSet rs) throws SQLException {
        Utilisateur myUser = new Utilisateur();
        myUser.setEmail(rs.getString(1));
        myUser.setNom(rs.getString(2));
        myUser.setPassword(rs.getString(4));
        myUser.setAdmin(rs.getBoolean(5));
        return myUser;
    }
}