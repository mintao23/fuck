<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.mintao.bean.BoardVO" %>
<%@ page import="org.example.mintao.dao.BoardDAO" %>

<%
    int seq = Integer.parseInt(request.getParameter("seq"));

    BoardDAO dao = new BoardDAO();
    BoardVO board = dao.getBoard(seq);
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Post</title>
</head>
<body>
<h1>View Post</h1>
<p><strong>Title:</strong> <%= board.getTitle() %></p>
<p><strong>Writer:</strong> <%= board.getWriter() %></p>
<p><strong>Content:</strong></p>
<p><%= board.getContent() %></p>
<p><strong>RegDate:</strong> <%= board.getRegDate() %></p>
<a href="list.jsp">Back to List</a>
</body>
</html>
