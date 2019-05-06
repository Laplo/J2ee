package java.fr.epsi.jeeProject.dao.HSQLImpl;

import java.fr.epsi.jeeProject.beans.Blog;
import java.fr.epsi.jeeProject.beans.Reponse;
import java.fr.epsi.jeeProject.beans.Utilisateur;
import java.fr.epsi.jeeProject.dao.interfaces.IBlogDao;

import java.sql.SQLException;
import java.util.List;

public class BlogDao implements IBlogDao {
    @Override
    public Blog getBlog(Integer id) {
        return null;
    }

    @Override
    public List<Blog> getBlogs(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Integer createBlog(Blog blog) throws SQLException {
        return null;
    }

    @Override
    public void updateBlog(Blog blog) throws SQLException {

    }

    @Override
    public void deleteBlog(Blog blog) throws SQLException {

    }

    @Override
    public void addReponse(Blog blog, Reponse reponse) throws SQLException {

    }
}
