package fr.epsi.jeeProject.dao.interfaces;

import fr.epsi.jeeProject.beans.Blog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IArticleDao {
    List<Blog> getArticles() throws SQLException, ClassNotFoundException;

    Blog getArticle(int id) throws SQLException, ClassNotFoundException;

    void createArticle(Blog blog) throws ClassNotFoundException, SQLException;

    Blog createArticle(ResultSet rs) throws SQLException, ClassNotFoundException;
}
