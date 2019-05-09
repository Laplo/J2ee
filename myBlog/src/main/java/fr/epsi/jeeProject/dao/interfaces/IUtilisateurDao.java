package fr.epsi.jeeProject.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.epsi.jeeProject.beans.Utilisateur;

public interface IUtilisateurDao {

	void countUtilisateurs() throws ClassNotFoundException;
	List<Utilisateur> getUtilisateurs() throws ClassNotFoundException;
	List<Utilisateur> getNonAdminUtilisateurs() throws ClassNotFoundException;
	Utilisateur getUtilisateur(String email) throws ClassNotFoundException;
	void createUtilisateur(Utilisateur utilisateur) throws ClassNotFoundException;
	void updateUtilisateur(Utilisateur utilisateur, String oldEmail) throws ClassNotFoundException;
	boolean deleteUtilisateur(Utilisateur utilisateur) throws ClassNotFoundException;

	Utilisateur createUtilisateur(ResultSet rs) throws SQLException;
}
