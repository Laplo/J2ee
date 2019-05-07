<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="fr.epsi.jeeProject.beans.Reponse" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<link href="blog.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,700' rel='stylesheet' type='text/css'>
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
