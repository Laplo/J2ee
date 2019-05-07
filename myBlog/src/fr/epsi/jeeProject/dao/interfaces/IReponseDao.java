package fr.epsi.jeeProject.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Utilisateur;

public interface IReponseDao {

	Integer countReponseByBlog(Blog blog) throws ClassNotFoundException;
	List<Reponse> getAllReponseByBlog(Blog blog) throws ClassNotFoundException;
	Reponse getReponse(Integer id) throws ClassNotFoundException;
	List<Reponse> getReponses(Utilisateur utilisateur) throws ClassNotFoundException;
	void createReponse(Reponse reponse) throws  ClassNotFoundException;
	Reponse createReponse(ResultSet resultSet) throws SQLException, ClassNotFoundException;
	
}
