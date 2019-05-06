<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<link href="blog.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<% Blog blog = (Blog) request.getAttribute("article");
    String email = (String) session.getAttribute("user_email");
    System.out.println("email article jsp");
    System.out.println(email);
    System.out.println("email article createur");
    System.out.println(blog.getCreateur().getEmail());%>
<head>
    <title><% out.print(blog.getTitre());%></title>
</head>
<body>

<div class="wrapper">
    <div class="top"><div class="title"><h1><% out.print(blog.getTitre());%></h1></div></div>
    <div class="content">
        <div class="card first">
            <h2 class="astyle"><% out.print(blog.getTitre()); %>
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
</body>
</html>
