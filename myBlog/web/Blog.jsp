<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<link href="blog.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,700' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<head>
  <title>EPSI Blog</title>
</head>
<body>

  <div class="wrapper">
    <div class="top">
      <div class="title"><h1>EPSI Blog</h1></div>
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" style="float: right;">
        Créer
      </button>
    </div>
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
              <div><label>Titre</label></div>
              <input type="text" name="titre"/>
              <br>
              <div><label>Description</label></div>
              <textarea rows = "5" cols = "50" name = "description"></textarea>
              <br>
              <br>
              <button type="submit" class="button button-block">Get Started</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <% List<Blog> blogs = (List<Blog>) request.getAttribute("articles");
      String email = (String) session.getAttribute("user_email");
      for (int i = 0; i < blogs.size(); i++) {
      Blog blog = blogs.get(i); %>
      <div class="content">
        <% if (i == 0) { %>
        <div class="card first">
        <% } else { %>
        <div class="card">
        <% } %>
          <h2 class="astyle">
            <a href="/myEpsi/Article?id=<%=blog.getId()%>"><% out.print(blog.getTitre()); %></a>
            <% if (email.compareTo(blog.getCreateur().getEmail()) == 0) { %>
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
    <% } %>
  </div>
</body>
</html>
