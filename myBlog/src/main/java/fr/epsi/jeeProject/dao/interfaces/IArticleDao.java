package fr.epsi.jeeProject.dao.interfaces;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IArticleDao {

    int countArticles() throws ClassNotFoundException;

    List<Blog> getArticles() throws ClassNotFoundException;

    Blog getArticle(String email, int id) throws ClassNotFoundException;

    void createArticle(Blog blog) throws ClassNotFoundException;

    int deleteArticle (int id, Utilisateur user) throws ClassNotFoundException;

    void updateArticle(Blog blog) throws ClassNotFoundException;

    Blog createArticle(ResultSet rs) throws SQLException, ClassNotFoundException;
}
