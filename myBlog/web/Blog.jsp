<%@ page import="fr.epsi.jeeProject.domain.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.IOException" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %><%--
  Created by IntelliJ IDEA.
  User: lhoul
  Date: 26/04/2019
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
  <title>EPSI Blog</title>
</head>
<body>
<table style="border: 1px solid;">
  <% List<Blog> blogs = (List<Blog>) request.getAttribute("articles");
    for (int i = 0; i < blogs.size(); i++) {
    Blog blog = blogs.get(i); %>
  <tr>
    <th><% out.print(blog.getTitre()); %></th>
    <td><% out.print(blog.getDescription()); %></td>
  </tr>
  <% } %>
</table>
</body>
</html>
