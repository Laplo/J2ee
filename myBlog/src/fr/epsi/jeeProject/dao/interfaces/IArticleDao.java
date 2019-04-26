package fr.epsi.jeeProject.dao.interfaces;

import fr.epsi.jeeProject.beans.Blog;

import java.sql.SQLException;
import java.util.List;

public interface IArticleDao {
    List<Blog> getArticles() throws SQLException, ClassNotFoundException;
}
