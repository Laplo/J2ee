package fr.epsi.jeeProject.jmx;

import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;

public class Post implements PostMBean {

    @Override
    public int nbPosts() throws ClassNotFoundException {
        return new ArticleDao().countArticles();
    }
}
