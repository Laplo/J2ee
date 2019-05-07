<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: ronan
  Date: 28/04/2019
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
                        <label for="nameId"></label><input type="text" name="name" id="nameId"/>
                        <br/>
                        <div><label>Email</label></div>
                        <label for="emailId"></label><input type="email" name="email" id="emailId" required autocomplete="off"/>
                        <br/>
                        <div><label>Mot de passe</label></div>
                        <label for="passwordId"></label><input type="password" name="password" id="passwordId" required autocomplete="off"/>
                        <br/>
                        <input type="hidden" name="isCreating" id="isCreating" value="true">
                        <input type="hidden" name="oldEmail" id="oldEmail">
                        <br/>
                        <button id="submitModal" type="submit" class="button button-block">Créer</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div>
        <a href="/myEpsi/Blog">Retour à la page d'accueil</a>
    </div>
    <div>
        <button onclick="createUser()" type="button" class="btn btn-primary" data-toggle="modal" data-target="#createUserModal">
            Créer un utilisateur
        </button>
    </div>
    <%
        List<Blog> articles = (List<Blog>) request.getAttribute("articles");
        List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");
        if (!utilisateurs.isEmpty()) {
            %><h2>Utilisateurs</h2><%
        }
        for (Utilisateur utilisateur : utilisateurs) {
            %> <div class="users">
                <%out.print(utilisateur.getNom()); %>
                <button type="button" class="btn btn-primary" onclick="onClickModify('<% out.print(utilisateur.getEmail());%>','<% out.print(utilisateur.getNom());%>')">
                    Modifier
                </button>
            <a href="Utilisateur?delete=<%=utilisateur.getEmail()%>">Supprimer</a>
            </div>
         <%  }
        if (!articles.isEmpty()) {
            %><h2>Articles</h2><%
        }
        for (Blog blog : articles) {
         %> <div class="articles">
                <% out.print(blog.getTitre()); %>
                <a href="Article?delete=<%=blog.getId()%>">Supprimer</a>
            </div>
    <%  } %>
</body>
</html>
<script>
    function onClickModify(email, name) {
        document.getElementById('emailId').value = email;
        document.getElementById('nameId').value = name;
        document.getElementById('isCreating').value = false;
        document.getElementById('oldEmail').value = email;
        document.getElementById('submitModal').innerText = 'Modifier';
        document.getElementById('exampleModalLabel').innerText = 'Modification de l\'utilisateur '+email;
        $('#createUserModal').modal('show');
    }
    function createUser() {
        document.getElementById('emailId').value = '';
        document.getElementById('nameId').value = '';
        document.getElementById('isCreating').value = true;
        document.getElementById('oldEmail').value = null;
        document.getElementById('submitModal').innerText = 'Créer';
        document.getElementById('exampleModalLabel').innerText = 'Créer un utilisateur';
    }
</script>
