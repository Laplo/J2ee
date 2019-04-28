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
    <title>Title</title>
</head>
<body>
    <%
        List<Blog> articles = (List<Blog>) request.getAttribute("articles");
        List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");
        for (int i = 0; i < utilisateurs.size(); i++) { %>
            <div>
                <%out.print(utilisateurs.get(i).getNom()); %>
                <button>Modifier</button>
                <button>Supprimer</button>
            </div>
    <%  }
        for (int i = 0; i < articles.size(); i++) { %>
            <div>
                <%out.print(articles.get(i).getTitre()); %>
                <button>Supprimer</button>
            </div>
    <%  } %>
</body>
</html>
