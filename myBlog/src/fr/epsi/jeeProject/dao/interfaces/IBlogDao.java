package fr.epsi.jeeProject.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Utilisateur;

public interface IBlogDao {

	Blog getBlog(Integer id) throws SQLException, ClassNotFoundException;
	List<Blog> getBlogs(Utilisateur utilisateur) throws SQLException, ClassNotFoundException;
	Integer createBlog(Blog blog) throws SQLException, ClassNotFoundException;
	void updateBlog(Blog blog) throws SQLException, ClassNotFoundException;
	void deleteBlog(Blog blog) throws SQLException, ClassNotFoundException;
	void addReponse(Blog blog, Reponse reponse) throws SQLException, ClassNotFoundException;
	
}
