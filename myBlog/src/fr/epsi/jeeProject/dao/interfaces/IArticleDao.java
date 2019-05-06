package fr.epsi.jeeProject.dao.interfaces;

import fr.epsi.jeeProject.beans.Blog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IArticleDao {

    void countArticles() throws ClassNotFoundException;

    List<Blog> getArticles() throws ClassNotFoundException;

    Blog getArticle(int id) throws SQLException, ClassNotFoundException;

    void createArticle(Blog blog) throws ClassNotFoundException;

    void deleteArticle(int id) throws ClassNotFoundException;

    Blog createArticle(ResultSet rs) throws SQLException, ClassNotFoundException;
}
