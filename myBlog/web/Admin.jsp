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
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,700' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <div class="modal fade" id="createUserModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Créer un utilisateur</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float: right;">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Utilisateur" method="post">
                        <div><label>Nom</label></div>
                        <input type="text" name="name" id="nameId"/>
                        <br>
                        <div><label>Email</label></div>
                        <input type="email" name="email" id="emailId" required autocomplete="off"/>
                        <br>
                        <div><label>Mot de passe</label></div>
                        <input type="password" name="password" id="passwordId" required autocomplete="off"/>
                        <br>
                        <br>
                        <button type="submit" class="button button-block">Créer</button>
                    </form>
                </div>
            </div>
        </div>
    </div><div>
        <a href="/myEpsi/Blog">Retour à la page d'accueil</a>
    </div>
    <div>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createUserModal">
            Créer un utilisateur
        </button>
    </div>
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
                <button type="button" class="btn btn-primary" data-id="<%utilisateurs.get(i);%>" data-toggle="modal" data-target="#createUserModal" id="modify-user">
                    Modifier
                </button>
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
<script>
    $("#modify-user").click(function () {
        var name = $(this).data('id');
        console.log(name);
    });
</script>
