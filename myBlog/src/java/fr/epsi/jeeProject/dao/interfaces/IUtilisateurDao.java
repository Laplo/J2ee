package java.fr.epsi.jeeProject.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.fr.epsi.jeeProject.beans.Utilisateur;

public interface IUtilisateurDao {

	void countUtilisateurs() throws ClassNotFoundException;
	List<Utilisateur> getUtilisateurs() throws ClassNotFoundException;
	List<Utilisateur> getNonAdminUtilisateurs() throws ClassNotFoundException;
	Utilisateur getUtilisateur(String email) throws ClassNotFoundException;
	void createUtilisateur(Utilisateur utilisateur) throws ClassNotFoundException;
	void updateUtilisateur(Utilisateur utilisateur);
	void deleteUtilisateur(Utilisateur utilisateur);

	Utilisateur createUtilisateur(ResultSet rs) throws SQLException;
}
