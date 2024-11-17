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
    <title>Edit Post</title>
</head>
<body>
<h1>Edit Post</h1>
<form action="edit_ok.jsp" method="post">
    <input type="hidden" name="seq" value="<%= board.getSeq() %>">
    <label>Title:</label>
    <input type="text" name="title" value="<%= board.getTitle() %>"><br>
    <label>Writer:</label>
    <input type="text" name="writer" value="<%= board.getWriter() %>"><br>
    <label>Content:</label>
    <textarea name="content"><%= board.getContent() %></textarea><br>
    <button type="submit">Update</button>
</form>
</body>
</html>
