package fr.epsi.jeeProject.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.epsi.jeeProject.beans.Utilisateur;

public interface IUtilisateurDao {

	List<Utilisateur> getUtilisateurs() throws SQLException, ClassNotFoundException;

	Utilisateur getUtilisateur(String email) throws ClassNotFoundException, SQLException;
	void createUtilisateur(Utilisateur utilisateur) throws SQLException, ClassNotFoundException;
	void updateUtilisateur(Utilisateur utilisateur) throws SQLException;
	void deleteUtilisateur(Utilisateur utilisateur) throws SQLException;

	Utilisateur createUtilisateur(ResultSet rs) throws SQLException;
}
