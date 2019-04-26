<%@ page import="fr.epsi.jeeProject.domain.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.IOException" %><%--
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
<%
  List<Article> articleList = (List<Article>) request.getAttribute("articles");
  articleList.forEach((article) -> {
    try {
      out.println("<div>");
      out.println("<div class=\"title\">" + article.getTitle() + "</div>");
      out.println("<div class=\"body\">" + article.getBody() + "</div>");
      out.println("</div>");
    } catch (IOException e) {
      e.printStackTrace();
    }
  });
%>>
</body>
</html>
