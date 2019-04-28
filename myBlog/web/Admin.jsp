<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %><%--
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
        List<Blog> articles = (List<Blog>) request.getAttribute("articles");
        List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");
        for (int i = 0; i < utilisateurs.size(); i++) {
            if (i == 0) { %>
                <h2>Utilisateurs</h2>
            <% } %>
            <div>
                <%out.print(utilisateurs.get(i).getNom()); %>
                <button>Modifier</button>
                <form action="Utilisateur?delete=<%utilisateurs.get(i).getEmail();%>">
                    <button>Supprimer</button>
                </form>
            </div>
    <%  }
        for (int i = 0; i < articles.size(); i++) {
            if (i == 0) { %>
                <h2>Articles</h2>
            <% } %>
            <div>
                <%out.print(articles.get(i).getTitre()); %>
                <a href="/myEpsi/Article?delete=<%=articles.get(i).getId()%>">Supprimer</a>
            </div>
    <%  } %>
</body>
</html>
