package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestArticleDao {

    private ArticleDao articleDao;
    private Utilisateur user;

    @Before
    public void setUp() {
        articleDao = new ArticleDao();
        user = new Utilisateur("t@t.fr");
    }

    @Test
    public void testDeleteNonAuthorArticle() {
        try {
            assertEquals(0, articleDao.deleteArticle(0,user));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCorrectFeed() {
        Blog blogTest = new Blog();
        blogTest.setTitre("blogTest1");
        Blog blogTest2 = new Blog();
        blogTest2.setTitre("blogTest2");
        try {
            //Archivé
            blogTest = articleDao.getArticle(user.getEmail(),1);
            //Annulé
            blogTest2 = articleDao.getArticle(user.getEmail(),5);
            assertNull(blogTest);
            assertNull(blogTest2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
