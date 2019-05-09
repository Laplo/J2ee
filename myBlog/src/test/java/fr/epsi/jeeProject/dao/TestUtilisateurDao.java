package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUtilisateurDao {

    private UtilisateurDao utilisateurDao;
    private Utilisateur user;

    @Before
    public void setUp() {
        utilisateurDao = new UtilisateurDao();
        user = new Utilisateur("contact@aquasys.fr");
        user.setAdmin(true);
        user.setNom("Test");
        user.setPassword("sa");
    }

    @Test
    public void testCreateAccountSameEmail() {
        try {
            assertEquals(0,utilisateurDao.createUtilisateur(user));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteAdminUser(){
        try {
            assertEquals(0,utilisateurDao.deleteUtilisateur(user));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
