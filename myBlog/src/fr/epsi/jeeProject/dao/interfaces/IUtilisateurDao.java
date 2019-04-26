package fr.epsi.jeeProject.dao.interfaces;

import java.sql.SQLException;

import fr.epsi.jeeProject.beans.Utilisateur;

public interface IUtilisateurDao {

	Utilisateur getUtilisateur(String email) throws ClassNotFoundException, SQLException;
	void createUtilisateur(Utilisateur utilisateur) throws SQLException, ClassNotFoundException;
	void updateUtilisateur(Utilisateur utilisateur) throws SQLException;
	void deleteUtilisateur(Utilisateur utilisateur) throws SQLException;
	
}
