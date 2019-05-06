<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %>
<%@ page import="fr.epsi.jeeProject.dao.HSQLImpl.ArticleDao" %>
<%@ page import="fr.epsi.jeeProject.dao.HSQLImpl.UtilisateurDao" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: ronan
  Date: 28/04/2019
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <a href="/myEpsi/Blog">Retour à la page d'accueil</a>
    <%
        List<Blog> articles = null;
        List<Utilisateur> utilisateurs = null;
        try {
            articles = new ArticleDao().getArticles();
            utilisateurs = new UtilisateurDao().getUtilisateurs();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Objects.requireNonNull(utilisateurs).size(); i++) {
            if (i == 0) { %>
                <h2>Utilisateurs</h2>
            <% } %>
            <div class="users">
                <%out.print(utilisateurs.get(i).getNom()); %>
                <button>Modifier</button>
                <a href="Utilisateur?delete=<%=utilisateurs.get(i).getEmail()%>">Supprimer</a>
            </div>
    <%  }
        for (int i = 0; i < Objects.requireNonNull(articles).size(); i++) {
            if (i == 0) { %>
                <h2>Articles</h2>
            <% } %>
            <div class="articles">
                <% out.print(articles.get(i).getTitre()); %>
                <a href="Article?delete=<%=articles.get(i).getId()%>">Supprimer</a>
            </div>
    <%  } %>
</body>
</html>
