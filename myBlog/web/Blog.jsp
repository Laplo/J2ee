<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<link href="blog.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,700' rel='stylesheet' type='text/css'>
<html>
<head>
  <title>EPSI Blog</title>
</head>
<body>

  <div class="wrapper">
    <div class="top"><div class="title"><h1>EPSI Blog</h1></div></div>
    <% List<Blog> blogs = (List<Blog>) request.getAttribute("articles");
      for (int i = 0; i < blogs.size(); i++) {
      Blog blog = blogs.get(i); %>
      <div class="content">
        <div class="card first">
          <h2><a href="#"><% out.print(blog.getTitre()); %></a></h2>
          <p class="date"><% out.print(blog.getDateModification()); %></p>
          <p class="text"><% out.print(blog.getDescription()); %></p>
        </div>
      </div>
    <% } %>
  </div>
</body>
</html>
