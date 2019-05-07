<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="fr.epsi.jeeProject.beans.Reponse" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<link href="blog.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,700' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<% Blog blog = (Blog) request.getAttribute("article");
    String email = (String) session.getAttribute("user_email");%>
<head>
    <title><% out.print(blog.getTitre());%></title>
</head>
<body>
<div class="wrapper">
    <div class="top">
        <div><a href="/myEpsi/Blog">Retour à la page d'accueil</a></div>
        <div class="title"><h1><% out.print(blog.getTitre());%></h1></div>
    </div>
    <div class="content">
        <div class="card first">
            <h2 class="astyle"><% out.print(blog.getTitre()); %>
                <% if (email.compareTo(blog.getCreateur().getEmail()) != 0) { %>
                    <i class="glyphicon glyphicon-user" style="float: right"></i>
                <% } %>
            </h2>
            <p class="date">
                <%
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String date = formatter.format(blog.getDateModification());
                    out.print(date);
                    out.print(" - ");
                    out.print(blog.getCreateur().getNom());
                %>
            </p>
            <p class="text"><% out.print(blog.getDescription()); %></p>
            <% if (blog.getStatut().getId() == 1) { %>
            <a href="/myEpsi/Article?id=<%out.print(blog.getId());%>&publier=true">Publier</a>
            <a href="/myEpsi/Article?id=<%out.print(blog.getId());%>&annuler=true">Annuler</a>
            <% } else if(blog.getStatut() .getId()== 2) { %>
                <a href="/myEpsi/Article?id=<%out.print(blog.getId());%>&archiver=true">Archiver</a>
            <% }

            if (blog.getStatut().getId() != 4) { %>
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Créer un article</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float: right;">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="Blog" method="post">
                                    <div>
                                        <label>Titre</label><br/>
                                        <label><input type="text" name="titre" id="titreId"/></label>
                                    </div>
                                    <br/>
                                    <div>
                                        <label>Description</label><br/>
                                        <label><textarea rows="5" cols="50" name="description" id="descriptionId"></textarea></label>
                                    </div>
                                    <br/>
                                    <br/>
                                    <button type="submit" class="btn btn-primary">Ajouter</button>
                                    <button data-dismiss="modal" class="btn btn-secondary">Annuler</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn btn-primary"
                        onclick="OnClickModify('<%out.println(blog.getTitre());%>', '<%out.println(blog.getDescription());%>');">
                    Modifier
                </button>
            <%}%>
        </div>
    </div>
</div>
<div class="container">
    <%
        if (!session.getAttribute("user_email").equals(blog.getCreateur().getEmail())){%>
    <div class="row">
        <form action="Article" method="post">
            <label><textarea style="resize: none;" rows="2" cols="50" name="comment"></textarea></label>
            <input type="hidden" name="blogId" value="<% out.print(blog.getId());%>"/>
            <button class="btn btn-primary" type="submit">Commenter</button>
        </form>
    </div>
    <% } %>
    <div class="row">
        <div class="col-sm-12">
            <h3><% out.print(request.getAttribute("nbComments") + " commentaires"); %></h3>
        </div>
    </div>
    <%List<Reponse> reponseList = (List<Reponse>) request.getAttribute("reponseList");
    for (Reponse reponse : reponseList){
        %>
    <div class="row">
        <div class="col-sm-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong><% out.print(reponse.getBlogger().getNom()); %></strong> <span class="text-muted">commented
                    <% out.print(formatter.format(reponse.getPublication())); %></span>
                </div>
                <div class="panel-body">
                    <% out.print(reponse.getCommentaire()); %>
                </div><!-- /panel-body -->
            </div><!-- /panel panel-default -->
        </div><!-- /col-sm-5 -->
    </div>
    <% }
    %>
</div> <!-- container -->
</body>
</html>
<script>
    function OnClickModify(titre, description) {
        document.getElementById('titreId').value = titre;
        document.getElementById('descriptionId').value = description;
        $('#exampleModal').modal('show');
    }
</script>
